package com.xv.KeyF5andF6;


import com.xv.XMod;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.glfw.GLFW;

/**
 * 新增了两个单独的按键绑定来切换第三人称和第二人称
 */

public class KeyF5andF6 {
//    注册F6按键绑定
    public static final KeyBinding TOGGLE_VIEW2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "切换到第二人称",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F6,  // 注意：这里是注册 F6 键的绑定
            XMod.MODID
    ));
//    注册F7按键绑定
    public static final KeyBinding TOGGLE_VIEW3 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "切换到第三人称",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F7,  // 注意：这里是注册 F7 键的绑定
            XMod.MODID
    ));

//    注册两个按键的事件
    public KeyF5andF6() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE_VIEW2.wasPressed()) {
                // 处理按下 F6 键的事件
                // ...
                if (client.player != null) {
                    PlayerEntity player = client.player;
                    if (player.isSpectator()) {
                        // 如果当前是旁观者则不处理
                        return;
                    }
                    if (client.options.getPerspective().isFrontView()){
                        client.options.setPerspective(Perspective.FIRST_PERSON);
                    }else {
                        client.options.setPerspective(Perspective.THIRD_PERSON_FRONT);
                    }
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE_VIEW3.wasPressed()) {
                // 处理按下 F7 键的事件
                // ...
                if (client.player != null) {
                    PlayerEntity player = client.player;
                    if (player.isSpectator()) {
                        // 如果当前是旁观者则不处理
                        return;
                    }
                    if (!client.options.getPerspective().isFirstPerson()&&!client.options.getPerspective().isFrontView()){
                        client.options.setPerspective(Perspective.FIRST_PERSON);
                    }else {
                        client.options.setPerspective(Perspective.THIRD_PERSON_BACK);
                    }
                }
            }
        });
    }
}
