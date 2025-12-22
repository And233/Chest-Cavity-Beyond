package net.zhaiji.chestcavitybeyond.network.client;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.zhaiji.chestcavitybeyond.network.client.packet.SyncChestCavityDataPacket;
import net.zhaiji.chestcavitybeyond.util.ChestCavityUtil;

public class ClientPacketHandler {
    public static void handlerSyncChestCavityDataPacket(final SyncChestCavityDataPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ChestCavityUtil.getData(context.player()).setOrgans(packet.organs());
        });
    }
}
