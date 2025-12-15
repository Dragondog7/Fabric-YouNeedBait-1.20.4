package net.cookiebrain.youneedbait.block.custom;

import com.mojang.serialization.MapCodec;
import net.cookiebrain.youneedbait.block.entity.MinnowBucketBlockEntity;
import net.cookiebrain.youneedbait.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MinnowBucketBlock extends BlockWithEntity implements BlockEntityProvider, Waterloggable {
//    private static final VoxelShape MINNOW_BUCKET_SHAPE = MinnowBucketBlock.createCuboidShape(
//            3.0, 0.0, 3.0,  // Min X, Y, Z (shifted inward for the bucket base)
//            13.0, 13.0, 13.0  // Max X, Y, Z (trimmed to fit the rim/handle without excess
//    );
    private static final VoxelShape MINNOW_BUCKET_SHAPE = VoxelShapes.union(
        // Body/middle (y=0-11, slightly wider than original middle for better feel)
        Block.createCuboidShape(3.5, 0, 3.5, 12.5, 11, 12.5),

        // Rim/top (y=11-13, full width for the lip)
        Block.createCuboidShape(3, 11, 3, 13, 13, 13)

        // Handle protrusion (unchanged, approximating your side elements)
        //Block.createCuboidShape(10, 2, 11, 13, 10, 12)
    );
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    //public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    //private final Block baseBlock;
    //protected final BlockState baseBlockState;
    public MinnowBucketBlock(Settings settings) {
        super(settings);
        setDefaultState(stateManager.getDefaultState().with(WATERLOGGED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient && hand == Hand.MAIN_HAND) {
            BlockEntity be = world.getBlockEntity(pos);
//            if(be instanceof MinnowBucketBlockEntity blockEntity){
//                //System.out.println("You used the minnow bucket");
//                //ItemStack extracted = ((MinnowBucketBlockEntity) be).removeMinnows();
//                int selectedSlot = player.getInventory().selectedSlot;
//                player.getInventory().main.set(selectedSlot,((MinnowBucketBlockEntity) be).removeBait());
//                return ActionResult.SUCCESS;
//            }
        }
        return ActionResult.SUCCESS;
    }
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            // Same block type, just a state change (e.g., property update)
            // Don't drop the block or clean up the block entity
            super.onStateReplaced(state, world, pos, newState, moved);
            return;
        }

        // Block is being replaced with a different block (including air when broken)
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof MinnowBucketBlockEntity blockEntity) {
            ItemStack mbItem = new ItemStack(this);

            // Spawn the item entity at the block's center
            ItemEntity itemEntity = new ItemEntity(
                    world,
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    mbItem
            );
            itemEntity.setVelocity(Vec3d.ZERO);
            world.spawnEntity(itemEntity);
        }

        // Call super AFTER your logic to ensure proper cleanup
        // This will handle removing the block entity and other vanilla cleanup
        super.onStateReplaced(state, world, pos, newState, moved);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(WATERLOGGED);
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        return this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }
    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED).booleanValue()) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return MINNOW_BUCKET_SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        //Without this the block is invisible
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MinnowBucketBlockEntity(pos,state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type != ModBlockEntities.MINNOWBUCKET_BLOCK_ENTITY) {
            return null;
        }

        return (world1, pos, state1, blockEntity) ->
                MinnowBucketBlockEntity.tick(world1, pos, state1, (MinnowBucketBlockEntity) blockEntity);
    }

    // 1.20.3+ block codecs: required by BlockWithEntity in 1.20.3/1.20.4+.
    // Currently unused, so returning null is acceptable. This compiles fine on 1.20.1/1.20.2 as well.
    protected MapCodec<? extends net.minecraft.block.BlockWithEntity> getCodec() {
        return null;
    }
//    @Override
//    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
//        if (state.get(WATERLOGGED).booleanValue()) {
//            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
//        }
//        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
//    }
//
//    @Override
//    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
//        if (state.getBlock() != newState.getBlock()) {
//            BlockEntity blockEntity = world.getBlockEntity(pos);
//            if (blockEntity instanceof MinnowBucketBlockEntity) {
//                ItemScatterer.spawn(world, pos, (MinnowBucketBlockEntity)blockEntity);
//                world.updateComparators(pos,this);
//            }
//            super.onStateReplaced(state, world, pos, newState, moved);
//        }
//    }
}
