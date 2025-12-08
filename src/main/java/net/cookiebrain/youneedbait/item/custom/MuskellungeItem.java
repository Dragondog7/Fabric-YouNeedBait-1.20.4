package net.cookiebrain.youneedbait.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MuskellungeItem extends AbstractFishItem {

    public MuskellungeItem(Settings settings) {
        super(settings, 4.0, 28.0, 2.3);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        // Your existing behavior
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.youneedbait.muskellunge.tooltip.shift"));
        } else {
            tooltip.add(Text.translatable("tooltip.youneedbait.muskellunge.tooltip"));
        }

        // Append weight line at the end (if feature is enabled)
        super.appendTooltip(stack, world, tooltip, context);
    }
}