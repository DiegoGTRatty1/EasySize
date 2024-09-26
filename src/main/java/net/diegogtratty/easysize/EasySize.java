package net.diegogtratty.easysize;

import net.diegogtratty.easysize.init.BlockRegistration;
import net.diegogtratty.easysize.init.BlockEntityRegistration;
import net.diegogtratty.easysize.init.CreativeTabRegistration;
import net.diegogtratty.easysize.init.ItemRegistration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EasySize.MODID)

public class EasySize {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "easysize";

    public EasySize() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemRegistration.ITEMS.register(modEventBus);
        BlockRegistration.BLOCKS.register(modEventBus);
        CreativeTabRegistration.CREATIVE_MODE_TABS.register(modEventBus);
        BlockEntityRegistration.BLOCK_ENTITIES.register(modEventBus);

    }
}
