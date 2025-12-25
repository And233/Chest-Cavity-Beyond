package net.zhaiji.chestcavitybeyond.client.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class ChestCavityClientUtil {
    /**
     * 检测按键是否按下
     * <p>
     * 不走Minecraft默认的按键检测
     * </P>
     *
     * @param key GLFW
     * @return 是否按下
     */
    public static boolean isKeyDown(int key) {
        return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), key);
    }

    public static boolean isKeyDown(InputConstants.Key key) {
        return isKeyDown(key.getValue());
    }

    public static boolean isKeyDown(KeyMapping keyMapping) {
        return isKeyDown(keyMapping.getKey().getValue());
    }
}
