package net.diegogtratty.easysize.block.entity;

import net.diegogtratty.easysize.EasySize;
import net.diegogtratty.easysize.block.entity.utility.TickableBlockEntity;
import net.diegogtratty.easysize.init.BlockEntityRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SizeshiftingStationBlockEntity extends BlockEntity implements TickableBlockEntity {

    private int tickCounter = 0;
    private int secondsExisted = 0;

    public SizeshiftingStationBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistration.SIZESHIFTING_STATION_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide()) {
            return;
        }

        if (this.tickCounter++ % 20 == 0) {
            this.tickCounter++;
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
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);

        var EasySizeData = new CompoundTag();
        nbt.putInt("SecondsExisted", this.secondsExisted);
        //nbt.putInt("TickCounter", this.tickCounter);
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

    //custom. can try to make set, get, etc
    public int incrementCounter() {
        ++this.secondsExisted;
        setChanged();
        return this.secondsExisted;
    }
}
