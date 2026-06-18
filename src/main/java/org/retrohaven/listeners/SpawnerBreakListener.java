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
        PlayerItemDamageEvent damageEvent = new PlayerItemDamageEvent(player, player.getItemInHand(), 1);

        if (block.getType() == Material.MOB_SPAWNER) {
            if (player.getItemInHand().getType() == Material.GOLD_PICKAXE) {
                if (player.hasPermission("pickup.use")) {
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    damageEvent.setDamage(damageEvent.getDamage() - 1); // Decrease durability by 1
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.MOB_SPAWNER, 1));
                }
            }
        }
    }
}
