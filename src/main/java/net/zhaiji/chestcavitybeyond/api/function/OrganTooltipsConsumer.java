package net.zhaiji.chestcavitybeyond.api.function;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/**
 * 用于添加器官工具提示
 */
@FunctionalInterface
public interface OrganTooltipsConsumer {
    /**
     * @param stack             器官物品
     * @param context           工具提示上下文
     * @param tooltipComponents 工具提示组件列表
     * @param tooltipFlag       工具提示标识符
     */
    void accept(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag);
}
