package net.cookiebrain.youneedbait.item.custom;

import net.cookiebrain.youneedbait.block.entity.ImplementedInventory;
import net.cookiebrain.youneedbait.entity.custom.FancyFishingBobberEntity;
import net.cookiebrain.youneedbait.inventory.FishingHelper;
import net.cookiebrain.youneedbait.screen.FancyFishingRodScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class FancyFishingRodItem extends FishingRodItem implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private ItemStack rodInstance;
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3,ItemStack.EMPTY);
    public FancyFishingRodItem(Settings settings) {
        super(settings);

    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        this.rodInstance = user.getStackInHand(hand);
        int i;

        //Check if the player is holding down shift
        if (user.isSneaking()){
            if(!world.isClient()){
                NamedScreenHandlerFactory screenHandlerFactory = this;
                System.out.println(screenHandlerFactory);
                System.out.println("Screen Handler factory created");
                if (screenHandlerFactory != null) {
                    System.out.println("attempting to open the screen");
                    user.openHandledScreen(screenHandlerFactory);
                }
            }
        } else {

            //Check for requirements
            //Check for a hook
            if (FishingHelper.hasHook(user)) {
                if (!FishingHelper.hasBait(user)) {
                    // Cancel the event (prevent fishing rod use) if the condition is not met
                    // To effectively return "nothing" or indicate cancellation, use ActionResult.FAIL
                    // and ItemStack.EMPTY (or another appropriate ItemStack if needed).
                    //Without this the fishing rod disappears
                    return TypedActionResult.fail(this.rodInstance);

                }
            } else {
                return TypedActionResult.fail(this.rodInstance);
            }
            if (user.fishHook != null) {
                if (!world.isClient) {
                    i = user.fishHook.use(this.rodInstance);
                    this.rodInstance.damage(i, user, (p) -> {
                        p.sendToolBreakStatus(hand);
                    });
                }
                world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                user.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
            } else {
                world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                if (!world.isClient) {
                    i = EnchantmentHelper.getLure(this.rodInstance);
                    int j = EnchantmentHelper.getLuckOfTheSea(this.rodInstance);
                    Entity bobberEntity = new FancyFishingBobberEntity(user, world, j, i);
                    world.spawnEntity(bobberEntity);
                }

                user.incrementStat(Stats.USED.getOrCreateStat(this));
                user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
            }

            return TypedActionResult.success(this.rodInstance, world.isClient());
        }
        return TypedActionResult.success(this.rodInstance, world.isClient());
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(player.getSteppingPos());
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Fancy Fishing Rod");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FancyFishingRodScreenHandler(syncId,playerInventory,this.rodInstance);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    public void setItems(DefaultedList<ItemStack> itemStacks) {
        this.inventory = itemStacks;
    }
}
