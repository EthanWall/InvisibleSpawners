package com.github.ethanwall.invisiblespawners;

import org.bukkit.ChatColor;

public final class Messages {

	public static final String NO_PERMISSION_MESSAGE = ChatColor.DARK_RED + "You do not have permission to use that command!";
	public static final String SPAWNER_NOT_FOUND_MESSAGE = ChatColor.DARK_RED + "The specified spawner could not be found.";
	public static final String INVALID_ARGUMENT_MESSAGE = ChatColor.DARK_RED + "Invalid argument!";
	
	public static final String CONFIGS_LOADED_MESSAGE = ChatColor.GREEN + "%1$d" + ChatColor.RESET + " / " + ChatColor.RED + "%2$d" + ChatColor.RESET + " configs have been loaded.";
	public static final String SPAWNERS_LOADED_MESSAGE = ChatColor.GREEN + "%d" + ChatColor.RESET + " spawners have been loaded.";
	
	public static final String LIST_SPAWNERS_MESSAGE = ChatColor.BLUE + "Spawners:";
	
	public static final String SPAWNER_CREATED_MESSAGGE = ChatColor.BLUE + "%s" + ChatColor.GREEN + " has been successfully created!";
	public static final String SPAWNER_REMOVED_MESSAGGE = ChatColor.BLUE + "%s" + ChatColor.GREEN + " has been successfully removed!";
	
}
