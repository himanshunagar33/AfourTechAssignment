package TestScripts;

import java.util.Collections;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.GoogleSeachPage;

public class SearchWikicontent {

	WebDriver driver;

	@BeforeMethod
	public void Setup() {
		String userDir = System.getProperty("user.dir");
		System.setProperty("WebDriver.chrome.driver", userDir + "\\ChromeDriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
		driver = new ChromeDriver(options);
	}

	@AfterMethod
	public void TearDown() {
		driver.close();
	}

	@Test
	public void SearchOnGoogle() throws InterruptedException {
		driver.get("https://www.google.com/");
		GoogleSeachPage googlesearch = new GoogleSeachPage(driver);
		googlesearch.search("Selenium hq");
		String str = googlesearch.searchwikiLink();
		Assert.assertFalse(str.isEmpty());
		System.out.println("Output  : \n" + str);

	}
}
