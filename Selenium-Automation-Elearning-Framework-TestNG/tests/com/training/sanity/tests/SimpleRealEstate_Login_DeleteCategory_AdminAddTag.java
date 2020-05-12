//Simple Tests
//1.RETC-011-To Verify whether application allows registered admin to login into application
//2.RETC-019-TO Verify whether application allows admin to delete category from the categories page
//3.RETC-020-TO Verify whether application allows admin to add new tag

package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RealEstateLoginPOM;
import com.training.pom.RealEstateTagsPOM;
import com.training.pom.RealEstateCategoriesPOM;
import com.training.pom.RealEstateDashBoardPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
import org.testng.Assert;

public class SimpleRealEstate_Login_DeleteCategory_AdminAddTag {

	private WebDriver driver;
	private String baseUrl;
	private RealEstateLoginPOM REloginPOM;
	private RealEstateDashBoardPOM REDashBoardPOM;
	private RealEstateCategoriesPOM RECategoriesPOM;
	private RealEstateTagsPOM RETagPOM;
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
		RETagPOM = new RealEstateTagsPOM(driver);
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

	@Test(priority = 1)
	public void deletecategories() throws InterruptedException {
		System.out.println(
				"RETC-019:  To Verify whether application allows admin to delete category from the categories page");
		Thread.sleep(3000);

		Actions act = new Actions(driver);
		act.moveToElement(REDashBoardPOM.Posts).build().perform();
		screenShot.captureScreenShot("SubMenuUnderPosts");
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
		REDashBoardPOM.ClikOnCategories();
		Thread.sleep(3000);
		if (RECategoriesPOM.AddNewCategoryLabel.isDisplayed()) {
			System.out.println("Add New Category - label is displayed");
		}

		if (RECategoriesPOM.Categorytable.isDisplayed()) {
			System.out.println("Category table is displayed");
		}

		RECategoriesPOM.SelectCategoryCheckBoxToDelete();
		Thread.sleep(3000);
		RECategoriesPOM.SelectCategoryBulkActionsDropDown();
		Thread.sleep(3000);
		RECategoriesPOM.ClikOnApplyOnCategoryTable();
		String Expected = "Categories deleted.";
		String Actual = RECategoriesPOM.CategoryDeleteMsg();
		Assert.assertEquals(Expected, Actual);
		System.out.println("Categories deleted,message  displayed");
		screenShot.captureScreenShot("RETC_019_RealEstateDeleteCategory");
		System.out.println("Admin can delete category");
	}

	@Test(priority = 2)
	public void AddTagDashBoard() throws InterruptedException {
		System.out.println("RETC-020 : To Verify whether application allows admin to add new tag");
		REDashBoardPOM.clickonPosts();

		System.out.println("Values inside sub-menu");
		List<WebElement> alllinks = driver.findElements(By.xpath("//ul[@class='wp-submenu wp-submenu-wrap']//li/a"));
		for (WebElement element : alllinks) {

			System.out.println(element.getText());
		}

		Thread.sleep(3000);
		REDashBoardPOM.ClickOnTags();

		if (RETagPOM.AddNewTagLabel.isDisplayed()) {
			System.out.println("Add New Tag - label is displayed");
		}

		if (RETagPOM.Tagtable.isDisplayed()) {
			System.out.println("Tag table is displayed");
		}

		RETagPOM.OperatiOnTagNameField("Reshma");
		RETagPOM.OperatiOnTagSlug("Apartment");
		RETagPOM.OperatiOnTagDescription("Apartments, flats,villas");
		RETagPOM.OperatiOnAddNewTagBtn();
		
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(15000);
		Assert.assertEquals(RETagPOM.AddedTag.getText(), "Reshma");
		screenShot.captureScreenShot("RETC_020_RealEstateDashBoardAddTag");
		Thread.sleep(10000);
		System.out.println("Admin can add new tag");

	}

}
