package de.rasorsystems.bansystem.utils;

public class Messages {

    private FileBuilder fileBuilder;
    private String path = "plugins//BanSystem//Settings//";
    private String file = "messages.yml";

    public Messages(){ this.fileBuilder = new FileBuilder(path, file);}

    public boolean exist(){ return this.fileBuilder.exist(); }

    public void createMessagesConfig(){
        this.fileBuilder.setValue("prefix", "&bBanSystem&8> ");
        this.fileBuilder.setValue("noperm", "&bBanSystem&8> &cYou don't have permission to do that!");
        this.fileBuilder.setValue("ban.banSyntax", "&bBanSystem&8> &cSyntax: Use /ban <Playername> <Id> !");
        this.fileBuilder.setValue("ban.alreadyBanned", "&bBanSystem&8> &cThat person is already banned!");
        this.fileBuilder.setValue("ban.bannerBanMessage", "&bBanSystem&8> &7You have banned &8'&b%player%&8' &7for &8'&b%reason%&8' &7until &8'&b%endDate%&8'&7 !");
        this.fileBuilder.setValue("ban.kickMessage", "&8<----------[&bBanSystem&8]---------->\n\n" +
                                                                     "        &cYou have been banned!\n" +
                                                                     "        &7Reason: &b%reason%\n" +
                                                                     "        &7Time Remaining: &b%timeLeft%\n"+
                                                                     "        &7End Date: &b%endDate%\n\n"+
                                                                     "     &cIf you think that this ban is not right,\n"+
                                                                     "     &cyou can write a ticket at &bYourWebsite\n\n"+
                                                                     "&8<----------[&bBanSystem&8]---------->");
        this.fileBuilder.setValue("ban.timeRemaining", "&b%weeks% &7week(s) &b%days% &7day(s) &b%hours% &7hour(s) &b%minutes% &7minute(s) &b%seconds% &7second(s)");
        this.fileBuilder.setValue("ban.unbanSyntax", "&bBanSystem&8> &cSyntax: Use /unban <Playername> !");
        this.fileBuilder.setValue("ban.playerNotBanned", "&bBanSystem&8> &cThat player is not banned!");
        this.fileBuilder.setValue("ban.playerUnbanned", "&bBanSystem&8> &7You have unbanned &8'&b%player%&8'&7!");
        this.fileBuilder.setValue("ban.timeRemainingLifetime", "LIFETIME");
        this.fileBuilder.setValue("ban.banCheckSyntax", "&bBanSystem&8> &cSyntax: Use /bancheck <Playername> !");
        this.fileBuilder.setValue("ban.banCheckBanned", "\n"+
                                                                  "&7Player: &b%player%\n"+
                                                                  "&7Banned: &b%banned%\n"+
                                                                  "&7Reason: &b%reason%\n"+
                                                                  "&7Duration: &b%duration%\n"+
                                                                  "&7End Date: &b%endDate%\n"+
                                                                  "&7Banner: &b%banner%\n"+
                                                                  "\n");
        this.fileBuilder.setValue("ban.banCheckNotBanned", "\n"+
                "&7Player: &b%player%\n"+
                "&7Banned: &b%banned%\n"+
                "\n");
        this.fileBuilder.save();
    }

    public String getMessage(String path){ return this.fileBuilder.getString(path).replace("&", "§"); }

}
