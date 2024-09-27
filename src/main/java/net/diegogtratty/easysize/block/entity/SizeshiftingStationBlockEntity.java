package net.diegogtratty.easysize.block.entity;

import net.diegogtratty.easysize.EasySize;
import net.diegogtratty.easysize.block.entity.utility.TickableBlockEntity;
import net.diegogtratty.easysize.block.menu.SizeshiftingStationMenu;
import net.diegogtratty.easysize.init.BlockEntityRegistration;
import net.diegogtratty.easysize.utility.CustomEnergyStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class SizeshiftingStationBlockEntity extends BlockEntity implements TickableBlockEntity, MenuProvider {
    private static final Component TITLE =
            Component.translatable("container." + EasySize.MODID + ".sizeshifting_station");

    private int tickCounter = 0;
    private int secondsExisted = 0;

    private final ContainerData containerData = new ContainerData() {
        @Override
        public int get(int i) {
            return switch (i) {
                case 0 -> SizeshiftingStationBlockEntity.this.energy.getEnergyStored();
                case 1 -> SizeshiftingStationBlockEntity.this.energy.getMaxEnergyStored();
                default -> throw new UnsupportedOperationException("Unexpected value: " + i);
            };
        }

        @Override
        public void set(int i, int i1) {
            switch (i) {
                case 0 -> SizeshiftingStationBlockEntity.this.energy.setEnergy(i1);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    private final CustomEnergyStorage energy = new CustomEnergyStorage(64000, 100, 0, 0);
    private final LazyOptional<CustomEnergyStorage> energyOptional = LazyOptional.of(() -> this.energy);

    public SizeshiftingStationBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistration.SIZESHIFTING_STATION_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide()) {
            return;
        }

        if (this.tickCounter++ % 20 == 0) {
            this.secondsExisted++;
            if (this.energy.getEnergyStored() < this.energy.getMaxEnergyStored()) {
                this.energy.addEnergy(1);
            }
            setChanged();

            //synced functions every tick here


            //sync block every frame
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);

        CompoundTag EasySizeData = nbt.getCompound(EasySize.MODID);
        this.secondsExisted = nbt.getInt("SecondsExisted");
        //this.tickCounter = nbt.getInt("TickCounter");

        if (EasySizeData.contains("Energy", Tag.TAG_INT)) {
            this.energy.deserializeNBT(EasySizeData.get("Energy"));
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);

        var EasySizeData = new CompoundTag();
        nbt.putInt("SecondsExisted", this.secondsExisted);
        EasySizeData.put("Energy", this.energy.serializeNBT());
        //nbt.putInt("TickCounter", this.tickCounter);

        nbt.put(EasySize.MODID, EasySizeData);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ENERGY) {
            return this.energyOptional.cast();
        } else {
            return super.getCapability(cap);
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player player) {
        return new SizeshiftingStationMenu(pContainerId, pPlayerInventory, this, this.containerData);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.energyOptional.invalidate();
    }

    //block update
    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    //chunk update
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public LazyOptional<CustomEnergyStorage> getEnergyOptional() {
        return this.energyOptional;
    }

    public CustomEnergyStorage getEnergy() {
        return this.energy;
    }

    private void sendUpdate() {
        setChanged();

        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    public int getCurrentPlayerScale() {
        Player player = Minecraft.getInstance().player;
        return ((int) player.getScale());
    }

    @Override
    public Component getDisplayName() {
        return TITLE;
    }
}