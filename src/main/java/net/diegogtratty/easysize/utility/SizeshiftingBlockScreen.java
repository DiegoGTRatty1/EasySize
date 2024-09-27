package net.diegogtratty.easysize.utility;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.diegogtratty.easysize.EasySize;
import net.diegogtratty.easysize.block.entity.SizeshiftingStationBlockEntity;
import net.diegogtratty.easysize.block.menu.SizeshiftingStationMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class SizeshiftingBlockScreen extends AbstractContainerScreen<SizeshiftingStationMenu> {

    private final Minecraft mc;
    private float playerScale;

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
    private static final Component CUSTOM_TEXT_INPUT =
            Component.translatable("gui." + EasySize.MODID + ".sizeshifting_block_screen.textfield.custom");

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(EasySize.MODID, "textures/gui/sizeshifting_station_gui.png");

    private final int imageWidth, imageHeight;

    private SizeshiftingStationBlockEntity blockEntity;
    private int leftPos, topPos;

    private Button smallButton, normalButton, bigButton, customButton;
    private EditBox customSizeInput;

    public SizeshiftingBlockScreen(SizeshiftingStationMenu menu, Inventory inventory, Component titlee) {
        super(menu, inventory, titlee);

        this.mc = Minecraft.getInstance();

        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    /*@Override
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
        this.customSizeInput = new EditBox(this.font, this.leftPos + 74, this.topPos + 87, 32, 18, CUSTOM_TEXT_INPUT);
        addRenderableWidget(customSizeInput);
    }*/

    @Override
    protected void renderBg (GuiGraphics graphics, float v, int i, int i1) {
        renderBackground(graphics);
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        int energyScaled = this.menu.getEnergyStoredScaled();

        // energy
        graphics.fill(
                this.leftPos + 115,
                this.topPos + 20,
                this.leftPos + 131,
                this.topPos + 60,
                0xFF555555
        );
        graphics.fill(
                this.leftPos + 116,
                this.topPos + 21 + (38 - energyScaled),
                this.leftPos + 130,
                this.topPos + 59,
                0xFFCC2222
        );
    }

    @Override
    public void render (GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.render(graphics, mouseX, mouseY, partialTicks);
        renderTooltip(graphics, mouseX, mouseY);

        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        graphics.drawString(this.font, TITLE, this.leftPos + 8, this.topPos + 6, 0x404040, false);

        int energyStored = this.menu.getEnergy();
        int maxEnergy = this.menu.getMaxEnergy();

        Component textt = Component.literal("Energy: " + energyStored + " / " + maxEnergy);
        if (isHovering(115, 20, 16, 40, mouseX, mouseY)) {
            graphics.renderTooltip(this.font, textt, mouseX, mouseY);
        }

        graphics.drawString(this.font, "Current Scale: %d".formatted(this.blockEntity.getCurrentPlayerScale()), this.leftPos + 44, this.topPos + 112, 0x404040, false);
    }

    private void handleSmallButton(Button smallButton) {
        String pPlayer = minecraft.player.getName().getString();

        executeCommand("scale set 0.4 " + pPlayer);
        executeCommand("scale persist set true " + pPlayer);
    }

    private void handleNormalButton(Button normalButton) {
        String pPlayer = minecraft.player.getName().getString();

        executeCommand("scale set 1 " + pPlayer);
        executeCommand("scale persist set true " + pPlayer);
    }

    private void handleBigButton(Button bigButton) {
        String pPlayer = minecraft.player.getName().getString();

        executeCommand("scale set 1.33 " + pPlayer);
        executeCommand("scale persist set true " + pPlayer);
    }

    private void handleCustomButton(Button customButton) {
        String inputText = customSizeInput.getValue();
        String pPlayer = minecraft.player.getName().getString();
        String finalMessage = ("scale set " + inputText + " " + pPlayer);

        executeCommand(finalMessage);
        executeCommand("scale persist set true " + pPlayer);
    }
    //1.29 for carpet usage, 1.33 for 2-block crouching

    private void executeCommand(String command) {
        MinecraftServer server = mc.getSingleplayerServer();
        if (server != null) {
            CommandSourceStack source = server.createCommandSourceStack();

            CommandDispatcher<CommandSourceStack>
                    dispatcher = server.getCommands().getDispatcher();
            try {
                dispatcher.execute(command, source);
            } catch (CommandSyntaxException e) {
                mc.player.sendSystemMessage(Component.translatable("gui.easysize.sizeshifting_block_screen.wronginput"));
            }
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
