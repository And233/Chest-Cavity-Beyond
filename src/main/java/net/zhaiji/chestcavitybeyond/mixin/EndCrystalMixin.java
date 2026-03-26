package net.zhaiji.chestcavitybeyond.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.Level;
import net.zhaiji.chestcavitybeyond.util.MixinUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndCrystal.class)
public abstract class EndCrystalMixin extends Entity {
    public EndCrystalMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    /**
     * 应用结晶效果
     */
    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;blockPosition()Lnet/minecraft/core/BlockPos;"
            )
    )
    public void chestCavityBeyond$tick(CallbackInfo ci) {
        MixinUtil.applyCrystalHealing((EndCrystal) (Object) this, tickCount);
    }
}
