/*package net.diegogtratty.easysize.screen;

import net.diegogtratty.easysize.block.ModBlocks;
import net.diegogtratty.easysize.block.entity.SizeshiftingStationBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.level.Level;

public class SizeshiftingStationMenu extends AbstractContainerMenu {
    public final SizeshiftingStationBlockEntity blockEntity;
    private final BlockPos pos;
    private int power;
    private final Player pPlayer;
    private final Level level;

    public SizeshiftingStationMenu(int windowId, Inventory inventory, FriendlyByteBuf extraData) {
        super(ModMenuTypes.SIZESHIFTING_STATION_MENU.get(), windowId);
        this.pos = pos;
        if (pPlayer.level().getBlockEntity(pos) instanceof SizeshiftingStationBlockEntity ssstation) {
            //addSlot(new SlotItemHandler(SizeshiftingStationMenu.getItems(), SLOT, 64, 24));

            addDataSlot(new DataSlot() {
                @Override
                public int get() {
                    return ssstation.getStoredPower() & 0xffff;
                }

                @Override
                public void set(int pValue) {
                    SizeshiftingStationMenu.this.power = (SizeshiftingStationMenu.this.power & 0xffff0000) | (pValue & 0xffff);
                }
            });
            addDataSlot(new DataSlot() {
                @Override
                public int get() {
                    return (ssstation.getStoredPower() >> 16) & 0xffff;
                }

                @Override
                public void set(int pValue) {
                    SizeshiftingStationMenu.this.power = (SizeshiftingStationMenu.this.power & 0xffff) | ((pValue & 0xffff) << 16);
                }
            });
        }
    }

    public int getPower() {
        return power;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.SIZESHIFTING_STATION.get());
    }
}*/
