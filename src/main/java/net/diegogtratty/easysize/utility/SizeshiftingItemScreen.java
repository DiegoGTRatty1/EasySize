package net.diegogtratty.easysize.utility;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.diegogtratty.easysize.EasySize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;


public class SizeshiftingItemScreen extends Screen {

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

    private int leftPos, topPos;

    private Button smallButton, normalButton, bigButton, customButton;
    private EditBox customSizeInput;

    public SizeshiftingItemScreen() {

        super(TITLE);

        this.mc = Minecraft.getInstance();

        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

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
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {

        Player player = mc.player;
        playerScale = player.getScale();
        playerScale = (int) playerScale;
        String currentScale = "Current Scale: " + playerScale;

        renderBackground(graphics);
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        super.render(graphics, mouseX, mouseY, partialTicks);
        graphics.drawString(this.font, TITLE, this.leftPos + 8, this.topPos + 6, 0x404040, false);

        graphics.drawString(this.font, currentScale, this.leftPos + 44, this.topPos + 112, 0x404040, false);
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
                mc.player.sendSystemMessage(Component.translatable("gui.easysize.sizeshifting_block_screen.button.big"));
            }
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
