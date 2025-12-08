package net.cookiebrain.youneedbait.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PumpkinSeedItem extends AbstractFishItem {
    public PumpkinSeedItem(Settings settings) {
        // Example range: 2 kg to 30 kg, with base exponent 4.0
        super(settings, 0.02, 0.6, 1.5);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
    if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.youneedbait.pumpkinseed.tooltip.shift"));
        } else {
            tooltip.add(Text.translatable("tooltip.youneedbait.pumpkinseed.tooltip"));

            super.appendTooltip(stack, world, tooltip, context);
        }
    }
}


