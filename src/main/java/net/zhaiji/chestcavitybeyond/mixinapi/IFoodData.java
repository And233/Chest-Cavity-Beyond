package net.zhaiji.chestcavitybeyond.mixinapi;

import net.zhaiji.chestcavitybeyond.attachment.ChestCavityData;

public interface IFoodData {
    /**
     * 设置玩家胸腔
     * <p>
     * 只允许设置一次，因为FoodData所属不能变更
     * </P>
     *
     * @param data 胸腔数据
     */
    void setChestCavityData(ChestCavityData data);
}
