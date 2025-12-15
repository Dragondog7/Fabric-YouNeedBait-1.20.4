package net.cookiebrain.youneedbait.block.custom;

import com.mojang.serialization.MapCodec;
import net.cookiebrain.youneedbait.block.entity.FishCleaningStationBlockEntity;
import net.cookiebrain.youneedbait.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FishCleaningStationBlock extends BlockWithEntity{
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    private static final VoxelShape FISHCLEANINGSTATION_BLOCK_SHAPE = Block.createCuboidShape(0, 0, 0, 12, 12, 12);

    public FishCleaningStationBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        //Without this the block is invisible
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FishCleaningStationBlockEntity(pos,state);
    }

//    //@Override
//    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
//        YouNeedBait.LOGGER.info("Block was broken here");
//        BlockEntity be = world.getBlockEntity(pos);
//        if(be instanceof FishCleaningStationBlockEntity blockEntity){
//            ItemStack tbItem = new ItemStack(this);
//            YouNeedBait.LOGGER.info("Saving the items from the fishcleaningstation");
//            ItemStackHelper.itemStackToNBT(tbItem,"fishcleaningstation_inv",((FishCleaningStationBlockEntity) be).getItems());
//            //Get rid of the item
//            DefaultedList<ItemStack> emptyItems = DefaultedList.ofSize(27,ItemStack.EMPTY);
//            //((FishCleaningStationBlockEntity) be).setItems(emptyItems);
//            world.removeBlock(pos,false);
//
//            // Create a new ItemEntity at the specified position
//            ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, tbItem);
//            // Set motion for the item entity if desired
//            itemEntity.setVelocity(Vec3d.ZERO); // Example: Set no motion
//            // Spawn the ItemEntity in the world
//            world.spawnEntity(itemEntity);
//        }
//        //return super.onBreak(world, pos, state, player);
//    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        //This makes the inventory drop when your block breaks
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof FishCleaningStationBlockEntity) {
                ItemScatterer.spawn(world, pos, (FishCleaningStationBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity entity = world.getBlockEntity(pos);
            if(entity instanceof FishCleaningStationBlockEntity){
                //((FishCleaningStationBlockEntity) entity).setPlayer((ServerPlayerEntity) player);
            }
            NamedScreenHandlerFactory screenHandlerFactory = ((FishCleaningStationBlockEntity) world.getBlockEntity(pos));
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        // Only our block entity type gets a ticker
        if (type != ModBlockEntities.FISHCLEANINGSTATION_BLOCK_ENTITY) {
            return null;
        }

        // The static tick method already checks world.isClient(), so we don't have to here.
        return (world1, pos, state1, blockEntity) ->
                FishCleaningStationBlockEntity.tick(world1, pos, state1, (FishCleaningStationBlockEntity) blockEntity);
    }

    // 1.20.3+ block codecs: required by BlockWithEntity in 1.20.3/1.20.4+.
    // Currently unused, so returning null is acceptable. This compiles fine on 1.20.1/1.20.2 as well.
    protected MapCodec<? extends net.minecraft.block.BlockWithEntity> getCodec() {
        return null;
    }

}