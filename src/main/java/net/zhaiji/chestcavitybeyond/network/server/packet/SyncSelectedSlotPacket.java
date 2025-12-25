package net.zhaiji.chestcavitybeyond.network.server.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.zhaiji.chestcavitybeyond.ChestCavityBeyond;

public record SyncSelectedSlotPacket(int slot) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SyncSelectedSlotPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(ChestCavityBeyond.MOD_ID, "sync_selectedslot"));

    public static final StreamCodec<ByteBuf, SyncSelectedSlotPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            SyncSelectedSlotPacket::slot,
            SyncSelectedSlotPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
