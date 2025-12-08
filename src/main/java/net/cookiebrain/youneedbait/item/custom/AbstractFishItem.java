package net.cookiebrain.youneedbait.item.custom;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.cookiebrain.youneedbait.config.ConfigManager;
import net.cookiebrain.youneedbait.config.ModConfig;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractFishItem extends Item {

    public static final String WEIGHT_KG_KEY = "WeightKg";

    private final double minKg;
    private final double maxKg;
    private final double baseBiasExponent;
    private static final Random RANDOM = Random.create();

    protected AbstractFishItem(Settings settings, double minKg, double maxKg, double baseBiasExponent) {
        super(settings);
        if (minKg <= 0 || maxKg <= 0 || maxKg <= minKg) {
            throw new IllegalArgumentException("Invalid fish weight bounds: " + minKg + " - " + maxKg);
        }
        this.minKg = minKg;
        this.maxKg = maxKg;
        this.baseBiasExponent = baseBiasExponent;
    }

    /**
     * Ensure this stack has a weight if weighted fish are enabled.
     * If disabled in config, this is a no-op (no NBT written).
     */
    public void ensureWeight(ItemStack stack) {
        if (stack.isEmpty()) return;

        ModConfig.Features features = ConfigManager.get().features;
        if (!features.weightedFishEnabled) {
            // Feature is off: make sure we don't leave stale weight around
            NbtCompound nbt = stack.getNbt();
            if (nbt != null && nbt.contains(WEIGHT_KG_KEY, NbtElement.DOUBLE_TYPE)) {
                nbt.remove(WEIGHT_KG_KEY);
            }
            return;
        }

        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains(WEIGHT_KG_KEY, NbtElement.DOUBLE_TYPE)) {
            double weightKg = generateRandomWeightKg(features);
            nbt.putDouble(WEIGHT_KG_KEY, weightKg);

            // ADD LOGGING HERE:
            YouNeedBait.LOGGER.info("Generated weight for {}: {} kg (min={}, max={}, bias={})",
                    this.getClass().getSimpleName(),
                    String.format("%.2f", weightKg),
                    minKg,
                    maxKg,
                    baseBiasExponent * features.fishWeightBias
            );
        }
    }

    /**
     * Get the weight in kg. If none, generate one (respecting config).
     * If the feature is disabled, returns 0 and does not write NBT.
     */
    public double getWeightKg(ItemStack stack) {
        ModConfig.Features features = ConfigManager.get().features;
        if (!features.weightedFishEnabled) {
            return 0.0;
        }
        ensureWeight(stack);
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(WEIGHT_KG_KEY, NbtElement.DOUBLE_TYPE)) {
            return 0.0;
        }
        return nbt.getDouble(WEIGHT_KG_KEY);
    }

    /**
     * Generates a random fish weight in kilograms using a center-biased distribution
     * that is slightly skewed toward the lower end of the range (fewer tiny/max fish,
     * average a bit below the exact mid-point).
     */
    protected double generateRandomWeightKg(ModConfig.Features features) {
        double globalBias = Math.max(0.1, features.fishWeightBias); // clamp to avoid 0 or negative
        double exponent = Math.max(1.0, baseBiasExponent * globalBias);

        // Step 1: base center‑biased value in [0,1], peaked around 0.5
        double r1 = RANDOM.nextDouble();
        double r2 = RANDOM.nextDouble();
        double centerBiased = (r1 + r2) / 2.0; // bell-ish around 0.5

        // Step 2: apply exponent to control how peaked the middle is
        double biased;
        if (centerBiased < 0.5) {
            // Left side: bend toward center
            double t = centerBiased / 0.5; // [0,1]
            double shaped = Math.pow(t, 1.0 / exponent);
            biased = shaped * 0.5;         // back to [0,0.5]
        } else {
            // Right side: bend toward center
            double t = (1.0 - centerBiased) / 0.5; // [0,1]
            double shaped = Math.pow(t, 1.0 / exponent);
            biased = 1.0 - shaped * 0.5;           // back to [0.5,1]
        }

        // Step 3: skew slightly toward the low end so average weight is lower.
        // skewStrength > 0 pushes more mass below 0.5 without reintroducing many extremes.
        // 0.0 = no skew, 0.2–0.3 = gentle skew left.
        double skewStrength = 0.5; // tune this constant after playtesting
        biased = biased * (1.0 - skewStrength);

        // Map to [minKg, maxKg]
        return minKg + biased * (maxKg - minKg);
    }

    /**
     * Helper for subclasses to append the "Weight: X" line after their own text.
     */
    protected void appendWeightTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        ModConfig.Features features = ConfigManager.get().features;
        if (!features.weightedFishEnabled) return;

        double weightKg = getWeightKg(stack);
        if (weightKg <= 0.0) return;

        String unit = features.weightUnit == null ? "kg" : features.weightUnit.trim().toLowerCase();
        String formatted;
        if ("lb".equals(unit) || "lbs".equals(unit) || "pound".equals(unit) || "pounds".equals(unit)) {
            double weightLb = weightKg * 2.2046226218;
            formatted = String.format("%.2f lb", weightLb);
        } else {
            // default to kg for anything else
            formatted = String.format("%.2f kg", weightKg);
        }

        tooltip.add(
                Text.translatable("tooltip.youneedbait.weight",
                        formatted
                ).formatted(Formatting.AQUA)
        );
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        // Default implementation only handles weight line.
        // Subclasses should call super.appendTooltip(...) at the END of their override
        // to keep the weight line last.
        appendWeightTooltip(stack, world, tooltip, context);
    }
    public double getMinKg() {
        return this.minKg;
    }

    public double getMaxKg() {
        return this.maxKg;
    }
}