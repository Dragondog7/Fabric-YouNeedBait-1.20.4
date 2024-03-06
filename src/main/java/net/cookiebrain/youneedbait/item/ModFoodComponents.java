package net.cookiebrain.youneedbait.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent RAWFISHFILET = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200), 0.9f).build();
    public static final FoodComponent SALTEDFISHFILET = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build();
    public static final FoodComponent DRIEDFISHFILET = new FoodComponent.Builder().hunger(4).saturationModifier(0.3f).build();

}
