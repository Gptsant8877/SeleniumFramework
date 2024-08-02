package Runner;

import com.eva.vtiger.page_layer.HomePage;
import com.eva.vtiger.page_layer.Login_Page;
import com.eva.vtiger.util_layer.WebUtil;

import TestCase_Layer.CreateAccountTestCase;
import TestCase_Layer.HomePageTestcase;
import TestCase_Layer.LoginPage;
import TestCase_Layer.MarketingTestCase;

public class Run {
	public static void main(String[] args) {
		WebUtil gm=   new WebUtil();
		gm.launchbrowser("chrome");
		    gm.createExtentReport();
		    
		LoginPage lgt=	  new LoginPage(gm);
		HomePageTestcase hmpt=new HomePageTestcase(gm);
		MarketingTestCase mrkt=new MarketingTestCase(gm);
		CreateAccountTestCase catcr=new CreateAccountTestCase(gm);
		lgt.verifyloginpage();
		hmpt.verifyHomePageTestcases();
		mrkt.verifyTestMarketing();
		catcr.verifycreateTestAccount();
	}

}
