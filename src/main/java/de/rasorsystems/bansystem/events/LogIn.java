package de.rasorsystems.bansystem.events;

import de.rasorsystems.bansystem.config.BanConfig;
import de.rasorsystems.bansystem.utils.Messages;
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

        if(banConfig.isBanned()) {
            if (banConfig.getDurationMillis().equalsIgnoreCase("LIFETIME")) {
                    event.disallow(PlayerLoginEvent.Result.KICK_BANNED, messages.getMessage("ban.kickMessage").replace("%reason%", banConfig.getReason()).replace("%endDate%", banConfig.getEndDate()).replace("%timeLeft%", banConfig.getRemainigTime()));
                } else {
                long current = System.currentTimeMillis();
                long end = Long.valueOf(banConfig.getDurationMillis()) + banConfig.getTimeStampMillis();
                if (current == end || current >= end) {
                    banConfig.setUnBanned();
                    event.allow();
                } else {
                    event.disallow(PlayerLoginEvent.Result.KICK_BANNED, messages.getMessage("ban.kickMessage").replace("%reason%", banConfig.getReason()).replace("%endDate%", banConfig.getEndDate()).replace("%timeLeft%", banConfig.getRemainigTime()));
                }
            }
        }
    }

}
