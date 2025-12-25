package net.zhaiji.chestcavitybeyond.api.capability;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.zhaiji.chestcavitybeyond.api.ChestCavitySlotContext;
import net.zhaiji.chestcavitybeyond.api.TooltipsKeyContext;
import net.zhaiji.chestcavitybeyond.attachment.ChestCavityData;

import java.util.List;

/**
 * 器官
 */
public interface IOrgan {
    /**
     * 获取器官提供的属性
     *
     * @param context 胸腔槽位上下文
     * @return 器官提供的属性
     */
    default Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ChestCavitySlotContext context) {
        return HashMultimap.create();
    }

    /**
     * 器官物品提示
     *
     * @param data              胸腔数据
     * @param stack             器官物品
     * @param keyContext        工具提示按键上下文
     * @param context           工具提示上下文
     * @param tooltipComponents 工具提示组件列表
     * @param tooltipFlag       工具提示标识符
     */
    default void organTooltip(ChestCavityData data, ItemStack stack, TooltipsKeyContext keyContext, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
    }

    /**
     * 器官tick
     *
     * @param context 胸腔槽位上下文
     */
    default void tick(ChestCavitySlotContext context) {
    }

    /**
     * 器官被移植进胸腔时调用
     *
     * @param context 胸腔槽位上下文
     */
    default void organAdded(ChestCavitySlotContext context) {
    }

    /**
     * 器官从胸腔中摘除时调用
     *
     * @param context 胸腔槽位上下文
     */
    default void organRemoved(ChestCavitySlotContext context) {
    }

    /**
     * 器官是否有技能
     *
     * @return 是否有技能
     */
    default boolean hasSkill() {
        return false;
    }

    /**
     * 器官技能
     *
     * @param context 胸腔槽位上下文
     */
    default void organSkill(ChestCavitySlotContext context) {
    }
}
