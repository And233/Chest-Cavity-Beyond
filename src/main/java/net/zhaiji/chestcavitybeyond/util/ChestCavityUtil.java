package net.zhaiji.chestcavitybeyond.util;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.zhaiji.chestcavitybeyond.ChestCavityBeyond;
import net.zhaiji.chestcavitybeyond.api.capability.IOrgan;
import net.zhaiji.chestcavitybeyond.api.capability.OrganFactory;
import net.zhaiji.chestcavitybeyond.attachment.ChestCavityData;
import net.zhaiji.chestcavitybeyond.manager.CapabilityManager;
import net.zhaiji.chestcavitybeyond.menu.ChestCavityMenu;
import net.zhaiji.chestcavitybeyond.register.InitAttachmentType;

import java.util.Objects;

public class ChestCavityUtil {
    /**
     * 获取槽位id
     *
     * <p>
     * TODO 目前没有使用，且修饰符用了一种很绕远路的方法进行统一，后续考虑重构或者删除这个方法
     * </P>
     *
     * @param index 槽位索引
     * @return 槽位id
     */
    public static ResourceLocation getSlotId(int index) {
        return ChestCavityBeyond.of("organ_" + index);
    }

    /**
     * 获取目标的胸腔数据
     *
     * @param entity 目标
     * @return 目标的胸腔数据
     */
    public static ChestCavityData getData(LivingEntity entity) {
        return entity.getData(InitAttachmentType.CHEST_CAVITY);
    }

    /**
     * 获取物品的器官capability
     *
     * @param stack 物品
     * @return 器官capability
     */
    public static IOrgan getOrganCap(ItemStack stack) {
        return Objects.requireNonNullElse(stack.getCapability(CapabilityManager.ORGAN), OrganFactory.EMPTY_ORGAN);
    }

    /**
     * 获取器官提供的属性
     *
     * @param stack 器官
     * @return 器官提供的属性
     */
    public static Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        return getOrganCap(stack).getAttributeModifiers();
    }

    /**
     * 打开目标的胸腔
     *
     * @param player 打开胸腔的玩家
     * @param entity 被打开的目标
     */
    public static void openChestCavity(Player player, LivingEntity entity) {
        ChestCavityData data = getData(entity);
        player.openMenu(
                new SimpleMenuProvider(
                        (containerId, playerInventory, player1) ->
                                new ChestCavityMenu(containerId, playerInventory, data),
                        entity.getName()
                ),
                (extraData) -> extraData.writeInt(data.getSlots())
        );
    }

    /**
     * 打开自身的胸腔
     *
     * @param player 自身
     */
    public static void openChestCavity(Player player) {
        openChestCavity(player, player);
    }
}
