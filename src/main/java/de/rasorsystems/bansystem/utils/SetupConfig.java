package de.rasorsystems.bansystem.utils;

public class SetupConfig {

    private FileBuilder fileBuilder;
    private String path = "plugins//BanSystem//Settings//";
    private String file = "setup.yml";

    public SetupConfig() { this.fileBuilder = new FileBuilder(path, file); }

    public boolean exist() { return this.fileBuilder.exist(); }

    public void createSetupConfig(){
        this.fileBuilder.setValue("mysql.use", false);
        this.fileBuilder.setValue("mysql.username", "root");
        this.fileBuilder.setValue("mysql.password", "localhost");
        this.fileBuilder.setValue("mysql.database", "BanSystem");
        this.fileBuilder.setValue("mysql.host", "localhost");
        this.fileBuilder.setValue("mysql.port", "3306");
        this.fileBuilder.save();
    }

    public String getMySQLPort(){
        return this.fileBuilder.getString("mysql.port");
    }

    public String getMySQLHost(){
        return this.fileBuilder.getString("mysql.host");
    }

    public String getMySQLDatabase(){
        return this.fileBuilder.getString("mysql.database");
    }

    public String getMySQLPassword(){
        return this.fileBuilder.getString("mysql.password");
    }

    public String getMySQLUsername(){
        return this.fileBuilder.getString("mysql.username");
    }

}
