package net.zhaiji.chestcavitybeyond.mixinapi;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;

public interface IMobEffectInstance {
    /**
     * @return 是否为负面效果
     */
    boolean isHarmful();

    /**
     * 设置持续时间
     *
     * @param duration 持续时间
     */
    void setDuration(int duration);

    /**
     * 设置持续时间
     *
     * @param mapper 修改持续事件回调
     */
    void setDuration(Int2IntFunction mapper);
}
