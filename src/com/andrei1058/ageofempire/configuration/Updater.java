package com.andrei1058.ageofempire.configuration;

import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.andrei1058.ageofempire.Main.plugin;

public class Updater {
    public static boolean updateAvailable = false;
    public static String newVersion = "";

    public static void checkUpdates() {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            try {
                HttpURLConnection checkUpdate = (HttpURLConnection)new URL("https://api.spigotmc.org/legacy/update.php?resource=39573").openConnection();
                checkUpdate.setDoOutput(true);
                String old = plugin.getDescription().getVersion();
                String newV = new BufferedReader(new InputStreamReader(checkUpdate.getInputStream())).readLine();
                if (!newV.equalsIgnoreCase(old)) {
                    updateAvailable = true;
                    newVersion = newV;
                    plugin.getLogger().info("------------------------------------");
                    plugin.getLogger().info(" ");
                    plugin.getLogger().info("There is a nev version available!");
                    plugin.getLogger().info("Version: "+newVersion);
                    plugin.getLogger().info(" ");
                    plugin.getLogger().info("https://www.spigotmc.org/resources/39573/");
                    plugin.getLogger().info("------------------------------------");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        , 30);
    }
}

