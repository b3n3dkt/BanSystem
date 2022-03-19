package me.rasorsystem.utils;

public class BanReasons {

    private FileBuilder fileBuilder;
    private String path = "plugins//BanSystem//Settings//";
    private String file = "banreasons.yml";

    public BanReasons() { this.fileBuilder = new FileBuilder(path, file); }

    public boolean exist() { return this.fileBuilder.exist(); }

    public void createBanConfig(){
        this.fileBuilder.setValue("banreason.index", 10);
        this.fileBuilder.setValue("banreason.1.reason", "Cheating");
        this.fileBuilder.setValue("banreason.1.duration", "86400000");
        this.fileBuilder.setValue("banreason.2.reason", "Hate Speech");
        this.fileBuilder.setValue("banreason.2.duration", "604800000");
        this.fileBuilder.setValue("banreason.3.reason", "Sexual content");
        this.fileBuilder.setValue("banreason.3.duration", "2592000000");
        this.fileBuilder.setValue("banreason.4.reason", "Impersonating Staff");
        this.fileBuilder.setValue("banreason.4.duration", "1209600000");
        this.fileBuilder.setValue("banreason.5.reason", "Spamming");
        this.fileBuilder.setValue("banreason.5.duration", "600000");
        this.fileBuilder.setValue("banreason.6.reason", "Provocation");
        this.fileBuilder.setValue("banreason.6.duration", "900000");
        this.fileBuilder.setValue("banreason.7.reason", "Chat behavior");
        this.fileBuilder.setValue("banreason.7.duration", "3600000");
        this.fileBuilder.setValue("banreason.8.reason", "Bugusing");
        this.fileBuilder.setValue("banreason.8.duration", "86400000");
        this.fileBuilder.setValue("banreason.9.reason", "Advertisment");
        this.fileBuilder.setValue("banreason.9.duration", "LIFETIME");
        this.fileBuilder.setValue("banreason.10.reason", "Cheating");
        this.fileBuilder.setValue("banreason.10.duration", "LIFETIME");
        this.fileBuilder.save();
    }

    public int getReasonsIndex(){ return this.fileBuilder.getInt("banreason.index"); }

    public String getBanReason(int number){
        return this.fileBuilder.getString("banreason." + number + ".reason");
    }

    public String getBanDuration(int number){
        return this.fileBuilder.getString("banreason." + number + ".duration");
    }

    /**
     * It takes a number and returns a string
     *
     * @param number The number of the ban.
     * @return The time that the ban will last.
     */
    public String getDurationDate(int number){
        if(getBanDuration(number).equalsIgnoreCase("LIFETIME")){
            return getBanDuration(number);
        }else{
            long seconds = Long.valueOf(getBanDuration(number)) / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;
            minutes = minutes % 60;
            hours = hours % 24;
            String time = "";
            if(days != 0){
                time = "" + days + " day(s)";
            }
            if(hours != 0){
                time = time + " " + hours + " hour(s)";
            }
            if(minutes != 0){
                time = time + " " + minutes + " minute(s)";
            }
            return time;
        }
    }

}
