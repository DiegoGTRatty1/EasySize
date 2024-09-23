/*package net.diegogtratty.easysize.utility;

import net.diegogtratty.easysize.EasySize;
import net.diegogtratty.easysize.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.BiConsumer;

public class ModBlockStates extends BlockStateProvider {
    public static final ResourceLocation BOTTOM = new ResourceLocation(EasySize.MODID, "block/sizeshifting_station_gui");
    public static final ResourceLocation TOP = new ResourceLocation(EasySize.MODID, "block/sizeshifting_station_gui");
    public static final ResourceLocation SIDE = new ResourceLocation(EasySize.MODID, "block/sizeshifting_station_gui");

    public ModBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EasySize.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerSizeshifterStation();
    }

    private void registerSizeshifterStation() {
        BlockModelBuilder modelOn = models().cube(ModBlocks.SIZESHIFTING_STATION.getId().getPath()+"_on", SIDE, BOTTOM, ResourceLocation("block/sizeshifting_station_on")).texture("particle", SIDE);
        BlockModelBuilder modelOff = models().cube(ModBlocks.SIZESHIFTING_STATION.getId().getPath()+"_off", SIDE, BOTTOM, ResourceLocation("block/sizeshifting_station")).texture("particle", SIDE);
        directionBlock(ModBlocks.SIZESHIFTING_STATION.get(), (state, builder) -> {
            builder.modelFile(state.getValue(BlockStateProperties.POWERED) ? modelOn : modelOff);
        });
    }

    private VariantBlockStateBuilder directionBlock(Block block, BiConsumer<BlockState, ConfiguredModel.Builder<?>> model) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        builder.forAllStates(state -> {
            ConfiguredModel.Builder<?> bld = ConfiguredModel.builder();
            model.accept(state, bld);
            applyRotationBld(bld, state.getValue(BlockStateProperties.FACING));
            return bld.build();
        });
        return builder;
    }

    private void applyRotationBld(ConfiguredModel.Builder<?> builder, Direction direction) {
        switch (direction) {
            case DOWN -> builder.rotationX(90);
            case UP -> builder.rotationX(-90);
            case NORTH -> { }
            case SOUTH -> builder.rotationY(180);
            case WEST -> builder.rotationY(270);
            case EAST -> builder.rotationY(90);
        }
    }
}*/
