package net.zhaiji.chestcavitybeyond.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.zhaiji.chestcavitybeyond.attachment.ChestCavityData;
import net.zhaiji.chestcavitybeyond.register.InitItem;
import net.zhaiji.chestcavitybeyond.util.ChestCavityUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(Guardian.class)
public abstract class GuardianMixin extends Monster {
    public GuardianMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Mixin(Guardian.GuardianAttackGoal.class)
    public abstract static class GuardianAttackGoalMixin extends Goal {
        @Shadow
        @Final
        private Guardian guardian;

        @Mutable
        @Shadow
        @Final
        private boolean elder;

        // 没有守卫者之眼不允许射激光
        @Inject(
                method = "tick",
                at = @At("HEAD"),
                cancellable = true
        )
        public void chestCavityBeyond$tick(CallbackInfo ci) {
            ChestCavityData data = ChestCavityUtil.getData(guardian);
            elder = data.hasOrgan(InitItem.ELDER_GUARDIAN_EYE.get());
            if (!data.hasOrgan(InitItem.GUARDIAN_EYE.get()) && !elder) {
                ci.cancel();
            }
        }
    }

    @Mixin(Guardian.GuardianAttackSelector.class)
    public abstract static class GuardianAttackSelectorMixin implements Predicate<LivingEntity> {
        @Shadow
        @Final
        private Guardian guardian;

        // 没有守卫者之眼不允许射激光
        @Inject(
                method = "test(Lnet/minecraft/world/entity/LivingEntity;)Z",
                at = @At("RETURN"),
                cancellable = true
        )
        public void chestCavityBeyond$test(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
            ChestCavityData data = ChestCavityUtil.getData(guardian);
            boolean flag = data.hasOrgan(InitItem.GUARDIAN_EYE.get()) || data.hasOrgan(InitItem.ELDER_GUARDIAN_EYE.get());
            cir.setReturnValue(cir.getReturnValue() && flag);
        }
    }
}
