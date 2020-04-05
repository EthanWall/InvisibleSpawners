package com.github.ethanwall.invisiblespawners;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

public class SpawnerManager {

	public Map<String, Spawner> spawners;
	
	Plugin plugin;
	
	public void createSpawner(String name, EntityType mob, Location spawnerLocation, int range, int numberOfSpawns, long interval) {
		Spawner spawner = (Spawner) new Spawner(mob, spawnerLocation, range, numberOfSpawns).runTaskTimer(plugin, 0L, interval);
		spawners.put(name, spawner);
	}
	
	public void removeSpawner(String name) {
		spawners.remove(name);
	}
	
	public SpawnerManager(Plugin plugin) {
		this.plugin = plugin;
	}
	
}
