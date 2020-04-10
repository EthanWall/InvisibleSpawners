package com.github.ethanwall.invisiblespawners;

import java.util.Collection;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffect;

public class SpawnerManager {

	public HashMap<String, Spawner> spawners = new HashMap<>();
	
	private InvisibleSpawners plugin;
	
	public Spawner createSpawner(String name, EntityType mob, Location spawnerLocation, int range, int numberOfSpawns, long interval, Collection<PotionEffect> effects) {
		Spawner spawner = new Spawner(mob, spawnerLocation, range, numberOfSpawns, effects);
		spawner.runTaskTimer(plugin, 0L, interval);
		spawners.put(name, spawner);
		plugin.loader.saveSpawner(name, mob, spawnerLocation, range, numberOfSpawns, interval);
		
		return spawner;
	}
	
	public Spawner removeSpawner(String name) {
		Spawner spawner = spawners.get(name);
		spawner.cancel();
		spawners.remove(name);
		
		return spawner;
	}
	
	public SpawnerManager(InvisibleSpawners plugin) {
		this.plugin = plugin;
	}
	
}
