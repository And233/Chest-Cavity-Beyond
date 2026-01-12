package net.zhaiji.chestcavitybeyond.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.zhaiji.chestcavitybeyond.ChestCavityBeyondConfig;
import net.zhaiji.chestcavitybeyond.attachment.ChestCavityData;
import net.zhaiji.chestcavitybeyond.register.InitAttribute;
import net.zhaiji.chestcavitybeyond.util.ChestCavityUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

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
        List<LivingEntity> list = level().getEntitiesOfClass(
                LivingEntity.class,
                getBoundingBox().inflate(ChestCavityBeyondConfig.crystalEffectSearchRange),
                entity -> !(entity instanceof EnderDragon)
                        && entity.getAttribute(InitAttribute.CRYSTALLIZATION).getValue() > 0
        );
        for (LivingEntity entity : list) {
            ChestCavityData data = ChestCavityUtil.getData(entity);
            double crystallization = data.getCurrentValue(InitAttribute.CRYSTALLIZATION);
            if (crystallization <= 0) continue;
            if (tickCount % 10 == 0) {
                entity.heal((float) crystallization / 5);
                if (tickCount % 20 == 0 && entity instanceof Player player) {
                    player.getFoodData().eat((int) Math.min(crystallization / 5, 1), 0.5F);
                }
            }
        }
    }
}
