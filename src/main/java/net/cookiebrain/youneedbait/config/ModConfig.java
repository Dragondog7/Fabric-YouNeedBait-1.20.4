package net.cookiebrain.youneedbait.config;

import net.cookiebrain.youneedbait.YouNeedBait;

public final class ModConfig {
    public String schemaVersion = "0.4.0";
    public String modId = YouNeedBait.MOD_ID;
    public Features features = new Features();
    public Logging logging = new Logging();
//    public Runtime runtime = new Runtime();
//    public Balance balance = new Balance();
//    public Experimental experimental = new Experimental();

    public static final class Features {
        public boolean baitRequired = true;
        public int baitUsagePercent = 10; // 0..100

        // NEW:
        /**
         * Master switch to enable/disable the whole weighted fish system.
         * If false, no weight NBT is written and fish stack normally.
         */
        public boolean weightedFishEnabled = true;

        /**
         * Display unit for tooltips. "kg" or "lb".
         * Internally we always store kg in NBT.
         */
        public String weightUnit = "kg";

        /**
         * Global multiplier for how skewed weights are toward small fish.
         * > 1 = big fish rarer; 1 = uniform; < 1 = big fish more common.
         * We combine this with a per-fish bias exponent.
         */
        public double fishWeightBias = 4.0;
    }

    public static final class Logging {
        public String level = "info";
        public boolean toFile = true;
        public String filePath = "logs/youneedbait.log";
    }

    public static final class Runtime {
//        public boolean hotReload = false;
//        public int reloadIntervalSeconds = 2;
    }

    public static final class Balance {
        // reserved for future settings
    }

    public static final class Experimental {
        // reserved for future flags
    }
}