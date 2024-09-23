package net.diegogtratty.easysize.utility;

import net.minecraftforge.energy.EnergyStorage;

public class AdaptedEnergyStorage extends EnergyStorage {

    public AdaptedEnergyStorage(int capacity) {
        super(capacity);
    }
    public AdaptedEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }
    public AdaptedEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }
    public AdaptedEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    public void setEnergy(int energy) {
        if (energy < 0) {
            energy = 0;
        }
        if (energy > capacity) {
            energy = capacity;
        }
        this.energy = energy;
    }
    public void addEnergy(int energy) {
        this.energy += energy;
    }
    public void removeEnergy(int energy) {
        this.energy -= energy;
    }

    /* @Override
    public int getEnergyStored() {
        return energy;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return energy.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return energy.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return energy.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return energy.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return energy.canExtract();
    }

    @Override
    public boolean canReceive() {
        return energy.canReceive();
    }*/
}
