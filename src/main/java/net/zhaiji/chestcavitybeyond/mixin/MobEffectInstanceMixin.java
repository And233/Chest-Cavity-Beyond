package net.zhaiji.chestcavitybeyond.mixin;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.zhaiji.chestcavitybeyond.mixinapi.IMobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MobEffectInstance.class)
public abstract class MobEffectInstanceMixin implements IMobEffectInstance {
    @Shadow
    private int duration;
    @Shadow
    private int amplifier;

    @Shadow
    public abstract Holder<MobEffect> getEffect();

    @Unique
    @Override
    public boolean isHarmful() {
        return getEffect().value().getCategory() == MobEffectCategory.HARMFUL;
    }

    @Unique
    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Unique
    @Override
    public void setDuration(Int2IntFunction mapper) {
        duration = mapper.applyAsInt(duration);
    }
}
