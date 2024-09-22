package net.diegogtratty.easysize.screen;

import net.diegogtratty.easysize.block.ModBlocks;
import net.diegogtratty.easysize.block.entity.SizeshiftingStationBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;

public class SizeshiftingStationMenu extends AbstractContainerMenu {
    public final SizeshiftingStationBlockEntity blockEntity;
    private final BlockPos pos;
    private final Level level;
    private final ContainerData data;

    public SizeshiftingStationMenu(int windowId, Player pPlayer, BlockPos pos, ContainerData data) {
        super(ModMenuTypes.SIZESHIFTING_STATION_MENU.get(), windowId);
        this.pos = pos;

    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.SIZESHIFTING_STATION.get());
    }
}
