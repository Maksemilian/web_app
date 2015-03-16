package application;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
    private static final String conf = "app.properties";
    public static final String FILE_NAME, DIR;
       
    static{
        Properties props = new Properties();
        
        try(InputStream reader = Config.class.getClassLoader().getResourceAsStream(conf)){
            props.load(reader);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        DIR=props.getProperty("DIR");
        FILE_NAME=props.getProperty("FILE_NAME");
    }
}
