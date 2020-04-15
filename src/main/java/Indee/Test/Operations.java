package Indee.Test;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * 
 * @author rohith
 *
 */
public class Operations extends BaseClass {

	public static WebDriver openBrowser(String browserName) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/lib/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public static WebElement searchElement(String xpathKey) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathKey)));
	}

	public static void clickElement(String xpathKey) {
		WebElement temp = searchElement(xpathKey);
		Assert.assertNotNull(temp,"No such element found with xpath : "+xpathKey);
		temp.click();
	}

	public static void clickEnabledElement(String xpath) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> elements = searchElements(xpath);
		for (WebElement a : elements) {
			if (a.isDisplayed() && a.isEnabled()) {
				a.click();
			}
		}
	}

	public static void changePostalcode(String postalCode) {
		clickElement("//*[@id='nav-global-location-slot']/span/a");
		clickElement("//input[@id='GLUXZipUpdateInput']");
		typeAtElement("//input[@id='GLUXZipUpdateInput']", postalCode);
		clickElement("//span[@id='GLUXZipUpdate']/span/input");
		clickEnabledElement("//input[@id='GLUXConfirmClose']");
	}
	
	public static List<WebElement> searchElements(String xpathKey) {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> elements = driver.findElements(By.xpath(xpathKey));
		return elements;
	}

	public static void goTo(String url) throws InterruptedException {
		Thread.sleep(1000);
		driver.get(url);
	}

	public static void typeAtElement(String xpathKey, String text) {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		searchElement(xpathKey).sendKeys(text);
	}

	public static WebElement waitTillElementLoad(String xpathKey) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathKey)));
	}

	public static void closeBrowser() {
		driver.close();
		driver.quit();
	}
}
