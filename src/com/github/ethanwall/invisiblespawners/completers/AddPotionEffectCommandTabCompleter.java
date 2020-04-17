package com.github.ethanwall.invisiblespawners.completers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.StringUtil;

import com.github.ethanwall.invisiblespawners.SpawnerManager;

public class AddPotionEffectCommandTabCompleter implements TabCompleter {

	SpawnerManager spawnerManager;
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> completions = new ArrayList<>();
		
		switch (args.length) {
		case 1:
			StringUtil.copyPartialMatches(args[0], spawnerManager.spawners.keySet(), completions);
			break;
		case 2:
			ArrayList<String> effects = Arrays.stream(PotionEffectType.values())
			.map(e -> e.getName())
			.collect(Collectors.toCollection(ArrayList::new));
			StringUtil.copyPartialMatches(args[1], effects, completions);
			break;
		}
		
		return completions;
	}

	public AddPotionEffectCommandTabCompleter(SpawnerManager spawnerManager) {
		this.spawnerManager = spawnerManager;
	}
	
}
