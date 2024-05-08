package net.cookiebrain.youneedbait.block.entity;

import net.cookiebrain.youneedbait.block.ModBlockUtil;
import net.cookiebrain.youneedbait.item.ModItems;
import net.cookiebrain.youneedbait.loot.BonusLoot;
import net.cookiebrain.youneedbait.loot.ModBonusLoot;
import net.cookiebrain.youneedbait.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

public class MinnowTrapBlockEntity extends BlockEntity  {
    //implements ImplementedInventory
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private int baitTicks=0;
    private static final int MAX_BAIT = 5;
    private static final int BAIT_SPAWN_RATE = 5 * 60 * 20; //5 minutes
    private final ModBonusLoot bonusLoot = new ModBonusLoot();

    public MinnowTrapBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MINNOWTRAP_BLOCK_ENTITY, pos, state);
        }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

        if (this.baitTicks++ % BAIT_SPAWN_RATE == 0) {
//            System.out.println("Current minnow count: " + inventory.get(0).getCount());
//            System.out.println("Max Minnow Count:" + MAX_MINNOWS);
            ItemStack spawnItemStack = new ItemStack(ModItems.MINNOW_ITEM,1);
            //Determine what spawns
            RegistryEntry<Biome> biomeEntry = world.getBiome(pos);
            boolean isSwamp = biomeEntry.isIn(ModTags.Biomes.LEECH_TRAP_BIOMES);
            if (isSwamp){
                System.out.println("We are in leech country baby!");
                BonusLoot bl = bonusLoot.getLootTableByName("swampbaittrap");
                spawnItemStack = bl.selectRandomWeightedItem();
            }
            if(inventory.get(0).getCount() < MAX_BAIT) {
                //System.out.println("Checking blocks around the Minnow Trap");
                boolean inWater = ModBlockUtil.isAdjacentToWater(world, pos);

                if(inWater){
                    if (this.inventory.get(0).isEmpty()) {
                        this.inventory.set(0,spawnItemStack);
                    } else {
                        //Add a bait item if it's of the same type
                        if(this.inventory.get(0).isOf(spawnItemStack.getItem())){
                            this.inventory.get(0).increment(1);
                        }

                    }
                }
            }
        }
    }

    public Item getBaitType(){
        return inventory.get(0).getItem();
    }
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    public ItemStack removeBait() {
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
