package com.xv.WaterWalkingHorse;

//	实现了马在水中的行走


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

/**
 * 玩家可以骑马在水中行走
 */

public class WaterWalkingHorse {
    private static Vec3d lastPos = null;

    public WaterWalkingHorse() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            PlayerEntity player = client.player;
            if (player == null) {
                return;
            }
            Entity entity = player.getVehicle();
            if (!(entity instanceof AbstractHorseEntity)) {
                return;
            }
            BlockPos entityPos = entity.getBlockPos();
            BlockState blockState = entity.getWorld().getBlockState(entityPos.up());
            BlockState blockState2 = entity.getWorld().getBlockState(entityPos.down());

            if (blockState.getBlock()==Blocks.WATER){
                entity.setNoGravity(true);//实体设置为没有重力
                entity.setVelocity(entity.getVelocity().x, entity.getVelocity().y, entity.getVelocity().z);
                lastPos = entity.getPos();
            }
            if (blockState2.getBlock()!=Blocks.WATER){
                entity.setNoGravity(false);
                if (lastPos != null) {
                    entity.teleport(lastPos.x, lastPos.y, lastPos.z);
                    lastPos = null;
                }
            }
        });
    }
}

