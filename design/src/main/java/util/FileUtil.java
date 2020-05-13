package util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class FileUtil {

    public static String readTxtFromPropertiesFiles(String propFile, String key) throws IOException, URISyntaxException {
        FileReader reader = new FileReader(String.valueOf(Paths.get(FileUtil.class.getResource(propFile).toURI())));
        Properties properties = new Properties();
        properties.load(reader);
        File file =
                new File(properties.getProperty(key));
        Scanner sc = new Scanner(file);
        String OutPut = null;
        sc.useDelimiter("\\Z");
        OutPut = sc.next();
        return OutPut;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> rows = Files.readAllLines(Paths.get(FileUtil.class.getResource("/application.properties").toURI()), Charset.forName("UTF-8"));
        for (String row : rows)
            System.out.println(row);
    }
}
