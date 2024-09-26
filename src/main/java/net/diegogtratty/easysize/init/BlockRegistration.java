package net.diegogtratty.easysize.init;

import net.diegogtratty.easysize.EasySize;
import net.diegogtratty.easysize.block.custom.SizeshiftingStationBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistration {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EasySize.MODID);

    public static final RegistryObject<Block> FAKE_FURNACE = BLOCKS.register("fake_furnace",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(5.0f, 17f)
                    .lightLevel(state -> 10)
                    .requiresCorrectToolForDrops()
            ));
    public static final RegistryObject<Block> SIZESHIFTING_STATION = BLOCKS.register("sizeshifting_station",
            () -> new SizeshiftingStationBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .mapColor(MapColor.COLOR_ORANGE)
            ));
}
