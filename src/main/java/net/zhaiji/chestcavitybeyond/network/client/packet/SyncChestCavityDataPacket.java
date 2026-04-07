package net.zhaiji.chestcavitybeyond.network.client.packet;

import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.zhaiji.chestcavitybeyond.ChestCavityBeyond;
import net.zhaiji.chestcavitybeyond.api.ChestCavitySize;
import net.zhaiji.chestcavitybeyond.network.client.ClientPacketHandler;

/**
 * 同步胸腔数据
 * <p>
 * 只进行一次同步，后续的物品同步由原版的Menu数据同步处理
 * </P>
 */
public record SyncChestCavityDataPacket(NonNullList<ItemStack> organs, int slot, ChestCavitySize size) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SyncChestCavityDataPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(ChestCavityBeyond.MOD_ID, "sync_chestcavitydata"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SyncChestCavityDataPacket> STREAM_CODEC = StreamCodec.of(
            (buffer, packet) -> {
                buffer.writeEnum(packet.size());
                for (int i = 0; i < packet.size().getSlots(); i++) {
                    ItemStack.OPTIONAL_STREAM_CODEC.encode(buffer, packet.organs().get(i));
                }
                buffer.writeInt(packet.slot());
            },
            buffer -> {
                ChestCavitySize size = buffer.readEnum(ChestCavitySize.class);
                NonNullList<ItemStack> items = NonNullList.withSize(size.getSlots(), ItemStack.EMPTY);
                for (int i = 0; i < size.getSlots(); i++) {
                    items.set(i, ItemStack.OPTIONAL_STREAM_CODEC.decode(buffer));
                }
                return new SyncChestCavityDataPacket(items, buffer.readInt(), size);
            }
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handler(SyncChestCavityDataPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> ClientPacketHandler.handlerSyncChestCavityDataPacket(context.player(), packet));
    }
}
