package net.diegogtratty.easysize.block.entity;

import net.diegogtratty.easysize.EasySize;
import net.diegogtratty.easysize.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EasySize.MODID);

    public static final RegistryObject<BlockEntityType<SizeshiftingStationBlockEntity>> SIZESHIFTING_STATION_BE =
            BLOCK_ENTITIES.register("sizeshifting_station_be", () ->
                    BlockEntityType.Builder.of(SizeshiftingStationBlockEntity::new,
                            ModBlocks.SIZESHIFTING_STATION.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
