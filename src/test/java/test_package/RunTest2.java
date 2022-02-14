package test_package;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageObject.ChildPage1;
import pageObject.ParentPage1;
import resources.BrowserClass;
import resources.FileHandler;

import java.io.BufferedWriter;
import java.io.IOException;

public class RunTest2 extends BrowserClass{
	
	WebDriver driver;
	ParentPage1 pg;
	ChildPage1 cp;
	String cData;
	static BufferedWriter BW;
	WebElement pointer;
	String loopNumber;


	@Test
	
	public void getdata() throws IOException, InterruptedException
	{
		driver = initializeDriver();	
	    String baseUrl = "https://www.nite.go.jp/en/chem/chrip/chrip_search/cmpInfLst?slIdxNm=&slScNm=RJ_04_071&slScCtNm=&slScRgNm=&ltCatFl=&slMdDplt=0&ltPgCt=500&stMd=&adMdCl=";
	    driver.get(baseUrl);
	    BW=FileHandler.getBW();
	    String header = "[ID-NUM];DataProvider;Casnum;[ID-NUM2];[ID-NUM3];[CHEM-SYN-LONG];Eff_date;Code3;[ID-NUM4]";
	    BW.write(header);
	    BW.newLine();
	    pg = new ParentPage1(driver);

	    
//	    for(int i = 0; i<2;i++){
	    for(int i = 0; i<pg.getParentPagess();i++){
	    	
	    	loop();
	    	Thread.sleep(3000);
	    	if(i!=pg.getParentPagess()-1)
	    	pg.clickOnNextPage();
	    }
	    
	    Thread.sleep(3000);
	    BW.close();
	    tearDown();
	    
	}   
	    
	    
	 public void loop() throws InterruptedException{
       

//        for (int j=497;j<=499;j++)
        for (int j=0;j<=pg.parentPageColumns().size()-1;j++)
		{
			Thread.sleep(3000);
			pointer= pg.parentPageColumns().get(j);
			loopNumber=pointer.getText();

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pointer);
			pointer.click();
			
			cp= new ChildPage1(driver);
			cData = cp.getChildData();
			cData = loopNumber+";"+cData;
			System.out.println(cData);
			
			try {
				BW.write(cData);
				BW.newLine();
			}catch (IOException e) {
				System.out.println("Not writining into Text File");
				e.printStackTrace();
			}			
			cp.NavigateToParent();	
			
			
		}
		
	}
}
	
