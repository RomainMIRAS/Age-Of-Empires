package com.andrei1058.ageofempire.listeners;

import com.andrei1058.ageofempire.game.Status;
import com.andrei1058.ageofempire.locations.Region;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static com.andrei1058.ageofempire.Main.*;
import static com.andrei1058.ageofempire.configuration.Messages.getMsg;
import static com.andrei1058.ageofempire.game.Buildings.construct_in_inv;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void b(BlockPlaceEvent e){
        if (SETUP) return;
        if (STATUS == Status.PLAYING)
            switch (e.getBlock().getType()){
                case SPRUCE_DOOR:
                    if (construct_in_inv.containsKey(e.getPlayer())){
                        Region.check(e.getBlock().getLocation(), e.getPlayer());
                    }
                    e.setCancelled(true);
                    break;
                case GRASS:
                case DIRT:
                case WOOD_DOUBLE_STEP:
                case SAND:
                case GRAVEL:
                case SPONGE:
                case GLASS:
                case LAPIS_BLOCK:
                case WOOL:
                case BRICK:
                case MOSSY_COBBLESTONE:
                case LEAVES:
                case LEAVES_2:
                case WEB:
                case TORCH:
                    if (Region.place(e.getBlock().getLocation())) {
                        placedBlocks.add(e.getBlock());
                    } else {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(getMsg("cant-place-here"));
                    }
                    break;
                case FLINT_AND_STEEL:
                    if (Region.place(e.getBlock().getLocation())) {
                        e.setCancelled(false);
                    } else {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(getMsg("cant-place-here"));
                    }
                    break;
                default:
                    e.setCancelled(true);
                    break;
            }
    }
}
