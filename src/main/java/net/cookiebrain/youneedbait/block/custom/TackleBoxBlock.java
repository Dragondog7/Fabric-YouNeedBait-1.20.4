package net.cookiebrain.youneedbait.block.custom;

import com.mojang.serialization.MapCodec;
import net.cookiebrain.youneedbait.block.entity.ModBlockEntities;
import net.cookiebrain.youneedbait.block.entity.TackleBoxBlockEntity;
import net.cookiebrain.youneedbait.inventory.ItemStackHelper;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TackleBoxBlock extends BlockWithEntity implements BlockEntityProvider{
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    private static final VoxelShape TACKLEBOX_BLOCK_SHAPE = Block.createCuboidShape(0, 0, 0, 12, 12, 12);

    public TackleBoxBlock(Settings settings) {
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
        return new TackleBoxBlockEntity(pos,state);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        //System.out.println("Block was broken here");
        BlockEntity be = world.getBlockEntity(pos);
        if(be instanceof TackleBoxBlockEntity blockEntity){
            ItemStack tbItem = new ItemStack(this);
            //System.out.println("Saving the items from the tacklebox");
            System.out.println((long) ((TackleBoxBlockEntity) be).getItems().size());
            ItemStackHelper.itemStackToNBT(tbItem,"tacklebox_inv",((TackleBoxBlockEntity) be).getItems());
            //System.out.println("Checking if the saved items has nbt data");
            //System.out.println(tbItem.hasNbt());
            //This puts the item directly in the inventory, would rather have it spawn
            //ItemStackHelper.giveItemToPlayer(player,tbItem);
            //Attempt to spawn the item

            //Get rid of the item
            DefaultedList<ItemStack> emptyItems = DefaultedList.ofSize(27,ItemStack.EMPTY);
            ((TackleBoxBlockEntity) be).setItems(emptyItems);
            world.removeBlock(pos,false);

            // Create a new ItemEntity at the specified position
            ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, tbItem);
            // Set motion for the item entity if desired
            itemEntity.setVelocity(Vec3d.ZERO); // Example: Set no motion
            // Spawn the ItemEntity in the world
            world.spawnEntity(itemEntity);
        }
        return super.onBreak(world, pos, state, player);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        //System.out.println("Block was replaced here");
        //This makes the inventory drop when your block breaks
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof TackleBoxBlockEntity) {
                ItemScatterer.spawn(world, pos, (TackleBoxBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            //System.out.println("Not on client");
            NamedScreenHandlerFactory screenHandlerFactory = ((TackleBoxBlockEntity) world.getBlockEntity(pos));
            //System.out.println("Screen Handler factory created");
            if (screenHandlerFactory != null) {
                //System.out.println("attempting to open the screen");
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.TACKLEBOX_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

}