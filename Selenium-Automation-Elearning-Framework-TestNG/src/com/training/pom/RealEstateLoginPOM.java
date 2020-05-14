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

	@FindBy(xpath = "//div[@class='post-content']//h3[contains(text(),'Brigade Flats')]")
	public WebElement AddedPost;

	@FindBy(xpath = "//li[@id='menu-item-617']//a[contains(text(),'Blog')]")
	public WebElement Blog;

	@FindBy(xpath = "(//*[contains(text(),'Read More')])[1]")
	public WebElement ReadMoreLink;

	@FindBy(xpath = "//textarea[@id='comment']")
	public WebElement Commentbox;

	@FindBy(id = "author")
	public WebElement Name;

	@FindBy(id = "email")
	public WebElement email;

	@FindBy(xpath = "//input[@id='submit']")
	public WebElement PostCommentBtn;

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

	public void ClickBlogTab() {
		Blog.click();
	}

	public void ClickOnReadMoreLink() {
		ReadMoreLink.click();
	}

	public void EnterInComments(String sComments) {
		Commentbox.sendKeys(sComments);
	}

	public void EnterInName(String sName) {
		Name.sendKeys(sName);
	}

	public void EnterInemail(String semail) {
		email.sendKeys(semail);
	}

	public void ClickonPostComment() {
		PostCommentBtn.click();
	}
}
