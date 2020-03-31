package com.github.ethanwall.invisiblespawners;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

public class SpawnerCreator {

	Plugin plugin;
	
	public void createSpawner(EntityType mob, Location spawnerLocation, int range, int numberOfSpawns, long interval) {
		new Spawner(mob, spawnerLocation, range, numberOfSpawns).runTaskTimer(plugin, 0L, interval);
	}
	
	public SpawnerCreator(Plugin plugin) {
		this.plugin = plugin;
	}
	
}
