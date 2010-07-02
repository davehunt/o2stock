package o2stock;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class WebDriverTestBase {

	public static FirefoxDriver driver;
	public static Wait<WebDriver> wait;

	@BeforeMethod(alwaysRun = true)
    protected void startWebDriver() {
    	driver = new FirefoxDriver();
    	wait = new WebDriverWait(driver, 60);
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    protected void closeSession() {
	    driver.close();
    }
    
}
