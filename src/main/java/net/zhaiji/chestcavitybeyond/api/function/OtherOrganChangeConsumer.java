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
     * @param oldStack      旧器官物品栈（可能为空）
     * @param newStack      新器官物品栈（可能为空）
     */
    void accept(ChestCavitySlotContext context, int changedIndex, ItemStack oldStack, ItemStack newStack);
}
