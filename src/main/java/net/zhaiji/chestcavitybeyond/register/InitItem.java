package net.zhaiji.chestcavitybeyond.register;

import net.minecraft.core.registries.BuiltInRegistries;
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

    public static final Supplier<Item> HEART = ITEM.register(
            "heart",
            () -> OrganFactory.builder()
                    .modifiers(modifiers -> {
                        modifiers.put(InitAttribute.HEALTH, OrganAttributeUtil.createAddValueModifier(1));
                    })
                    .build()
    );

    public static final Supplier<Item> LUNG = ITEM.register(
            "lung",
            () -> OrganFactory.builder()
                    .modifiers(modifiers -> {
                        modifiers.put(InitAttribute.BREATH_RECOVERY, OrganAttributeUtil.createAddValueModifier(1));
                        modifiers.put(InitAttribute.BREATH_CAPACITY, OrganAttributeUtil.createAddValueModifier(1));
                        modifiers.put(InitAttribute.ENDURANCE, OrganAttributeUtil.createAddValueModifier(1));
                    })
                    .build()
    );

    public static final Supplier<Item> SPINE = ITEM.register(
            "spine",
            () -> OrganFactory.builder()
                    .modifiers(modifiers -> {
                        modifiers.put(InitAttribute.NERVES, OrganAttributeUtil.createAddValueModifier(1));
                        modifiers.put(InitAttribute.DEFENSE, OrganAttributeUtil.createAddValueModifier(0.5));
                    })
                    .build()
    );
}
