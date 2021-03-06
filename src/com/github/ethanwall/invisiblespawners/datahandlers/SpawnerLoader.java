package com.github.ethanwall.invisiblespawners.datahandlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.ethanwall.invisiblespawners.Spawner;
import com.github.ethanwall.invisiblespawners.SpawnerManager;

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
		
		ConfigurationSection potionEffectsSection = section.getConfigurationSection("effects");
		ArrayList<PotionEffect> potionEffects = new ArrayList<PotionEffect>();
		for (String key: potionEffectsSection.getKeys(false)) {
			ConfigurationSection effectSection = potionEffectsSection.getConfigurationSection("key");
			PotionEffectType effectType = PotionEffectType.getByName(key);
			int duration = effectSection.getInt("duration");
			int amplifier = effectSection.getInt("amplifier");
			
			// IF any of the variables are NULL or 0, THEN continue
			if (Stream.of(effectType, duration, amplifier).anyMatch(Arrays.asList(null, 0)::contains))
				continue;
			
			PotionEffect potionEffect = new PotionEffect(effectType, duration, amplifier);
			potionEffects.add(potionEffect);
		}
		
		Spawner spawner = spawnerManager.createSpawner(name, entity, location, range, spawnCount, interval, potionEffects);
		return spawner;
	}
	
	public Collection<Spawner> loadAllSpawners() {
		ArrayList<Spawner> spawners = new ArrayList<Spawner>();
		
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
