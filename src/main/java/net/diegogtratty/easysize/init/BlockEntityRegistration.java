package net.diegogtratty.easysize.init;

import net.diegogtratty.easysize.EasySize;
import net.diegogtratty.easysize.block.entity.SizeshiftingStationBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistration {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EasySize.MODID);

    public static final RegistryObject<BlockEntityType<SizeshiftingStationBlockEntity>> SIZESHIFTING_STATION_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("sizeshifting_station_block_entity",
                    () ->  BlockEntityType.Builder.of(SizeshiftingStationBlockEntity::new,
                                    BlockRegistration.SIZESHIFTING_STATION.get())
                            .build(null)
            );
}
