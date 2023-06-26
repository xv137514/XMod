package com.xv.mixin.Item.Elytra;

import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ElytraItem.class)
public class ElytraItemMixin {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public static boolean isUsable(ItemStack stack){return true;}
    /**
     * @author
     * @reason
     */
    @Overwrite
    public boolean canRepair(ItemStack stack, ItemStack ingredient){return true;}
}
