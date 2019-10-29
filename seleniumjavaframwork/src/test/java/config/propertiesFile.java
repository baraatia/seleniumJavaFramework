package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import tests.acceptedConditions;

public class propertiesFile {
	static Properties prop = new Properties();
	static String projectPAth = System.getProperty("user.dir");

	
	public static void main(String[] args) {

		getProperties();
		setProperties();
	}

	public static void getProperties() {
		try {
			InputStream input = new FileInputStream( projectPAth+"/src/test/java/config/config.properties");
			prop.load(input);
			String browser = prop.getProperty("browser");
			System.out.println(browser);
			acceptedConditions.browserName = browser;
		}
		catch(Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();

		}
		
	}
	public static void setProperties() {
		try {
			OutputStream output = new FileOutputStream( projectPAth+"/src/test/java/config/config.properties");
			prop.setProperty("browser", "chrome");
			prop.store(output, null);
		
		}
		catch(Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();

		}
		

	}
	
}
