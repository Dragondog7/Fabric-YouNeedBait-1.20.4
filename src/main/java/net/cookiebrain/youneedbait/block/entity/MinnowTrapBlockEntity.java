package net.cookiebrain.youneedbait.block.entity;

import net.cookiebrain.youneedbait.block.ModBlockUtil;
import net.cookiebrain.youneedbait.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MinnowTrapBlockEntity extends BlockEntity  {
    //implements ImplementedInventory
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private int minnowTicks;
    private static int MAX_MINNOWS = 5;
    private static int MINNOW_SPAWN_RATE = 5 * 60 * 20; //5 minutes

    //protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private boolean inWater;
    public MinnowTrapBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MINNOWTRAP_BLOCK_ENTITY, pos, state);
        };

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

        if (this.minnowTicks++ % MINNOW_SPAWN_RATE == 0) {
//            System.out.println("Start of Minnow Block Entity ticking");
//            System.out.println("Current minnow count: " + inventory.get(0).getCount());
//            System.out.println("Max Minnow Count:" + MAX_MINNOWS);
            if(inventory.get(0).getCount() < MAX_MINNOWS) {
                //System.out.println("Checking blocks around the Minnow Trap");
                inWater = ModBlockUtil.isAdjacentToWater(world,pos);

                if(inWater){
                    //System.out.println("A new minnow was spawned");
                    if (this.inventory.get(0).isEmpty()) {
                        //System.out.println("Inventory is empty");
                        this.inventory.set(0,new ItemStack(ModItems.MINNOW_ITEM, 1));
                        //this.inventory.add(0,new ItemStack(ModItems.MINNOW_ITEM, 1));
                    } else {
                        //Add a minnow
                        //System.out.println("Added a minnow to the current stack");
                        this.inventory.get(0).increment(1);
                    }
                } else {
                    //System.out.println("This block is NOT in water");
                }
            }
        }
    }

    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    public ItemStack removeMinnows() {
        return inventory.get(0).copyAndEmpty();
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    public Text getDisplayName() {
        return Text.literal("Minnow Trap");
    }
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }


}
