package net.zhaiji.chestcavitybeyond.damagesource;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public class MultipleSeathMessageDamageSource extends DamageSource {
    public MultipleSeathMessageDamageSource(Holder<DamageType> type, @Nullable Entity entity) {
        super(type, entity);
    }

    /**
     * 实现多条死亡信息随机选择
     */
    @Override
    public Component getLocalizedDeathMessage(LivingEntity livingEntity) {
        String translatableString = "death.attack." + type().msgId();
        if (getEntity() == null && getDirectEntity() == null) {
            return Component.translatable(translatableString, livingEntity.getDisplayName());
        } else {
            int i = livingEntity.getRandom().nextInt(5);
            Component component = getEntity() == null ? getDirectEntity().getDisplayName() : getEntity().getDisplayName();
            return Component.translatable(translatableString  + "."+ i, livingEntity.getDisplayName(), component);
        }
    }
}
