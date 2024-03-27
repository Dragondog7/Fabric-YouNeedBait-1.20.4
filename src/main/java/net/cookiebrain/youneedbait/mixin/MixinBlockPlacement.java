package net.cookiebrain.youneedbait.mixin;

import net.cookiebrain.youneedbait.block.ModBlocks;
import net.cookiebrain.youneedbait.block.custom.TackleBoxBlock;
import net.cookiebrain.youneedbait.block.entity.TackleBoxBlockEntity;
import net.cookiebrain.youneedbait.inventory.ItemStackHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public abstract class MixinBlockPlacement {

    @Inject(method = "postPlacement",at = @At(value = "RETURN"), cancellable = true)
    private void onBlockPlace(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack stack, BlockState state, CallbackInfoReturnable<ActionResult> cir) {
        System.out.println("You placed a block");
        ItemStack usedItem = player.getMainHandStack();
        if(usedItem.isOf(Item.fromBlock(ModBlocks.TACKLEBOX_BLOCK))) {
            System.out.println("This is a valid tackle box");
            DefaultedList<ItemStack> newInventory;
            System.out.println(usedItem.hasNbt());
            if(usedItem.hasNbt() && usedItem.getNbt().contains("tacklebox_inv")){
                System.out.println("This tacklebox has stuff in it");
                DefaultedList<ItemStack> tbInventory= ItemStackHelper.nbtToItemStack(usedItem,"tacklebox_inv");
                System.out.println("Inventory size:" + tbInventory.size());
                System.out.println(state.getBlock().getClass().toString());
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if(blockEntity != null){
                    System.out.println("The block entity is not null");
                    if(blockEntity instanceof TackleBoxBlockEntity){
                        System.out.println("Setting the inventory on the placed block");
                        ((TackleBoxBlockEntity) blockEntity).setItems(tbInventory);
                    }
                }
            }
        }
        //System.out.println(Objects.requireNonNull(contexet.getPlayer()).getInventory().getMainHandStack().getName());
//        Block block = this.getBlock();
//        if (!this.isEnabled(context.getWorld().getEnabledFeatures())) {
//            return ActionResult.FAIL;
//        }
//        if (!context.canPlace()) {
//            return ActionResult.FAIL;
//        }
//        ItemPlacementContext itemPlacementContext = this.getPlacementContext(context);
//        BlockPos blockPos = itemPlacementContext.getBlockPos();
//        World world = itemPlacementContext.getWorld();
//        PlayerEntity playerEntity = itemPlacementContext.getPlayer();
//        ItemStack itemStack = itemPlacementContext.getStack();
//        BlockState blockState2 = world.getBlockState(blockPos);
//        if (blockState2.isOf(blockState.getBlock())) {
//            blockState2 = this.placeFromNbt(blockPos, world, itemStack, blockState2);
//            this.postPlacement(blockPos, world, playerEntity, itemStack, blockState2);
//            blockState2.getBlock().onPlaced(world, blockPos, blockState2, playerEntity, itemStack);
//            if (playerEntity instanceof ServerPlayerEntity) {
//                Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
//            }
//        }
        // Your custom logic here. Example: Cancelling the block placement under certain conditions.
        // You can check the context to see where the block is being placed, who is placing it, etc.

        // If you want to prevent the block from being placed, you could do:
        // cir.setReturnValue(ActionResult.FAIL);
        // Note: Be careful with altering game behavior to not frustrate players or disrupt expected mechanics.
    }
}