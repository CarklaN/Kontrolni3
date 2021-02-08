package kontrolni3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Testiranje {
	WebDriver driver;
	@BeforeClass
	public void uvod() {
		System.setProperty("webdriver.chrome.driver", "C:\\drajver\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(Objekti.HOMEURL);
	}
	@Test (priority=1)
	public void invalidData() {

		File f = new File("InvalidData.xlsx"); 
		try {
			InputStream inp = new FileInputStream(f); 
			XSSFWorkbook wb = new XSSFWorkbook(inp); 
			Sheet sheet = wb.getSheetAt(0); 

			SoftAssert sa = new SoftAssert();
			
			for (int i = 0; i < 3 ; i++) {
				
				Row row = sheet.getRow(i);
				
				Cell c1 = row.getCell(0);
				Cell c2 = row.getCell(1);
	
				
				String username = c1.toString();
				String password = c2.toString();
				
				driver.navigate().to(Objekti.HOMEURL);
				Objekti.inputUsername(driver, username);
				Objekti.inputPassword(driver, password);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Objekti.login(driver);
				
				String actual = driver.getCurrentUrl();
				String expected = "https://www.saucedemo.com/inventory.html";
				
				sa.assertEquals(actual, expected);
				
			}
			sa.assertAll();
			
			wb.close();
			
			
		} catch (IOException e) {
			System.out.println("Nije pronadjen fajl!");
			e.printStackTrace();
		} 
		
		
	}

	@Test (priority=2)
	public void validData() {
		File f = new File("ValidData.xlsx"); 
		try {
			InputStream inp = new FileInputStream(f); 
			XSSFWorkbook wb = new XSSFWorkbook(inp); 
			Sheet sheet = wb.getSheetAt(0); 

			SoftAssert sa = new SoftAssert();
			
			for (int i = 0; i < 4 ; i++) {
				
				Row row = sheet.getRow(i);
				
				Cell c1 = row.getCell(0);
				Cell c2 = row.getCell(1);
	
				
				String username = c1.toString();
				String password = c2.toString();
				
				driver.navigate().to(Objekti.HOMEURL);
				Objekti.inputUsername(driver, username);
				Objekti.inputPassword(driver, password);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Objekti.login(driver);
				
				String actual = driver.getCurrentUrl();
				String expected = "https://www.saucedemo.com/inventory.html";
				
				sa.assertEquals(actual, expected, username);
				
			}
			driver.close();
			sa.assertAll();
			
			wb.close();
			
			
		} catch (IOException e) {
			System.out.println("Nije pronadjen fajl!");
			e.printStackTrace();
		} 
		
	}
	@Test(priority=3)
	public void sortTest() {
		boolean lth=false;
		Objekti.inputUsername(driver, "standard_user");
		Objekti.inputPassword(driver, "secret_sauce");
		Objekti.login(driver);
		Objekti.dropDownMenu(driver);
		Objekti.lowToHigh(driver);
		List<Double> values=new ArrayList<Double>();
		List<WebElement> elements=driver.findElements(By.className("inventory_item_price"));
		for(WebElement e : elements) {
			String s=e.getText();
			String price=s.replace("$", "");
			double d=Double.parseDouble(price);
			values.add(d);
			System.out.println(d);
		}
		for(int i=0; i<6;i++) {
			for(int j=i+1; j<6;j++) {
			if(values.get(i)<(values.get(j)))
				lth=true;
			else
				lth=false;
		}	
			System.out.println(lth);
			Assert.assertEquals(lth, true);
	}	
	}
}
