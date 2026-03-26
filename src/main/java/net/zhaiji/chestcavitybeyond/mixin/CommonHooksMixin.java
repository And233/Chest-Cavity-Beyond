package net.zhaiji.chestcavitybeyond.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.CommonHooks;
import net.zhaiji.chestcavitybeyond.util.MixinUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(CommonHooks.class)
public abstract class CommonHooksMixin {
    /**
     * @author zhaijineet
     * @reason 水平太菜，只能用Overwrite了
     */
    @Overwrite
    public static void onLivingBreathe(LivingEntity entity, int consumeAirAmount, int refillAirAmount) {
        MixinUtil.onLivingBreathe(entity, consumeAirAmount, refillAirAmount);
    }
}
