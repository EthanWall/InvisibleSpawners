package com.github.ethanwall.invisiblespawners;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

public class SpawnerLoader {
	
	SpawnerManager spawnerManager;
	YamlConfiguration config;
	
	public Spawner loadSpawner(String name) {
		ConfigurationSection section = config.getConfigurationSection(name);
		
		EntityType entity = EntityType.valueOf(section.getString("entity"));
		Location location = new Location(Bukkit.getWorld(section.getString("location.world")), 
				section.getDouble("location.x"), section.getDouble("location.y"), section.getDouble("location.z"));
		int range = section.getInt("range");
		long interval = section.getLong("interval");
		int spawnCount = section.getInt("spawnCount");
		
		Spawner spawner = spawnerManager.createSpawner(name, entity, location, range, spawnCount, interval);
		return spawner;
	}
	
	public Collection<Spawner> loadAllSpawners() {
		Collection<Spawner> spawners = new ArrayList<Spawner>();
		
		for (String key : config.getKeys(false)) {
			spawners.add(loadSpawner(key));
		}
		
		return spawners;
	}
	
	public SpawnerLoader(SpawnerManager spawnerManager, YamlConfiguration config) {
		this.spawnerManager = spawnerManager;
		this.config = config;
	}
	
}
