package net.zhaiji.chestcavitybeyond.api.capability;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.zhaiji.chestcavitybeyond.api.ChestCavitySlotContext;
import net.zhaiji.chestcavitybeyond.api.function.OrganAddedConsumer;
import net.zhaiji.chestcavitybeyond.api.function.OrganRemovedConsumer;
import net.zhaiji.chestcavitybeyond.api.function.OrganTickConsumer;

public class Organ implements IOrgan {
    private final Multimap<Holder<Attribute>, AttributeModifier> modifiers;
    private final OrganTickConsumer organTickConsumer;
    private final OrganAddedConsumer organAddedConsumer;
    private final OrganRemovedConsumer organRemovedConsumer;

    public Organ(Multimap<Holder<Attribute>, AttributeModifier> modifiers, OrganTickConsumer organTickConsumer, OrganAddedConsumer organAddedConsumer, OrganRemovedConsumer organRemovedConsumer) {
        this.modifiers = modifiers;
        this.organTickConsumer = organTickConsumer;
        this.organAddedConsumer = organAddedConsumer;
        this.organRemovedConsumer = organRemovedConsumer;
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers() {
        return modifiers;
    }

    @Override
    public void tick(ChestCavitySlotContext context) {
        organTickConsumer.accept(context);
    }

    @Override
    public void organAdded(ChestCavitySlotContext context) {
        organAddedConsumer.accept(context);
    }

    @Override
    public void organRemoved(ChestCavitySlotContext context) {
        organRemovedConsumer.accept(context);
    }
}
