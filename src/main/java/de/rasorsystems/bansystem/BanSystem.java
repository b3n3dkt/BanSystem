package de.rasorsystems.bansystem;

import de.rasorsystems.bansystem.commands.BanCheckCommand;
import de.rasorsystems.bansystem.events.LogIn;
import de.rasorsystems.bansystem.commands.BanCommand;
import de.rasorsystems.bansystem.commands.UnBanCommand;
import de.rasorsystems.bansystem.utils.BanReasons;
import de.rasorsystems.bansystem.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BanSystem extends JavaPlugin {

    private BanSystem main;
    private BanReasons banReasons;
    private Messages messages;

    public void onEnable(){
        init();
        Bukkit.getConsoleSender().sendMessage("§8<--------------------------------------------------------->");
        Bukkit.getConsoleSender().sendMessage("§a  ____               _____           _                       ");
        Bukkit.getConsoleSender().sendMessage("§a |  _ \\             / ____|         | |                    ");
        Bukkit.getConsoleSender().sendMessage("§a | |_) | __ _ _ __ | (___  _   _ ___| |_ ___ _ __ ___        ");
        Bukkit.getConsoleSender().sendMessage("§a |  _ < / _` | '_ \\ \\___ \\| | | / __| __/ _ \\ '_ ` _ \\  ");
        Bukkit.getConsoleSender().sendMessage("§a | |_) | (_| | | | |____) | |_| \\__ \\ ||  __/ | | | | |    ");
        Bukkit.getConsoleSender().sendMessage("§a |____/ \\__,_|_| |_|_____/ \\__, |___/\\__\\___|_| |_| |_|  ");
        Bukkit.getConsoleSender().sendMessage("§a                            __/ |                            ");
        Bukkit.getConsoleSender().sendMessage("§a                           |___/                             ");
        Bukkit.getConsoleSender().sendMessage("§a");
        Bukkit.getConsoleSender().sendMessage("§7Author: §bb3n3dkt");
        Bukkit.getConsoleSender().sendMessage("§7Publisher: §bRasorSystems");
        Bukkit.getConsoleSender().sendMessage("§7If you need help with anything you can write us on Discord:");
        Bukkit.getConsoleSender().sendMessage("§7- §bBenedikt#7121");
        Bukkit.getConsoleSender().sendMessage("§7- §bGaaehHacked#6791");
        Bukkit.getConsoleSender().sendMessage("§7- §bdiscord.gg/88x6CHK3qa");
        Bukkit.getConsoleSender().sendMessage("§8<--------------------------------------------------------->");

        register();
    }

    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage("§8<---------------->");
        Bukkit.getConsoleSender().sendMessage("§bBanSystem Disabled");
        Bukkit.getConsoleSender().sendMessage("§8<---------------->");
    }

    public void register(){
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("unban").setExecutor(new UnBanCommand());
        getCommand("bancheck").setExecutor(new BanCheckCommand());

        Bukkit.getPluginManager().registerEvents(new LogIn(), this);
    }

    public void init(){
        main = this;
        banReasons = new BanReasons();
        messages = new Messages();

        if(!messages.exist()){
            messages.createMessagesConfig();
        }

        if(!banReasons.exist()){
            banReasons.createBanConfig();
        }
    }

    public BanSystem getMain(){return this.main;}

}
