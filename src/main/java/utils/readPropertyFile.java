package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class readPropertyFile {
    //Read the config file
    static Logger log = LogManager.getLogger();
    static Properties prop = new Properties();

    public readPropertyFile() {

        try {
            InputStream input = new FileInputStream("src/main/resources/conf.properties");
            prop.load(input);

        } catch (Exception e) {
            log.error(e);
        }
    }

    public String getPlatformName() {
        return prop.getProperty("Platform_Name");
    }

    public String getDeviceName() {
        return prop.getProperty("Device_Name");
    }

    public String getPlatformVersion() {
        return prop.getProperty("Platform_Version");
    }

    public String getHost() {
        return prop.getProperty("HOST");
    }

    public String getPort() {
        return prop.getProperty("PORT");
    }
}
