package net.diegogtratty.easysize.block.menu;

import net.diegogtratty.easysize.block.entity.SizeshiftingStationBlockEntity;
import net.diegogtratty.easysize.init.BlockRegistration;
import net.diegogtratty.easysize.init.MenuRegistration;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class SizeshiftingStationMenu extends AbstractContainerMenu {
    private final SizeshiftingStationBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;
    private final ContainerData data;

    public SizeshiftingStationMenu(int containerId, Inventory playerInv, FriendlyByteBuf additionalData) {
        this(containerId,
                playerInv,
                playerInv.player.level().getBlockEntity(additionalData.readBlockPos()),
                new SimpleContainerData(2));
    }

    public SizeshiftingStationMenu(int containerId, Inventory playerInv, BlockEntity blockEntity, ContainerData data) {
        super(MenuRegistration.SIZESHIFTING_STATION_MENU.get(), containerId);

        if (blockEntity instanceof SizeshiftingStationBlockEntity be) {
            this.blockEntity = be;
        } else {
            throw new IllegalStateException("Bruh (%s) why"
                    .formatted(blockEntity.getClass().getCanonicalName()));
        }

        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        this.data = data;

        createPlayerHotbar(playerInv);
        createPlayerInventory(playerInv);
        addDataSlots(data);

    }

    private void createPlayerInventory(Inventory playerInv) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInv,
                        9 + column + (row * 9),
                        8 + (column * 18),
                        84 + (row * 18)));
            }
        }
    }

    private void createPlayerHotbar(Inventory playerInv) {
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInv,
                    column,
                    8 + (column * 18),
                    142));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
        Slot fromSlot = getSlot(pIndex);
        ItemStack fromStack = fromSlot.getItem();

        if(fromStack.getCount() <= 0)
            fromSlot.set(ItemStack.EMPTY);

        if(!fromSlot.hasItem())
            return ItemStack.EMPTY;

        ItemStack copyFromStack = fromStack.copy();

        if(pIndex < 36) {
            // We are inside of the player's inventory
            if(!moveItemStackTo(fromStack, 36, 37, false))
                return ItemStack.EMPTY;
        } else if (pIndex < 37) {
            // We are inside of the block entity inventory
            if(!moveItemStackTo(fromStack, 0, 36, false))
                return ItemStack.EMPTY;
        } else {
            System.err.println("Invalid slot index: " + pIndex);
            return ItemStack.EMPTY;
        }

        fromSlot.setChanged();
        fromSlot.onTake(pPlayer, fromStack);

        return copyFromStack;
    }

    public int getEnergy() {
        return this.data.get(0);
    }

    public int getMaxEnergy() {
        return this.data.get(1);
    }

    public int getEnergyStoredScaled() {
        return (int) (((float) getEnergy() / (float) getMaxEnergy()) * 38);
    }

    public SizeshiftingStationBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(this.levelAccess, player, BlockRegistration.SIZESHIFTING_STATION.get());
    }
}

