package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RealEstateTagsPOM {
	WebDriver driver;

	public RealEstateTagsPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[contains(text(),'Add New Tag')]")
	public WebElement AddNewTagLabel;

	@FindBy(xpath = "//table[@class='wp-list-table widefat fixed striped tags']")
	public WebElement Tagtable;

	@FindBy(xpath = "//input[@id='tag-name']")
	private WebElement TagNameTextBox;

	@FindBy(xpath = "//input[@id='tag-slug']")
	private WebElement TagSlugTextBox;

	@FindBy(xpath = "//textarea[@id='tag-description']")
	private WebElement TagDescriptionTextBox;

	@FindBy(xpath = "//input[@id='submit']")
	private WebElement AddNewTagBtn;

	@FindBy(xpath = "//a[contains(text(),'Reshma')]")
	public WebElement AddedTag;

	public void OperatiOnTagNameField(String Name) {
		TagNameTextBox.click();
		TagNameTextBox.sendKeys(Name);
	}

	public void OperatiOnTagSlug(String Slug) {
		TagSlugTextBox.click();
		TagSlugTextBox.sendKeys(Slug);
	}

	public void OperatiOnTagDescription(String Descr) {
		TagDescriptionTextBox.click();
		TagDescriptionTextBox.sendKeys(Descr);
	}

	public void OperatiOnAddNewTagBtn() {
		AddNewTagBtn.click();

	}

}
