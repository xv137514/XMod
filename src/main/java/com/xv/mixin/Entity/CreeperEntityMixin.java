package com.xv.mixin.Entity;

//  修改了苦力怕的爆炸方法，使其爆炸不可破坏方块

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * 苦力怕爆炸不再破坏方块
 */

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends Entity {
    public CreeperEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private void explode(){
        if (!this.getWorld().isClient) {
            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 6f, World.ExplosionSourceType.NONE);
            this.discard();
        }
    }
}
