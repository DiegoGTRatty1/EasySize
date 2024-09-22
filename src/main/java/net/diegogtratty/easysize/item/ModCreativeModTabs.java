package net.diegogtratty.easysize.item;

import net.diegogtratty.easysize.EasySize;
import net.diegogtratty.easysize.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EasySize.MODID);

    public static final RegistryObject<CreativeModeTab> EASYSIZE_TAB = CREATIVE_MODE_TABS.register("easysize_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SIZECORE.get()))
                    .title(Component.translatable("creativetab.easysize_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SIZECORE.get());
                        pOutput.accept(ModItems.WIRELESS_SIZESHIFTER.get());
                        pOutput.accept(ModBlocks.SIZESHIFTING_STATION.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
