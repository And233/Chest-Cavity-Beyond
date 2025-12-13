package net.zhaiji.chestcavitybeyond.api.function;

import net.zhaiji.chestcavitybeyond.api.ChestCavitySlotContext;

/**
 * 用于添加器官移植触发器
 */
@FunctionalInterface
public interface OrganAddedConsumer {
    /**
     * @param context 胸腔槽位上下文
     */
    void accept(ChestCavitySlotContext context);
}
