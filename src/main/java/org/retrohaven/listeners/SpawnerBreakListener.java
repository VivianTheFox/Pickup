package org.retrohaven.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class SpawnerBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (block.getType() == Material.MOB_SPAWNER) {
            if (player.getItemInHand().getType() == Material.GOLD_PICKAXE) {
                if (player.hasPermission("pickup.use")) {
                    useTool(player); // Decrease tool durability
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.MOB_SPAWNER, 1));
                }
            }
        }
    }

    public void useTool(Player player) {
        if(player.getItemInHand().getDurability() > 31) {
            player.setItemInHand(null);
        }
        player.getItemInHand().setDurability((short)(player.getItemInHand().getDurability() + 1));
    }
}
