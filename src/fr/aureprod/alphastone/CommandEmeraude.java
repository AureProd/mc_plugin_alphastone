
//       _                  ____                _ 
//      / \  _   _ _ __ ___|  _ \ _ __ ___   __| |
//     / _ \| | | | '__/ _ \ |_) | '__/ _ \ / _` |
//    / ___ \ |_| | | |  __/  __/| | | (_) | (_| |
//   /_/   \_\__,_|_|  \___|_|   |_|  \___/ \__,_|
//                                                 

package fr.aureprod.alphastone;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandEmeraude implements CommandExecutor 
{
	private Main main;
	
	public CommandEmeraude(Main mainbis)
	{
		this.main = mainbis;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) 
	{
		if (cmd.getName().equalsIgnoreCase("give_kit"))
		{
			if (sender instanceof Player) 
			{
				if (args.length == 1)
				{
					for (String string : main.getConfig().getConfigurationSection("kits").getKeys(false)) 
					{
						if (args[0].equalsIgnoreCase(string)) 
						{
							Player player = (Player) sender;
							
							ItemStack customEmeraude = new ItemStack(Material.getMaterial(main.getConfig().getConfigurationSection("kits." + string).getString("block")), 1);
							ItemMeta customE = customEmeraude.getItemMeta();
				
							customE.setDisplayName(main.getConfig().getConfigurationSection("kits." + string).getString("name"));
							customE.setLocalizedName(string);
							customE.setUnbreakable(true);
							customE.addEnchant(Enchantment.THORNS, 1, true);
							customE.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							customE.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
							customEmeraude.setItemMeta(customE);
				
							player.getInventory().addItem(customEmeraude);
							player.updateInventory();
							
							player.sendMessage(ChatColor.YELLOW + "Vous recevez l'item pour le kit " + string);
							
							return true;
						}
					}
					
					sender.sendMessage(ChatColor.RED + "Mauvais arguments dans la commande !!");
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "Il faut mettre 1 seul arguments dans cette commande !!");
				}
			}
			else
			{
				System.out.println("Cette commande n'est pas accessible via la console !!");
			}
		}
		else if (cmd.getName().equalsIgnoreCase("give_kit_liste"))
		{
			if (sender instanceof Player) 
			{
				if (args.length == 0)
				{
					Player player = (Player) sender;
					StringBuilder liste = new StringBuilder();
					
					for (String string : main.getConfig().getConfigurationSection("kits").getKeys(false)) 
					{
						liste.append(string + " ");
					}
					
					player.sendMessage(ChatColor.YELLOW + "" + liste);
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "Il faut mettre 1 seul arguments dans cette commande !!");
				}
			}
			else
			{
				System.out.println("Cette commande n'est pas accessible via la console !!");
			}
		}
		else if (cmd.getName().equalsIgnoreCase("give_kit_player"))
		{
			if (args.length == 2)
			{
				for (Player player : main.getServer().getOnlinePlayers()) 
				{
					if (args[0].equalsIgnoreCase(player.getName())) 
					{
						for (String string : main.getConfig().getConfigurationSection("kits").getKeys(false)) 
						{					
							if (args[1].equalsIgnoreCase(string)) 
							{							
								ItemStack customEmeraude = new ItemStack(Material.getMaterial(main.getConfig().getConfigurationSection("kits." + string).getString("block")), 1);
								ItemMeta customE = customEmeraude.getItemMeta();
					
								customE.setDisplayName(main.getConfig().getConfigurationSection("kits." + string).getString("name"));
								customE.setLocalizedName(string);
								customE.setUnbreakable(true);
								customE.addEnchant(Enchantment.THORNS, 1, true);
								customE.addItemFlags(ItemFlag.HIDE_ENCHANTS);
								customE.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
								customEmeraude.setItemMeta(customE);
					
								player.getInventory().addItem(customEmeraude);
								player.updateInventory();
								
								player.sendMessage(ChatColor.YELLOW + "Vous recevez l'item pour le kit " + string);
								
								return true;
							}
						}
						
						sender.sendMessage(ChatColor.RED + "Mauvais arguments dans la commande !!!");
					}
				}
				
				if (sender instanceof Player) {
					sender.sendMessage(ChatColor.RED + "Le nome du joueur rentré n'est pas valide !!");
				}
				else
				{
					System.out.println("Le nome du joueur rentré n'est pas valide !!");
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "Il faut mettre 2 seul arguments dans cette commande !!");
			}
		}
		else
		{
			System.out.println("Cette commande n'est pas accessible via la console !!");
		}
		
		return false;
	}

}

