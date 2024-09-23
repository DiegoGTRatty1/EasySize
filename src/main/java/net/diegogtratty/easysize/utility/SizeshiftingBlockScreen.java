package net.diegogtratty.easysize.utility;

import net.diegogtratty.easysize.EasySize;
import net.diegogtratty.easysize.block.entity.SizeshiftingStationBlockEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.awt.*;

public class SizeshiftingBlockScreen extends Screen {
    private static final Component TITLE =
            Component.translatable("gui." + EasySize.MODID + ".sizeshifting_station_gui");
    private static final Component SMALL_BUTTON =
            Component.translatable("gui." + EasySize.MODID + ".sizeshifting_block_screen.button.small");
    private static final Component NORMAL_BUTTON =
            Component.translatable("gui." + EasySize.MODID + ".sizeshifting_block_screen.button.normal");
    private static final Component BIG_BUTTON =
            Component.translatable("gui." + EasySize.MODID + ".sizeshifting_block_screen.button.big");
    private static final Component CUSTOM_BUTTON =
            Component.translatable("gui." + EasySize.MODID + ".sizeshifting_block_screen.button.custom");

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(EasySize.MODID, "textures/gui/sizeshifting_station_gui.png");

    private final BlockPos position;
    private final int imageWidth, imageHeight;

    private SizeshiftingStationBlockEntity blockEntity;
    private int leftPos, topPos;

    private Button smallButton, normalButton, bigButton, customButton;

    public SizeshiftingBlockScreen(BlockPos position) {
        super(TITLE);

        this.position = position;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        Level level = this.minecraft.level;
        if (level == null) {
            return;
        }

        BlockEntity be = level.getBlockEntity(this.position);
        if (be instanceof SizeshiftingStationBlockEntity blockEntity) {
            this.blockEntity = blockEntity;
        } else {
            //System.err.print("BlockEntity at %s is not of type ScreenBlockEntity\n", this.position);
            return;
        }
        this.smallButton = addRenderableWidget(
                Button.builder(SMALL_BUTTON, this::handleSmallButton)
                .bounds(this.leftPos + 8, this.topPos + 20, 60, 20)
                        .tooltip(Tooltip.create(SMALL_BUTTON))
                        .build());
        this.normalButton = addRenderableWidget(
                Button.builder(NORMAL_BUTTON, this::handleNormalButton)
                        .bounds(this.leftPos + 8, this.topPos + 42, 60, 20)
                        .tooltip(Tooltip.create(NORMAL_BUTTON))
                        .build());
        this.bigButton = addRenderableWidget(
                Button.builder(BIG_BUTTON, this::handleBigButton)
                        .bounds(this.leftPos + 8, this.topPos + 64, 60, 20)
                        .tooltip(Tooltip.create(BIG_BUTTON))
                        .build());
        this.customButton = addRenderableWidget(
                Button.builder(CUSTOM_BUTTON, this::handleCustomButton)
                        .bounds(this.leftPos + 8, this.topPos + 86, 60, 20)
                        .tooltip(Tooltip.create(CUSTOM_BUTTON))
                        .build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphics);
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        super.render(graphics, mouseX, mouseY, partialTicks);
        graphics.drawString(this.minecraft.font, TITLE, this.leftPos + 8, this.topPos + 6, 0x404040, false);

        graphics.drawString(this.font, "Current Scale: %d".formatted(this.blockEntity.getStoredPower()), this.leftPos + 44, this.topPos + 112, 0x404040, false);
        graphics.drawString(this.font, "Power Supply [||||||||||]", this.leftPos + 8, this.topPos + 152, 0x404040, false);
    }

    private void handleSmallButton(Button smallButton) {
        //small poehkui
    }
    private void handleNormalButton(Button normalButton) {
        //small poehkui
    }
    private void handleBigButton(Button bigButton) {
        //small poehkui
    }
    private void handleCustomButton(Button customButton) {
        //small poehkui
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
