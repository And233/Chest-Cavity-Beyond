package net.zhaiji.chestcavitybeyond.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.zhaiji.chestcavitybeyond.mixinapi.IMobEffectInstance;
import net.zhaiji.chestcavitybeyond.register.InitAttribute;
import net.zhaiji.chestcavitybeyond.util.ChestCavityUtil;
import net.zhaiji.chestcavitybeyond.util.OrganAttributeUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    /**
     * 根据属性返回生物是否水过敏
     */
    @ModifyExpressionValue(
            method = "aiStep",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;isSensitiveToWater()Z"
            )
    )
    public boolean chestCavityBeyond$aiStep(boolean original) {
        return OrganAttributeUtil.isWaterAllergy((LivingEntity) (Object) this);
    }

    /**
     * 如果有腐食消化，则不会附加食物的负面效果
     */
    @WrapWithCondition(
            method = "addEatEffect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"
            )
    )
    public boolean chestCavityBeyond$addEatEffect(LivingEntity instance, MobEffectInstance effectInstance) {
        if (ChestCavityUtil.getData(instance).getCurrentValue(InitAttribute.SCAVENGER_DIGESTION) > 0) {
            return !((IMobEffectInstance) effectInstance).isHarmful();
        }
        return true;
    }
}
