package com.github.ethanwall.invisiblespawners;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ethanwall.invisiblespawners.commands.CreateSpawnerCommand;
import com.github.ethanwall.invisiblespawners.commands.RemoveSpawnerCommand;
import com.github.ethanwall.invisiblespawners.completers.CreateSpawnerCommandTabCompleter;
import com.github.ethanwall.invisiblespawners.completers.RemoveSpawnerCommandTabCompleter;

public class InvisibleSpawners extends JavaPlugin {

	PluginDescriptionFile pdf;

	@Override
	public void onEnable() {
		pdf = getDescription();
		getLogger().info(pdf.getFullName() + " has been enabled!");
		
		SpawnerManager manager = new SpawnerManager(this);
		
		getCommand("createspawner").setExecutor(new CreateSpawnerCommand(manager));
		getCommand("removespawner").setExecutor(new RemoveSpawnerCommand(manager));
		
		getCommand("createspawner").setTabCompleter(new CreateSpawnerCommandTabCompleter());
		getCommand("removespawner").setTabCompleter(new RemoveSpawnerCommandTabCompleter(manager));
	}

	@Override
	public void onDisable() {
		getLogger().info(pdf.getFullName() + " has been disabled!");
	}
	
}
