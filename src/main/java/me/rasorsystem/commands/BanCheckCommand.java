package me.rasorsystem.commands;

import me.rasorsystem.BanSystem;
import me.rasorsystem.config.BanConfig;
import me.rasorsystem.utils.BanReasons;
import me.rasorsystem.utils.Messages;
import me.rasorsystem.utils.UUIDGetter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCheckCommand implements CommandExecutor {

    Messages messages = new Messages();
    BanConfig banConfig;
    UUIDGetter uuidGetter = new UUIDGetter();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("banssytem.banCheck")){
            if(args.length != 1){
                sender.sendMessage(messages.getMessage("ban.banCheckSyntax").replace("&", "§"));
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
                    sender.sendMessage(messages.getMessage("ban.banCheckBanned").replace("%player%", name)
                            .replace("%banned%", ""+banConfig.isBanned())
                            .replace("%reason%", banConfig.getReason())
                            .replace("%duration%", banConfig.getRemainigTime())
                            .replace("%endDate%", banConfig.getEndDate())
                            .replace("%banner%", banConfig.getBannyBy())
                            .replace("&", "§"));
                }else{
                    sender.sendMessage(messages.getMessage("ban.banCheckNotBanned").replace("%player%", name)
                            .replace("%banned%", ""+banConfig.isBanned())
                            .replace("&", "§"));
                }

            }
        }else{
            sender.sendMessage(messages.getMessage("norperm").replace("&", "§"));
        }
        return false;
    }
}
