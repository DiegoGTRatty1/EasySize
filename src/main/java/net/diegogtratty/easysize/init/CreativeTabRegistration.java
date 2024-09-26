package net.diegogtratty.easysize.init;

import net.diegogtratty.easysize.EasySize;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabRegistration {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EasySize.MODID);

    public static final RegistryObject<CreativeModeTab> EASYSIZE_TAB = CREATIVE_MODE_TABS.register("easysize_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.easysize_tab"))
                    .icon(ItemRegistration.SIZECORE.get()::getDefaultInstance)
                    .displayItems((parameters, output) -> {
                        output.accept(ItemRegistration.SIZECORE.get());
                        output.accept(ItemRegistration.MUFFIN.get());
                        output.accept(ItemRegistration.FAKE_FURNACE.get());
                        output.accept(ItemRegistration.WIRELESS_SIZESHIFTER.get());
                        output.accept(ItemRegistration.SIZESHIFTING_STATION.get());
                    })
                    .build());
}
