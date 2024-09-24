package net.diegogtratty.easysize.item;

import net.diegogtratty.easysize.EasySize;
import net.diegogtratty.easysize.item.custom.WirelessSizeshifterItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EasySize.MODID);

    public static final RegistryObject<Item> SIZECORE = ITEMS.register("sizecore",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WIRELESS_SIZESHIFTER = ITEMS.register("wireless_sizeshifter",
            () -> new WirelessSizeshifterItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
