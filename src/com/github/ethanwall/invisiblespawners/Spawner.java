package com.github.ethanwall.invisiblespawners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.tr7zw.nbtapi.NBTEntity;
import io.netty.util.internal.ThreadLocalRandom;

public class Spawner extends BukkitRunnable {

	EntityType mobType;
	Location spawnerLocation;
	int range;
	int numberOfSpawns;
	
	@Override
	public void run() {
		boolean found = false;
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getLocation().distance(spawnerLocation) <= range) {
				found = true;
				break;
			}
		}
		if (!found)
			return;
		
		for (int i = 0; i < numberOfSpawns; i++) {
			int x = ThreadLocalRandom.current().nextInt(-range, range + 1);
			int z = ThreadLocalRandom.current().nextInt(-range, range + 1);
			Location mobLocation = new Location(spawnerLocation.getWorld(), spawnerLocation.getX() + x, spawnerLocation.getY(), spawnerLocation.getZ() + z);
			
			Entity mob = mobLocation.getWorld().spawnEntity(mobLocation, mobType);
			
			NBTEntity nbtMob = new NBTEntity(mob);
		}
	}

	public Spawner(EntityType mobType, Location spawnerLocation, int range, int numberOfSpawns) {
		this.mobType = mobType;
		this.spawnerLocation = spawnerLocation;
		this.range = range;
		this.numberOfSpawns = numberOfSpawns;
	}
	
}
