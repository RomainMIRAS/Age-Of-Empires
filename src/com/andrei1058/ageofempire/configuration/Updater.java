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
                HttpURLConnection checkUpdate = (HttpURLConnection)new URL("http://www.spigotmc.org/api/general.php").openConnection();
                checkUpdate.setDoOutput(true);
                checkUpdate.setRequestMethod("POST");
                checkUpdate.getOutputStream().write("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=39573".getBytes());
                String old = plugin.getDescription().getVersion();
                String newV = new BufferedReader(new InputStreamReader(checkUpdate.getInputStream())).readLine().replaceAll("[a-zA-Z ]", "");
                if (!newV.equalsIgnoreCase(old)) {
                    updateAvailable = true;
                    newVersion = newV;
                    plugin.getLogger().info("--------------------------------------------");
                    plugin.getLogger().info("");
                    plugin.getLogger().info("There is a nev version available!");
                    plugin.getLogger().info("Please update it! =)");
                    plugin.getLogger().info("https://www.spigotmc.org/resources/39573/");
                    plugin.getLogger().info("");
                    plugin.getLogger().info("--------------------------------------------");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        , 30);
    }
}

