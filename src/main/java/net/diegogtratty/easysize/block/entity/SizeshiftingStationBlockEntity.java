package net.diegogtratty.easysize.block.entity;

import net.diegogtratty.easysize.block.entity.utility.TickableBlockEntity;
import net.diegogtratty.easysize.init.BlockEntityRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class SizeshiftingStationBlockEntity extends BlockEntity implements TickableBlockEntity {

    private int counter;

    public SizeshiftingStationBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistration.SIZESHIFTING_STATION_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide()) {
            return;
        }

        System.out.println("Hello!");
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        this.counter = nbt.getInt("Counter");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt("Counter", this.counter);
    }

    //custom. can try to make set, get, etc
    public int incrementCounter() {
        ++this.counter;
        setChanged();
        return this.counter;
    }
}
