package com.github.ethanwall.invisiblespawners.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.ethanwall.invisiblespawners.Messages;
import com.github.ethanwall.invisiblespawners.SpawnerManager;

public class RemoveSpawnerCommand implements CommandExecutor {

	SpawnerManager spawnerManager;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("invisiblespawners.remove")) {
			sender.sendMessage(Messages.NO_PERMISSION_MESSAGE);
			return true;
		}
		
		switch (args.length) {
		case 1:
			return execute(sender, args[0]);
		default:
			break;
		}
		return false;
	}

	private boolean execute(CommandSender sender, String name) {
		spawnerManager.removeSpawner(name);
		sender.sendMessage(String.format(Messages.SPAWNER_REMOVED_MESSAGGE, name));
		return true;
	}
	
	public RemoveSpawnerCommand(SpawnerManager spawnerManager) {
		this.spawnerManager = spawnerManager;
	}
	
}
