package o2stock;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class O2Stock extends WebDriverTestBase {
	
	@SuppressWarnings("unused")
	@DataProvider
	private Object[][] stores() {
		return new Object[][] {
//				{ "London", "London" },
				{ "South East", "Bluewater" },
//				{ "South East", "Dartford" },
		};
	}
	
	@Test(dataProvider = "stores")
	public void checkStock(String region, String city) {
		String modelTitle = "iPhone 4 32GB : Black";
		driver.get("http://stock.o2.co.uk/");
		driver.findElement(By.xpath("//input[following-sibling::label[@title='" + modelTitle + "']]")).click();
		driver.findElement(By.id("locRet")).click();
		new Select(driver.findElement(By.id("ukregions"))).selectByValue(region);  
		new Select(driver.findElement(By.id("citySel"))).selectByValue(city);  
		driver.findElement(By.xpath("//div[@class='sfSubmit']/input")).click();
		
		System.out.println(driver.findElement(By.className("timeStamp")).getText() + "\n");
		
		int stores = driver.findElements(By.xpath("id('tblStyleRetail')/tbody/tr")).size();
		for (int i = 1; i <= stores; i++) {
			WebElement store = driver.findElement(By.xpath("id('tblStyleRetail')/tbody/tr[" + i + "]"));
			int withStock = store.findElements(By.xpath(".//img[@alt='In stock']")).size();
			System.out.println(withStock + " in stock at " + store.findElement(By.xpath("./td[1]")).getText());
		}
		System.out.println();
	}
	
	@SuppressWarnings("unused")
	@DataProvider
	private Object[][] models() {
		return new Object[][] {
//				{ "iPhone 4", "16GB", "Black" },
//				{ "iPhone 4", "16GB", "White" },
				{ "iPhone 4", "32GB", "Black" },
//				{ "iPhone 4", "32GB", "White" },
//				{ "iPhone 3GS", "8GB", "Black" },
//				{ "iPhone 3GS", "16GB", "Black" },
//				{ "iPhone 3GS", "16GB", "White" },
//				{ "iPhone 3GS", "32GB", "Black" },
//				{ "iPhone 3GS", "32GB", "White" },
		};
	}
	
	@Test(dataProvider = "models")
	public void findStock(String model, String capacity, String colour) {
		String modelTitle = model + " " + capacity + " : " + colour; 
		String modelName = model + " " + capacity + " (" + colour + ")";
		driver.get("http://stock.o2.co.uk/");
		driver.findElement(By.xpath("//input[following-sibling::label[@title='" + modelTitle + "']]")).click();
		driver.findElement(By.id("locRet")).click();
		new Select(driver.findElement(By.id("ukregions"))).selectByValue("All");  
		new Select(driver.findElement(By.id("citySel"))).selectByValue("All");  
		driver.findElement(By.xpath("//div[@class='sfSubmit']/input")).click();
		
		System.out.println(driver.findElement(By.className("timeStamp")).getText() + "\n");
		
		List<WebElement> storesWithStock = driver.findElements(By.xpath("//img[@alt='In stock']/ancestor::tr"));
		if (storesWithStock.isEmpty()) {
			System.err.println(modelName + " is not in stock anywhere!\n");
			return;
		}
		
		System.out.println(modelName + " is in stock at the following " + storesWithStock.size() + " O2 stores:");
		for (WebElement store : storesWithStock) {
			System.out.println(store.findElement(By.xpath("./td[1]")).getText());
		}
		System.out.println();
	}
	
}
