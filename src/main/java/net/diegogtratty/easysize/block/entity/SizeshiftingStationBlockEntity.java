package net.diegogtratty.easysize.block.entity;

import net.diegogtratty.easysize.utility.AdaptedEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

public class SizeshiftingStationBlockEntity extends BlockEntity {

    public static final String ENERGY_TAG = "Energy";
    public static final int MAXTRANSFER = 0;
    public static final int MAXRECEIVE = 4;
    public static final int CAPACITY = 4096;


    private final AdaptedEnergyStorage energy = new AdaptedEnergyStorage(CAPACITY, MAXTRANSFER, MAXRECEIVE, 0);

    private final LazyOptional<AdaptedEnergyStorage> energyOptional = LazyOptional.of(() -> this.energy);

    public SizeshiftingStationBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SIZESHIFTING_STATION_BE.get(), pPos, pBlockState);

    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        energyOptional.invalidate();
    }

    public Component getDisplayName() {
        return Component.translatable("block.easysize.sizeshifting_station");
    }

    public void tick() {
        boolean powered = energy.getEnergyStored() > 0;
        if (powered != getBlockState().getValue(BlockStateProperties.POWERED)) {
            level.setBlockAndUpdate(worldPosition, getBlockState().setValue(BlockStateProperties.POWERED, powered));
        }

    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put(ENERGY_TAG, energy.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        energy.deserializeNBT(pTag.get(ENERGY_TAG));
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ENERGY) {
            return energyOptional.cast();
        }
        return super.getCapability(cap);
    }

    public LazyOptional<AdaptedEnergyStorage> getEnergyOptional() {
        return this.energyOptional;
    }

    public int getEnergy() {
        return this.energy.getEnergyStored();
    }

    private void sendUpdate() {
        setChanged();

        if(this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }
}
