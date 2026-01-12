package net.zhaiji.chestcavitybeyond.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.warden.SonicBoom;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.monster.warden.Warden;
import net.zhaiji.chestcavitybeyond.register.InitItem;
import net.zhaiji.chestcavitybeyond.util.ChestCavityUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(SonicBoom.class)
public abstract class SonicBoomMixin extends Behavior<Warden> {
    public SonicBoomMixin(Map<MemoryModuleType<?>, MemoryStatus> entryCondition) {
        super(entryCondition);
    }

    @Inject(
            method = "tick(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/monster/warden/Warden;J)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void chestCavityBeyond$tick(ServerLevel level, Warden owner, long gameTime, CallbackInfo ci) {
        // 没有幽匿核心禁止发射音爆
        if (!ChestCavityUtil.getData(owner).hasOrgan(InitItem.SCULK_CORE.get())) {
            ci.cancel();
        }
    }
}
