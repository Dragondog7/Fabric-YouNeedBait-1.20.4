package net.cookiebrain.youneedbait.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.toml.TomlParser;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

import net.cookiebrain.youneedbait.YouNeedBait;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public final class ConfigManager {
    private static volatile ModConfig CONFIG;

    private static Path configPath() {
        return FabricLoader.getInstance()
                .getConfigDir()
                .resolve(YouNeedBait.MOD_ID + ".toml");
    }

    public static void init() {
        ensureExists();
        CONFIG = load();
        validate(CONFIG);
        YouNeedBait.LOGGER.info("Loaded config for {} v{}: baitRequired={}, baitUsagePercent={}",
                YouNeedBait.MOD_ID, YouNeedBait.VERSION,
                CONFIG.features.baitRequired, CONFIG.features.baitUsagePercent);
    }

    public static ModConfig get() {
        ModConfig local = CONFIG;
        if (local == null) {
            synchronized (ConfigManager.class) {
                local = CONFIG;
                if (local == null) {
                    // Safe, idempotent initialization on first use
                    init();
                    local = CONFIG;
                }
            }
        }
        return local;
    }

    public static void reloadIfChanged() {
        // Simple on-demand reload. For watchers, schedule a FS watch service.
        ModConfig newCfg = load();
        validate(newCfg);
        CONFIG = newCfg; // atomic swap
        YouNeedBait.LOGGER.info("Reloaded config for {}", YouNeedBait.MOD_ID);
    }

    private static void ensureExists() {
        Path path = configPath();
        if (Files.exists(path)) return;

        try {
            Files.createDirectories(path.getParent());
            // Copy default from resources
            String resourcePath = "/assets/" + YouNeedBait.MOD_ID + "/default_config.toml";
            try (InputStream in = ConfigManager.class.getResourceAsStream(resourcePath)) {
                if (in != null) {
                    Files.copy(in, path, REPLACE_EXISTING);
                } else {
                    // Fallback: write a minimal default if resource missing
                    Files.writeString(path, """
                            # youneedbait default config
                            schema_version = "0.4.0"
                            mod_id = "youneedbait"
                            [features]
                            # Whether bait is required. Default: true (i.e., "Y")
                            bait_required = true
                            # Percentage chance of bait being consumed per use (0..100). Default: 40
                            bait_usage_percent = 40
                            # Each custom fish has it's own unique weight
                            weighted_fish_enabled = true
                            # Unit of measure to display the weight lb or kg
                            weight_unit = "lb"
                            # Decrease this to make it easier to catch large fish
                            # Increase this to make it more difficult
                            # This is a global bias, each fish has it's own bias that combines with this
                            fish_weight_bias = 4.0
                        """);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create default config at " + path, e);
        }
    }

    private static ModConfig load() {
        Path path = configPath();
        try (CommentedFileConfig c = CommentedFileConfig.builder(path).build()) {
            c.load();

            ModConfig cfg = new ModConfig();
            cfg.schemaVersion = c.getOrElse("schema_version", YouNeedBait.VERSION);
            cfg.modId = c.getOrElse("mod_id", YouNeedBait.MOD_ID);

            CommentedConfig features = c.getOrElse("features", CommentedConfig.inMemory());
            cfg.features.baitRequired = features.getOrElse("bait_required", true);
            cfg.features.baitUsagePercent = features.getOrElse("bait_usage_percent", 10);

            // NEW:
            cfg.features.weightedFishEnabled = features.getOrElse("weighted_fish_enabled", true);
            cfg.features.weightUnit = features.getOrElse("weight_unit", "kg");
            cfg.features.fishWeightBias = features.getOrElse("fish_weight_bias", 4.0);

//            CommentedConfig logging = c.getOrElse("logging", CommentedConfig.inMemory());
//            cfg.logging.level = logging.getOrElse("level", "info");
//            cfg.logging.toFile = logging.getOrElse("to_file", true);
//            cfg.logging.filePath = logging.getOrElse("file_path", "logs/" + YouNeedBait.MOD_ID + ".log");

            CommentedConfig runtime = c.getOrElse("runtime", CommentedConfig.inMemory());
//        cfg.runtime.hotReload = runtime.getOrElse("hot_reload", false);
//        cfg.runtime.reloadIntervalSeconds = runtime.getOrElse("reload_interval_seconds", 2);

            boolean dirty = false;
            if (!c.contains("schema_version")) { c.set("schema_version", cfg.schemaVersion); dirty = true; }
            if (!c.contains("mod_id")) { c.set("mod_id", cfg.modId); dirty = true; }
            if (!c.contains("features.bait_required")) { c.set("features.bait_required", cfg.features.baitRequired); dirty = true; }
            if (!c.contains("features.bait_usage_percent")) { c.set("features.bait_usage_percent", cfg.features.baitUsagePercent); dirty = true; }

            // NEW defaults
            if (!c.contains("features.weighted_fish_enabled")) {
                c.set("features.weighted_fish_enabled", cfg.features.weightedFishEnabled);
                dirty = true;
            }
            if (!c.contains("features.weight_unit")) {
                c.set("features.weight_unit", cfg.features.weightUnit);
                dirty = true;
            }
            if (!c.contains("features.fish_weight_bias")) {
                c.set("features.fish_weight_bias", cfg.features.fishWeightBias);
                dirty = true;
            }

//            if (!c.contains("logging.level")) { c.set("logging.level", cfg.logging.level); dirty = true; }
//            if (!c.contains("logging.to_file")) { c.set("logging.to_file", cfg.logging.toFile); dirty = true; }
//            if (!c.contains("logging.file_path")) { c.set("logging.file_path", cfg.logging.filePath); dirty = true; }
//        if (!c.contains("runtime.hot_reload")) { c.set("runtime.hot_reload", cfg.runtime.hotReload); dirty = true; }
//        if (!c.contains("runtime.reload_interval_seconds")) { c.set("runtime.reload_interval_seconds", cfg.runtime.reloadIntervalSeconds); dirty = true; }

            if (dirty) c.save();

            return cfg;
        }
    }

    private static void validate(ModConfig cfg) {
        // Fail fast or clamp with warning
        int p = cfg.features.baitUsagePercent;
        if (p < 0 || p > 100) {
            // Clamp to safe value
            YouNeedBait.LOGGER.warn("Clamping features.bait_usage_percent {} to [0,100]", p);
            cfg.features.baitUsagePercent = Math.max(0, Math.min(100, p));
        }
    }
}