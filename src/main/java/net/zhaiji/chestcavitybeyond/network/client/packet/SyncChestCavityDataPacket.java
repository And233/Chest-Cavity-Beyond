package net.zhaiji.chestcavitybeyond.network.client.packet;

import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.zhaiji.chestcavitybeyond.ChestCavityBeyond;

/**
 * 同步胸腔数据
 * TODO 选择技能的同步
 */
public record SyncChestCavityDataPacket(NonNullList<ItemStack> organs, int slot) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SyncChestCavityDataPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(ChestCavityBeyond.MOD_ID, "sync_chestcavitydata"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SyncChestCavityDataPacket> STREAM_CODEC = StreamCodec.of(
            (buffer, packet) -> {
                for (ItemStack stack : packet.organs()) {
                    ItemStack.OPTIONAL_STREAM_CODEC.encode(buffer, stack);
                }
                buffer.writeInt(packet.slot());
            },
            buffer -> {
                NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
                for (int i = 0; i < 27; i++) {
                    items.set(i, ItemStack.OPTIONAL_STREAM_CODEC.decode(buffer));
                }
                return new SyncChestCavityDataPacket(items, buffer.readInt());
            }
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
