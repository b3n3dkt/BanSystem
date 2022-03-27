package de.rasorsystems.bansystem.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class UUIDGetter {

    /**
     * Given a player's name, return their UUID
     *
     * @param name The name of the player.
     * @return The UUID of the player.
     */
    public UUID getUUIDFromName(String name){

        Player player = Bukkit.getPlayer(name);
        if(player != null){
            return player.getUniqueId();
        }else{
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(name);

            if(offlinePlayer != null){
                return offlinePlayer.getUniqueId();
            }
        }
        return null;
    }

}
