//Medium Test Cases
//2.RETC_041 -To verify whether application display comments added by the user in admin page
package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RealEstateCommentsPOM;
import com.training.pom.RealEstateDashBoardPOM;
import com.training.pom.RealEstateLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import junit.framework.Assert;

public class RETC_041_MediumRealEstate_CommentDisplaysInCommentsPage {

	private WebDriver driver;
	private String baseUrl;
	private RealEstateLoginPOM REloginPOM;
	private RealEstateDashBoardPOM REDashBoardPOM;
	private RealEstateCommentsPOM RECommentsPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	public String EnteredComments;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		REloginPOM = new RealEstateLoginPOM(driver);
		REDashBoardPOM = new RealEstateDashBoardPOM(driver);
		RECommentsPOM = new RealEstateCommentsPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		driver.get(baseUrl);
		Thread.sleep(3000);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test(priority = 0)
	public void AddComment() throws InterruptedException {
		System.out.println("RETC_041 -To verify whether application display comments added by the user in admin page");
		REloginPOM.ClickBlogTab();
		REloginPOM.ClickOnReadMoreLink();
		REloginPOM.EnterInComments("RETC_041; Preethi Shivanna :This is the comment added by admin");
		EnteredComments = REloginPOM.Commentbox.getAttribute("value");
		Thread.sleep(2000);
		REloginPOM.EnterInName("Admin's Name");
		REloginPOM.EnterInemail("admin@in.ibm.com");
		REloginPOM.ClickonPostComment();
		screenShot.captureScreenShot("RETC_041_RealEstateCommentAddedyAdmin");
		
	}

	@Test(priority = 1)
	public void verifyAddedComment() {

		REloginPOM.clickonLoginRegisterLink();
		REloginPOM.enterUserName("PreethiKS");
		REloginPOM.enterPassword("IBMManipal1234");
		REloginPOM.ClickSignInBtn();
		REDashBoardPOM.clickonComments();

		String CommentSeenOnPage = RECommentsPOM.AddedComment.getText();
		Assert.assertEquals(EnteredComments, CommentSeenOnPage);

		screenShot.captureScreenShot("RETC_041_RealEstateAddedCommentsShown");
		System.out.println("Comments Added by Admin are shown");
	}
}
