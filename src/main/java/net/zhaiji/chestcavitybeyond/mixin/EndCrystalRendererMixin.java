package net.zhaiji.chestcavitybeyond.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EndCrystalRenderer;
import net.minecraft.client.renderer.entity.EnderDragonRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.zhaiji.chestcavitybeyond.ChestCavityBeyondConfig;
import net.zhaiji.chestcavitybeyond.register.InitAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EndCrystalRenderer.class)
public abstract class EndCrystalRendererMixin extends EntityRenderer<EndCrystal> {
    public EndCrystalRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    /**
     * 为有结晶效果的实体设置光线
     */
    @Inject(
            method = "render(Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At("RETURN")
    )
    public void chestCavityBeyond$render(EndCrystal crystal, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        List<LivingEntity> list = crystal.level().getEntitiesOfClass(
                LivingEntity.class,
                crystal.getBoundingBox().inflate(ChestCavityBeyondConfig.crystalEffectSearchRange),
                entity -> !(entity instanceof EnderDragon)
                        && entity.getAttribute(InitAttribute.CRYSTALLIZATION).getValue() > 0
        );
        for (LivingEntity entity : list) {
            float x = (float) (Mth.lerp(partialTicks, entity.xo, entity.getX()) - crystal.getX());
            float y = (float) (Mth.lerp(partialTicks, entity.yo - 0.9, entity.getY() - 0.9) - crystal.getY());
            float z = (float) (Mth.lerp(partialTicks, entity.zo, entity.getZ()) - crystal.getZ());
            poseStack.pushPose();
            poseStack.translate(x, y, z);
            EnderDragonRenderer.renderCrystalBeams(
                    -x,
                    -y + EndCrystalRenderer.getY(crystal, partialTicks),
                    -z,
                    partialTicks,
                    entity.tickCount,
                    poseStack,
                    buffer,
                    packedLight
            );
            poseStack.popPose();
        }
    }
}
