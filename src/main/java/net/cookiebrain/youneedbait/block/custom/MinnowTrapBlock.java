package net.cookiebrain.youneedbait.block.custom;

import com.mojang.serialization.MapCodec;
import net.cookiebrain.youneedbait.block.entity.MinnowBucketBlockEntity;
import net.cookiebrain.youneedbait.block.entity.MinnowTrapBlockEntity;
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

public class MinnowTrapBlock extends BlockWithEntity implements BlockEntityProvider, Waterloggable {
    private static final VoxelShape MINNOW_TRAP_SHAPE = Block.createCuboidShape(
            3.0, 0.0, 3.0,  // Min: Left-front bottom
            13.0, 10.0, 14.0  // Max: Right-back top
    );

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    //public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    //private final Block baseBlock;
    //protected final BlockState baseBlockState;
    public MinnowTrapBlock(Settings settings) {
        super(settings);
        setDefaultState(stateManager.getDefaultState().with(WATERLOGGED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient && hand == Hand.MAIN_HAND) {
            BlockEntity be = world.getBlockEntity(pos);
            if(be instanceof MinnowTrapBlockEntity blockEntity){
                int selectedSlot = player.getInventory().selectedSlot;
                System.out.println(((MinnowTrapBlockEntity) be).getBaitType().getName());
                ItemStack bait = ((MinnowTrapBlockEntity) be).removeBait();
                int existingSlot = player.getInventory().getSlotWithStack(bait);
                if(!bait.isEmpty()){
                    if(player.getInventory().getStack(selectedSlot).isOf(bait.getItem())) {
                        //Adds to the stack if they have it selected
                        player.getInventory().getStack(selectedSlot).increment(bait.getCount());
                    } else if (existingSlot>=0 && player.getInventory().getStack(existingSlot).getCount()<player.getInventory().getStack(existingSlot).getMaxCount()) {
                        //Adds to the stack if they have it somewhere in their inventory and it isn't full
                        player.getInventory().getStack(existingSlot).increment(bait.getCount());
                    } else if (player.getInventory().getStack(selectedSlot).isEmpty()) {
                        //Sets the stack if empty
                        player.getInventory().setStack(selectedSlot,bait);
                    } else {
                        //Find the first open slot
                        int openSlot = player.getInventory().getEmptySlot();
                        if(openSlot>=0){
                            player.getInventory().setStack(openSlot,bait);
                        } else {
                            // Create a new ItemEntity at the specified position
                            ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, bait);
                            // Set motion for the item entity if desired
                            itemEntity.setVelocity(Vec3d.ZERO); // Example: Set no motion
                            // Spawn the ItemEntity in the world
                            world.spawnEntity(itemEntity);
                        }
                    }
                }

                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onBreak(World world, BlockPos pos,BlockState state, PlayerEntity player){
        BlockEntity be = world.getBlockEntity(pos);
        if(be instanceof MinnowTrapBlockEntity blockEntity){
            ItemStack mbItem = new ItemStack(this);
            world.removeBlock(pos,false);
            ItemEntity itemEntity = new ItemEntity(world,pos.getX() + 0.5, pos.getY() + 0.5,pos.getZ() + 0.5,mbItem);
            itemEntity.setVelocity(Vec3d.ZERO);
            world.spawnEntity(itemEntity);
        }
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
        return MINNOW_TRAP_SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        //Without this the block is invisible
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MinnowTrapBlockEntity(pos,state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.MINNOWTRAP_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
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
//            if (blockEntity instanceof MinnowTrapBlockEntity) {
//                ItemScatterer.spawn(world, pos, (MinnowTrapBlockEntity)blockEntity);
//                world.updateComparators(pos,this);
//            }
//            super.onStateReplaced(state, world, pos, newState, moved);
//        }
//    }
}
