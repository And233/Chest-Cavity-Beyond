package net.zhaiji.chestcavitybeyond.util;

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

import java.util.ArrayList;
import java.util.List;

public class TooltipUtil {
    /**
     * 为器官添加类似create的tooltips
     */
    public static void addOrganTooltip(ChestCavityData data, ItemStack stack, TooltipsKeyContext keyContext, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        Multimap<Holder<Attribute>, AttributeModifier> attributeModifiers = ChestCavityUtil.getAttributeModifiers(
                new ChestCavitySlotContext(data, data.getOwner(), ChestCavityUtil.getSlotId(0), 0, stack)
        );
        if (attributeModifiers == null || attributeModifiers.isEmpty()) return;
        List<Component> tooltips = new ArrayList<>();
        //暂时用着,之后再换
        attributeModifiers.forEach((attribute, modifier) -> {
            String valueString = switch (modifier.operation()) {
                case ADD_VALUE -> modifier.amount() + "点";
                case ADD_MULTIPLIED_BASE -> modifier.amount() * 100 + "%倍率";
                case ADD_MULTIPLIED_TOTAL -> modifier.amount() * 100 + "%最终倍率";
            };
            tooltips.add(Component.literal("提供" + valueString + Component.translatable(attribute.value().getDescriptionId()).getString()));
        });
        for (int i = 0; i < tooltips.size(); i++) {
            tooltipComponents.add(i + 1, tooltips.get(i));
        }
        if (keyContext.isKeyShiftDown()) {
            // TODO
        } else {
            // TODO
        }
    }
}
