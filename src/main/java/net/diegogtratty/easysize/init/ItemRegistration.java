package net.diegogtratty.easysize.init;

import net.diegogtratty.easysize.EasySize;
import net.diegogtratty.easysize.item.custom.WirelessSizeshifterItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistration {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EasySize.MODID);

    public static final RegistryObject<Item> SIZECORE = ITEMS.register("sizecore",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
            ));
    public static final RegistryObject<Item> MUFFIN = ITEMS.register("muffin",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 2), .5f)
                                    .build())
                            .rarity(Rarity.UNCOMMON)
                    ));
    public static final RegistryObject<Item> WIRELESS_SIZESHIFTER = ITEMS.register("wireless_sizeshifter",
            () -> new WirelessSizeshifterItem(new Item.Properties()));

    public static final RegistryObject<BlockItem> FAKE_FURNACE = ITEMS.register("fake_furnace",
            () -> new BlockItem(BlockRegistration.FAKE_FURNACE.get(), new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
            ));
    public static final RegistryObject<BlockItem> SIZESHIFTING_STATION = ITEMS.register("sizeshifting_station",
            () -> new BlockItem(BlockRegistration.SIZESHIFTING_STATION.get(), new Item.Properties()
                    .rarity(Rarity.RARE)
            ));

}
