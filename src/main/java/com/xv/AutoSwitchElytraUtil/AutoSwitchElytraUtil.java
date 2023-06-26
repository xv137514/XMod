package com.xv.AutoSwitchElytraUtil;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * 在玩家尝试跳跃时自动切换鞘翅
 */

public class AutoSwitchElytraUtil {
    private static boolean isChange = false;
    private static ItemStack itemStack = ItemStack.EMPTY;

    private static boolean isToggle(PlayerEntity player){
        Item chestItem =  player.getInventory().armor.get(2).getItem();
        //      不处于创造模式                             玩家脚没着地
        return !player.getAbilities().creativeMode&&!player.isOnGround()&&
        //        玩家不在滑翔              玩家不在漂浮效果的影响下
                !player.isFallFlying()&&!player.hasStatusEffect(StatusEffects.LEVITATION)
        //          玩家没有装备鞘翅
                &&!(chestItem instanceof ElytraItem);
    }

    public AutoSwitchElytraUtil(){
        ClientTickEvents.END_CLIENT_TICK.register(
                AutoSwitchElytraUtil::toggle
        );

    }

    private static void toggle(MinecraftClient client){
        //  判断是否切换过
        if (isChange){
            //  判断玩家是否着地
            if (client.player.isOnGround()){
                //  获取服务器玩家实例（只在客户端修改物品是不起作用的）
                ServerPlayerEntity player = client.getServer().getPlayerManager().getPlayer(client.player.getEntityName());
                //  获取玩家胸甲插槽的物品
                ItemStack chestItemStack = player.getInventory().armor.get(2);
                //  将之前换下来的物品替换到胸甲
                player.equipStack(EquipmentSlot.CHEST,itemStack);
                //  将胸甲插槽的物品换到该物品的栏位
                player.getInventory().setStack(player.getInventory().getSlotWithStack(itemStack),chestItemStack);
                //  清除该物品的复制
                itemStack = ItemStack.EMPTY;
                isChange=false;
            }
        }else {
            //  判断玩家是否尝试跳跃
            while (client.options.jumpKey.wasPressed()){
                //  判断是否应该切换
                if (isToggle(client.player)){
                    //  获取服务器玩家实例（只在客户端修改物品是不起作用的）
                    ServerPlayerEntity player = client.getServer().getPlayerManager().getPlayer(client.player.getEntityName());
                    //  获取玩家胸甲插槽的物品
                    ItemStack chestItemStack = player.getInventory().armor.get(2);
                    //  遍历背包中的物品
                    for (int i = 0; i < player.getInventory().size(); i++) {
                        System.out.println(player.getInventory().getStack(i).getItem());
                        //  如果遍历到的物品是鞘翅类
                        if (player.getInventory().getStack(i).getItem()instanceof ElytraItem){
                            //  将该物品换到胸甲
                            player.equipStack(EquipmentSlot.CHEST,player.getInventory().getStack(i));
                            //  将胸甲插槽的物品换到该物品的栏位
                            player.getInventory().setStack(i,chestItemStack);
                            //  将该物品复制一份
                            itemStack = player.getInventory().getStack(i).copy();
                            //  记录换过
                            isChange=true;
                            break;
                        }
                    }
                }
            }
        }
    }
}
