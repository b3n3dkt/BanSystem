package de.rasorsystems.bansystem.commands;

import de.rasorsystems.bansystem.config.BanConfig;
import de.rasorsystems.bansystem.utils.Messages;
import de.rasorsystems.bansystem.utils.UUIDGetter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnBanCommand implements CommandExecutor {

    Messages messages = new Messages();
    BanConfig banConfig;
    UUIDGetter uuidGetter = new UUIDGetter();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("bansystem.unban")){
            if(args.length != 1){
                sender.sendMessage(messages.getMessage("ban.unbanSyntax").replace("&", "§"));
            }else if(args.length == 1){
                String name = args[0];
                Player player = Bukkit.getPlayer(uuidGetter.getUUIDFromName(name));
                if(player == null){
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuidGetter.getUUIDFromName(name));
                    banConfig = new BanConfig(offlinePlayer.getUniqueId().toString(), offlinePlayer.getName());
                }else{
                    banConfig = new BanConfig(player.getUniqueId().toString(), player.getName());
                }
                if(banConfig.isBanned()){
                    banConfig.setUnBanned();
                    sender.sendMessage(messages.getMessage("ban.playerUnbanned").replace("%player%", ""+name).replace("&", "§"));
                }else{
                    sender.sendMessage(messages.getMessage("ban.playerNotBanned").replace("&", "§"));
                }
            }
        }else{
            sender.sendMessage(messages.getMessage("noperm").replace("&", "§"));
        }
        return false;
    }
}
