package pageObjects;

import java.time.Duration;
import java.util.Map.Entry;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import helper.Driverclass;

public class yahooPage extends Driverclass{
	String searchbar = "//input[@id='ybar-sbq']";
	String autosugesstion = "(//ul[@role='listbox']//li/div)[1]";
	String autosugesstions = "//ul[@role='listbox']//li/div";
	String stockPrice = "//span[@data-testid='qsp-price']";
	String Otherdata = "//span[@title='PlaceHolder']/..//span[contains(@class, 'value')]";
	SoftAssert softAssert = new SoftAssert();
		public void geturl(String url)
		{
		getdriver().get(url);
		}
		public void searchStock(String Stock)
		{
			getdriver().findElement(By.xpath(searchbar)).clear();
			getdriver().findElement(By.xpath(searchbar)).sendKeys(Stock);
			getdriver().findElement(By.xpath(searchbar)).click();
		}
		public void getFirstSuggestion(String decscription)
		{
			WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(getdriver().findElement(By.xpath(autosugesstion))));
			List<WebElement> lstWb = getdriver().findElements(By.xpath(autosugesstions));
			WebElement first = lstWb.get(0);
			String suggestionText =first.getText();
			if(suggestionText.contains(decscription))
			{
				softAssert.assertTrue(suggestionText.contains(decscription), "Autosuggest is correct:");
				System.out.println("Autosuggest is correct: "+decscription+" :  Passed");
			}
			else
			{
				softAssert.assertFalse(suggestionText.contains(decscription), "Autosuggest is incorrect.");
				System.out.println("Autosuggest is incorrect.: Failed");
			}
			softAssert.assertAll();
		}
		public void clickFirstSuggestion()
		{
			getdriver().findElement(By.xpath(autosugesstion)).click();
		}
		public void validateStockPrice(int price)
		{
			 WebElement stockPriceElement = getdriver().findElement(By.xpath(stockPrice));
	            double stockPrice = Double.parseDouble(stockPriceElement.getText().replace(",", "").replace("$", ""));
	            System.out.println("Tesla Stock Price: $" + stockPrice);
	            if (stockPrice > price) {
	            	softAssert.assertTrue(stockPrice  > price,"Stock price is greater than $"+price+":");
	                System.out.println("Stock price is greater than $"+price+": Passed");
	            } else {
	            	softAssert.assertFalse(stockPrice  > price,"Stock price is not greater than $"+price+":");
	                System.out.println("Stock price is not greater than $"+price+": Failed");
	            }
	            softAssert.assertAll();
		}
		public void captureAdditionalData(Entry<Integer, String> nw)
		{
				String additiondata = nw.getValue();
	            String value = getdriver().findElement(By.xpath(Otherdata.replace("PlaceHolder",additiondata ))).getText();
	            System.out.println(additiondata+": " + value);
		}
		public void driverQuit()
		{
			getdriver().quit();
		}
		
}
