
//       _                  ____                _ 
//      / \  _   _ _ __ ___|  _ \ _ __ ___   __| |
//     / _ \| | | | '__/ _ \ |_) | '__/ _ \ / _` |
//    / ___ \ |_| | | |  __/  __/| | | (_) | (_| |
//   /_/   \_\__,_|_|  \___|_|   |_|  \___/ \__,_|
//                                                 

package fr.aureprod.alphastone;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin 
{
	@Override
	public void onEnable() 
	{
		saveDefaultConfig();
		
		System.out.println("Le plugin AlphaStone est est demarre !!!");
		getCommand("give_kit").setExecutor(new CommandEmeraude(this));
		getCommand("give_kit_liste").setExecutor(new CommandEmeraude(this));
		getCommand("give_kit_player").setExecutor(new CommandEmeraude(this));
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
	}
	
	@Override
	public void onDisable() 
	{
		System.out.println("Le plugin AlphaStone est arreter !!!");
	}	
}
