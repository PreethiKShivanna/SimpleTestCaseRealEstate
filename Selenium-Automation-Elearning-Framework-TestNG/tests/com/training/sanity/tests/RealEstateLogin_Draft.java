
package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RealEstateDashBoardPOM;
import com.training.pom.RealEstateLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RealEstateLogin_Draft {

	private WebDriver driver;
	private String baseUrl;
	private RealEstateLoginPOM REloginPOM;
	private RealEstateDashBoardPOM REDashBoardPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeSuite
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		REloginPOM = new RealEstateLoginPOM(driver);
		REDashBoardPOM = new RealEstateDashBoardPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
		Thread.sleep(3000);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test(priority = 0)
	public void validLoginTest() {
		System.out
				.println("RETC-011 : To Verify whether application allows registered admin to login into application");
		REloginPOM.clickonLoginRegisterLink();
		REloginPOM.enterUserName("PreethiKS");
		REloginPOM.enterPassword("IBMManipal1234");
		REloginPOM.ClickSignInBtn();
		String ExpectedTitle = "Dashboard";
		String ActualTitle = REDashBoardPOM.getHeading();
		Assert.assertEquals(ActualTitle, ExpectedTitle);
		System.out.println("Dashboard Seen");
		screenShot.captureScreenShot("RETC_011_RealEstateLoginDashboard");
		System.out.println("Admin can login into application");
	}



}
