package net.zhaiji.chestcavitybeyond.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.zhaiji.chestcavitybeyond.util.ChestCavityUtil;

public class ChestOpenerItem extends Item {
    public ChestOpenerItem() {
        super(new Properties().stacksTo(1));
    }

    /**
     * 打开生物胸腔
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (player.level().isClientSide()) return InteractionResultHolder.success(stack);
        // 搜寻在实体交互范围内且在视线上的实体
        HitResult hitResult = ProjectileUtil.getHitResultOnViewVector(
                player,
                checkEntity -> checkEntity != player,
                player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).getValue()
        );
        if (hitResult instanceof EntityHitResult entityHitResult && entityHitResult.getEntity() instanceof LivingEntity entity) {
            ChestCavityUtil.openChestCavity(player, entity);
        } else {
            ChestCavityUtil.openChestCavity(player);
        }
        return InteractionResultHolder.consume(stack);
    }
}
