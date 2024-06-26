package net.cookiebrain.youneedbait.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.cookiebrain.youneedbait.YouNeedBait;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FishCleaningStationScreen extends HandledScreen<FishCleaningStationScreenHandler> {
    public static final Identifier FISHCLEANINGSTATION_GUI =
            new Identifier(YouNeedBait.MOD_ID,"textures/gui/fishcleaningstation_gui.png");
    public FishCleaningStationScreen(FishCleaningStationScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, FISHCLEANINGSTATION_GUI);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(FISHCLEANINGSTATION_GUI, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(FISHCLEANINGSTATION_GUI, x + 100, y + 36, 176, 0, 8, handler.getScaledProgress());
        }
    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
