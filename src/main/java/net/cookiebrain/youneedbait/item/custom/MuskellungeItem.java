package net.cookiebrain.youneedbait.item.custom;

import com.mojang.datafixers.kinds.IdF;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


import java.util.List;

public class MuskellungeItem extends Item {
    public MuskellungeItem(Settings settings) {
        super(settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
    if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.youneedbait.muskellunge.tooltip.shift"));
        } else {
            tooltip.add(Text.translatable("tooltip.youneedbait.muskellunge.tooltip"));

            super.appendTooltip(stack, world, tooltip, context);
        }
    }
}


