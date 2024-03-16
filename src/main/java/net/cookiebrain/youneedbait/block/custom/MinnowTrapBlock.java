package net.cookiebrain.youneedbait.block.custom;

import com.mojang.serialization.MapCodec;
import net.cookiebrain.youneedbait.block.entity.MinnowTrapBlockEntity;
import net.cookiebrain.youneedbait.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
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
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class MinnowTrapBlock extends BlockWithEntity implements BlockEntityProvider, Waterloggable {
    private static final VoxelShape MINNOW_TRAP_SHAPE = MinnowTrapBlock.createCuboidShape(3, 1, 0, 14, 11, 15);
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
                //System.out.println("You used the minnow trap");
                //ItemStack extracted = ((MinnowTrapBlockEntity) be).removeMinnows();
                int selectedSlot = player.getInventory().selectedSlot;
                player.getInventory().main.set(selectedSlot,((MinnowTrapBlockEntity) be).removeMinnows());
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.SUCCESS;
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

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MinnowTrapBlockEntity(pos,state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.MINNOWTRAP_BLOCK_ENTITY,
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
