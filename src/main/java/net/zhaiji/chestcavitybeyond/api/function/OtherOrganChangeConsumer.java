package net.zhaiji.chestcavitybeyond.api.function;

import net.minecraft.world.item.ItemStack;
import net.zhaiji.chestcavitybeyond.api.ChestCavitySlotContext;

/**
 * 其他器官变化回调
 */
@FunctionalInterface
public interface OtherOrganChangeConsumer {
    /**
     * @param context       当前器官（接收回调的器官）的上下文
     * @param changedIndex  变化的器官槽位索引
     * @param changedStack  变化的器官物品栈
     * @param isAdded       true=添加，false=移除
     */
    void accept(ChestCavitySlotContext context, int changedIndex, ItemStack changedStack, boolean isAdded);
}
