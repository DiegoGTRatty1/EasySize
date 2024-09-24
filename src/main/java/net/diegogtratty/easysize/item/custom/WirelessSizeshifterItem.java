package net.diegogtratty.easysize.item.custom;

import net.diegogtratty.easysize.utility.ClientHooks;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class WirelessSizeshifterItem extends Item {

    public WirelessSizeshifterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (!pLevel.isClientSide) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openSizeshiftingItemScreen());
        }
        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
    }
    //open ui
    //integrate pehkui into ui
    //buttons to select pre-determined scales
    //text field to input custom
    //button to accept & close ui
}