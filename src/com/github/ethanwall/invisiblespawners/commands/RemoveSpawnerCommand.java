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
			return execute(args[0]);
		default:
			break;
		}
		return false;
	}

	private boolean execute(String name) {
		spawnerManager.removeSpawner(name);
		return true;
	}
	
	public RemoveSpawnerCommand(SpawnerManager spawnerManager) {
		this.spawnerManager = spawnerManager;
	}
	
}
