package com.github.ethanwall.invisiblespawners;

import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ethanwall.invisiblespawners.commands.CreateSpawnerCommand;
import com.github.ethanwall.invisiblespawners.commands.ListSpawnersCommand;
import com.github.ethanwall.invisiblespawners.commands.RemoveSpawnerCommand;
import com.github.ethanwall.invisiblespawners.completers.CreateSpawnerCommandTabCompleter;
import com.github.ethanwall.invisiblespawners.completers.RemoveSpawnerCommandTabCompleter;

public class InvisibleSpawners extends JavaPlugin {

	public SpawnerLoader loader;
	
	PluginDescriptionFile pdf;
	
	@Override
	public void onEnable() {
		pdf = getDescription();
		getLogger().info(pdf.getFullName() + " has been enabled!");
		
		SpawnerManager manager = new SpawnerManager(this);
		
		// Load configs
		int successfullyLoadedConfigs = ConfigurationLoader.loadConfigs(this);
		HashMap<String, YamlConfiguration> configs = ConfigurationLoader.configs;
		getLogger().info(String.format(Messages.CONFIGS_LOADED_MESSAGE, successfullyLoadedConfigs, configs.size()));
		
		YamlConfiguration spawnersConfig = configs.get("spawners.yml");
		
		// Load spawners
		loader = new SpawnerLoader(manager, spawnersConfig);
		loader.loadAllSpawners();
		
		// Register commands
		getCommand("createspawner").setExecutor(new CreateSpawnerCommand(manager));
		getCommand("removespawner").setExecutor(new RemoveSpawnerCommand(manager));
		
		getCommand("createspawner").setTabCompleter(new CreateSpawnerCommandTabCompleter());
		getCommand("removespawner").setTabCompleter(new RemoveSpawnerCommandTabCompleter(manager));
		
		getCommand("listspawners").setExecutor(new ListSpawnersCommand(manager));
	}

	@Override
	public void onDisable() {
		getLogger().info(pdf.getFullName() + " has been disabled!");
	}
	
}
