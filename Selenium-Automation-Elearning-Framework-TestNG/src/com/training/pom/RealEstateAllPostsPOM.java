package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RealEstateAllPostsPOM {
	WebDriver driver;

	public RealEstateAllPostsPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//h1[contains(text(),'Posts')]")
	public WebElement PostsLabel;

	@FindBy(xpath = "//a[@class='page-title-action']")
	public WebElement AddNewBtn;

	public void ClickOnAddNewBtn() {
		AddNewBtn.click();
	}

}
