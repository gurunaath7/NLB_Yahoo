package tests;

import org.testng.annotations.Test;
import pageObjects.*;
import java.util.*;
public class yahooTSLA {
	yahooPage yp = new yahooPage();
	@Test
	public void finyahoo()throws Exception {
		String stockName="TSLA"; 
		String stockSuggestion ="Tesla, Inc";
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "Previous Close");
		map.put(2, "Volume");
		map.put(3, "Open");
		map.put(4, "Avg. Volume");
		yp.geturl("https://finance.yahoo.com/");
		yp.searchStock(stockName);
		yp.getFirstSuggestion(stockSuggestion);
		yp.clickFirstSuggestion();
		yp.validateStockPrice(200);
		for (Map.Entry<Integer, String> nw :
			map.entrySet()) {
			yp.captureAdditionalData(nw);
       }
		yp.driverQuit();

	}
	

}
