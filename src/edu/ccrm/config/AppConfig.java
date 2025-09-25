
package edu.ccrm.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public final class AppConfig {
    private static AppConfig instance;
    private final Path dataFolder;
    private final LocalDateTime startedAt;

    private AppConfig() {
        this.dataFolder = Paths.get(System.getProperty("user.dir"), "ccrm_data");
        this.startedAt = LocalDateTime.now();
    }

    public static synchronized AppConfig getInstance() {
        if (instance == null) instance = new AppConfig();
        return instance;
    }

    public Path getDataFolder() { return dataFolder; }
    public LocalDateTime getStartedAt() { return startedAt; }
}
