package com.atmecs.saucelab.utills;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class PropertyParser {
	
	static Properties properties;

    public static Properties getProperties(String filePath) {
        // PropertyConfigurator.configure("./log4j.properties");
        try {
            FileReader fileReader = new FileReader(filePath);

            properties = new Properties();
            properties.load(fileReader);

        } catch (FileNotFoundException e) {
            // logger.error("Properties file not available at the given location.");
        } catch (Exception e) {
            //logger.error("Exception is " + e.getMessage());
        }
        return properties;
    }
}

