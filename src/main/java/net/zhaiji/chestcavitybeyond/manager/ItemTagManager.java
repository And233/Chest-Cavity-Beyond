package net.zhaiji.chestcavitybeyond.manager;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.zhaiji.chestcavitybeyond.ChestCavityBeyond;

public class ItemTagManager {
    // 器官
    public static final TagKey<Item> ORGANS = create("organs");
    // 心脏
    public static final TagKey<Item> HEART = create("organs/heart");
    // 肺脏
    public static final TagKey<Item> LUNG = create("organs/lung");
    // 肌肉
    public static final TagKey<Item> MUSCLE = create("organs/muscle");
    // 肋骨
    public static final TagKey<Item> RIB = create("organs/rib");
    // 阑尾
    public static final TagKey<Item> APPENDIX = create("organs/appendix");
    // 脾脏
    public static final TagKey<Item> SPLEEN = create("organs/spleen");
    // 肾脏
    public static final TagKey<Item> KIDNEY = create("organs/kidney");
    // 脊柱
    public static final TagKey<Item> SPINE = create("organs/spine");
    // 肝脏
    public static final TagKey<Item> LIVER = create("organs/liver");
    // 肠子
    public static final TagKey<Item> INTESTINE = create("organs/intestine");
    // 胃
    public static final TagKey<Item> STOMACH = create("organs/stomach");
    // 特殊
    public static final TagKey<Item> SPECIAL = create("organs/special");
    // 骨质器官
    public static final TagKey<Item> BONE = create("organs/bone");
    // 腐烂器官
    public static final TagKey<Item> ROTTEN = create("organs/rotten");
    // 铁质器官
    public static final TagKey<Item> IRON = create("organs/iron");

    public static TagKey<Item> create(String name) {
        return ItemTags.create(ChestCavityBeyond.of(name));
    }
}
