package me.lukacat10.exampleUUIDFetcherPlugin;

import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import me.lukacat10.UUIDFetcherANDCache.UUIDManager;
import me.lukacat10.UUIDFetcherANDCache.storage.UUIDStorage;

public class MainJavaPlugin extends JavaPlugin{
	UUIDStorage uuidStorage;
	
	@Override
	public void onEnable() {
		getLogger().info("The plugin " + getDescription().getFullName() + " has been enabled!");
		//Bukkit.getConsoleSender().sendMessage("The plugin " + getDescription().getFullName() + " has been enabled!");
		//^^^^The above method will print the (almost) same result as the getLogger(). Thanks to PvPNiK for mentioning this :P
		getLogger().info("The uuidstorage is now being initialized!");
		setUUIDStorage(new UUIDStorage(this, 24)); //Second parameter made by me :D (lukacat10). Controls the amount of hours until the uuid storage unit expires. 
		getLogger().info("Loading uuid storage units from last server shutdown\reload.");
		getUUIDStorage().createNewFiles();
		getLogger().info("The uuid storage unit has been successfully initialized and loaded from the configs.");
	}
	@Override
	public void onDisable() {
		getLogger().info("The plugin " + getDescription().getFullName() + " has been disabled!");
		//Bukkit.getConsoleSender().sendMessage("The plugin " + getDescription().getFullName() + " has been disabled!");
		//^^^^The above method will print the (almost) same result as the getLogger(). Thanks to PvPNiK for mentioning this :P
		getUUIDStorage().saveAllFiles();
		setUUIDStorage(null);//suggested by the author of the uuidstorage implementation - "garbage collection", which is
							//implemented by java, but might get messed up because of the problemetic reload method made by bukkit.
							//MY SUGGESTION: RESTART servers, dont reload them! If a plugin isn't coded well the reload will cause memory leaks
							//and the server's ram usage will be doubled!
	}
	
	public void setUUIDStorage(final UUIDStorage uuidStorage) { //Method for setting the uuidstorage unit. Used in the plugin's enable method.
		this.uuidStorage = uuidStorage; 
	}
	public UUIDStorage getUUIDStorage() { //Method for getting the uuidstorage unit. Used in the uuid libs. also used to save files OR create new ones.
		return uuidStorage;
	}
	public UUID GettinguuidFromPlayerNameExample(String playerName){
		UUID uuid;
		uuid = UUIDManager.getUUIDFromPlayer(playerName); //No need for running in a seperate thread, already done for us. IGNORE THIS COMMENT!
		return uuid;
	}
	public String GettingplayerNameFromUUIDExample(UUID uuid){
		String playerName;
		playerName = UUIDManager.getPlayerFromUUID(uuid); //No need for running in a seperate thread, already done for us. IGNORE THIS COMMENT!
		return playerName;
	}
	
}
