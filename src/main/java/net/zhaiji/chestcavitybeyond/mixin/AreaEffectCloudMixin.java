package net.zhaiji.chestcavitybeyond.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.zhaiji.chestcavitybeyond.mixinapi.IAreaEffectCloud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(AreaEffectCloud.class)
public abstract class AreaEffectCloudMixin extends Entity implements IAreaEffectCloud {
    public AreaEffectCloudMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract ParticleOptions getParticle();

    @Shadow
    @Nullable
    public abstract LivingEntity getOwner();

    @Unique
    @Override
    public boolean isDragonBreath() {
        return getParticle() == ParticleTypes.DRAGON_BREATH;
    }

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;isEmpty()Z"
            )
    )
    public void chestCavityBeyond$tick(CallbackInfo ci, @Local(ordinal = 1) LocalRef<List<LivingEntity>> list1) {
        // 当为龙息时，过滤所有者
        if (isDragonBreath()) {
            list1.set(level().getEntitiesOfClass(LivingEntity.class, getBoundingBox(), entity -> entity != getOwner()));
        }
    }
}
