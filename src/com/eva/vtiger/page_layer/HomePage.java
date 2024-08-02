package com.eva.vtiger.page_layer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.util_layer.WebUtil;
public class HomePage {
	
		public WebUtil gm;
		public HomePage(WebUtil gm) {
			this.gm=gm;
			PageFactory.initElements(gm.driver, this);
		}
		
	@FindBy(xpath = "//a[contains(text(),'My Home Page')]")
	WebElement MyHomepageTB;
	
	@FindBy(xpath = "//a[contains(text(),'Marketing')]")
	WebElement MarketingTB;
		
	@FindBy(xpath = "//a[contains(text(),'Sales')]")
	WebElement SalesTB;
	
	@FindBy(xpath = "//a[contains(text(),'Support')]")
	WebElement SupportTB;
	
	@FindBy(xpath = "//a[contains(text(),'Analytics')]")
	WebElement AnalyticsTB;
	
	@FindBy(xpath = "//a[contains(text(),'Inventory')]")
	WebElement InventoryTB;
	
	@FindBy(xpath = "//a[contains(text(),'Tools')]")
	WebElement ToolsTB;
	
	@FindBy(xpath = "//a[contains(text(),'Settings')]")
	WebElement SettingTB;
	
	//@FindBy(xpath = "//a[contains(text(),'Sign Out')]")
	//WebElement  SignOutBT;
	
	public void myhomepage() {
		gm.mouseOver(MyHomepageTB);
		gm.mouseOver(MarketingTB);
		gm.mouseOver(SalesTB);
		gm.mouseOver(SupportTB);
		gm.mouseOver(AnalyticsTB);
		gm.mouseOver(InventoryTB);
		gm.mouseOver(ToolsTB);
		gm.mouseOver(SettingTB);
		//gm.click(SignOutBT, "Signout button click");
		//gm.createExtentReport();
		gm.createTestReport("HomePageTestcase");
	}
}
