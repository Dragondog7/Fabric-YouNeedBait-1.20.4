package net.cookiebrain.youneedbait.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MinnowBucketBlockEntity extends BlockEntity {
    public MinnowBucketBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MINNOWBUCKET_BLOCK_ENTITY, pos, state);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    public static void tick(World world, BlockPos pos, BlockState state, MinnowBucketBlockEntity blockEntity) {
        if (world.isClient()) {
            return;
        }
    }
}
