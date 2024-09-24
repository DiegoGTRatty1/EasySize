package net.diegogtratty.easysize.utility;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public class ClientHooks {
    public static void openSizeshiftingBlockScreen(BlockPos pos) {
        Minecraft.getInstance().setScreen(new SizeshiftingBlockScreen(pos));
    }
    public static void openSizeshiftingItemScreen() {
        Minecraft.getInstance().setScreen(new SizeshiftingItemScreen());
    }
}
