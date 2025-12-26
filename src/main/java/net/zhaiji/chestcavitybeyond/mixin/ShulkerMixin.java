package net.zhaiji.chestcavitybeyond.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.level.Level;
import net.zhaiji.chestcavitybeyond.register.InitAttribute;
import net.zhaiji.chestcavitybeyond.util.ChestCavityUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Shulker.class)
public abstract class ShulkerMixin extends AbstractGolem {
    protected ShulkerMixin(EntityType<? extends AbstractGolem> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyExpressionValue(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/Shulker;isPassenger()Z"
            )
    )
    public boolean chestCavityBeyond$tick(boolean original) {
        // 由于最终值要取反，所以此处需要判断小于等于0
        return original || ChestCavityUtil.getData(this).getCurrentValue(InitAttribute.ENDER) <= 0;
    }

    /**
     * TODO 随机传送距离应该受末影属性影响
     */
    @ModifyExpressionValue(
            method = "teleportSomewhere",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/Shulker;isAlive()Z"
            )
    )
    public boolean chestCavityBeyond$teleportSomewhere(boolean original) {
        return original && ChestCavityUtil.getData(this).getCurrentValue(InitAttribute.ENDER) > 0;
    }
}
