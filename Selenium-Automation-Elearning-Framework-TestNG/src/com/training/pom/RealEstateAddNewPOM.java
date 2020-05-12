package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RealEstateAddNewPOM {
	WebDriver driver;

	public RealEstateAddNewPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

		
	@FindBy(xpath = "//h1[@class='wp-heading-inline']")
	public WebElement AddNewPostHeading;

	@FindBy(xpath = "//input[@id='title']")
	public WebElement TitleTextBox;

	@FindBy(xpath = "//textarea[@id='content']")
	public WebElement TextArea;

	@FindBy(xpath = "//label[contains(text(),'DHFL Villa')]")
	private WebElement CategoryName;

	@FindBy(xpath = "//input[@id='publish']")
	private WebElement PublishBtn;

	@FindBy(xpath = "//a[contains(text(),'View post')]")
	public WebElement ViewPostLink;


	public void EnterPostTitle(String title) {
		TitleTextBox.sendKeys(title);
	}

	public void EnterPostBody(String postbody) {
		TextArea.sendKeys(postbody);
	}

	public void SelectCategory() {
		CategoryName.click();
	}

	public void ClickPublishBtn() {
		PublishBtn.click();
	}

	public void ClickOnViewPosts() {
		ViewPostLink.click();
	}

}
