package net.zhaiji.chestcavitybeyond.register;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zhaiji.chestcavitybeyond.ChestCavityBeyond;
import net.zhaiji.chestcavitybeyond.api.capability.OrganFactory;
import net.zhaiji.chestcavitybeyond.item.ChestOpenerItem;
import net.zhaiji.chestcavitybeyond.util.OrganAttributeUtil;

import java.util.function.Supplier;

public class InitItem {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(BuiltInRegistries.ITEM, ChestCavityBeyond.MOD_ID);
    // 开胸器
    public static final Supplier<Item> CHEST_OPENER = ITEM.register(
            "chest_opener",
            ChestOpenerItem::new
    );

    // 心脏
    public static final Supplier<Item> HEART = ITEM.register(
            "heart",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.HEALTH, OrganAttributeUtil.createAddValueModifier(id, 1));
                    })
                    .build()
    );

    // 肺脏
    public static final Supplier<Item> LUNG = ITEM.register(
            "lung",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.BREATH_RECOVERY, OrganAttributeUtil.createAddValueModifier(id, 1));
                        modifiers.put(InitAttribute.BREATH_CAPACITY, OrganAttributeUtil.createAddValueModifier(id, 1));
                        modifiers.put(InitAttribute.ENDURANCE, OrganAttributeUtil.createAddValueModifier(id, 1));
                    })
                    .build()
    );

    // 脊柱
    public static final Supplier<Item> SPINE = ITEM.register(
            "spine",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.NERVES, OrganAttributeUtil.createAddValueModifier(id, 1));
                        modifiers.put(InitAttribute.DEFENSE, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                    })
                    .build()
    );

    // 胃
    public static final Supplier<Item> STOMACH = ITEM.register(
            "stomach",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.DIGESTION, OrganAttributeUtil.createAddValueModifier(id, 1));
                    })
                    .build()
    );

    // 肠子
    public static final Supplier<Item> INTESTINE = ITEM.register(
            "intestine",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.NUTRITION, OrganAttributeUtil.createAddValueModifier(id, 1));
                    })
                    .build()
    );

    // 肾脏
    public static final Supplier<Item> KIDNEY = ITEM.register(
            "kidney",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.FILTRATION, OrganAttributeUtil.createAddValueModifier(id, 1));
                    })
                    .build()
    );

    // 脾脏
    public static final Supplier<Item> SPLEEN = ITEM.register(
            "spleen",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.METABOLISM, OrganAttributeUtil.createAddValueModifier(id, 1));
                    })
                    .build()
    );

    // 肝脏
    public static final Supplier<Item> LIVER = ITEM.register(
            "liver",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.DETOXIFICATION, OrganAttributeUtil.createAddValueModifier(id, 1));
                    })
                    .build()
    );

    // 阑尾
    public static final Supplier<Item> APPENDIX = ITEM.register(
            "appendix",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(Attributes.LUCK, OrganAttributeUtil.createAddValueModifier(id, 1));
                    })
                    .build()
    );

    // 肋骨
    public static final Supplier<Item> RIB = ITEM.register(
            "rib",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.DEFENSE, OrganAttributeUtil.createAddValueModifier(id, 1));
                    })
                    .build()
    );

    // 肌肉
    public static final Supplier<Item> MUSCLE = ITEM.register(
            "muscle",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.STRENGTH, OrganAttributeUtil.createAddValueModifier(id, 1));
                        modifiers.put(InitAttribute.SPEED, OrganAttributeUtil.createAddValueModifier(id, 1));
                    })
                    .build()
    );

    // 动物心脏
    public static final Supplier<Item> ANIMAL_HEART = ITEM.register(
            "animal_heart",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.HEALTH, OrganAttributeUtil.createAddValueModifier(id, 0.75));
                    })
                    .build()
    );

    // 动物肺脏
    public static final Supplier<Item> ANIMAL_LUNG = ITEM.register(
            "animal_lung",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.BREATH_RECOVERY, OrganAttributeUtil.createAddValueModifier(id, 0.75));
                        modifiers.put(InitAttribute.BREATH_CAPACITY, OrganAttributeUtil.createAddValueModifier(id, 0.75));
                        modifiers.put(InitAttribute.ENDURANCE, OrganAttributeUtil.createAddValueModifier(id, 0.75));
                    })
                    .build()
    );

    // 动物脊柱 - 防御特殊处理为0.375
    public static final Supplier<Item> ANIMAL_SPINE = ITEM.register(
            "animal_spine",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.NERVES, OrganAttributeUtil.createAddValueModifier(id, 0.75));
                        modifiers.put(InitAttribute.DEFENSE, OrganAttributeUtil.createAddValueModifier(id, 0.375));
                    })
                    .build()
    );

    // 动物胃
    public static final Supplier<Item> ANIMAL_STOMACH = ITEM.register(
            "animal_stomach",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.DIGESTION, OrganAttributeUtil.createAddValueModifier(id, 0.75));
                    })
                    .build()
    );

    // 动物肠子
    public static final Supplier<Item> ANIMAL_INTESTINE = ITEM.register(
            "animal_intestine",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.NUTRITION, OrganAttributeUtil.createAddValueModifier(id, 0.75));
                    })
                    .build()
    );

    // 动物肾脏
    public static final Supplier<Item> ANIMAL_KIDNEY = ITEM.register(
            "animal_kidney",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.FILTRATION, OrganAttributeUtil.createAddValueModifier(id, 0.75));
                    })
                    .build()
    );

    // 动物脾脏
    public static final Supplier<Item> ANIMAL_SPLEEN = ITEM.register(
            "animal_spleen",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.METABOLISM, OrganAttributeUtil.createAddValueModifier(id, 0.75));
                    })
                    .build()
    );

    // 动物肝脏
    public static final Supplier<Item> ANIMAL_LIVER = ITEM.register(
            "animal_liver",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.DETOXIFICATION, OrganAttributeUtil.createAddValueModifier(id, 0.75));
                    })
                    .build()
    );

    // 动物阑尾
    public static final Supplier<Item> ANIMAL_APPENDIX = ITEM.register(
            "animal_appendix",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(Attributes.LUCK, OrganAttributeUtil.createAddValueModifier(id, 0.75));
                    })
                    .build()
    );

    // 动物肋骨
    public static final Supplier<Item> ANIMAL_RIB = ITEM.register(
            "animal_rib",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.DEFENSE, OrganAttributeUtil.createAddValueModifier(id, 0.75));
                    })
                    .build()
    );

    // 动物肌肉
    public static final Supplier<Item> ANIMAL_MUSCLE = ITEM.register(
            "animal_muscle",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.STRENGTH, OrganAttributeUtil.createAddValueModifier(id, 0.75));
                        modifiers.put(InitAttribute.SPEED, OrganAttributeUtil.createAddValueModifier(id, 0.75));
                    })
                    .build()
    );

    // 小型动物心脏
    public static final Supplier<Item> SMALL_ANIMAL_HEART = ITEM.register(
            "small_animal_heart",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.HEALTH, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                    })
                    .build()
    );

    // 小型动物肺脏
    public static final Supplier<Item> SMALL_ANIMAL_LUNG = ITEM.register(
            "small_animal_lung",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.BREATH_RECOVERY, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                        modifiers.put(InitAttribute.BREATH_CAPACITY, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                        modifiers.put(InitAttribute.ENDURANCE, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                    })
                    .build()
    );

    // 小型动物脊柱 - 防御特殊处理为0.375，其他属性0.5
    public static final Supplier<Item> SMALL_ANIMAL_SPINE = ITEM.register(
            "small_animal_spine",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.NERVES, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                        modifiers.put(InitAttribute.DEFENSE, OrganAttributeUtil.createAddValueModifier(id, 0.375));
                    })
                    .build()
    );

    // 小型动物胃
    public static final Supplier<Item> SMALL_ANIMAL_STOMACH = ITEM.register(
            "small_animal_stomach",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.DIGESTION, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                    })
                    .build()
    );

    // 小型动物肠子
    public static final Supplier<Item> SMALL_ANIMAL_INTESTINE = ITEM.register(
            "small_animal_intestine",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.NUTRITION, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                    })
                    .build()
    );

    // 小型动物肾脏
    public static final Supplier<Item> SMALL_ANIMAL_KIDNEY = ITEM.register(
            "small_animal_kidney",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.FILTRATION, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                    })
                    .build()
    );

    // 小型动物脾脏
    public static final Supplier<Item> SMALL_ANIMAL_SPLEEN = ITEM.register(
            "small_animal_spleen",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.METABOLISM, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                    })
                    .build()
    );

    // 小型动物肝脏
    public static final Supplier<Item> SMALL_ANIMAL_LIVER = ITEM.register(
            "small_animal_liver",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.DETOXIFICATION, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                    })
                    .build()
    );

    // 小型动物阑尾
    public static final Supplier<Item> SMALL_ANIMAL_APPENDIX = ITEM.register(
            "small_animal_appendix",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(Attributes.LUCK, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                    })
                    .build()
    );

    // 小型动物肋骨
    public static final Supplier<Item> SMALL_ANIMAL_RIB = ITEM.register(
            "small_animal_rib",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.DEFENSE, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                    })
                    .build()
    );

    // 小型动物肌肉
    public static final Supplier<Item> SMALL_ANIMAL_MUSCLE = ITEM.register(
            "small_animal_muscle",
            () -> OrganFactory.builder()
                    .organModifier((id, modifiers) -> {
                        modifiers.put(InitAttribute.STRENGTH, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                        modifiers.put(InitAttribute.SPEED, OrganAttributeUtil.createAddValueModifier(id, 0.5));
                    })
                    .build()
    );
}
