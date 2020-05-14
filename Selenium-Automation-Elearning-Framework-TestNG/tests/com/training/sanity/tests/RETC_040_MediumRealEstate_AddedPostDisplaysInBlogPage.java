//Medium Test Cases
//1.RETC_040 - To verify whether application displays added post in blog section of home screen

package com.training.sanity.tests;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RealEstateAddNewPOM;
import com.training.pom.RealEstateAllPostsPOM;
import com.training.pom.RealEstateCategoriesPOM;
import com.training.pom.RealEstateDashBoardPOM;
import com.training.pom.RealEstateLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_040_MediumRealEstate_AddedPostDisplaysInBlogPage {

	private WebDriver driver;
	private String baseUrl;
	private RealEstateLoginPOM REloginPOM;
	private RealEstateDashBoardPOM REDashBoardPOM;
	private RealEstateCategoriesPOM RECategoriesPOM;
	private RealEstateAllPostsPOM REAllPostsPOM;
	private RealEstateAddNewPOM REAddNewPOM;
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
		RECategoriesPOM = new RealEstateCategoriesPOM(driver);
		REAllPostsPOM = new RealEstateAllPostsPOM(driver);
		REAddNewPOM = new RealEstateAddNewPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		driver.get(baseUrl);
		Thread.sleep(3000);
	}

	@AfterSuite
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test(priority = 0)
	public void LogintoRealEstateApp() {
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
		screenShot.captureScreenShot("RETC_040_RealEstateLoginDashboard");
		System.out.println("Admin can login into application");
	}

	@Test(priority = 1)
	public void VerifyAddedPostUnderBlog() throws InterruptedException, AWTException {
		System.out
				.println("RETC_040 - To verify whether application displays added post in blog section of home screen");
		Thread.sleep(3000);

		Actions act = new Actions(driver);
		act.moveToElement(REDashBoardPOM.Posts).build().perform();
		screenShot.captureScreenShot("RETC_040_RealEstateSubMenuUnderPosts");

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
		Thread.sleep(3000);
		REDashBoardPOM.ClikOnCategories();

		Thread.sleep(3000);
		if (RECategoriesPOM.AddNewCategoryLabel.isDisplayed()) {
			System.out.println("Add New Category - label is displayed");
		}

		if (RECategoriesPOM.Categorytable.isDisplayed()) {
			System.out.println("Category table is displayed");
		}

		RECategoriesPOM.OperatiOnCategoryNameField("Brigade Apartments");
		RECategoriesPOM.OperatiOnCategorySlug("launch");
		RECategoriesPOM.OperatiOnCategoryDescription("New Launches of villas, apartments, flats");
		RECategoriesPOM.OperatiOnAddNewCategoryBtn();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		Assert.assertEquals(RECategoriesPOM.AddedCategory.getText(), "Brigade Apartments");
		Thread.sleep(2000);
		screenShot.captureScreenShot("RETC_040_RealEstateDashBoardAddCategory");
		Thread.sleep(2000);
		REDashBoardPOM.clickonAllPosts();
		REAllPostsPOM.ClickOnAddNewBtn();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(REAddNewPOM.AddNewPostHeading));
		boolean viewAddNewPost = REAddNewPOM.AddNewPostHeading.isDisplayed();
		Assert.assertTrue(viewAddNewPost);
		REAddNewPOM.EnterPostTitle("Brigade Flats");
		screenShot.captureScreenShot("RETC_040_RealEstatePostAdded");
		String PostTitle = REAddNewPOM.TitleTextBox.getAttribute("value");
		Thread.sleep(2000);

		driver.switchTo().frame("content_ifr");
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(REAddNewPOM.TextArea));
		REAddNewPOM.EnterPostBody("New Launch in Home");
		driver.switchTo().defaultContent();

		REAddNewPOM.SelectCategory();
		REAddNewPOM.ClickPublishBtn();
		Thread.sleep(2000);
		REAddNewPOM.ClickPublishBtn();
		Boolean ViewPostsVisible = REAddNewPOM.ViewPostLink.isDisplayed();
		Assert.assertTrue(ViewPostsVisible);
		REAddNewPOM.ClickOnViewPosts();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		screenShot.captureScreenShot("RETC_040_RealEstatePostAddedInBlogSection");
		String AddedPostTitle = REloginPOM.AddedPost.getText();
		Assert.assertEquals(AddedPostTitle, PostTitle);

	}

}
