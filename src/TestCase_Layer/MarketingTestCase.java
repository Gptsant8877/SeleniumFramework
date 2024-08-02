package TestCase_Layer;

import com.eva.vtiger.page_layer.MarketingPage;
import com.eva.vtiger.util_layer.WebUtil;

public class MarketingTestCase {

	public WebUtil gm;
	public MarketingTestCase(WebUtil gm) {
	  this.gm=gm;
	}
	
	public void verifyTestMarketing() {
		MarketingPage mkl=  new	MarketingPage(gm);
		mkl.marketingPage();
		
	}
	
	
}
