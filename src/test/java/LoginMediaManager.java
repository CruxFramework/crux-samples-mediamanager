import java.io.File;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginMediaManager {
    	
	private String baseUrl;
	private WebDriver driver;
	private final StringBuffer verificationErrors = new StringBuffer();

	private final long defaultTimeDelay = 1000;
	
	public void setURL() {
		// baseUrl = "http://halley.sysmap.com.br:8081/cruxsite/";
		// baseUrl = "http://halley.sysmap.com.br:8081/";
		baseUrl = "http://halley.sysmap.com.br:8081/mediamanager/mediamanager/";
	}
	
	@Before
	public void openBrowserFirefox() {
		setURL();
		driver = new FirefoxDriver();		
	}
	 

	@After
	public void closeBrowser() throws InterruptedException {
		  driver.quit();
		  Thread.sleep(defaultTimeDelay * 3);
	}
	
	@Test
	public void testSite_Navega01() throws Exception {
		
		File scrFile;
		
		driver.get(baseUrl + "index.html");
		// driver.get(baseUrl + "/cruxsite/cruxsite/index.html#topMenuDisposal:home");
		Thread.sleep(defaultTimeDelay);

		scrFile = ((FirefoxDriver) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("D:\\tmp\\selenium-out-file-test\\tela-01.png"),true);	
		
		
		driver.findElement(By.id("login_loginTextBox")).clear();		
		driver.findElement(By.id("login_loginTextBox")).sendKeys("admin");
		Thread.sleep(defaultTimeDelay);
		
		driver.findElement(By.id("login_passwordTextBox")).clear();
		driver.findElement(By.id("login_passwordTextBox")).sendKeys("admin");
		Thread.sleep(defaultTimeDelay);
		
		driver.findElement(By.id("login_loginButton")).click();
		Thread.sleep(defaultTimeDelay);
		
		driver.findElement(By.id("menu_searchMediaButton")).click();
		Thread.sleep(defaultTimeDelay);
		
		driver.findElement(By.id("medias_pesquisarButton")).click();
		Thread.sleep(defaultTimeDelay);
		
		driver.findElement(By.id("medias_edit")).click();
		Thread.sleep(defaultTimeDelay);
		
		driver.findElement(By.id("media_updateButton")).click();
		Thread.sleep(defaultTimeDelay);
		
		driver.findElement(By.cssSelector("div.messageBoxContents > button.faces-Button")).click();
		Thread.sleep(defaultTimeDelay);
		
		driver.findElement(By.id("menu_signOutButton")).click();
		Thread.sleep(defaultTimeDelay);

	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
