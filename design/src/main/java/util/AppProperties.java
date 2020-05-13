package util;


import java.io.*;
import java.util.Properties;

public class AppProperties {
    private static Properties properties = new Properties();
    private static InputStream input;
    private static final String PATH = "src/main/resources/application.properties";
    static {
        try {
            input = new FileInputStream(PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String previousURL;

    public static void resetDataBaseURL(){
        properties.setProperty("url","jdbc:h2:file:./userDB;DB_CLOSE_DELAY=-1");
    }

    public static final String path(){
        return PATH;
    }

    public static String getDataBaseURL() {
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("url");
    }

    public static void setDataBaseURL(String url) {
        String newUrl = "jdbc:h2:file:./" + url + ";DB_CLOSE_DELAY=-1";
        properties.setProperty("url",newUrl);
    }
}
