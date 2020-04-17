package com.github.ethanwall.invisiblespawners;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ethanwall.invisiblespawners.commands.AddPotionEffectCommand;
import com.github.ethanwall.invisiblespawners.commands.CreateSpawnerCommand;
import com.github.ethanwall.invisiblespawners.commands.ListSpawnersCommand;
import com.github.ethanwall.invisiblespawners.commands.RemovePotionEffectCommand;
import com.github.ethanwall.invisiblespawners.commands.RemoveSpawnerCommand;
import com.github.ethanwall.invisiblespawners.completers.AddPotionEffectCommandTabCompleter;
import com.github.ethanwall.invisiblespawners.completers.CreateSpawnerCommandTabCompleter;
import com.github.ethanwall.invisiblespawners.completers.RemovePotionEffectCommandTabCompleter;
import com.github.ethanwall.invisiblespawners.completers.RemoveSpawnerCommandTabCompleter;
import com.github.ethanwall.invisiblespawners.datahandlers.ConfigurationLoader;
import com.github.ethanwall.invisiblespawners.datahandlers.SpawnerLoader;

public class InvisibleSpawners extends JavaPlugin {

	public SpawnerLoader loader;
	
	PluginDescriptionFile pdf;
	
	private YamlConfiguration spawnersConfig;
	
	@Override
	public void onEnable() {
		pdf = getDescription();
		getLogger().info(pdf.getFullName() + " has been enabled!");
		
		SpawnerManager manager = new SpawnerManager(this);
		
		// Load configs
		int successfullyLoadedConfigs = ConfigurationLoader.loadConfigs(this);
		HashMap<String, YamlConfiguration> configs = ConfigurationLoader.configs;
		getLogger().info(String.format(Messages.CONFIGS_LOADED_MESSAGE, successfullyLoadedConfigs, configs.size()));
		
		spawnersConfig = configs.get("spawners.yml");
		
		// Load spawners
		loader = new SpawnerLoader(manager, spawnersConfig);
		Collection<Spawner> loadedSpawners = loader.loadAllSpawners();
		getLogger().info(String.format(Messages.SPAWNERS_LOADED_MESSAGE, loadedSpawners.size()));
		
		// Register commands
		getCommand("createspawner").setExecutor(new CreateSpawnerCommand(manager));
		getCommand("removespawner").setExecutor(new RemoveSpawnerCommand(manager));
		
		getCommand("createspawner").setTabCompleter(new CreateSpawnerCommandTabCompleter());
		getCommand("removespawner").setTabCompleter(new RemoveSpawnerCommandTabCompleter(manager));
		
		getCommand("listspawners").setExecutor(new ListSpawnersCommand(manager));
		
		getCommand("addspawnerpotioneffect").setExecutor(new AddPotionEffectCommand(manager));
		getCommand("addspawnerpotioneffect").setTabCompleter(new AddPotionEffectCommandTabCompleter(manager));
		
		getCommand("removespawnerpotioneffect").setExecutor(new RemovePotionEffectCommand(manager));
		getCommand("removespawnerpotioneffect").setTabCompleter(new RemovePotionEffectCommandTabCompleter(manager));
	}

	@Override
	public void onDisable() {
		try {
			spawnersConfig.save(new File(getDataFolder(), "spawners.yml"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		getLogger().info(pdf.getFullName() + " has been disabled!");
	}
	
}
