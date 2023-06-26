package com.xv.mixin.Item.Elytra;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * 修改鞘翅的耐久为无限耐久
 */

@Mixin(Item.class)
public class ItemMixin {
    @Shadow @Final private int maxDamage;

    /**
     * @author
     * @reason
     */
    @Overwrite
    public final int getMaxDamage(){
        if (this.equals(Items.ELYTRA)){
            return 0;
        }return this.maxDamage;
    }
}
