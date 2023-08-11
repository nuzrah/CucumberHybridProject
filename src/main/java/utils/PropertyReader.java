package utils;


import java.io.*;
import java.util.Properties;

public class PropertyReader {

//    private Properties getData(String fileName){ //verify file exists
//        ClassLoader classLoader = getClass().getClassLoader();
//        //Look for file either in the main resources / test resources and get the filename from the argument
//        File file =new File(classLoader.getResource(fileName+".properties").getFile());
//        //Only if the file exists, the content of the file will be read
//        FileInputStream fileInput = null;
//        try {
//            //create the object and bind the identified file
//            fileInput = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Properties prop = new Properties();
//
//        try {
//            prop.load(fileInput);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return prop;
//    }

//    public String getProperty(String fileName,String key){
//        return getData(fileName).getProperty(key);
//
//    }

//    public Properties PropertyFileReader(String fileName) {
//        Properties properties = new Properties();
//
//        // Get the class loader and use it to load the resource
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
//            if (inputStream != null) {
//                properties.load(inputStream);
//            } else {
//                throw new IOException("Property file '" + fileName + "' not found.");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return properties;
//    }
//
//    public String getProperty(String fileName, String key){
//        return PropertyFileReader(fileName).getProperty(key);
//    }

    public Properties initializeProperties() {

        Properties prop = new Properties();
        File proFile = new File(System.getProperty("user.dir")+"/src/test/resources/config/config.properties");

        try {
            FileInputStream fis = new FileInputStream(proFile);
            prop.load(fis);
        }catch(Throwable e) {
            e.printStackTrace();
        }

        return prop;

    }

}
