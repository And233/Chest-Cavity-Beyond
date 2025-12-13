package net.zhaiji.chestcavitybeyond.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.zhaiji.chestcavitybeyond.api.function.OrganTooltipsConsumer;

import java.util.List;

public class OrganItem extends Item {
    private final OrganTooltipsConsumer organTooltipsConsume;

    public OrganItem(Properties properties, OrganTooltipsConsumer organTooltipsConsume) {
        super(properties);
        this.organTooltipsConsume = organTooltipsConsume;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        organTooltipsConsume.accept(stack, context, tooltipComponents, tooltipFlag);
    }
}
