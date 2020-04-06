package com.github.ethanwall.invisiblespawners.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.github.ethanwall.invisiblespawners.Messages;
import com.github.ethanwall.invisiblespawners.SpawnerManager;

public class CreateSpawnerCommand implements CommandExecutor {

	private SpawnerManager spawnerManager;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("invisiblespawners.remove")) {
			sender.sendMessage(Messages.NO_PERMISSION_MESSAGE);
			return true;
		}
		switch (args.length) {
		case 9:
			return execute(args[0], args[1], args[5], args[6], args[7], args[8], args[2], args[3], args[4]);
		case 8:
			return execute(sender, args[0], args[1], args[5], args[6], args[7], args[2], args[3], args[4]);
		case 5:
			return execute(sender, args[0], args[1], args[2], args[3], args[4]);
		default:
			break;
		}
		return false;
	}

	private boolean execute(String name, EntityType mobType, Location spawnerLocation, int range, int numberOfSpawns, long interval) {
		spawnerManager.createSpawner(name, mobType, spawnerLocation, range, numberOfSpawns, interval);
		return true;
	}
	
	private boolean execute(String name, String mobType, String x, String y, String z, String world, String range, String numberOfSpawns, String interval) {
		EntityType convMobType;
		double convX;
		double convY;
		double convZ;
		World convWorld;
		Location convLocation;
		int convRange;
		int convNumberOfSpawns;
		long convInterval;
		try {
			convMobType = EntityType.valueOf(mobType);
			convX = Double.parseDouble(x);
			convY = Double.parseDouble(y);
			convZ = Double.parseDouble(z);
			convWorld = Bukkit.getWorld(world);
			convLocation = new Location(convWorld, convX, convY, convZ);
			convRange = Integer.parseInt(range);
			convNumberOfSpawns = Integer.parseInt(numberOfSpawns);
			convInterval = Integer.parseInt(interval);
		}
		catch (IllegalArgumentException e) {
			return false;
		}
		return execute(name, convMobType, convLocation, convRange, convNumberOfSpawns, convInterval);
	}
	
	private boolean execute(CommandSender sender, String name, String mobType, String x, String y, String z, String range, String numberOfSpawns, String interval) {
		if (!(sender instanceof Player))
			return false;
		Player player;
		EntityType convMobType;
		double convX;
		double convY;
		double convZ;
		Location convLocation;
		int convRange;
		int convNumberOfSpawns;
		long convInterval;
		try {
			player = (Player) sender;
			convMobType = EntityType.valueOf(mobType);
			convX = Double.parseDouble(x);
			convY = Double.parseDouble(y);
			convZ = Double.parseDouble(z);
			convLocation = new Location(player.getWorld(), convX, convY, convZ);
			convRange = Integer.parseInt(range);
			convNumberOfSpawns = Integer.parseInt(numberOfSpawns);
			convInterval = Integer.parseInt(interval);
		}
		catch (IllegalArgumentException e) {
			return false;
		}
		return execute(name, convMobType, convLocation, convRange, convNumberOfSpawns, convInterval);
	}
	
	
	private boolean execute(CommandSender sender, String name, String mobType, String range, String numberOfSpawns, String interval) {
		if (!(sender instanceof Player))
			return false;
		Player player;
		EntityType convMobType;
		int convRange;
		int convNumberOfSpawns;
		long convInterval;
		try {
			player = (Player) sender;
			convMobType = EntityType.valueOf(mobType);
			convRange = Integer.parseInt(range);
			convNumberOfSpawns = Integer.parseInt(numberOfSpawns);
			convInterval = Integer.parseInt(interval);
		}
		catch (IllegalArgumentException e) {
			return false;
		}
		return execute(name, convMobType, player.getLocation(), convRange, convNumberOfSpawns, convInterval);
	}
	
	public CreateSpawnerCommand(SpawnerManager spawnerManager) {
		this.spawnerManager = spawnerManager;
	}
	
}
