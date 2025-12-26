package net.zhaiji.chestcavitybeyond.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;

public class OrganSkillUtil {
    /**
     * 将目标传送到周围随机位置
     * <p>
     * {@link EnderMan//#teleport}
     * </p>
     *
     * @param entity 传送目标
     * @return 传送是否成功
     */
    public static boolean randomTeleport(LivingEntity entity) {
        // TODO 尝试循环次数写入配置
        for (int i = 0; i < 16; i++) {
            if (TeleportUtil.randomTeleport(entity)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 让玩家沿着视线方向传送
     *
     * @param player 传送目标
     */
    public static void teleport(Player player) {
        TeleportUtil.teleport(player);
    }
}
