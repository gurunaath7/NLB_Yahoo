package helper;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driverclass {
	public WebDriver driver;
	  public Driverclass(){
		  try {
			  String path = System.getProperty("user.dir");
			  System.out.println("path"+path);
		  System.setProperty("webdriver.chrome.driver", new File(System.getProperty("user.dir"), "chromedriver.exe").getAbsolutePath());
//		  System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		  ChromeOptions option=new ChromeOptions();
		  option.setPageLoadStrategy(PageLoadStrategy.NONE);
		  driver =new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		  }catch(Exception e)
		  {
			  System.out.println(e);
		  }
	  }
	  public WebDriver getdriver(){
	    if (driver == null){
	      driver = new ChromeDriver();
	      return driver;
	    }else{
	      return driver;
	    }
	  }

}
