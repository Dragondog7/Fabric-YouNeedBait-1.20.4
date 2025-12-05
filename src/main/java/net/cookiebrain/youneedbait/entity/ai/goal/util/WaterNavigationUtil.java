package net.cookiebrain.youneedbait.entity.ai.goal.util;

import net.minecraft.block.Block;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.SeaPickleBlock;
import net.minecraft.block.SeagrassBlock;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class WaterNavigationUtil {

    public static Vec3d findRandomWaterPosition(WaterCreatureEntity creature, int searchRange, int attempts) {
        Vec3d currentPos = creature.getPos();

        for (int i = 0; i < attempts; i++) {
            double x = currentPos.x + (creature.getRandom().nextDouble() * 2 - 1) * searchRange;
            double y = currentPos.y + (creature.getRandom().nextDouble() * 2 - 1) * searchRange;
            double z = currentPos.z + (creature.getRandom().nextDouble() * 2 - 1) * searchRange;

            BlockPos pos = new BlockPos((int)x, (int)y, (int)z);

            if (creature.getWorld().getFluidState(pos).isIn(FluidTags.WATER)) {
                return new Vec3d(x, y, z);
            }
        }

        return null;
    }

    public static Vec3d findRandomWaterPosition(WaterCreatureEntity creature, int searchRange) {
        return findRandomWaterPosition(creature, searchRange, 10);
    }

    public static boolean isPositionInWater(WaterCreatureEntity creature, Vec3d position) {
        BlockPos pos = new BlockPos((int)position.x, (int)position.y, (int)position.z);
        return creature.getWorld().getFluidState(pos).isIn(FluidTags.WATER);
    }

    public static int getLightLevel(WaterCreatureEntity creature, Vec3d position) {
        BlockPos pos = new BlockPos((int)position.x, (int)position.y, (int)position.z);
        return creature.getWorld().getLightLevel(pos);
    }

    public static int countWaterPlantsInRadius(WaterCreatureEntity creature, Vec3d position, int radius) {
        int plantCount = 0;
        BlockPos centerPos = new BlockPos((int)position.x, (int)position.y, (int)position.z);

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos checkPos = centerPos.add(x, y, z);

                    if (creature.getWorld().getFluidState(checkPos).isIn(FluidTags.WATER)) {
                        Block block = creature.getWorld().getBlockState(checkPos).getBlock();
                        if (block instanceof PlantBlock || block instanceof SeagrassBlock || block instanceof SeaPickleBlock) {
                            plantCount++;
                        }
                    }
                }
            }
        }

        return plantCount;
    }
}
