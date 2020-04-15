package Indee.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
/**
 * 
 * @author rohith
 *
 */
public class BaseClass {
	static WebDriver driver;
	// static IOSDriver iosDriver;

	public static Properties getTestData(String filePath) {
		Properties property = new Properties();
		File file = new File(System.getProperty("user.dir") + "/src/testData/" + filePath + ".properties");
		InputStream input = null;
		try {
			input = new FileInputStream(file);
			property.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return property;
	}
}
