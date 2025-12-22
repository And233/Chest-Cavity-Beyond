package net.zhaiji.chestcavitybeyond.network.server;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.zhaiji.chestcavitybeyond.attachment.ChestCavityData;
import net.zhaiji.chestcavitybeyond.network.server.packet.UseSkillPacket;
import net.zhaiji.chestcavitybeyond.util.ChestCavityUtil;

public class ServerPacketHandler {
    public static void handleUseSkillPacket(final UseSkillPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            ChestCavityData data = ChestCavityUtil.getData(context.player());
            int slot = packet.slot();
            ItemStack stack = data.getStackInSlot(slot);
            ChestCavityUtil.organSkill(data, player, slot, stack);
        });
    }
}
