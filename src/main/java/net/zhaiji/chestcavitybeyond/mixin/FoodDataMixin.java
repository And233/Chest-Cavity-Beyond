package net.zhaiji.chestcavitybeyond.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.zhaiji.chestcavitybeyond.attachment.ChestCavityData;
import net.zhaiji.chestcavitybeyond.mixinapi.IFoodData;
import net.zhaiji.chestcavitybeyond.util.MixinUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public abstract class FoodDataMixin implements IFoodData {
    @Shadow
    private int tickTimer;
    @Shadow
    private int foodLevel;
    @Unique
    private ChestCavityData data;
    /**
     * 缓存当前食用食物
     */
    @Unique
    private ItemStack food = ItemStack.EMPTY;
    /**
     * 剩余新陈代谢缓存
     */
    @Unique
    private double metabolismRemainder;
    /**
     * 光合作用计时器
     */
    @Unique
    private double photosynthesisTimer;

    @Shadow
    protected abstract void add(int foodLevel, float saturationLevel);

    @Unique
    @Override
    public void setChestCavityData(ChestCavityData data) {
        if (this.data == null) {
            this.data = data;
        }
    }

    @Unique
    @Override
    public ChestCavityData getChestCavityData() {
        return data;
    }

    @Unique
    @Override
    public void setFood(ItemStack food) {
        this.food = food;
    }

    @Unique
    @Override
    public ItemStack getFood() {
        return food;
    }

    @Unique
    @Override
    public int getTickTimer() {
        return tickTimer;
    }

    @Unique
    @Override
    public void setTickTimer(int tickTimer) {
        this.tickTimer = tickTimer;
    }

    @Unique
    @Override
    public double getMetabolismRemainder() {
        return metabolismRemainder;
    }

    @Unique
    @Override
    public void setMetabolismRemainder(double metabolismRemainder) {
        this.metabolismRemainder = metabolismRemainder;
    }

    @Unique
    @Override
    public double getPhotosynthesisTimer() {
        return photosynthesisTimer;
    }

    @Unique
    @Override
    public void setPhotosynthesisTimer(double photosynthesisTimer) {
        this.photosynthesisTimer = photosynthesisTimer;
    }

    /**
     * 修改食物获取到的饥饿值和饱食度
     */
    @Inject(
            method = "eat(Lnet/minecraft/world/food/FoodProperties;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void chestCavityBeyond$eat(FoodProperties foodProperties, CallbackInfo ci) {
        MixinUtil.eat((FoodData) (Object) this, foodProperties);
        ci.cancel();
    }

    /**
     * 根据{@link net.zhaiji.chestcavitybeyond.register.InitAttribute#METABOLISM}属性，修改Timer的累计速度，影响消耗饥饿回复生命值的速度
     * <p>
     * 根据{@link net.zhaiji.chestcavitybeyond.register.InitAttribute#PHOTOSYNTHESIS}属性，新增在白天可以进行光合作用增加饥饿值和饱食度
     * </p>
     */
    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    public void chestCavityBeyond$tick(Player player, CallbackInfo ci) {
        MixinUtil.tickMetabolism((FoodData) (Object) this, player);
    }

    /**
     * 修改向exhaustion添加的值
     */
    @ModifyVariable(
            method = "addExhaustion",
            at = @At("HEAD"),
            argsOnly = true
    )
    public float chestCavityBeyond$modifyExhaustion(float value) {
        return MixinUtil.modifyExhaustion(value, data);
    }
}
