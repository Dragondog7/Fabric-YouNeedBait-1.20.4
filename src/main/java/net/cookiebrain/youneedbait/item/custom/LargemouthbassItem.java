package net.cookiebrain.youneedbait.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LargemouthbassItem extends Item {
    public LargemouthbassItem(Settings settings) {
        super(settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
    if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.youneedbait.largemouthbass.tooltip.shift"));
        } else {
            tooltip.add(Text.translatable("tooltip.youneedbait.largemouthbass.tooltip"));

            super.appendTooltip(stack, world, tooltip, context);
        }
    }
}


