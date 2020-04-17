package com.github.ethanwall.invisiblespawners.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.github.ethanwall.invisiblespawners.Messages;
import com.github.ethanwall.invisiblespawners.Spawner;
import com.github.ethanwall.invisiblespawners.SpawnerManager;

public class AddPotionEffectCommand implements CommandExecutor {

	private SpawnerManager spawnerManager;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("invisiblespawners.potioneffects.add")) {
			sender.sendMessage(Messages.NO_PERMISSION_MESSAGE);
			return true;
		}
		
		switch (args.length) {
		case 4:
			return execute(sender, args[0], args[1], args[2], args[3]);
		default:
			break;
		}
		return false;
	}

	private boolean execute(CommandSender sender, String name, PotionEffectType type, int duration, int amplifier) {
		Spawner spawner = spawnerManager.spawners.get(name);
		if (spawner == null) {
			sender.sendMessage(Messages.SPAWNER_NOT_FOUND_MESSAGE);
			return true;
		}
		
		PotionEffect effect = new PotionEffect(type, duration, amplifier);
		spawner.effects.add(effect);
		spawnerManager.editSpawner(name, "effects." + type.getName() + ".duration", duration);
		spawnerManager.editSpawner(name, "effects." + type.getName() + ".amplifier", amplifier);
		
		return true;
	}
	private boolean execute(CommandSender sender, String name, String type, String duration, String amplifier) {
		PotionEffectType convType;
		int convDuration;
		int convAmplifier;
		try {
			convType = PotionEffectType.getByName(type);
			convDuration = Integer.parseInt(duration);
			convAmplifier = Integer.parseInt(amplifier);
		}
		catch (NumberFormatException e) {
			sender.sendMessage(Messages.INVALID_ARGUMENT_MESSAGE);
			return true;
		}
		if (convType == null) {
			sender.sendMessage(Messages.INVALID_ARGUMENT_MESSAGE);
			return true;
		}
		
		return execute(sender, name, convType, convDuration, convAmplifier);
	}
	
	public AddPotionEffectCommand(SpawnerManager spawnerManager) {
		this.spawnerManager = spawnerManager;
	}
	
}
