package com.github.ethanwall.invisiblespawners;

import java.util.Collection;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class SpawnerManager {

	public HashMap<String, Spawner> spawners = new HashMap<>();
	
	private InvisibleSpawners plugin;
	
	public Spawner createSpawner(String name, EntityType mob, Location spawnerLocation, int range, int numberOfSpawns, long interval, Collection<PotionEffect> effects, ItemStack[] armor) {
		Spawner spawner = new Spawner(mob, spawnerLocation, range, numberOfSpawns, effects, armor);
		spawner.runTaskTimer(plugin, 0L, interval);
		spawners.put(name, spawner);
		saveSpawner(name, mob, spawnerLocation, range, numberOfSpawns, interval, effects);
		
		return spawner;
	}
	
	public Spawner removeSpawner(String name) {
		Spawner spawner = spawners.get(name);
		spawner.cancel();
		spawners.remove(name);
		
		return spawner;
	}
	
	public void saveSpawner(String name, EntityType mob, Location spawnerLocation, int range, int numberOfSpawns, long interval, Collection<PotionEffect> effects) {
		plugin.loader.saveSpawner(name, mob, spawnerLocation, range, numberOfSpawns, interval, effects);
	}
	
	public void editSpawner(String name, String path, Object value) {
		plugin.loader.editSpawner(name, path, value);
	}
	
	public SpawnerManager(InvisibleSpawners plugin) {
		this.plugin = plugin;
	}
	
}
