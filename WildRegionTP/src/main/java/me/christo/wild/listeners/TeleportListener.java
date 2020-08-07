package me.christo.wild.listeners;


import me.christo.wild.Main;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Random;


/**
 * Copy Right ©
 * This code is private
 * Owner: Christo
 * From: 10/22/19-2023
 * Any attempts to use these program(s) may result in a penalty of up to $1,000 USD
 **/

public class TeleportListener implements Listener {

    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event)
    {
        Player player = Bukkit.getPlayer(event.getUUID());
        if (player == null) return;

        String regionName = event.getRegionName();
        if(regionName.equalsIgnoreCase(Main.getInstance().getConfig().getString("Region"))) {



            Location portalloc = player.getLocation();
            int range = Main.getInstance().getConfig().getInt("TeleportRange");
            Location maxLocX = portalloc.clone().add(range,0,0);
            Location minLocX = portalloc.clone().subtract(range,0,0);

            Location maxLocZ = portalloc.clone().add(0,0,range);
            Location minLocZ = portalloc.clone().subtract(0,0,range);
            int i = 0;
            do {
                int randomX = randInt(minLocX.getBlockX(), maxLocX.getBlockX());
                int randomZ = randInt(minLocZ.getBlockZ(), maxLocZ.getBlockZ());
                for (int n = 0; n < 255; n++) {
                    Random ran = new Random();
                    ArrayList<String> worlds = (ArrayList<String>) Main.getInstance().getConfig().get("WorldList");
                    World world = Bukkit.getServer().getWorld(worlds.get(ran.nextInt(worlds.size())));
                    Location newLoc = new Location(world, randomX, n, randomZ);
                    Block block = newLoc.getBlock();
                    Block blockUnder = newLoc.clone().subtract(0,1,0).getBlock();
                    Block blockAbove = newLoc.clone().add(0,1,0).getBlock();
                    if (block.getType().equals(Material.AIR)) {
                        if (!blockUnder.getType().equals(Material.AIR) && !blockUnder.getType().equals(Material.LAVA)
                                && !blockUnder.getType().equals(Material.WATER)) {
                            if (blockAbove.getType().equals(Material.AIR)) {
                                player.teleport(newLoc);
                                i++;
                            }
                        }
                    }
                }
            } while (i == 0);
        }
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}


