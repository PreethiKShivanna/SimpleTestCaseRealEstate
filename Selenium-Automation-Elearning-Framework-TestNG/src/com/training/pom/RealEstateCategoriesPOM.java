package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RealEstateCategoriesPOM {
	WebDriver driver;

	public RealEstateCategoriesPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	

	}

	@FindBy(xpath = "//h2[contains(text(),'Add New Category')]")
	public WebElement AddNewCategoryLabel;

	@FindBy(xpath = "//table[@class='wp-list-table widefat fixed striped tags']")
	public WebElement Categorytable;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[3]/div[3]/div[2]/div[1]/form[1]/table[1]/tbody[1]/tr[1]/th[1]/input[1]")
	private WebElement CategoryCheckBox;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[3]/div[3]/div[2]/div[1]/form[1]/table[1]/tbody[1]/tr[1]/td[1]/strong[1]/a[1]")
	private WebElement CategorySelected;

	@FindBy(id = "bulk-action-selector-top")
	private WebElement CategoryBulkActionsListBox;

	@FindBy(xpath = "//input[@id='doaction']")
	private WebElement CategoryApplyBtn;

	@FindBy(xpath = "//div[@id='message']/p")
	private WebElement CategoryDeleteMsg;

	@FindBy(xpath = "//input[@id='tag-name']")
	private WebElement CategoryNameTextBox;

	@FindBy(xpath = "//input[@id='tag-slug']")
	private WebElement CategorySlugTextBox;

	@FindBy(xpath = "//textarea[@id='tag-description']")
	private WebElement CategoryDescriptionTextBox;

	@FindBy(xpath = "//input[@id='submit']")
	private WebElement AddNewCategoryBtn;

	@FindBy(xpath = "//a[contains(text(),'Brigade Apartments')]")
	public WebElement AddedCategory;

	public void CheckCategoryLabel() {
		System.out.println(AddNewCategoryLabel.getText());
	}

	public void SelectCategoryCheckBoxToDelete() {
		CategoryCheckBox.click();
	}

	public void SelectCategoryBulkActionsDropDown() {
		CategoryBulkActionsListBox.click();
		Select slc = new Select(CategoryBulkActionsListBox);
		List<WebElement> DropDownValues = driver.findElements(By.id("bulk-action-selector-top"));

		for (int i = 0; i < DropDownValues.size(); i++) {
			System.out.println("Items in the list are :" + DropDownValues.get(i).getText());
		}
		slc.selectByVisibleText("Delete");
	}

	public void ClikOnApplyOnCategoryTable() {
		CategoryApplyBtn.click();

	}

	public String CategoryDeleteMsg() {
		return CategoryDeleteMsg.getText();
	}

	public void OperatiOnCategoryNameField(String Name) {
		CategoryNameTextBox.click();
		CategoryNameTextBox.sendKeys(Name);
	}

	public void OperatiOnCategorySlug(String Slug) {
		CategorySlugTextBox.click();
		CategorySlugTextBox.sendKeys(Slug);
	}

	public void OperatiOnCategoryDescription(String Descr) {
		CategoryDescriptionTextBox.click();
		CategoryDescriptionTextBox.sendKeys(Descr);
	}

	public void OperatiOnAddNewCategoryBtn() {
		AddNewCategoryBtn.click();

	}

}
