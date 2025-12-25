package net.zhaiji.chestcavitybeyond.network.server;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.zhaiji.chestcavitybeyond.attachment.ChestCavityData;
import net.zhaiji.chestcavitybeyond.network.server.packet.SyncSelectedSlotPacket;
import net.zhaiji.chestcavitybeyond.network.server.packet.UseSkillPacket;
import net.zhaiji.chestcavitybeyond.util.ChestCavityUtil;

public class ServerPacketHandler {
    public static void handleUseSkillPacket(UseSkillPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            ChestCavityData data = ChestCavityUtil.getData(player);
            int slot = packet.slot();
            ItemStack stack = data.getStackInSlot(slot);
            ChestCavityUtil.organSkill(data, player, slot, stack);
        });
    }

    public static void handlerSyncSelectedSlotPacket(SyncSelectedSlotPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ChestCavityUtil.getData(context.player()).selectedSlot = packet.slot();
        });
    }
}
