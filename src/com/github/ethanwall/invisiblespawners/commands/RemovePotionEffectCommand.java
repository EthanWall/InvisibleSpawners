package com.github.ethanwall.invisiblespawners.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.ethanwall.invisiblespawners.Messages;
import com.github.ethanwall.invisiblespawners.Spawner;
import com.github.ethanwall.invisiblespawners.SpawnerManager;

public class RemovePotionEffectCommand implements CommandExecutor {

	SpawnerManager spawnerManager;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("invisiblespawners.potioneffects.add")) {
			sender.sendMessage(Messages.NO_PERMISSION_MESSAGE);
			return true;
		}
		switch (args.length) {
		case 2:
			return execute(sender, args[0], args[1]);
		default:
			break;
		}
		return false;
	}

	private boolean execute(CommandSender sender, String name, PotionEffectType type) {
		Spawner spawner = spawnerManager.spawners.get(name);
		if (spawner == null) {
			sender.sendMessage(Messages.SPAWNER_NOT_FOUND_MESSAGE);
			return true;
		}
		
		// Removes all matching effects
		Collection<PotionEffect> newEffects = spawner.effects.stream()
				.filter(e -> e.getType() != type)
				.collect(Collectors.toCollection(ArrayList::new));
		if (newEffects.size() != spawner.effects.size()) {
			spawner.effects = newEffects;
			sender.sendMessage(String.format(Messages.EFFECT_REMOVED_MESSAGE, type.toString()));
		}
		else {
			sender.sendMessage(Messages.EFFECT_NOT_FOUND_MESSAGE);
		}
		
		return true;
	}
	private boolean execute(CommandSender sender, String name, String type) {
		PotionEffectType convType = PotionEffectType.getByName(type);
		if (convType == null) {
			sender.sendMessage(Messages.INVALID_ARGUMENT_MESSAGE);
			return true;
		}
		
		return execute(sender, name, convType);
	}
	
	public RemovePotionEffectCommand(SpawnerManager spawnerManager) {
		this.spawnerManager = spawnerManager;
	}
	
}
