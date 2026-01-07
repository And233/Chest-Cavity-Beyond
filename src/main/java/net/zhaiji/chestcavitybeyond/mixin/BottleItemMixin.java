package net.zhaiji.chestcavitybeyond.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BottleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.zhaiji.chestcavitybeyond.mixinapi.IAreaEffectCloud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BottleItem.class)
public abstract class BottleItemMixin extends Item {
    public BottleItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;"
            )
    )
    public void chestCavityBeyond$use(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir, @Local LocalRef<List<AreaEffectCloud>> list) {
        // 使用新增龙息检测方法
        list.set(
                level.getEntitiesOfClass(
                        AreaEffectCloud.class,
                        player.getBoundingBox().inflate(2.0),
                        entity -> entity != null
                                && entity.isAlive()
                                && entity instanceof IAreaEffectCloud cloud
                                && cloud.isDragonBreath()
                )
        );
    }
}
