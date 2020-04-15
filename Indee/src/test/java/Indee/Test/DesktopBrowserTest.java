package Indee.Test;

import java.util.List;
import java.util.Properties;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
/**
 * 
 * @author rohith
 *
 */
public class DesktopBrowserTest extends BaseClass{
	static Class<? extends Object> thisClass = new Object() {}.getClass(); 
	Properties testData = Operations.getTestData(thisClass.getName().split("[.]")[2].replace("$1", ""));

	@BeforeTest
	public void setup() {
		// Step1: Open GoogleChrome browser
		Assert.assertNotNull(Operations.openBrowser("Chrome"),"Web Driver initialization failed!");
	}

	@Test
	public void test() throws InterruptedException {
		// Step2: Open www.amazon.com Web-site
		Operations.goTo(testData.get("url").toString());

		// Step3: Change Postal code
		Operations.changePostalcode(testData.getProperty("postalCode"));

		// Step4: Enter the text "Apple iPhone 11 Pro Max" in the search box.
		// Hit Enter Apple iPhone 11 Pro Max
		Operations.typeAtElement(testData.getProperty("searchField"), testData.getProperty("searchKeyWord") + Keys.ENTER);

		// Step5: Get list of Carrier – Simple Mobiles'
		List<WebElement> mobiles = Operations.searchElements(testData.getProperty("simpleMobiles"));
		Assert.assertNotNull(mobiles,"There are no such mobiles available on the screen right now!");
		
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
	public void clear() {
		// Step9 : Close browser
		Operations.closeBrowser();
	}
}
