package com.github.ethanwall.invisiblespawners;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

public class SpawnerManager {

	public HashMap<String, Spawner> spawners = new HashMap<>();
	
	Plugin plugin;
	
	public void createSpawner(String name, EntityType mob, Location spawnerLocation, int range, int numberOfSpawns, long interval) {
		Spawner spawner = new Spawner(mob, spawnerLocation, range, numberOfSpawns);
		spawner.runTaskTimer(plugin, 0L, interval);
		spawners.put(name, spawner);
	}
	
	public void removeSpawner(String name) {
		spawners.get(name).cancel();
		spawners.remove(name);
	}
	
	public SpawnerManager(Plugin plugin) {
		this.plugin = plugin;
	}
	
}
