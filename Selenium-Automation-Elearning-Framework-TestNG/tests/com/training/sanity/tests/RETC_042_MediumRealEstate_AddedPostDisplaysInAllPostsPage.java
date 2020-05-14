//Medium Test Cases
//3.RETC_042 - To verify whether application displays added post in all post 
package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RealEstateAddNewPOM;
import com.training.pom.RealEstateAllPostsPOM;
import com.training.pom.RealEstateCommentsPOM;
import com.training.pom.RealEstateDashBoardPOM;
import com.training.pom.RealEstateLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import junit.framework.Assert;

public class RETC_042_MediumRealEstate_AddedPostDisplaysInAllPostsPage {

	private WebDriver driver;
	private String baseUrl;
	private RealEstateLoginPOM REloginPOM;
	private RealEstateDashBoardPOM REDashBoardPOM;
	private RealEstateAddNewPOM REAddNewPOM;
	private RealEstateAllPostsPOM REAllPostsPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	public String EnteredComments;

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
		REAddNewPOM = new RealEstateAddNewPOM(driver);
		REAllPostsPOM = new RealEstateAllPostsPOM(driver);
		
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		driver.get(baseUrl);
		Thread.sleep(3000);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test(priority = 0)
	public void LogIn() {
		
		REloginPOM.clickonLoginRegisterLink();
		REloginPOM.enterUserName("PreethiKS");
		REloginPOM.enterPassword("IBMManipal1234");
		REloginPOM.ClickSignInBtn();
	}
	
	

	@Test(priority = 1)
	public void AddPost() throws InterruptedException{
		System.out
				.println("RETC_042 : To verify whether application displays added post in all post ");
		Actions act = new Actions(driver);
		act.moveToElement(REDashBoardPOM.Posts).build().perform();
		screenShot.captureScreenShot("SubMenuUnderPosts");

		Thread.sleep(3000);
		boolean viewSubMenuPosts = REDashBoardPOM.SubMenuPosts.isDisplayed();
		boolean viewAllPosts = REDashBoardPOM.SubMenuAllPosts.isDisplayed();
		boolean viewAddNew = REDashBoardPOM.SubMenuAddNew.isDisplayed();
		boolean viewCategories = REDashBoardPOM.SubMenuCategories.isDisplayed();
		boolean viewTags = REDashBoardPOM.SubMenuTags.isDisplayed();

		Assert.assertTrue(viewSubMenuPosts);
		Assert.assertTrue(viewAllPosts);
		Assert.assertTrue(viewAddNew);
		Assert.assertTrue(viewCategories);
		Assert.assertTrue(viewTags);

		REDashBoardPOM.clickonPosts();
		REDashBoardPOM.clickonAddNew();
		
	
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(REAddNewPOM.AddNewPostHeading));
		boolean viewAddNewPost = REAddNewPOM.AddNewPostHeading.isDisplayed();
		Assert.assertTrue(viewAddNewPost);
		
		REAddNewPOM.EnterPostTitle("Prestige Builders");
		String PostTitle = REAddNewPOM.TitleTextBox.getAttribute("value");
		Thread.sleep(5000);
		driver.switchTo().frame("content_ifr");
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(REAddNewPOM.TextArea));
		REAddNewPOM.EnterPostBody("New Launch in Home");
		driver.switchTo().defaultContent();
		REAddNewPOM.ClickPublishBtn();
		screenShot.captureScreenShot("RETC_042_RealEstatePostAddedFromAddNew");
		Thread.sleep(2000);
		REAddNewPOM.ClickPublishBtn();
		Thread.sleep(3000);
		REDashBoardPOM.clickonAllPosts();
		screenShot.captureScreenShot("RETC_042_RealEstatePostsSeenUnderAllPosts");
		String AddedPostTitle=REAllPostsPOM.AddedPost.getText();
		Assert.assertEquals(PostTitle, AddedPostTitle);
		System.out.println("Post added by the admin is displayed");
		REAllPostsPOM.ClickOnAddedPost();
		String EditPost=REAllPostsPOM.EditPost.getAttribute("value");
		System.out.println("Value in Edit post :"  + EditPost);
		Assert.assertEquals("Details of added post is displayed", AddedPostTitle, EditPost);
		

}
}
