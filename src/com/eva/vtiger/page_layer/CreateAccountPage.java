package com.eva.vtiger.page_layer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.util_layer.WebUtil;

public class CreateAccountPage extends BasePage {
public WebUtil gm;
 public  CreateAccountPage(WebUtil gm) {
	 super(gm);
	this.gm=gm;
	PageFactory.initElements(gm.driver, this);
 }
@FindBy(xpath = "//a[contains(text(),'Accounts')]")	
WebElement AccountLK;
 
@FindBy(xpath = "//img[@title='Create Account...']")	
 WebElement  CreateACCTB;
 
@FindBy(xpath = "//input[@name='accountname']")	
WebElement  AccountsendSD;

@FindBy(xpath = "//input[@name='website']")	
WebElement  WebsiteSD;

@FindBy(xpath = "//input[@name='tickersymbol']")	
WebElement  TicketSD;



@FindBy(xpath = "//textarea[@name='bill_street']")
WebElement  BillStreetSD;

@FindBy(xpath = "//input[@name='bill_pobox']")
WebElement  BillPoxSD;

@FindBy(xpath = "//input[@name='bill_city']")
WebElement  BillCitySD;

@FindBy(xpath = "//input[@name='bill_state']")
WebElement  BillStateSD;

@FindBy(xpath = "//input[@name='bill_code']")
WebElement  BillCodeSD;

@FindBy(xpath = "//input[@name='bill_country']")
WebElement  BillCountrySD;

@FindBy(xpath = "//textarea[@name='description']")
WebElement  BilldescriptionSD;

@FindBy(xpath = "//input[@name='phone']")
WebElement  PhoneNumberSDBX;

@FindBy(xpath = "//input[@name='fax']")
WebElement  FaxSDBX;

@FindBy(xpath = "//input[@name='email1']")
WebElement  EmailSDBX;

@FindBy(xpath = "//textarea[@name='ship_street']")
WebElement   ShipStreetSDBX;

@FindBy(xpath = "//input[@name='ship_pobox']")
WebElement   ShipPOBSDBX;

@FindBy(xpath = "//input[@name='ship_city']")
WebElement   ShipCitySDBX;

@FindBy(xpath = "//input[@name='ship_state']")
WebElement   shipstateSDBX;

@FindBy(xpath = "//input[@name='ship_code']")
WebElement   ShipingSDBX;

@FindBy(xpath = "//input[@name='ship_country']")
WebElement   ShipCountrySDBX;


public void createacc() {
	

	//gm.actionDoubleClick(CreateACCTB);
	gm.actionDoubleClick(AccountLK);
	gm.click(CreateACCTB, "Click on  create AccountTB");
	gm.inputValue(AccountsendSD, "Suresh Kumar Gupta Mk");
	gm.inputValue(WebsiteSD, "gmail.com");
	gm.inputValue(TicketSD, "1234");
	//gm.inputValue(MemberOFFSD, "Mokalpur mariahu");
	gm.inputValue(BillStreetSD, "Mokalpur Jaunpur");
	gm.inputValue(BillPoxSD, "Mokalpur");
	gm.inputValue(BillCitySD, "Jaunpur");
	gm.inputValue(BillStateSD, "Uttar Pradesh");
	gm.inputValue(BillCodeSD, "222161");
	gm.inputValue(BillCountrySD, "India");
	gm.inputValue(BilldescriptionSD, "This is laocal information of santosh kumar");
	gm.inputValue(PhoneNumberSDBX, "8808992219");
	gm.inputValue(FaxSDBX, "05452686265");
	gm.inputValue(EmailSDBX, "skgtester@gmail.com");
	gm.inputValue(ShipStreetSDBX, "Jaunpur UP");
	gm.inputValue(ShipPOBSDBX, "Mokalpur 222161");
	gm.inputValue(ShipCitySDBX, "Jaunpur ");
	gm.inputValue(shipstateSDBX, " UP");
	gm.inputValue(ShipingSDBX, "222161");
	gm.inputValue(ShipCountrySDBX, "India ");
}
}
