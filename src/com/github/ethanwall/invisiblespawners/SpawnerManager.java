package com.github.ethanwall.invisiblespawners;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

public class SpawnerManager {

	public HashMap<String, Spawner> spawners = new HashMap<>();
	
	Plugin plugin;
	
	public Spawner createSpawner(String name, EntityType mob, Location spawnerLocation, int range, int numberOfSpawns, long interval) {
		Spawner spawner = new Spawner(mob, spawnerLocation, range, numberOfSpawns);
		spawner.runTaskTimer(plugin, 0L, interval);
		spawners.put(name, spawner);
		
		return spawner;
	}
	
	public Spawner removeSpawner(String name) {
		Spawner spawner = spawners.get(name);
		spawner.cancel();
		spawners.remove(name);
		
		return spawner;
	}
	
	public SpawnerManager(Plugin plugin) {
		this.plugin = plugin;
	}
	
}
