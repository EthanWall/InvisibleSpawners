package com.github.ethanwall.invisiblespawners;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ethanwall.invisiblespawners.commands.InvisibleSpawnerCommand;

public class InvisibleSpawners extends JavaPlugin {

	PluginDescriptionFile pdf;

	@Override
	public void onEnable() {
		pdf = getDescription();
		getLogger().info(pdf.getFullName() + " has been enabled!");
		
		getCommand("invisiblespawners").setExecutor(new InvisibleSpawnerCommand());
	}

	@Override
	public void onDisable() {
		getLogger().info(pdf.getFullName() + " has been disabled!");
	}
	
}
