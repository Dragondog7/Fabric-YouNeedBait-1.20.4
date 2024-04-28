package net.cookiebrain.youneedbait.block.custom;

import com.mojang.serialization.MapCodec;
import net.cookiebrain.youneedbait.block.entity.FishCleaningStationBlockEntity;
import net.cookiebrain.youneedbait.block.entity.ModBlockEntities;
import net.cookiebrain.youneedbait.block.entity.TackleBoxBlockEntity;
import net.cookiebrain.youneedbait.inventory.ItemStackHelper;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FishCleaningStationBlock extends BlockWithEntity implements BlockEntityProvider{
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    private static final VoxelShape FISHCLEANINGSTATION_BLOCK_SHAPE = Block.createCuboidShape(0, 0, 0, 12, 12, 12);

    public FishCleaningStationBlock(Settings settings) {
        super(settings);
    }


    //Constructors
    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
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

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        System.out.println("Block was broken here");
        BlockEntity be = world.getBlockEntity(pos);
        if(be instanceof TackleBoxBlockEntity blockEntity){
            ItemStack tbItem = new ItemStack(this);
            System.out.println("Saving the items from the fishcleaningstation");
            System.out.println((long) ((TackleBoxBlockEntity) be).getItems().size());
            ItemStackHelper.itemStackToNBT(tbItem,"fishcleaningstation_inv",((FishCleaningStationBlockEntity) be).getItems());
            System.out.println("Checking if the saved items has nbt data");
            System.out.println(tbItem.hasNbt());
            ItemStackHelper.giveItemToPlayer(player,tbItem);
            //Get rid of the item
            DefaultedList<ItemStack> emptyItems = DefaultedList.ofSize(27,ItemStack.EMPTY);
            ((TackleBoxBlockEntity) be).setItems(emptyItems);
            world.removeBlock(pos,false);
        }
        return super.onBreak(world, pos, state, player);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        System.out.println("Block was replaced here");
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
            System.out.println("Not on client");
            BlockEntity entity = world.getBlockEntity(pos);
            if(entity instanceof FishCleaningStationBlockEntity){
                ((FishCleaningStationBlockEntity) entity).setPlayer((ServerPlayerEntity) player);
            }
            NamedScreenHandlerFactory screenHandlerFactory = ((FishCleaningStationBlockEntity) world.getBlockEntity(pos));
            System.out.println("Screen Handler factory created");
            if (screenHandlerFactory != null) {
                System.out.println("attempting to open the screen");
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.FISHCLEANINGSTATION_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    //    @Override
    //    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    //        if (world.isClient) {
    //            return ActionResult.SUCCESS;
    //        }
    //        System.out.println("You used the tackle box");
    //        NamedScreenHandlerFactory namedScreenHandlerFactory = this.createScreenHandlerFactory(state, world, pos);
    //        if (namedScreenHandlerFactory != null) {
    //            System.out.println("namedScreenHandlerFactory is not null");
    //            player.openHandledScreen(namedScreenHandlerFactory);
    //            System.out.println("openHandledScreen worked");
    //            //player.incrementStat(this.getOpenStat());
    //            //PiglinBrain.onGuardedBlockInteracted(player, true);
    //        }
    //        return ActionResult.CONSUME;
    //    }
}