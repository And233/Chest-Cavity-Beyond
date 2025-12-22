package net.zhaiji.chestcavitybeyond.client.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.Input;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.network.PacketDistributor;
import net.zhaiji.chestcavitybeyond.client.key.KeyMappings;
import net.zhaiji.chestcavitybeyond.client.screen.ChestCavityScreen;
import net.zhaiji.chestcavitybeyond.client.screen.OrganSkillScreen;
import net.zhaiji.chestcavitybeyond.network.server.packet.UseSkillPacket;
import net.zhaiji.chestcavitybeyond.register.InitMenuType;
import net.zhaiji.chestcavitybeyond.util.ChestCavityClientUtil;

public class ClientEventHandler {
    /**
     * @param event 注册MenuScreen事件
     */
    public static void handlerRegisterMenuScreensEvent(RegisterMenuScreensEvent event) {
        event.register(InitMenuType.CHEST_CAVITY.get(), ChestCavityScreen::new);
    }

    /**
     * @param event 按键注册事件
     */
    public static void handlerRegisterKeyMappingsEvent(RegisterKeyMappingsEvent event) {
        event.register(KeyMappings.OPEN_SKILL_GUI);
    }

    /**
     * 设置自定义按键的功能
     *
     * @param event 按键输入事件
     */
    public static void handlerInputEvent$Key(InputEvent.Key event) {
        if (event.getAction() != InputConstants.PRESS) return;
        int key = event.getKey();
        Minecraft minecraft = Minecraft.getInstance();
        if (key == KeyMappings.OPEN_SKILL_GUI.getKey().getValue()) {
            if (minecraft.screen instanceof OrganSkillScreen) {
                minecraft.screen.onClose();
            } else if (minecraft.screen == null) {
                minecraft.setScreen(new OrganSkillScreen());
            }
        }

        if (key == KeyMappings.USE_ORGAN_SKILL.getKey().getValue()) {
            if (OrganSkillScreen.selectedSlot != -1) {
                PacketDistributor.sendToServer(new UseSkillPacket(OrganSkillScreen.selectedSlot));
            }
        }
    }

    /**
     * 在打开OrganSkill界面时禁止渲染准星
     *
     * @param event 渲染界面图层前事件
     */
    public static void handlerRenderGuiLayerEvent$Pre(RenderGuiLayerEvent.Pre event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.screen instanceof OrganSkillScreen && event.getName().equals(VanillaGuiLayers.CROSSHAIR)) {
            event.setCanceled(true);
        }
    }

    /**
     * 为了在OrganSkill界面打开时也能够移动
     *
     * @param event 移动输入更新事件
     */
    public static void handlerMovementInputUpdateEvent(MovementInputUpdateEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.screen instanceof OrganSkillScreen) {
            Options options = minecraft.options;
            Input input = event.getInput();
            // 按键检测
            input.up = ChestCavityClientUtil.isKeyDown(options.keyUp.getKey().getValue());
            input.down = ChestCavityClientUtil.isKeyDown(options.keyDown.getKey().getValue());
            input.left = ChestCavityClientUtil.isKeyDown(options.keyLeft.getKey().getValue());
            input.right = ChestCavityClientUtil.isKeyDown(options.keyRight.getKey().getValue());
            // 移动计算
            input.forwardImpulse = input.up == input.down ? 0.0F : (input.up ? 1.0F : -1.0F);
            input.leftImpulse = input.left == input.right ? 0.0F : (input.left ? 1.0F : -1.0F);
            input.jumping = ChestCavityClientUtil.isKeyDown(options.keyJump.getKey().getValue());
            input.shiftKeyDown = ChestCavityClientUtil.isKeyDown(options.keyShift.getKey().getValue());
            if (minecraft.player.isMovingSlowly()) {
                input.leftImpulse *= 0.3F;
                input.forwardImpulse *= 0.3F;
            }
        }
    }
}
