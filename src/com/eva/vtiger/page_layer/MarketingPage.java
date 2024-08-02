package com.eva.vtiger.page_layer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.util_layer.WebUtil;

public class MarketingPage extends BasePage{
public WebUtil gm;

public MarketingPage(WebUtil gm){
	super(gm);
	this.gm=gm;
	PageFactory.initElements(gm.driver, this);	
}

@FindBy(xpath = "//a[contains(text(),'Marketing')]")
WebElement  MarketingLK;
 

@FindBy(xpath = "//input[@name='selectall']")
WebElement  CompainSelectAll;

@FindBy(xpath = "//a[contains(text(),'Go to Advanced Search')]")
WebElement  AdvancedSerchLK;

@FindBy(xpath = "//input[@class='txtBox']")
WebElement  SerachBX;

@FindBy(xpath = "//select[@id='viewname']")
WebElement  ViewNameTB;

@FindBy(xpath = "//a[contains(text(),'Accounts')]")
WebElement  AccountsLK;

@FindBy(xpath = "//a[contains(text(),'Contacts')]")
WebElement  ContactsLK;

@FindBy(xpath = "//a[contains(text(),'Webmail')]")
WebElement  WebmailLK;

@FindBy(xpath = "//a[contains(text(),'Calendar')]")
WebElement  CalendarLK;

@FindBy(xpath = "//a[contains(text(),'Leads')]")
WebElement  LeadsLK;

@FindBy(xpath = "//a[contains(text(),'Documents')]")
WebElement  DocumentsLK;



public void marketingPage() {
	//gm.click(MarketingLK, "Click on marketingLk");
	gm.actionDoubleClick(MarketingLK);
	gm.click(CompainSelectAll, "compaindpd");
	gm.click(AdvancedSerchLK, "link tab");	
	gm.click(SerachBX, "search barr text");
	gm.click(ViewNameTB, "view Tab");
	gm.mouseOver(AccountsLK);
	gm.mouseOver(ContactsLK);
	gm.mouseOver(WebmailLK);
	gm.mouseOver(LeadsLK);
	gm.mouseOver(DocumentsLK);
	gm.click(AccountsLK, "click on account link");

	
}

}
