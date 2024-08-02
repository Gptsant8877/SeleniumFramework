package TestCase_Layer;

import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.page_layer.BasePage;
import com.eva.vtiger.page_layer.CreateAccountPage;
import com.eva.vtiger.util_layer.WebUtil;

public class CreateAccountTestCase {
WebUtil gm;

public CreateAccountTestCase(WebUtil gm) {
	this.gm=gm;
}
public void verifycreateTestAccount() {
	CreateAccountPage cap=new CreateAccountPage(gm);
	cap.createacc();

	
}}
