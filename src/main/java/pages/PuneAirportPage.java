package pages;

import java.io.File;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PuneAirportPage {

	WebDriver driver;

	public PuneAirportPage(WebDriver driver) {
		this.driver = driver;
	}

	// WebElement useds
	By ArrivalTable = By.xpath("//body/div[@id='content']/div[2]/section[1]//section[1]//div[1]/table[1]/tbody");
	By firstrowlink = By.xpath("//body/div[@id='content']/div[2]/section[1]//section[1]//div[1]/table[1]/tbody/tr[1]");

	// method to navigate to the Arrivals table
	public void NavigattoArrivals() throws InterruptedException {
		WebElement table = driver.findElement(ArrivalTable);
		WebElement firstrow = driver.findElement(firstrowlink);
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(table));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", firstrow);

		// Screenshot of the table in the Screenshot folder
		String userDir = System.getProperty("user.dir");
		File source = table.getScreenshotAs(OutputType.FILE);
		File destination = new File(userDir + "//Screenshots//MobileArrivalTable.png");
		source.renameTo(destination);
		Thread.sleep(3000);
	}

	// Method to get the flight arrival status from the table
	public void getArrivalStatus(String place) {
		List<WebElement> table_rows = driver.findElements(
				By.xpath("//body/div[@id='content']/div[2]/section[1]//section[1]//div[1]/table[1]/tbody/tr"));
		int rowsize = table_rows.size();
		String ArrivalTime = "";
		for (int i = 0; i <= rowsize; i++) {
			List<WebElement> Row_cols = driver.findElements(By.xpath(
					"//body/div[@id='content']/div[2]/section[1]//section[1]//div[1]/table[1]/tbody/tr[" + i + "]/td"));
			if (Row_cols.size() == 7) {
				String fromPlace = driver.findElement(
						By.xpath("//body/div[@id='content']/div[2]/section[1]//section[1]//div[1]/table[1]/tbody/tr["
								+ i + "]/td[3]/div[1]/span[1]"))
						.getText();

				if (fromPlace.equalsIgnoreCase(place)) {
					ArrivalTime = driver.findElement(By
							.xpath("//body/div[@id='content']/div[2]/section[1]//section[1]//div[1]/table[1]/tbody/tr["
									+ i + "]/td[7]"))
							.getText();

					System.out.println(place + " : " + ArrivalTime);
					// Uncomment the break statement if you want to check only the first flight
					// status for the given place

					// break;
				}
			}
		}
		if (ArrivalTime.isEmpty()) {
			ArrivalTime = "No Data Available";
			System.out.println(place + " : " + ArrivalTime);
		}

	}

	public void getArrivalStatusMobile(String place) {
		List<WebElement> table_rows = driver.findElements(
				By.xpath("//body/div[@id='content']/div[2]/section[1]//section[1]//div[1]/table[1]/tbody/tr"));
		int rowsize = table_rows.size();
		String ArrivalTime = "";
		for (int i = 0; i <= rowsize; i++) {
			List<WebElement> Row_cols = driver.findElements(
					By.xpath("//body/div[@id='content']/div[2]/section[1]//section[1]//div[1]/table[1]/tbody/tr[" + i
							+ "]/td/div[2]/div"));
			if (Row_cols.size() == 3) {
				String fromPlace = driver.findElement(
						By.xpath("//body/div[@id='content']/div[2]/section[1]//section[1]//div[1]/table[1]/tbody/tr["
								+ i + "]/td/div[2]/div[3]/span"))
						.getText();

				if (fromPlace.equalsIgnoreCase(place)) {
					ArrivalTime = driver.findElement(By
							.xpath("//body/div[@id='content']/div[2]/section[1]//section[1]//div[1]/table[1]/tbody/tr["
									+ i + "]/td/div[1]/div"))
							.getText();

					System.out.println(place + " : " + ArrivalTime);
				}
			}
		}
		if (ArrivalTime.isEmpty()) {
			ArrivalTime = "No Data Available";
			System.out.println(place + " : " + ArrivalTime);
		}
	}
}