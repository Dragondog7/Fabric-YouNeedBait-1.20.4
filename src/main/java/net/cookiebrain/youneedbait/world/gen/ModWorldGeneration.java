package net.cookiebrain.youneedbait.world.gen;

public class ModWorldGeneration {

    public static void generateModWorldGen() {

        ModOreGeneration.generateOres();


        ModEntitySpawns.addSpawn();
    }
}
