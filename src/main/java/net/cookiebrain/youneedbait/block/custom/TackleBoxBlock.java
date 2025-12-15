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
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TackleBoxBlock extends BlockWithEntity implements BlockEntityProvider{
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    private static final VoxelShape TACKLEBOX_BLOCK_SHAPE = VoxelShapes.union(
            // Main base body (core rectangular prism, y=0-6)
            Block.createCuboidShape(3.5, 0.0, 5.2, 12.5, 6.0, 11.2),

            // Left side panel (thin vertical, y=0-5.5)
            Block.createCuboidShape(2.0, 0.0, 6.2, 3.5, 5.5, 10.2),

            // Right side panel (symmetric thin vertical)
            Block.createCuboidShape(12.5, 0.0, 6.2, 14.0, 5.5, 10.2),

            // Left back extension (protruding ledge)
            Block.createCuboidShape(4.0, 0.0, 10.9, 7.5, 5.0, 11.9),

            // Right back extension (symmetric)
            Block.createCuboidShape(8.5, 0.0, 10.9, 12.0, 5.0, 11.9),

            // Front bottom slat (rotated but forms a thin front panel)
            Block.createCuboidShape(4.5, 0.0, 4.2, 11.5, 5.0, 5.2),

            // Lid (slab on top, y=5.7-6.7, slightly inset)
            Block.createCuboidShape(4.0, 5.7, 5.7, 12.0, 6.7, 10.7),

            // Center latch/handle (small top bump)
            Block.createCuboidShape(6.5, 8.2, 7.7, 9.5, 9.2, 8.7),

            // Left side handle (protruding from lid edge)
            Block.createCuboidShape(5.0, 6.7, 7.7, 6.0, 7.7, 8.7),

            // Right side handle (symmetric)
            Block.createCuboidShape(10.0, 6.7, 7.7, 11.0, 7.7, 8.7)

            // Left clip/hinge (angled protrusion, clamped x=0-1 for block bounds)
            //Block.createCuboidShape(0.0, 9.0, 7.7, 1.0, 11.0, 8.7),

            // Right clip/hinge (angled, clamped x=15-16)
            //Block.createCuboidShape(15.0, 9.0, 7.7, 16.0, 11.0, 8.7)
    );

    public TackleBoxBlock(Settings settings) {
        super(settings);
    }


//    //Constructors

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

//    @Override
//    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
//        //System.out.println("Block was broken here");
//        BlockEntity be = world.getBlockEntity(pos);
//        if(be instanceof TackleBoxBlockEntity blockEntity){
//            ItemStack tbItem = new ItemStack(this);
//            //System.out.println("Saving the items from the tacklebox");
//            System.out.println((long) ((TackleBoxBlockEntity) be).getItems().size());
//            ItemStackHelper.itemStackToNBT(tbItem,"tacklebox_inv",((TackleBoxBlockEntity) be).getItems());
//            //System.out.println("Checking if the saved items has nbt data");
//            //System.out.println(tbItem.hasNbt());
//            //This puts the item directly in the inventory, would rather have it spawn
//            //ItemStackHelper.giveItemToPlayer(player,tbItem);
//            //Attempt to spawn the item
//
//            //Get rid of the item
//            DefaultedList<ItemStack> emptyItems = DefaultedList.ofSize(4,ItemStack.EMPTY);
//            ((TackleBoxBlockEntity) be).setItems(emptyItems);
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

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return TACKLEBOX_BLOCK_SHAPE;
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type != ModBlockEntities.TACKLEBOX_BLOCK_ENTITY) {
            return null;
        }

        return (world1, pos, state1, blockEntity) ->
                TackleBoxBlockEntity.tick(world1, pos, state1, (TackleBoxBlockEntity) blockEntity);
    }

    // 1.20.3+ block codecs: required by BlockWithEntity in 1.20.3/1.20.4+.
    // Currently unused, so returning null is acceptable. This compiles fine on 1.20.1/1.20.2 as well.
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

}