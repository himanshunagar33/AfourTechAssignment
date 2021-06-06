package TestScripts;

import java.util.Collections;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PuneAirportPage;

public class ArrivalStatus {
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
	public void GetArrivalStatus() throws InterruptedException {
		driver.get("https://www.flightradar24.com/data/airports/pnq");
		PuneAirportPage puneairport= new PuneAirportPage(driver);
		puneairport.NavigattoArrivals();
		puneairport.getArrivalStatus("Bangaluru");
		puneairport.getArrivalStatus("Delhi");
		puneairport.getArrivalStatus("Goa");
		puneairport.getArrivalStatus("Chandigarh");
		puneairport.getArrivalStatus("Hyderabad");
		puneairport.getArrivalStatus("Nagpur");
		puneairport.getArrivalStatus("Dubai");
		
		
	}
}
