package com.github.ethanwall.invisiblespawners;

import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import io.netty.util.internal.ThreadLocalRandom;

public class Spawner extends BukkitRunnable {

	public EntityType mobType;
	public Location spawnerLocation;
	public int range;
	public int numberOfSpawns;
	public Collection<PotionEffect> effects;
	public ItemStack[] armor;
	
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
			
			if (!(mob instanceof LivingEntity))
				return;
			LivingEntity livingMob = (LivingEntity) mob;
			
			livingMob.addPotionEffects(effects);
			
			EntityEquipment equipment = livingMob.getEquipment();
			equipment.setArmorContents(armor);
		}
	}

	public Spawner(EntityType mobType, Location spawnerLocation, int range, int numberOfSpawns, Collection<PotionEffect> effects, ItemStack[] armor) {
		this.mobType = mobType;
		this.spawnerLocation = spawnerLocation;
		this.range = range;
		this.numberOfSpawns = numberOfSpawns;
		this.effects = effects;
		this.armor = armor;
	}
}
