package com.github.ethanwall.invisiblespawners.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.ethanwall.invisiblespawners.Messages;
import com.github.ethanwall.invisiblespawners.SpawnerManager;

public class ListSpawnersCommand implements CommandExecutor {

	private SpawnerManager spawnerManager;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("invisiblespawners.list")) {
			sender.sendMessage(Messages.NO_PERMISSION_MESSAGE);
			return true;
		}
		
		ArrayList<String> messages = new ArrayList<String>();
		messages.add(Messages.LIST_SPAWNERS_MESSAGE);
		messages.addAll(spawnerManager.spawners.keySet());
		
		sender.sendMessage(messages.toArray(new String[messages.size()]));
		return true;
	}

	public ListSpawnersCommand(SpawnerManager spawnerManager) {
		this.spawnerManager = spawnerManager;
	}
	
}
