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

import java.text.SimpleDateFormat;

// /ban <player> <id>

public class BanCommand implements CommandExecutor {

    BanReasons banReasons = new BanReasons();
    Messages messages = new Messages();
    BanConfig banConfig;
    UUIDGetter uuidGetter = new UUIDGetter();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("bansystem.ban")){
            if(args.length != 2){
                sender.sendMessage("\n");
                sender.sendMessage(messages.getMessage("ban.banSyntax").replace("&", "§"));
                sender.sendMessage("\n");
                for(int i = 1;i<banReasons.getReasonsIndex()+1;i++){
                    if(banReasons.getBanDuration(i).equalsIgnoreCase("LIFETIME")){
                        sender.sendMessage("§8-§b" + i + " §c" + banReasons.getBanReason(i) + " §e" + banReasons.getBanDuration(i).replace("&", "§"));
                    }else {
                        sender.sendMessage("§8-§b" + i + " §c" + banReasons.getBanReason(i) + " §e" + banReasons.getDurationDate(i).replace("&", "§"));
                    }
                }
                sender.sendMessage("\n");

            }else if(args.length == 2){
                int id = Integer.valueOf(args[1]);
                String name = args[0];
                String banner = "";
                if(sender instanceof Player){
                    banner = sender.getName();
                }else{
                    banner = "Console";
                }
                Player player = Bukkit.getPlayer(uuidGetter.getUUIDFromName(name));
                if(player == null){
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuidGetter.getUUIDFromName(name));
                    banConfig = new BanConfig(offlinePlayer.getUniqueId().toString(), offlinePlayer.getName());
                }else{
                    banConfig = new BanConfig(player.getUniqueId().toString(), player.getName());
                }

                if(banConfig.isBanned() == true){
                    sender.sendMessage(messages.getMessage("ban.alreadyBanned").replace("&", "§"));
                    return true;
                }

                if(banReasons.getBanDuration(id).equalsIgnoreCase("LIFETIME")){
                    banConfig.setBanned(banReasons.getBanDuration(id), banReasons.getBanReason(id), banner, true);
                }else{
                    banConfig.setBanned(banReasons.getBanDuration(id), banReasons.getBanReason(id), banner, false);
                }
                if(player != null){
                    player.kickPlayer(messages.getMessage("ban.kickMessage").replace("%reason%", banConfig.getReason()).replace("%endDate%", banConfig.getEndDate()).replace("%timeLeft%", banConfig.getRemainigTime()).replace("&", "§"));
                }
                sender.sendMessage(messages.getMessage("ban.bannerBanMessage").replace("%player%", name).replace("%reason%", banConfig.getReason()).replace("%endDate%", banConfig.getEndDate()).replace("&", "§"));
            }
        }else{
            sender.sendMessage(messages.getMessage("noperm").replace("&", "§"));
        }

        return false;
    }
}
