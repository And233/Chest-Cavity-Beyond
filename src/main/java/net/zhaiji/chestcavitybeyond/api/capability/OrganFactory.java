package net.zhaiji.chestcavitybeyond.api.capability;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.zhaiji.chestcavitybeyond.api.function.*;
import net.zhaiji.chestcavitybeyond.item.OrganItem;
import net.zhaiji.chestcavitybeyond.util.ChestCavityClientUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 器官工厂类
 */
public class OrganFactory {
    public static final Map<Item, Organ> ORGAN_REGISTRY = new HashMap<>();

    public static final IOrgan EMPTY_ORGAN = new IOrgan() {
    };
    private static final OrganTickConsumer EMPTY_TICK = context -> {
    };
    private static final OrganAddedConsumer EMPTY_ADDED = context -> {
    };
    private static final OrganRemovedConsumer EMPTY_REMOVED = context -> {
    };

    /**
     * 新建构建器
     * @return 构建器
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Item.Properties properties;
        private final Multimap<Holder<Attribute>, AttributeModifier> modifiers;
        private OrganTooltipsConsumer organTooltipsConsumer;
        private OrganTickConsumer organTickConsumer;
        private OrganAddedConsumer organAddedConsumer;
        private OrganRemovedConsumer organRemovedConsumer;

        private Builder() {
            this.properties = new Item.Properties().stacksTo(1);
            this.modifiers = HashMultimap.create();
            this.organTooltipsConsumer = ChestCavityClientUtil::addOrganTooltips;
            this.organTickConsumer = EMPTY_TICK;
            this.organAddedConsumer = EMPTY_ADDED;
            this.organRemovedConsumer = EMPTY_REMOVED;
        }

        /**
         * 设置器官物品属性
         */
        public Builder properties(ItemPropertiesConsumer itemPropertiesConsumer) {
            itemPropertiesConsumer.accept(properties);
            return this;
        }

        /**
         * 设置器官提供的属性修饰符
         */
        public Builder modifiers(AttributeModifierConsumer attributeModifierConsumer) {
            attributeModifierConsumer.accept(modifiers);
            return this;
        }

        /**
         * 设置器官工具提示
         */
        public Builder tooltips(OrganTooltipsConsumer organTooltipsConsumer) {
            this.organTooltipsConsumer = organTooltipsConsumer;
            return this;
        }

        /**
         * 设置器官tick触发器
         */
        public Builder tick(OrganTickConsumer organTickConsumer) {
            this.organTickConsumer = organTickConsumer;
            return this;
        }

        /**
         * 设置器官移植触发器
         */
        public Builder added(OrganAddedConsumer organAddedConsumer) {
            this.organAddedConsumer = organAddedConsumer;
            return this;
        }

        /**
         * 设置器官摘除触发器
         */
        public Builder removed(OrganRemovedConsumer organRemovedConsumer) {
            this.organRemovedConsumer = organRemovedConsumer;
            return this;
        }

        /**
         * 构建
         */
        public OrganItem build() {
            OrganItem organItem = new OrganItem(properties, organTooltipsConsumer);
            ORGAN_REGISTRY.put(organItem, new Organ(modifiers, organTickConsumer, organAddedConsumer, organRemovedConsumer));
            return organItem;
        }
    }
}
