package me.rasorsystem.events;

import me.rasorsystem.config.BanConfig;
import me.rasorsystem.utils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LogIn implements Listener {

    BanConfig banConfig;
    Messages messages = new Messages();

    @EventHandler
    public void onHandle(PlayerLoginEvent event){
        Player player = event.getPlayer();
        banConfig = new BanConfig(player.getUniqueId().toString(), player.getName());

        if(banConfig.isBanned()){
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, messages.getMessage("ban.kickMessage"));
        }
    }

}
