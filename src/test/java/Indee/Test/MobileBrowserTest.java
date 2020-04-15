package Indee.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Service;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

/**
 * 
 * @author rohith
 *
 */
public class MobileBrowserTest extends BaseClass{
	AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildDefaultService();
	DesiredCapabilities cap = new DesiredCapabilities();
	Properties testData = getTestData("DesktopBrowserTest");

	@BeforeTest
	public void setup() {
		appiumService.start();
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITEST");
		cap.setCapability("deviceName", "iPad Pro (11-inch)");
		cap.setCapability("platformName", "iOS");
		cap.setCapability("platformVersion", "13.0");
		cap.setCapability(CapabilityType.BROWSER_NAME, "safari");
		
		driver = new IOSDriver(appiumService,cap);
	}
	
	
	@Test
	public void indeeTest() throws MalformedURLException{
		
		// Step2: Open www.amazon.com Web-site
		driver.get(testData.get("url").toString());

		// Step3: Change Postal code
		Operations.changePostalcode(testData.getProperty("postalCode"));

		// Step4: Enter the text "Apple iPhone 11 Pro Max" in the search box.
		// Hit Enter Apple iPhone 11 Pro Max
		Operations.typeAtElement(testData.getProperty("searchField"), testData.getProperty("searchKeyWord") + Keys.ENTER);

		// Step5: Get list of Carrier – Simple Mobiles'
		List<WebElement> mobiles = Operations.searchElements(testData.getProperty("simpleMobiles"));

		boolean targetFound = false;
		for (WebElement a : mobiles) {
			if (a.getText().contains("Midnight Green") || a.getText().contains("White")) {
				if (a.getText().contains("Midnight Green")) {
					a.click();
					targetFound = true;
					break;
				}
			}
		}

		if (!targetFound) {
			mobiles.get(0).click();
		}

		// Step6 : Click on add to cart button
		Operations.clickElement(testData.getProperty("addCartButton"));

		// Step7 : Ignoring the additional suggestions
		Operations.clickEnabledElement(testData.getProperty("noThanks"));

		// Step8 : Proceed to checkout
		Operations.clickElement(testData.getProperty("checkOut"));
	
	}
	
	@AfterTest
	public void cleanUp() {
		// Step9 : Close the appium session
		appiumService.stop();
	}
	
	
}
