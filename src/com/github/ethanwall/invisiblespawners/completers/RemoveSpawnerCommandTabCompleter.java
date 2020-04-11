package com.github.ethanwall.invisiblespawners.completers;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import com.github.ethanwall.invisiblespawners.SpawnerManager;

public class RemoveSpawnerCommandTabCompleter implements TabCompleter {

	private SpawnerManager spawnerManager;
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> completions = new ArrayList<>();
		
		switch (args.length) {
		case 1:
			StringUtil.copyPartialMatches(args[0], spawnerManager.spawners.keySet(), completions);
			break;
		default:
			break;
		}
		
		return completions;
	}

	public RemoveSpawnerCommandTabCompleter(SpawnerManager spawnerManager) {
		this.spawnerManager = spawnerManager;
	}
	
}
