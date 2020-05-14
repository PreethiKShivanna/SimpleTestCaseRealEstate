package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RealEstateDashBoardPOM {
	WebDriver driver;

	public RealEstateDashBoardPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//h1[contains(text(),'Dashboard')]")
	private WebElement HeadingDashboard;

	@FindBy(xpath = "//div[contains(text(),'Posts')]")
	public WebElement Posts;

	@FindBy(xpath = "//div[contains(text(),'Comments')]")
	public WebElement Comments;

	@FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/ul/li[3]/ul")
	public WebElement SubMenuPosts;

	@FindBy(xpath = "//ul[@class='wp-submenu wp-submenu-wrap']//li/a")
	WebElement SubMenuLinks;

	@FindBy(xpath = "//a[contains(text(),'All Posts')]")
	public WebElement SubMenuAllPosts;

	@FindBy(xpath = "//a[contains(text(),'Add New')]")
	public WebElement SubMenuAddNew;

	@FindBy(xpath = "//a[contains(text(),'Categories')]")
	public WebElement SubMenuCategories;

	@FindBy(xpath = "//a[contains(text(),'Tags')]")
	public WebElement SubMenuTags;

	@FindBy(linkText = "All Posts")
	private WebElement AllPosts;

	@FindBy(linkText = "Categories")
	private WebElement Categories;

	@FindBy(linkText = "Tags")
	private WebElement Tags;

	@FindBy(linkText = "Add New")
	private WebElement AddNew;

	
	public String getHeading() {
		return HeadingDashboard.getText();
	}

	public void clickonPosts() {
		AllPosts.click();
	}

	public void clickonComments() {
		Comments.click();
	}

	public void clickonAllPosts() {
		Posts.click();
	}
	
	public void clickonAddNew() {
		AddNew.click();
	}

	public void ClikOnCategories() {
		Categories.click();
	}

	public void ClickOnTags() {
		Tags.click();
	}

}
