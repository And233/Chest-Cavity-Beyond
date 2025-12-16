package net.zhaiji.chestcavitybeyond.mixin;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.zhaiji.chestcavitybeyond.util.ChestCavityUtil;
import net.zhaiji.chestcavitybeyond.util.MathUtil;
import org.checkerframework.common.aliasing.qual.Unique;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RandomSwimmingGoal.class)
public abstract class RandomSwimmingGoalMixin extends RandomStrollGoal {
    public RandomSwimmingGoalMixin(PathfinderMob mob, double speedModifier, int interval) {
        super(mob, speedModifier, interval);
    }

    /**
     * 修改水中移动速度
     * <p>
     * 水生动物的游泳速度不影响他们在水中的实际速度，不太想兼容这个，太麻烦了
     * </P>
     * 写了这一个应该够糊弄了（）
     * <p>
     * TODO 还是写个TODO记录一下，鱼，鱿鱼，海豚没写
     * </P>
     */
    @Unique
    public double getSpeedModifier() {
        return speedModifier * MathUtil.getDirectScale(ChestCavityUtil.getData(mob).getDifferenceValue(NeoForgeMod.SWIM_SPEED));
    }

    @Override
    public void start() {
        mob.getNavigation().moveTo(wantedX, wantedY, wantedZ, getSpeedModifier());
    }
}
