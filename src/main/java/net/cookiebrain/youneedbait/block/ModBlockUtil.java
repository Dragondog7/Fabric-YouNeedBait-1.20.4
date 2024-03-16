package net.cookiebrain.youneedbait.block;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ModBlockUtil {

    public static boolean isAdjacentToWater(World world, BlockPos pos) {
        // List only the horizontal directions
        Direction[] horizontalDirections = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

        for (Direction dir : horizontalDirections) {
            // Calculate the position of the adjacent block in the current direction
            BlockPos adjacentPos = pos.offset(dir);

            // Get the block state of the adjacent block
            BlockState adjacentState = world.getBlockState(adjacentPos);

            // Check if the material of the block is water
            if (adjacentState.getFluidState().getFluid() == Fluids.WATER) {
                // Optionally, you could also check if the block is specifically a water block, like so:
                // if (adjacentState.isOf(Blocks.WATER)) {
                return true; // Return true immediately if any water block is found
            }
        }

        // Return false if no adjacent water blocks were found
        return false;
    }
}