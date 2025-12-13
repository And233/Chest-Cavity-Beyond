package net.zhaiji.chestcavitybeyond.api.function;

import net.minecraft.world.item.Item;

/**
 * 用于修改物品属性
 */
@FunctionalInterface
public interface ItemPropertiesConsumer {
    /**
     * @param properties 物品属性
     */
    void accept(Item.Properties properties);
}
