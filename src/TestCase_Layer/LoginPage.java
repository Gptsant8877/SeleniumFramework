package TestCase_Layer;

import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.page_layer.Login_Page;
import com.eva.vtiger.util_layer.WebUtil;

public class LoginPage {
	 public WebUtil gm;
	public LoginPage(WebUtil gm) {
	  this.gm=gm;	   
	}
	
	
public void verifyloginpage() {
	
	gm.createTestReport("verifyloginpage");
	Login_Page lg=    new  Login_Page(gm);
	//lg.validloginPageCredencial();
	//lg.InvalidloginPageCredencial();
}
	}

	

