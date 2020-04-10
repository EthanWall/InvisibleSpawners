package com.github.ethanwall.invisiblespawners.datahandlers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigurationLoader {

	public static HashMap<String, YamlConfiguration> configs = new HashMap<String, YamlConfiguration>();
	
	private static final String[] CONFIG_FILE_NAMES = {"spawners.yml"};
	
	public static int loadConfigs(JavaPlugin plugin) {
		int successfullyLoadedConfigs = 0;
		
		for (String name : CONFIG_FILE_NAMES) {
			File customConfigFile = new File(plugin.getDataFolder(), name);
			if (!customConfigFile.exists()) {
				customConfigFile.getParentFile().mkdirs();
				plugin.saveResource(name, false);
			}
			
			YamlConfiguration customConfig = new YamlConfiguration();
			try {
				customConfig.load(customConfigFile);
			}
			catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
				continue;
			}
			
			configs.put(name, customConfig);
			successfullyLoadedConfigs++;
		}
		
		return successfullyLoadedConfigs;
	}
	
}
