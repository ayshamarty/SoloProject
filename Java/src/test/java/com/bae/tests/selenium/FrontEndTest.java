package com.bae.tests.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrontEndTest {

	public static WebDriver driver;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\aysha\\Downloads\\chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@After
	public void teardown() {
		driver.quit();
	}

	@Ignore
	@Test
	public void testMoreDetails() {
		driver.manage().window().maximize();
		driver.get("http://35.228.110.11:8888/Yoga/index.html");

		WebElement goToPoses = driver.findElement(By.xpath("//*[@id=\"sharedNav\"]/nav/button/span"));	

		goToPoses.click();
		goToPoses = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a"));
		goToPoses.click();
		goToPoses = driver.findElement(By.xpath("//*[@id=\"table\"]/tr[2]/td[5]/button"));
		goToPoses.click();
		goToPoses = driver.findElement(By.id("poseIMG"));
		assertTrue(goToPoses.isDisplayed());

	}

}