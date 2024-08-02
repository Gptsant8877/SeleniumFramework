package com.eva.vtiger.page_layer;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.util_layer.WebUtil;


public class BasePage {

	public BasePage(WebUtil gm) {
		gm = gm;
		PageFactory.initElements(gm.driver, this);

	}

	private WebUtil gm;
	
	
	@FindBy(xpath = "(//div[@align='center']/ancestor::table[@class='small']/ancestor::div[@id='basicTab']//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBT;
	@FindBy(xpath = "//table[@class='small']//input[@value='Delete']")
	private WebElement landingPageDeleteBT;
	@FindBy(xpath = "//table[@class='small']//input[@value='Mass Edit']")
	private WebElement landingPageEditBT;
	@FindBys(@FindBy(xpath = "//input[@name='selectall']"))
	private List<WebElement> clickheadCheckBoxBT;
	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement landingPageSearchTB;
	@FindBy(xpath = "//select[@id='bas_searchfield']")
	private WebElement searchByDD;
	@FindBy(xpath = "//input[@name='submit']")
	private WebElement scarchNowBT;

	@FindBy(xpath = "//td[@class='dvtTabCache']/child::input[@title='Edit [Alt+E]']")
	private WebElement detailsPageEditBT;

	@FindBy(xpath = "//td[@class='dvtTabCache']/child::input[@title='Duplicate [Alt+U]']")
	private WebElement detailsPageDuplicateBT;

	@FindBy(xpath = "//td[@class='dvtTabCache']/child::input[@title='Delete [Alt+D]']")
	private WebElement detailsPageDeleteBT;
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement detailsPageHeaderINT;
	
//	@FindBy(linkText =  "")
//	private WebElement signOutBt;
	

	
	//  return Details Page Locator
	public WebElement getDetailsPageHeaderINT() {
		return detailsPageHeaderINT;
	}
	
	public WebElement getEditBT() {
		
		return detailsPageEditBT;
	}

	public WebElement getDeleteBT() {
		return detailsPageDeleteBT;
	}

	public WebElement getDuplicateBT() {
		return detailsPageDuplicateBT;
	}

	// Functionality 
	
	public void clickSaveBT() {
		gm.click( saveBT,"Save Button");
	}

	public void clickDeleteBT() {
		gm.click(landingPageDeleteBT,"Delete Button");
	}

	public void clickEditBT() {
		gm.click(landingPageEditBT,"Edit Button");
	}

	public void clickOnHeaderCheckBox() {
		gm.ckeckedAllCheckBoxes(clickheadCheckBoxBT, "Accounts");
	}

	public void searchTextBox(String valueForSearch, String searchBy) {
		gm.inputValue(landingPageSearchTB, valueForSearch);
		gm.selectByText(searchByDD, searchBy);
	}
	public void clickSearchNowBT() {
		gm.click(scarchNowBT,"Search Now Button");
	}
//	public void clickonLogOutorBT() {
//		
//		gm.click("SingOutBT", accountLK);
//	}

}
