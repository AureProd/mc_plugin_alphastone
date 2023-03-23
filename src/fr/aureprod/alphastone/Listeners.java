
//       _                  ____                _ 
//      / \  _   _ _ __ ___|  _ \ _ __ ___   __| |
//     / _ \| | | | '__/ _ \ |_) | '__/ _ \ / _` |
//    / ___ \ |_| | | |  __/  __/| | | (_) | (_| |
//   /_/   \_\__,_|_|  \___|_|   |_|  \___/ \__,_|
//                                                 

package fr.aureprod.alphastone;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Listeners implements Listener 
{
	private Main main;
	
	public Listeners(Main mainbis)
	{
		this.main = mainbis;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onclic(PlayerInteractEvent event) 
	{
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack item = event.getItem();
		
		if(item == null) return;
		
		for (String string : main.getConfig().getConfigurationSection("kits").getKeys(false)) 
		{
			if (item.getItemMeta().getLocalizedName().equals(string) && item.getType().equals(Material.getMaterial(main.getConfig().getConfigurationSection("kits." + string).getString("block")))) 
			{
				if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) 
				{
					player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
					
					for (String stringbis : main.getConfig().getConfigurationSection("kits." + string + ".items").getKeys(false)) 
					{
						if (main.getConfig().getConfigurationSection("kits." + string + ".items." + stringbis).get("enchants") == null) 
						{
							String stringbisbis = stringbis.toUpperCase();
						
							ItemStack customEmeraude = new ItemStack(Material.getMaterial(stringbisbis), main.getConfig().getConfigurationSection("kits." + string + ".items." + stringbis).getInt("count"));
						
							player.getInventory().addItem(customEmeraude);
							player.updateInventory();
						} 
						else 
						{
							String stringbisbis = stringbis.toUpperCase();
							
							ItemStack customEmeraude = new ItemStack(Material.getMaterial(stringbisbis), main.getConfig().getConfigurationSection("kits." + string + ".items." + stringbis).getInt("count"));
							ItemMeta customE = customEmeraude.getItemMeta();
							
							for (String stringters : main.getConfig().getConfigurationSection("kits." + string + ".items." + stringbis + ".enchants").getKeys(false)) 
							{
								customE.addEnchant(Enchantment.getByName(stringters), main.getConfig().getConfigurationSection("kits." + string + ".items." + stringbis + ".enchants").getInt(stringters), true);
							}
							
							customEmeraude.setItemMeta(customE);
						
							player.getInventory().addItem(customEmeraude);
							player.updateInventory();
						}
					}
					player.sendMessage(main.getConfig().getConfigurationSection("kits." + string).getString("ouverture_recompence"));
				}
				
				event.setCancelled(true);
				return;
			}
		}
		
	}
}
