package me.rasorsystem.config;

import me.rasorsystem.utils.FileBuilder;
import me.rasorsystem.utils.Messages;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;

public class BanConfig {

    private FileBuilder fileBuilder;
    private String path = "plugins//BanSystem//Players//";
    private String uuid;
    private String name;
    private Messages messages = new Messages();

    public BanConfig(String uuid, String name){
        fileBuilder = new FileBuilder(path, uuid + ".yml");
        this.uuid = uuid;
        this.name = name;
    }

    public boolean exist(){
        return fileBuilder.exist();
    }

    public void createConfig(){
        this.fileBuilder.setValue("isBanned", false);
        this.fileBuilder.save();
    }

    public void setBanned(String duration, String reason, String banner, boolean lifetime){
        long currenTimeMillis = System.currentTimeMillis();
        if(lifetime == false){
            long end = System.currentTimeMillis() + Long.valueOf(duration);
            this.fileBuilder.setValue("duration.endDate", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(end));
            this.fileBuilder.setValue("history." + new SimpleDateFormat("dd_MM_yyyy HH_mm_ss").format(currenTimeMillis)+ ".duration.date" , new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(end));
        }else{
            this.fileBuilder.setValue("duration.endDate", "LIFETIME");
            this.fileBuilder.setValue("history." + new SimpleDateFormat("dd_MM_yyyy HH_mm_ss").format(currenTimeMillis)+ ".duration.date" , "LIFETIME");
        }
        this.fileBuilder.setValue("isBanned", "true");
        this.fileBuilder.setValue("duration.millis", duration);
        this.fileBuilder.setValue("reason", reason);
        this.fileBuilder.setValue("bannedBy", banner);
        this.fileBuilder.setValue("timestamp.millis", currenTimeMillis);
        this.fileBuilder.setValue("timestamp.date", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(currenTimeMillis));
        this.fileBuilder.setValue("history." + new SimpleDateFormat("dd_MM_yyyy HH_mm_ss").format(currenTimeMillis)+ ".reason" , reason);
        this.fileBuilder.setValue("history." + new SimpleDateFormat("dd_MM_yyyy HH_mm_ss").format(currenTimeMillis)+ ".duration.millis" , duration);
        this.fileBuilder.setValue("history." + new SimpleDateFormat("dd_MM_yyyy HH_mm_ss").format(currenTimeMillis) + ".bannedBy", banner);
        this.fileBuilder.save();
    }

    public String getEndDate(){
        return this.fileBuilder.getString("duration.endDate");
    }

    public boolean isBanned(){
        return Boolean.valueOf(this.fileBuilder.getString("isBanned"));
    }

    public long getTimeStampMillis(){
        return Long.valueOf(this.fileBuilder.getString("timestamp.millis"));
    }

    public String getBannyBy(){
        return this.fileBuilder.getString("bannedBy");
    }

    public String getReason(){
        return this.fileBuilder.getString("reason");
    }

    public String getDurationMillis(){
        return this.fileBuilder.getString("duration.millis");
    }

    public void setUnBanned(){
        this.fileBuilder.setValue("isBanned", false);
        this.fileBuilder.setValue("duration", null);
        this.fileBuilder.setValue("reason", null);
        this.fileBuilder.setValue("bannedBy", null);
        this.fileBuilder.setValue("timestamp", null);
        this.fileBuilder.save();
    }

    public String getRemainigTime() {
        if (getDurationMillis().equalsIgnoreCase("LIFETIME")) {
            return messages.getMessage("ban.timeRemainingLifetime").replace("&", "§");
        } else {
            System.out.println(getDurationMillis());
            long current = System.currentTimeMillis();
            long end = Long.valueOf(getDurationMillis())+getTimeStampMillis();
            long millis = end-current;
            int seconds = 0;
            int minutes = 0;
            int hours = 0;
            int days = 0;
            int weeks = 0;

            while (millis > 1000L) {
                millis -= 1000L;
                ++seconds;
            }

            while (seconds > 60) {
                seconds -= 60;
                ++minutes;
            }

            while (minutes > 60) {
                minutes -= 60;
                ++hours;
            }

            while (hours > 24) {
                hours -= 24;
                ++days;
            }

            while (days > 7) {
                days -= 7;
                ++weeks;
            }

            return messages.getMessage("ban.timeRemaining").replace("%weeks%", "" + weeks)
                    .replace("%days%", "" + days)
                    .replace("%hours%", "" + hours)
                    .replace("%minutes%", "" + minutes)
                    .replace("%seconds%", "" + seconds)
                    .replace("&", "§");
        }
    }

}
