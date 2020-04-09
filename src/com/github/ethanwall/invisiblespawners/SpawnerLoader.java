package com.github.ethanwall.invisiblespawners;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

public class SpawnerLoader {
	
	private SpawnerManager spawnerManager;
	private YamlConfiguration config;
	
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
	
	public void saveSpawner(String name, EntityType mob, Location spawnerLocation, int range, int numberOfSpawns, long interval) {
		String strMob = mob.name();
		double x = spawnerLocation.getX();
		double y = spawnerLocation.getY();
		double z = spawnerLocation.getZ();
		String world = spawnerLocation.getWorld().getName();
		
		ConfigurationSection section = config.createSection(name);
		
		section.set("entity", strMob);
		section.createSection("location");
		section.set("location.x", x);
		section.set("location.x", y);
		section.set("location.x", z);
		section.set("location.world", world);
		section.set("range", range);
		section.set("interval", interval);
		section.set("spawnCount", numberOfSpawns);
	}
	
	public SpawnerLoader(SpawnerManager spawnerManager, YamlConfiguration config) {
		this.spawnerManager = spawnerManager;
		this.config = config;
	}
	
}
