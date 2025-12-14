package net.zhaiji.chestcavitybeyond.manager;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.zhaiji.chestcavitybeyond.api.ChestCavityType;
import net.zhaiji.chestcavitybeyond.register.InitItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChestCavityManager {
    public static final List<ChestCavityType> CHEST_CAVITY_TYPES = new ArrayList<>();

    public static final Map<EntityType<?>, ChestCavityType> ENTITY_CHEST_CAVITY_TYPE_MAP = new HashMap<>();

    public static final ChestCavityType HUMAN = register("human")
            .setFirstRow(0, InitItem.MUSCLE.get())
            .setFirstRow(1, InitItem.RIB.get())
            .setFirstRow(2, InitItem.APPENDIX.get())
            .setFirstRow(3, InitItem.LUNG.get())
            .setFirstRow(4, InitItem.HEART.get())
            .setFirstRow(5, InitItem.LUNG.get())
            .setFirstRow(7, InitItem.RIB.get())
            .setFirstRow(8, InitItem.MUSCLE.get())

            .setSecondRow(0, InitItem.MUSCLE.get())
            .setSecondRow(1, InitItem.RIB.get())
            .setSecondRow(2, InitItem.SPLEEN.get())
            .setSecondRow(3, InitItem.KIDNEY.get())
            .setSecondRow(4, InitItem.SPINE.get())
            .setSecondRow(5, InitItem.KIDNEY.get())
            .setSecondRow(6, InitItem.LIVER.get())
            .setSecondRow(7, InitItem.RIB.get())
            .setSecondRow(8, InitItem.MUSCLE.get())

            .setThirdRow(0, InitItem.MUSCLE.get())
            .setThirdRow(1, InitItem.MUSCLE.get())
            .setThirdRow(2, InitItem.INTESTINE.get())
            .setThirdRow(3, InitItem.INTESTINE.get())
            .setThirdRow(4, InitItem.STOMACH.get())
            .setThirdRow(5, InitItem.INTESTINE.get())
            .setThirdRow(6, InitItem.INTESTINE.get())
            .setThirdRow(7, InitItem.MUSCLE.get())
            .setThirdRow(8, InitItem.MUSCLE.get());

    // 为实体类型添加胸腔类型
    // 后续考虑挪位置
    static {
        registerEntity(EntityType.PLAYER, HUMAN);
    }

    /**
     * 获取实体的胸腔类型
     *
     * @param entity 实体
     * @return 胸腔类型
     */
    public static ChestCavityType getType(LivingEntity entity) {
        return ENTITY_CHEST_CAVITY_TYPE_MAP.get(entity.getType());
    }

    /**
     * 为实体类型注册胸腔类型
     *
     * @param entityType      实体类型
     * @param chestCavityType 胸腔类型
     */
    public static void registerEntity(EntityType<? extends LivingEntity> entityType, ChestCavityType chestCavityType) {
        ENTITY_CHEST_CAVITY_TYPE_MAP.put(entityType, chestCavityType.builder(entityType));
    }

    /**
     * 注册胸腔类型
     *
     * @param name 类型名称
     * @return 胸腔类型
     */
    public static ChestCavityType register(String name) {
        ChestCavityType chestCavityType = new ChestCavityType(name);
        CHEST_CAVITY_TYPES.add(chestCavityType);
        return chestCavityType;
    }
}
