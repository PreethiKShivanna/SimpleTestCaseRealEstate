package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RealEstateLoginPOM {
	WebDriver driver;

	public RealEstateLoginPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='sign-in']")
	private WebElement LogInRegisterLink;

	@FindBy(xpath = "//input[@id='user_login']")
	private WebElement UserName;

	@FindBy(xpath = "//input[@id='user_pass']")
	private WebElement Password;

	@FindBy(xpath = "//input[@name='login']")
	private WebElement SignInBtn;

	@FindBy(xpath = "//a[contains(text(),'Lost Your Password?')]")
	private WebElement ForgotPassword;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/h3[1]/a[1]")
	public WebElement AddedPost;

	public void clickonLoginRegisterLink() {
		LogInRegisterLink.click();
	}

	public void enterUserName(String sUserName) {
		this.UserName.clear();
		this.UserName.sendKeys(sUserName);

	}

	public void enterPassword(String sPassword) {
		this.Password.clear();
		this.Password.sendKeys(sPassword);
	}

	public void ClickSignInBtn() {
		SignInBtn.click();
	}
}
