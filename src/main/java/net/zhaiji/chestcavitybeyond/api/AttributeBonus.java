package net.zhaiji.chestcavitybeyond.api;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

/**
 * 器官的额外属性加成
 *
 * @param attribute  属性
 * @param bonusValue 数值
 * @param operation  计算方式
 */
public record AttributeBonus(
        Holder<Attribute> attribute,
        double bonusValue,
        AttributeModifier.Operation operation
) {
    /**
     * 创建当前加成的属性修饰符
     *
     * @param slotId 槽位id
     * @return 属性修饰符
     */
    public AttributeModifier create(ResourceLocation slotId) {
        return new AttributeModifier(slotId.withSuffix("_bonus"), bonusValue, operation);
    }
}
