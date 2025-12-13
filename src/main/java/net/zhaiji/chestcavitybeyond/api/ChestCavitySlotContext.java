package net.zhaiji.chestcavitybeyond.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.zhaiji.chestcavitybeyond.attachment.ChestCavityData;

/**
 * 胸腔槽位上下文
 *
 * @param data  胸腔数据
 * @param id    槽位id
 * @param index 位置索引
 * @param stack 对应物品
 */
public record ChestCavitySlotContext(ChestCavityData data, ResourceLocation id, int index, ItemStack stack) {
}
