package kontrolni3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Objekti {
	public static final String HOMEURL="https://www.saucedemo.com/";
	private static final String USERNAME_XPATH = "//*[@id=\"user-name\"]";
	private static final String PASS_XPATH = "//*[@id=\"password\"]";
	private static final String LIST_XPATH="//*[@id=\"inventory_filter_container\"]/select";

	public static void inputUsername(WebDriver driver, String username) {
		driver.findElement(By.xpath(USERNAME_XPATH)).sendKeys(username);
	}
	public static void inputPassword(WebDriver driver, String password) {
		driver.findElement(By.name("password")).sendKeys(password);
	}
	public static void login(WebDriver driver) {
		driver.findElement(By.className("btn_action")).click();
	}
	public static void dropDownMenu(WebDriver driver) {
		driver.findElement(By.xpath(LIST_XPATH)).click();
	}
	public static void lowToHigh(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select/option[3]")).click();
	}
}
