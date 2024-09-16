package SQA;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test {
	WebDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String baseUrl = "http://localhost/wordpress/";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get(baseUrl + "wp-login.php");

		WebElement usernameField = driver.findElement(By.id("user_login"));
		WebElement passwordField = driver.findElement(By.id("user_pass"));
		WebElement loginButton = driver.findElement(By.id("wp-submit"));
		usernameField.sendKeys("Admin");
		passwordField.sendKeys("Rajesh@20");
		loginButton.click();

		driver.get(baseUrl + "wp-admin/plugins.php");
		String pluginName = "WP Dark Mode";
		boolean isPluginActive = false;

		try {
			WebElement pluginRow = driver.findElement(By.xpath("//strong[normalize-space()='WP Dark Mode']"));

			try {
				WebElement isActive = driver.findElement(By.xpath("//a[@id='activate-wp-dark-mode']"));

				System.out.println(pluginName + " is currently inactive.");
				WebElement activateButton = driver.findElement(By.xpath("//a[@id='activate-wp-dark-mode']"));
				activateButton.click();
				System.out.println(pluginName + " has been activated.");

			} catch (NoSuchElementException e) {
				System.out.println(pluginName + " is currently active.");
			}

		} catch (NoSuchElementException e) {
			System.out.println("The plugin " + pluginName + " is not installed on this site.");

			WebElement addNewPlugin = driver.findElement(By.xpath("//a[@class='page-title-action']"));
			addNewPlugin.click();

			WebElement searchPlugin = driver.findElement(By.xpath(" //input[@id='search-plugins']"));
			searchPlugin.sendKeys("WP Dark Mode");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			WebElement installButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//a[@aria-label='Install WP Dark Mode – WordPress Dark Mode Plugin for Improved Accessibility, Dark Theme, Night Mode, and Social Sharing 5.1.0 now']")));

			installButton.click();

			WebElement activateButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Activate']")));

			activateButton.click();

			driver.get(baseUrl + "wp-admin/plugins.php");
		}

		driver.get(baseUrl + "wp-admin/admin.php?page=wp-dark-mode#/switch");

		WebElement customizationTab = driver.findElement(By.cssSelector(
				"body > div:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2)\r\n"
						+ ""));
		customizationTab.click();

		WebElement customSizeOption = driver.findElement(By.cssSelector(
				"body > div:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(6) > span:nth-child(1)"));
		customSizeOption.click();

		WebElement scaleInput = driver.findElement(By.xpath("//input[@type='range']"));

		for (int i = 1; i <= 250; i++) {
			scaleInput.sendKeys(Keys.ARROW_LEFT);
		}
		
		for (int i = 1; i <= 170; i++) {
			scaleInput.sendKeys(Keys.ARROW_RIGHT);
		}

		WebElement leftOption = driver.findElement(By.xpath(
				"//body/div[@id='wpwrap']/div[@id='wpcontent']/div[@id='wpbody']/div[@id='wpbody-content']/div[@id='wp-dark-mode-admin']/div[@class='relative']/div[@class='relative']/div[@class='app-wrapper']/div[@class='main-content']/div[@class='main-content-body']/div[@class='rounded text-base flex flex-col gap-3 bg-transparent gap-5']/section[@class='flex flex-col gap-6 w-full']/div[@class='flex flex-col gap-6']/div[@class='rounded text-base flex flex-col gap-3 bg-transparent gap-5']/div[@class='rounded text-base flex flex-col gap-3 bg-transparent gap-5']/div[@class='rounded text-base flex flex-col gap-3 bg-white py-5 px-6 gap-8']/div[@class='flex gap-6 justify-between flex-wrap']/div[@class='flex flex-col w-fit gap-6']/div[2]/div[2]/div[1]"));
		leftOption.click();

		driver.get(baseUrl + "wp-admin/admin.php?page=wp-dark-mode#/accessibility");

		WebElement keyboardShortcutCheckbox = driver.findElement(By.xpath(
				"//div[@class='rounded text-base flex flex-col gap-3 bg-white py-5 px-6 gap-5']//div[1]//label[1]//div[1]//div[1]"));

		if (keyboardShortcutCheckbox.isEnabled()) {
			System.out.println("Enable radio button is selected.");
			keyboardShortcutCheckbox.click();
		} else {
			System.out.println("Enable radio button is not selected.");

		}

		driver.get(baseUrl + "wp-admin/admin.php?page=wp-dark-mode#/animation");

		WebElement saveButton = driver.findElement(By.xpath("//button[normalize-space()='Save Changes']"));
		saveButton.click();

		driver.get(baseUrl);
		WebElement darkLightMode = driver.findElement(By.xpath("//div[@class='_track wp-dark-mode-ignore']"));
		darkLightMode.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		darkLightMode.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		darkLightMode.click();

	}

}