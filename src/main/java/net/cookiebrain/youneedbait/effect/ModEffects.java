package net.cookiebrain.youneedbait.effect;

import net.cookiebrain.youneedbait.YouNeedBait;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final StatusEffect Electrified = registerStatusEffect("electrified",
            new ElectrifiedEffect(StatusEffectCategory.NEUTRAL, 36)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            "6a34903f-3654-41dd-a37a-f538efe318f5", 0.25, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));


    private static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(YouNeedBait.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        YouNeedBait.LOGGER.info("Registering Mod Effects for " + YouNeedBait.MOD_ID);
    }

}
