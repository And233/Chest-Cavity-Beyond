package net.zhaiji.chestcavitybeyond.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.zhaiji.chestcavitybeyond.ChestCavityBeyond;

import java.util.function.IntFunction;

/**
 * 胸腔容量枚举，定义不同大小的胸腔
 */
public enum ChestCavitySize implements StringRepresentable {
    ROW_3(0, "row_3", 3, "chest_cavity_3", 181, 171),  // 3行 x 9列 = 27槽
    ROW_4(1, "row_4", 4, "chest_cavity_4", 181, 189),  // 4行 x 9列 = 36槽
    ROW_5(2, "row_5", 5, "chest_cavity_5", 181, 207),  // 5行 x 9列 = 45槽
    ROW_6(3, "row_6", 6, "chest_cavity_6", 181, 225);  // 6行 x 9列 = 54槽

    public static final ChestCavitySize DEFAULT_SIZE = ROW_3;

    private static final IntFunction<ChestCavitySize> BY_ID = ByIdMap.continuous(
        ChestCavitySize::getId,
        values(),
        ByIdMap.OutOfBoundsStrategy.CLAMP
    );

    public static final StringRepresentable.EnumCodec<ChestCavitySize> CODEC = StringRepresentable.fromEnum(ChestCavitySize::values);

    private final int id;
    private final String name;
    private final int rows;
    private final int slots;
    private final String textureName;
    private final int imageWidth;
    private final int imageHeight;


    ChestCavitySize(int id, String name, int rows, String textureName, int imageWidth, int imageHeight) {
        this.id = id;
        this.name = name;
        this.rows = rows;
        this.slots = rows * 9;
        this.textureName = textureName;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public static ChestCavitySize byId(int id) {
        return BY_ID.apply(id);
    }

    public static ChestCavitySize byName(String name) {
        return CODEC.byName(name, DEFAULT_SIZE);
    }

    /**
     * 根据槽位数获取枚举值，用于 setSize(int) 校验
     */
    public static ChestCavitySize bySlots(int slots) {
        return switch (slots) {
            case 36 -> ROW_4;
            case 45 -> ROW_5;
            case 54 -> ROW_6;
            default -> ROW_3;
        };
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getRows() {
        return rows;
    }

    public int getSlots() {
        return slots;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * 获取玩家背包起始Y坐标
     */
    public int getPlayerInventoryY() {
        return 18 + rows * 18 + 12;
    }

    /**
     * 获取快捷栏Y坐标
     */
    public int getHotbarY() {
        return getPlayerInventoryY() + 58;
    }

    /**
     * 获取该容量对应的 GUI 纹理 ResourceLocation
     */
    public ResourceLocation getTextureLocation() {
        return ResourceLocation.fromNamespaceAndPath(ChestCavityBeyond.MOD_ID, "textures/gui/" + textureName + ".png");
    }
}
