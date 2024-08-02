package TestCase_Layer;

import com.eva.vtiger.page_layer.HomePage;
import com.eva.vtiger.util_layer.WebUtil;

public class HomePageTestcase {

	 public WebUtil gm;
		public HomePageTestcase(WebUtil gm) {
		  this.gm=gm;

}
public void verifyHomePageTestcases() {
	HomePage hmp=	new HomePage(gm);
	hmp.myhomepage();
	
	
	
}


}
