package com.task;
import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Flipkart {
	static WebDriver driver;
	static long startTime;
	static String first;
	
	@BeforeClass
	public void beforeClass() throws InterruptedException {
		 System.setProperty("webdriver.chrome.driver", "F:\\\\java\\\\Selenium\\\\drivers\\\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("https://www.flipkart.com/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(3000);

	}
	
	@BeforeMethod
	public void beforeMethod() {
		
		 startTime = System.currentTimeMillis();

	}
	
	@AfterMethod
    public void afterMethod() {
		
		long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");

	}
	
	@AfterClass
    public static void afterClass() {
		
		driver.quit();

	}
	
	@Test(priority = -1)
	public void login() throws Throwable {
		driver.findElement(By.xpath("(//input[@autocomplete='off'])[2]")).sendKeys("9597666445");
		driver.findElement(By.xpath("(//input[@autocomplete='off'])[3]")).sendKeys("vijay1995");
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		Thread.sleep(2000);

	}
	
	@Test(priority = 1)
	public void search() throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("reamle c21y black");
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
		Thread.sleep(2000);

	}
	
	@Test(priority = 2)
	public void getText() throws InterruptedException {
		
		WebElement text = driver.findElement(By.xpath("(//*[@class='_4rR01T'])[1]"));
	    first = text.getText();
		System.out.println(first);
		Thread.sleep(3000);
		text.click();
		Thread.sleep(3000);

	}
	
	public void windowHandle() throws InterruptedException {
		Thread.sleep(5000);
		String par = driver.getWindowHandle();
		Thread.sleep(3000);
		Set<String> child = driver.getWindowHandles();
		
		for(String x : child) {
			if(!par.equals(x)) {
				System.out.println("tab switched");
				driver.switchTo().window(x);
			}
		}

	}
	
	@Test(priority = 3)
	public void windows() throws InterruptedException {
		
		windowHandle();
	
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		WebElement real = driver.findElement(By.xpath("//*[@class='B_NuCI']"));
		wait.until(ExpectedConditions.elementToBeClickable(real));
		String second = real.getText();
		System.out.println(second);
		
		if(first == second) {
			System.out.println("The Parent Window Name and Child Window Name are SAME");
		}
		else {
			System.out.println("The Parent and child Window name are NOT SAME");
		}
		WebElement down = driver.findElement(By.xpath("//*[text()='RMX3261 / RMX3263']"));
		JavascriptExecutor jsc = (JavascriptExecutor)driver;
		jsc.executeScript("arguments[0].scrollIntoView(true)",down);
		Thread.sleep(2000);
		
		
		WebElement mark = driver.findElement(By.xpath("//*[text()='Smartphones']"));
		//Assert.assertTrue(mark.isSelected());
		System.out.println(mark.getText());
		String text = mark.getText();
		System.out.println(text);
		Assert.assertEquals("Smartphones", text);
		
		Thread.sleep(3000);
		Actions ac = new Actions(driver);
		Thread.sleep(3000);
		//ac.moveToElement(mark).perform();
		ac.doubleClick(mark).build().perform();

	}
	
	
	@Test(priority = 4)
	public void screenshot() {
		
		TakesScreenshot ts= (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File("F:\\java\\testNG\\screenshot");

	}
}
