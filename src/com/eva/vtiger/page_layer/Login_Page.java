package com.eva.vtiger.page_layer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.util_layer.WebUtil;

public class   Login_Page
{
	public WebUtil gm;
	public Login_Page(WebUtil gm) {
		this.gm=gm;
		PageFactory.initElements(gm.driver, this);
	}
	public WebElement getUserNamebox() {
		return UserNamebox;
	}

	public void setUserNamebox(WebElement userNamebox) {
		UserNamebox = userNamebox;
	}
	@FindBy(xpath = "//input[@name='user_name']")
	private
	WebElement UserNamebox;

	@FindBy(xpath = "//input[@name='user_password']")
	WebElement PasswordBox;

	@FindBy(xpath = "//select[@name='login_theme']")
	WebElement  ColourTheme;
	
	@FindBy(xpath = "//select[@name='login_language']")
	WebElement  languagedropdown;
	
	@FindBy(xpath = "//input[@name='Login']")
	WebElement  loginbutton;
	




}
