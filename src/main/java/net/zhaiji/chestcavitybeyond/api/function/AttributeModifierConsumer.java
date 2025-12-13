package net.zhaiji.chestcavitybeyond.api.function;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

/**
 * 用于添加器官属性修饰符
 */
@FunctionalInterface
public interface AttributeModifierConsumer {
    /**
     * @param modifiers 修饰符映射
     */
    void accept(Multimap<Holder<Attribute>, AttributeModifier> modifiers);
}
