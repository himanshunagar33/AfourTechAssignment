package pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSeachPage {

	WebDriver driver;

	public GoogleSeachPage(WebDriver driver) {
		this.driver = driver;
	}

	By Searchbox = By.xpath("//*[@name='q']");
	By WikiLink = By.xpath("//*[contains(text(),'Wikipedia')]");

	public void search(String username) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='q']")));
		driver.findElement(Searchbox).sendKeys(username);
		driver.findElement(Searchbox).submit();
		Thread.sleep(3000);
	}

	public String searchwikiLink() {
		String wikiText = "";
		// Code for iterating and Searching the wikipedia page link
		for (int i = 3; i <= 11; i++) {
			try {
				WebElement Wiki = driver.findElement(WikiLink);

				if (Wiki.isDisplayed()) {
					// Scrolling to the Wikipedia search link
					WebElement WikiText = driver
							.findElement(By.xpath("//*[contains(text(),'Wikipedia')]//ancestor::div[2]"));
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView(true);", WikiText);
					Thread.sleep(3000);

					//Taking the screenshot of the particular wikipage link from Google Search				
					String userDir = System.getProperty("user.dir");
					File source = WikiText.getScreenshotAs(OutputType.FILE);
					File destination = new File(userDir + "//Screenshots//wiki.png");
					source.renameTo(destination);

					// Capturing the Required text from the search Result
					wikiText = driver
							.findElement(By.xpath("//*[contains(text(),'Wikipedia')]//ancestor::div[2]/div[2]/div"))
							.getText();
					break;
				}
			} catch (Exception e) {
				driver.findElement(By.xpath("//tbody/tr[1]/td[" + i + "]/a[1]")).click();
				;
			}
		}
		return wikiText;
	}

}
