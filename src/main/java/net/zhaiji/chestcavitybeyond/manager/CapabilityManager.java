package net.zhaiji.chestcavitybeyond.manager;

import net.neoforged.neoforge.capabilities.ItemCapability;
import net.zhaiji.chestcavitybeyond.ChestCavityBeyond;
import net.zhaiji.chestcavitybeyond.api.capability.IOrgan;

public class CapabilityManager {
    /**
     * 器官Capability
     */
    public static final ItemCapability<IOrgan, Void> ORGAN = ItemCapability.createVoid(ChestCavityBeyond.of("organ"), IOrgan.class);
}
