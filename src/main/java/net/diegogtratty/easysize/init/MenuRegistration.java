package net.diegogtratty.easysize.init;

import net.diegogtratty.easysize.EasySize;
import net.diegogtratty.easysize.block.menu.SizeshiftingStationMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuRegistration {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, EasySize.MODID);

    public static final RegistryObject<MenuType<SizeshiftingStationMenu>> SIZESHIFTING_STATION_MENU = MENU_TYPES.register("sizeshifting_station_menu",
            () -> IForgeMenuType.create(SizeshiftingStationMenu::new));

}
