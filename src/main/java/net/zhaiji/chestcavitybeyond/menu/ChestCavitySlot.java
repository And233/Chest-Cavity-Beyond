package net.zhaiji.chestcavitybeyond.menu;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.zhaiji.chestcavitybeyond.attachment.ChestCavityData;
import net.zhaiji.chestcavitybeyond.util.ChestCavityUtil;

public class ChestCavitySlot extends SlotItemHandler {
    public ChestCavitySlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public void setByPlayer(ItemStack newStack, ItemStack oldStack) {
        // 更新器官
        if (
            // 有时候会测出来新旧都为空的情况，先给他排除了
                !(newStack.isEmpty() && oldStack.isEmpty())
                        && getItemHandler() instanceof ChestCavityData data
                        && !data.getOwner().level().isClientSide()
        ) {
            ChestCavityUtil.changeOrgan(data, data.getOwner(), index, oldStack, newStack);
        }
        super.setByPlayer(newStack, oldStack);
    }
}
