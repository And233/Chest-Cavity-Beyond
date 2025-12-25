package net.zhaiji.chestcavitybeyond.network.client;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.zhaiji.chestcavitybeyond.attachment.ChestCavityData;
import net.zhaiji.chestcavitybeyond.client.screen.OrganSkillScreen;
import net.zhaiji.chestcavitybeyond.network.client.packet.SyncChestCavityDataPacket;
import net.zhaiji.chestcavitybeyond.util.ChestCavityUtil;

public class ClientPacketHandler {
    public static void handlerSyncChestCavityDataPacket(final SyncChestCavityDataPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ChestCavityData data = ChestCavityUtil.getData(context.player());
            data.setOrgans(packet.organs());
            OrganSkillScreen.selectedSlot = packet.slot();
        });
    }
}
