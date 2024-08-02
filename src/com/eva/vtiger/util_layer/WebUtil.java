package com.eva.vtiger.util_layer;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

public class WebUtil {

	public WebDriver driver;

	private static ExtentReports extreport;

	private ExtentTest extTest;

	public static void createExtentReport() {
		String dt = new SimpleDateFormat("dd_mm_yyyy HH_mm_ss").format(new Date());
		extreport = new ExtentReports();
		File folder = new File("Reports");
		if (folder.exists() == false) {
			folder.mkdir();
		}
		  ExtentSparkReporter extSpark = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/Reports/" + "VtigerReports__" + dt + ".html");
		 extreport.attachReporter(extSpark);
	}

	public void createTestReport(String testcaseName) {
		if(extreport==null) {
			createExtentReport();
		}
		extTest = extreport.createTest(testcaseName);
	}

	public void flushReport() {
		extreport.flush();
	}

	// ***********************Browser Launch Generic method*******************
	/*
	 * This method will take screenshot of page where will it find exception
	 * 
	 * @Param -String imagetName
	 * 
	 * @Return- not return
	 */
	public String screenShot(String imageName) {
		DateFormat datef = new SimpleDateFormat("MM_dd_yyyy HH_MM_ss a");
		String dateTime = datef.format(new Date());
		TakesScreenshot tss = (TakesScreenshot) driver;
		File source = tss.getScreenshotAs(OutputType.FILE);

		File folder = new File("SnapShots");
		//

		if (folder.exists() == false) {
			folder.mkdir();
		}

		File finaldestination = new File(
				System.getProperty("user.dir") + "/SnapShots/" + imageName + dateTime + ".png");
		String destination = finaldestination.getAbsolutePath();
		try {
			Files.copy(source, finaldestination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destination;
	}
	/*
	 * This method will Launch Browser
	 * 
	 * @Param - String browserName
	 * 
	 * @Return- no return
	 */

	public void launchbrowser(String browser) {

		//driver.manage().addCookie(null);
		try {
			switch (browser.toLowerCase()) {

			case "chrome":
				driver = new ChromeDriver();
				createTestReport("testcaseName");
				extTest.log(Status.INFO, "chrome : launched Successfully ");

				break;
			case "firefox":
				driver = new FirefoxDriver();
				extTest.log(Status.INFO, "firefox : launched Successfully ");
				break;
			case "edge":
				driver = new EdgeDriver();
				extTest.log(Status.INFO, "edge : launched Successfully ");
				break;
			default:
				extTest.log(Status.FAIL, "Browser : name did not match ");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Browser : did not launch  ");
			extTest.addScreenCaptureFromPath(screenShot(browser));
			throw e;
		}
		// it is implicit wait WebElement default run with driver for all element //

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	// ****************driver generic method******************

	/*
	 * This method will force for sleep and stop execution of code specified
	 * duration
	 * 
	 * @Param= int durationForWaitInSecond
	 * 
	 * 
	 * @return -no return
	 */
	public void threadSleep(int duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	/*
	 * This method will wait for specific element until element not be enabled
	 * 
	 * @Param= int durationForWaitInSecond
	 * 
	 * @Param=WebElement objectForLocating
	 * 
	 * @Param=String
	 * 
	 * @return -no return
	 */
	public void exWaitElementEnabled(int durationOfSecond, WebElement we) {
		try {
			WebDriverWait exwaitObj = new WebDriverWait(driver, Duration.ofSeconds(durationOfSecond));
			exwaitObj.until(ExpectedConditions.elementToBeClickable(we));
			extTest.log(Status.PASS,"Successfully match ");
		} catch (Exception e) {
			extTest.log(Status.FAIL,"UnSuccessfully match ");
			throw e;
		}
	}
	/*
	 * This method will wait for specific element until page not be loaded
	 * 
	 * @Param= int durationForWaitInSecond
	 * 
	 * @Param=WebElement objectForLocating
	 * 
	 * @Param=String
	 * 
	 * @return -no return
	 */

	public void exWaitElementPresence(int durationOfSecond, By we) {
		try {
			WebDriverWait exwaitObj = new WebDriverWait(driver, Duration.ofSeconds(durationOfSecond));
			exwaitObj.until(ExpectedConditions.presenceOfElementLocated(we));
			extTest.log(Status.PASS,"Successfully match ");
		} catch (Exception e) {
			extTest.log(Status.FAIL,"unSuccessfully match ");
			throw e;
		}
	}

	/*
	 * This method will wait for specific element until Element not be visible
	 * 
	 * @Param= int durationForWaitInSecond
	 * 
	 * @Param=WebElement objectForLocating
	 * 
	 * @Param=String
	 * 
	 * @return -no return
	 */

	public void exWaitElementVisibility(int durationOfSecond, WebElement we) {
		try {
			WebDriverWait exwaitObj = new WebDriverWait(driver, Duration.ofSeconds(durationOfSecond));
			exwaitObj.until(ExpectedConditions.visibilityOf(we));
			extTest.log(Status.PASS,  "  Element is visible on page ");
		} catch (Exception e) {
			extTest.log(Status.FAIL, "  Element is not Visible ");
			throw e;
		}
	}
	/*
	 * This method will wait for specific element until Element text not be changed
	 * 
	 * @Param= int durationForWaitInSecond
	 * 
	 * @Param=WebElement objectForLocating
	 * 
	 * @Param=String
	 * 
	 * @Param=String textForMatch
	 * 
	 * @return -no return
	 */

	public void exWaitElementTextChange(int durationOfSecond, WebElement we,String textForMactch) {
		try {
			WebDriverWait exwaitObj = new WebDriverWait(driver, Duration.ofSeconds(durationOfSecond));
			exwaitObj.until(ExpectedConditions.textToBePresentInElementValue(we, textForMactch));
			extTest.log(Status.PASS,"  Element is present on html ");
		} catch (Exception e) {
			extTest.log(Status.FAIL,"  Text is not present  ");
			throw e;
		}
	}
	/* This method will refresh page */

	public void refresh() {
		driver.navigate().refresh();
		extTest.log(Status.INFO, " Page refreshed ");
	}

	/*
	 * This method will Back to previous page
	 * 
	 * @return no return
	 */
	public void back() {
		driver.navigate().back();
		extTest.log(Status.INFO, " Come on previous window Successfully");
	}

	/*
	 * This method will take you to the next page
	 * 
	 * @return -no return
	 */
	public void forword() {
		driver.navigate().forward();
		extTest.log(Status.INFO, " Come on next window Successfully");
	}

	/*
	 * This method will Maximize the window
	 * 
	 * @Return-Not return
	 */
	public void maximize() {
		driver.manage().window().maximize();
		extTest.log(Status.INFO, " Full screen window Successfully");
	}

	/*
	 * This method retrieve the size of Element
	 * 
	 * @Param - WebElement object
	 * 
	 * @Param - String
	 * 
	 * @Return - Array int
	 * 
	 */
	public int[] getElementSize( WebElement we) {

		Dimension dimension = we.getSize();
		int hieghtOfElment = dimension.getHeight();
		int widthOfElement = dimension.getWidth();
		int elementSize[] = { hieghtOfElment, widthOfElement };
		extTest.log(Status.INFO,
				"Retrieve Size of element and return the 1 : height and 2: width ");
		return elementSize;

	}
	/*
	 * This method retrieve the Location of Element
	 * 
	 * @Param - WebElement object
	 * 
	 * @Param - String
	 * 
	 * @Return - Array int
	 * 
	 */

	public int[] getElementLocation( WebElement we) {

		Point point = we.getLocation();
		int positionOfXcoOrdinate = point.getX();
		int positionOfYcoOrdinate = point.getY();
		int elementLocation[] = { positionOfXcoOrdinate, positionOfYcoOrdinate };
		extTest.log(Status.INFO, "Retrieve location  of element"
			);
		return elementLocation;

	}

	/*
	 * This method will Open Url at browser
	 * 
	 * @Param-String url
	 * 
	 * @Return- not return
	 */
	public void openUrl(String url) {
		try {
			driver.get(url);
			extTest.log(Status.INFO, url + " - Opened Successfully  ");
		} catch (Exception e) {
			extTest.log(Status.FAIL, url + " - did not  Open ");
			throw e;
		}
	}

	/*
	 * This method will take focus on Frame
	 * 
	 * @Param- WebElement object
	 * 
	 * @Param- String
	 * 
	 * @Return- not return
	 */
	public void switchToFrameWebElementWebElement(WebElement we) {

		driver.switchTo().frame(we);
		extTest.log(Status.INFO,  " -  Focus on the current iframe  ");

	}

	/*
	 * This method will take focus on main window from frame
	 * 
	 * @Return- not return
	 */
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
		extTest.log(Status.INFO, "  Focus on the main page from iFrame ");
	}

	public void alertAccept() {
		driver.switchTo().alert().accept();
		extTest.log(Status.INFO, "Alert is accepted");
	}

	public void alertDismissed() {
		driver.switchTo().alert().dismiss();
		extTest.log(Status.INFO, "Alert is Dismissed");
	}

	/*
	 * This method will switch focus on window behalf title
	 * 
	 * @Param- String title
	 * 
	 * @Return- not return
	 */

	public void switchTowindowWebElementTitle(String switchWindowTitle) {
		try {
			Set<String> windows = driver.getWindowHandles();

			for (String window : windows) {
				driver.switchTo().window(window);
				String title = driver.getTitle();
				if (title.equals(switchWindowTitle)) {
					extTest.log(Status.INFO, "  Focus on the New Window WebElement title  ");
					break;
				}
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "  Window did not handal and Focus Not change  - " + switchWindowTitle);
		}
	}

	/*
	 * This method will switch focus on window behalf contain title
	 * 
	 * @Param- String containfromTitle
	 * 
	 * @Return- not return
	 */
	public void switchTowindowWebElementTitleContains(String switchWindowTitle) {
		try {
			Set<String> windows = driver.getWindowHandles();

			for (String window : windows) {
				driver.switchTo().window(window);
				String title = driver.getTitle();
				if (title.contains(switchWindowTitle)) {
					extTest.log(Status.INFO, "  Focus on the New Window WebElement some contains of matching  title  ");
					break;
				}
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "  Window did not handal and Focus Not change  - " + switchWindowTitle);
		}

	}
	/*
	 * This method will switch focus on window behalf URL
	 * 
	 * @Param- String URL
	 * 
	 * @Return- not return
	 */

	public void switchTowindowWebElementURL(String switchWindowURL) {
		try {
			Set<String> windows = driver.getWindowHandles();

			for (String window : windows) {
				driver.switchTo().window(window);
				String urkValue = driver.getCurrentUrl();
				if (urkValue.equals(switchWindowURL)) {
					extTest.log(Status.INFO, "  Focus on the New Window WebElement URL  ");
					break;
				}
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "  Window did not handal and Focus Not change  and url is - " + switchWindowURL);
		}
	}

	/*
	 * This method will switch focus on window behalf URL contain
	 * 
	 * @Param- String containFromURL
	 * 
	 * @Return- not return
	 */

	public void switchTowindowWebElementURLConatains(String switchWindowURL) {
		try {
			Set<String> windows = driver.getWindowHandles();

			for (String window : windows) {
				driver.switchTo().window(window);
				String urkValue = driver.getCurrentUrl();
				if (urkValue.contains(switchWindowURL)) {
					extTest.log(Status.INFO,
							"  Focus on the New Window WebElement some contains of matching  URL - " + switchWindowURL);
					break;
				}
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "  Window did not handal and Focus Not change  and url is -  " + switchWindowURL);

		}

	}
	/*
	 * This method will retrieve url of page
	 * 
	 * @Param- String pageName
	 * 
	 * @Return- not return
	 */

	public String getCurrentURL(String pageName) {
		String url = driver.getCurrentUrl();
		extTest.log(Status.INFO, pageName + "URL is -[  " + url + " ] Successfilly retrieve the url of the page ");

		return url;
	}
	/*
	 * This method will retrieve title of page
	 * 
	 * @Param- String pagetitle
	 * 
	 * @Return- not return
	 */

	public String getTitle(String pageName) {
		String title = driver.getTitle();
		extTest.log(Status.INFO, "Successfully return the title of page : - " + pageName);
		return title;

	}

	/*
	 * This method will checked all check boxses
	 * 
	 * @Param - WebElement object
	 * 
	 * @Param- String elementCollectionName
	 * 
	 * @Return- not return
	 */
	public void ckeckedAllCheckBoxes(List<WebElement> listCheckedBoxes, String elementCollectionName) {

		for (int i = 0; i < listCheckedBoxes.size(); i++) {
			WebElement weCheckeBox = listCheckedBoxes.get(i);
			if (weCheckeBox.isSelected() == false) {
				weCheckeBox.click();
			}
			extTest.log(Status.INFO, "Successfully checked all check Boxes  Which is : - " + elementCollectionName);
		}
	}

	/*
	 * This method will unchecked all check boxses
	 * 
	 * @Param - WebElement object
	 * 
	 * @Param- String elementCollectionName
	 * 
	 * @Return- not return
	 */
	public void unckeckedAllCheckBoxes(WebElement we, String elementCollectionName) {
		List<WebElement> listCheckedBoxes = null;
		for (int i = 0; i < listCheckedBoxes.size(); i++) {
			WebElement weCheckeBox = listCheckedBoxes.get(i);
			if (weCheckeBox.isSelected() == true) {
				weCheckeBox.click();
			}
			extTest.log(Status.INFO, "Successfully unchecked all check Boxes  Which is : " + elementCollectionName);
		}

	}

	/*
	 * This method will close All browser
	 * 
	 */
	public void tearDown_Quit() {
		threadSleep(2000);
		driver.quit();
	}
	/*
	 * This method will close current working browser
	 * 
	 */

	public void closeWindow() {
		driver.close();
	}
	// ****** Actions class generic method *****/

	/*
	 * This method will mouseOver on the element After that click on element
	 * 
	 * @Param - WebElement object
	 * 
	 * @Param - String
	 * 
	 * @Return- Not return AnyThings
	 * 
	 */
	public void actionClick(WebElement we) {

		Actions act = new Actions(driver);

		try {
			act.click(we).build().perform();
			extTest.log(Status.INFO, "Successfully : Maouse  over and After that click on Element : " );
		} catch (ElementNotInteractableException e) {
			jsMouseOver(we);
			jsClickMethod(we);

		} catch (Exception e) {
			extTest.log(Status.FAIL, "Due to Exception Action not PerForm on Element :  - " );
			throw e;
		}
	}

	/*
	 * This method will mouseOver on the element After that send value in the Text
	 * box
	 * 
	 * @Param-String
	 * 
	 * @Param - WebElement object
	 * 
	 * @Param - String valueFor Send In textBox
	 * 
	 * @Return- Not return AnyThings
	 * 
	 */
	public void actionInputValue(WebElement we, String value) {

		Actions act = new Actions(driver);

		try {
			act.sendKeys(we, value).build().perform();
			extTest.log(Status.INFO,
					"Successfully mouceOver and After that enter value in the");

		} catch (ElementNotInteractableException e) {
			jsMouseOver(we);
			jsInputValueMethod(we, value);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/*
	 * This method will mouseOver on the element
	 * 
	 * @Param - WebElement object
	 * 
	 * @Param -String
	 * 
	 * @Return- Not return AnyThings
	 * 
	 */
	public void mouseOver(WebElement we) {

		Actions act = new Actions(driver);

		try {
			act.moveToElement(we).build().perform();
			extTest.log(Status.INFO, "Successfully mouceOver on the element");

		} catch (ElementNotInteractableException e) {
			jsMouseOver(we);

		} catch (Exception e) {
			extTest.log(Status.FAIL, "Due to Exception Action not perform ");

			throw e;
		}
	}

	/*
	 * This method will pick the element from one place and drop another place
	 * 
	 * @Param- WebElement dragObject
	 * 
	 * @Param - WebElement dropObject
	 * 
	 * @Return- Not return AnyThings
	 * 
	 */
	public void actionDragandDrop(WebElement we, WebElement we1) throws InterruptedException {

		Actions act = new Actions(driver);

		try {
			act.dragAndDrop(we, we1).build().perform();
			extTest.log(Status.INFO, "Successfully Drag one place and Drop another Place  ");

		} catch (ElementNotInteractableException e) {
			jsdragAndDrop(we, we1);

		} catch (Exception e) {
			extTest.log(Status.FAIL, "Due to Exception Darg and Drop Action not perform ");
			throw e;
		}
	}

	/*
	 * This method will perform vertical scroll or up-down /down-up
	 * 
	 * @Param - WebElement object
	 * 
	 * @Return- Not return AnyThings
	 * 
	 */
	public void actionScroll(WebElement we) throws InterruptedException {

		Actions act = new Actions(driver);
		try {
			act.scrollToElement(we).build().perform();
			extTest.log(Status.INFO, "Successfully Scroll to Element  ");

		} catch (ElementNotInteractableException e) {
			jsMouseOver(we);
			jsScroll(we);

		} catch (Exception e) {
			extTest.log(Status.FAIL, "Due to Exception Scroll Action Not Perform  ");
			throw e;
		}

	}

	/*
	 * This method will mouseOver on the element After that Double click on element
	 * 
	 * @Param - WebElement object
	 * 
	 * @Return- Not return AnyThings
	 * 
	 */
	public void actionDoubleClick(WebElement we) {

		Actions act = new Actions(driver);
		try {
			act.doubleClick(we).build().perform();
			extTest.log(Status.INFO, "Successfully perform double Click on  Element ");

		} catch (ElementNotInteractableException e) {
			jsMouseOver(we);
			jsDoubleClick(we);

		} catch (Exception e) {
			extTest.log(Status.FAIL,
					"Due To Exception Double Click Action not perform on element ");
			throw e;
		}

	}
	// *******************Select class generic method**************

	/*
	 * this method will select option from dropdown WebElement option visible text
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - String Visible Text
	 * 
	 * @return - no return
	 */
	public void selectByText( WebElement we, String textToSelect) {

		Select selectDD = new Select(we);
		try {
			selectDD.selectByVisibleText(textToSelect);
			extTest.log(Status.INFO, "Successfully Selected text in Drop Down Through Visisble Text  ");
		} catch (ElementNotInteractableException e) {
			jsDropdown(we, textToSelect);

		} catch (Exception e) {
			extTest.log(Status.FAIL, "Due To Exception DropDown From visible text was not selected   ");
			throw e;
		}
	}

	/*
	 * this method will select option from dropdown WebElement optioon index number
	 * and index number starts from 0
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - int indexNumber
	 * 
	 * @return - no return
	 */
	public void selectByIndex(WebElement we, int optionIndex) {

		Select selectDD = new Select(we);
		try {
			selectDD.selectByIndex(optionIndex);
			extTest.log(Status.INFO, "Successfully Selected text in Drop Down Through Index   ");
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Due To Exception DropDown From Index was not selected    ");
			throw e;
		}
	}

	/*
	 * this method will select option from dropdown WebElement option Attribute
	 * value
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - String value Attribute value
	 * 
	 * @return - no return
	 */
	public void selectByValue(WebElement we, String optionValueAttribute) {

		Select selectDD = new Select(we);
		try {
			selectDD.selectByValue(optionValueAttribute);
			extTest.log(Status.INFO, "Successfully Selected text in Drop Down Through Value Attribute Value    ");
		} catch (ElementNotInteractableException e) {
			jsDropdown(we, optionValueAttribute);

		} catch (Exception e) {
			extTest.log(Status.FAIL, "Due To Exception DropDown From Value  was not selected    ");
			throw e;
		}
	}

	/*
	 * this method will select option from dropdown WebElement Partial text
	 *
	 * @Param - WebElement
	 * 
	 * @Param - String partialvisibletext
	 * 
	 * @Param - Element Name
	 * 
	 * @return - no return
	 */
	public void selectByTextContains(WebElement we, String selectText) {
		int indexNumber = -1;

		Select slt = new Select(we);
		List<WebElement> weListOption = null;
		try {
			weListOption = slt.getOptions();
			for (int i = 0; i <= weListOption.size() - 1; i++) {
				WebElement weOption = weListOption.get(i);
				String optionText = weOption.getText();
				if (optionText.contains(selectText) == true) {
					indexNumber = i;
				}
			}
		} catch (StaleElementReferenceException e) {
			weListOption = slt.getOptions();
			for (int i = 0; i <= weListOption.size() - 1; i++) {
				WebElement weOption = weListOption.get(i);
				String optionText = weOption.getText();
				if (optionText.contains(selectText) == true) {
					indexNumber = i;
				}
			}

		} catch (Exception e) {
			throw e;
		}
		slt.selectByIndex(indexNumber);
		extTest.log(Status.INFO, "Successfully Selected text in Drop Down Through Partial Visisble text");
	}
	/*
	 * this method will retrieve All Selected Text from Drop Down
	 * 
	 * @Param - WebElement
	 * 
	 * @Param -
	 * 
	 * @return - List of All selected text
	 */

	public List<String> getAllSelectedOptions(WebElement we) {

		Select slt = new Select(we);
		List<String> list = new ArrayList<>();
		List<WebElement> listofSelectedOptions = null;
		try {
			listofSelectedOptions = slt.getAllSelectedOptions();
			for (WebElement weOptions : listofSelectedOptions) {
				String strSelectedoptions = weOptions.getText();
				list.add(strSelectedoptions);
				extTest.log(Status.INFO,
						"Successfully Retrieve the Inner Text Of All Slected Option from Drop Down    ");
			}

		} catch (Exception e) {
			extTest.log(Status.INFO, "Due To Exception not retrieve inner Text from drop down ");
			e.printStackTrace();
		}
		return list;

	}

	/*
	 * this method will retrieve Select text from drop down
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - String Element name
	 * 
	 * @return - Select Text in Drop Down
	 */
	public String getDropdownSelectedText(WebElement we) {

		Select slt = new Select(we);
		String selectedOp = null;
		try {
			WebElement selectOption = slt.getFirstSelectedOption();
			selectedOp = selectOption.getText();
			extTest.log(Status.INFO, "Successfully : get Selected option in Drop Down and return it  ");
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Due to Excetion not get selected otption from drop down");
			e.printStackTrace();
		}
		return selectedOp;
	}

	/*
	 * this method will retrieve All options from Drop Down
	 * 
	 * @Param - WebElement
	 * 
	 * @Param -
	 * 
	 * @return - List of All options
	 */
	public List<String> getTextAllOptionsDropdown(WebElement we) {

		Select slt = new Select(we);
		List<String> alloptions = new ArrayList<String>();
		List<WebElement> allop = slt.getOptions();
		for (int i = 0; i < allop.size() - 1; i++) {
			WebElement weAllop = allop.get(i);
			String ailoption = weAllop.getText();
			alloptions.add(ailoption);
		}
		extTest.log(Status.INFO, "Successfully get the all Option from the drop down and return text of all   ");
		return alloptions;

	}

	/*
	 * this method will retrieve count of All options from Drop Down
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - String element name
	 * 
	 * @return - int number
	 */
	public int getAllOptionCount(WebElement we) {

		Select slt = new Select(we);
		List<WebElement> ListAlloptions = slt.getOptions();
		int allOptions = ListAlloptions.size();
		extTest.log(Status.INFO, "Successfully get the all Option from th drop down and return All count of text  ");
		return allOptions;

	}

	// **************java script generic method********************

	/*
	 * this method will select drop Down
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - String selectText
	 * 
	 * @return - not return anything
	 */

	public void jsDropdown(WebElement we, String value) {

		((JavascriptExecutor) driver).executeScript("var select = arguments[0]; for(var i = 0;"
				+ " i < select.options.length; i++){ if(select.options[i].text == arguments[1])"
				+ "{ select.options[i].selected = true; } }", we, value);
		extTest.log(Status.INFO,
				"Successfully Selected text in Drop Down Through Visisble Text  ,Through java Script ");
	}

	/*
	 * this method will type value in text Box
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - String value
	 * 
	 * @return - not return anything
	 */
	public void jsInputValueMethod(WebElement we, String value) {
		try {

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].value='" + value + "';", we);

			extTest.log(Status.INFO,
					"Successfully enter value in the ");
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Value not entered  in the ");
	     screenShot(we.getText());
		
		}

	
	
	}

	/*
	 * this method will click on button
	 * 
	 * @Param - WebElement
	 * 
	 * @return - not return anything
	 */
	public void jsClickMethod(WebElement we) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", we);
		extTest.log(Status.INFO,
				"Successfully : Maouse  over and After that click on Element Through Java script : - ");

	}

	/*
	 * this method will mouse over on element
	 * 
	 * @Param - WebElement
	 * 
	 * @return - not return anything
	 */
	public void jsMouseOver(WebElement we) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(
				"if(document.createEvent){var evObj = " + "document.createEvent('MouseEvents');evObj.initEvent"
						+ "('mouseover',true, false); arguments[0].dispatchEvent(evObj);} "
						+ "else if(document.createEventObject) " + "{ arguments[0].fireEvent('onmouseover');}",
						we);
		extTest.log(Status.INFO, "Successfully mouceOver on the element  ");
	}

	/*
	 * this method will drag and drop from one place to another place to element
	 * 
	 * @Param - WebElement drag
	 * 
	 * @Param - WebElement drop
	 * 
	 * @return - not return anything
	 */
	public void jsdragAndDrop(WebElement source, WebElement destination)
			throws InterruptedException {

		Actions act = new Actions(driver);
		act.moveToElement(source).pause(Duration.ofSeconds(1)).clickAndHold(source).pause(Duration.ofSeconds(1))
		.moveByOffset(1, 0).moveToElement(destination).moveByOffset(1, 0).pause(Duration.ofSeconds(1)).release()
		.perform();

		Thread.sleep(3000);

		System.out.println("Drag And Drop action perfrom WebElement js");
	}

	/*
	 * this method will scroll Vertical
	 * 
	 * @Param - WebElement
	 * 
	 * @return - not return anything
	 */
	public void jsScroll(WebElement we) throws InterruptedException {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
		Thread.sleep(2000);
		extTest.log(Status.INFO, "Successfully Scroll through java script   ");
	}

	/*
	 * this method will perform double click on element
	 * 
	 * @Param - WebElement
	 * 
	 * @return - not return anything
	 */
	public void jsDoubleClick(WebElement we) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].dispatchEvent(new MouseEvent('dblclick', { bubbles: true }));", we);
		extTest.log(Status.INFO,
				"Successfully perform double Click on  Element");
	}

	// ****************WebElement generic method****************
	/*
	 * this method will clear text Box
	 * 
	 * @Param - WebElement
	 * 
	 * @return - not return anything
	 */
	public void clear(WebElement we) {
		try {

			we.clear();
			extTest.log(Status.INFO, "Successfully will  clear  text box ");
		} catch (Exception e) {
			extTest.log(Status.FAIL, "text box  is not  clear  ");
			e.printStackTrace();
		}
	}

	/*
	 * this method will click on element
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - String element Name
	 * 
	 * @return - not return anything
	 */
	public void click(WebElement we,String element) {

		try {
		we.sendKeys(element);
			extTest.log(Status.INFO, "Successfully clicked on the");
		} catch (ElementNotInteractableException e) {

			jsClickMethod(we);
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Element could not be clicked Due to Exception ");
			e.printStackTrace();
			throw e;
		}
	}

	/*
	 * this method will Type vale in Text Box
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - String element Name
	 * 
	 * @Param- String value for send
	 * 
	 * @return - not return anything
	 */
	public void inputValue(WebElement we, String value) {

		try {
		
			
		we.sendKeys(value);
			extTest.log(Status.INFO,
					"Successfully Value - [" + value + "] enterd in the");
		} catch (ElementNotInteractableException e) {
			jsInputValueMethod(we, value);
		} catch (Exception e) {
			extTest.log(Status.FAIL,
					"Value could not be Entered in " );

			throw e;
		}

	}

	/*
	 * this method will retrieve inner text of element
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - String
	 * 
	 * @return - String innerTextOfElement
	 */
	public String getText(WebElement we) {

		String innertext = null;
		try {
			innertext = we.getText();
			extTest.log(Status.INFO, "Successfully Retrieve inner Text - ");

		} catch (StaleElementReferenceException e) {
			innertext = we.getText();
			extTest.log(Status.INFO,
					"Successfully Value enterd in the  ");

		} catch (Exception e) {
			extTest.log(Status.FAIL, "Due To Exception Inner text not found");
			e.printStackTrace();

		}
		return innertext;

	}
	public String getAttribute( WebElement we,String attributeName) {

		String attributeValue = null;
		try {
			attributeValue = we.getAttribute(attributeName);
			extTest.log(Status.INFO, "Successfully got  Attribute Value - " );

		} catch (Exception e) {
			extTest.log(Status.FAIL, "Due To Exception Attribute Value not found");
			e.printStackTrace();

		}
		return attributeValue;

	}
	/* Verification method */

	public void verifyTextContains(String actualValue,String expectedTextContains) {
		if(actualValue.contains(expectedTextContains)) {
			extTest.log(Status.PASS, actualValue+" is contains value :" +expectedTextContains);
		}else {
			extTest.log(Status.FAIL, actualValue+" is not contains value :" +expectedTextContains);
		}
	}

	/*
	 * this method will verify Enability of element
	 * 
	 * @Param - WebElement we
	 * 
	 * 
	 * @return - not return anything
	 */
	public void verifyEnabled(WebElement we) {
		try {
			boolean status = we.isEnabled();
			if (status == true) {
				extTest.log(Status.PASS, " is enable for action performing ");
			} else {
				extTest.log(Status.FAIL,"unSuccessfully match ");
				extTest.addScreenCaptureFromPath(screenShot(we.getText()));
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Element is not enable for action performing and Exception occur");
			e.printStackTrace();
			extTest.addScreenCaptureFromPath("");
		}
	}
	public void verifySelected(WebElement we) {
		try {
			boolean status = we.isSelected();
			if (status == true) {
				extTest.log(Status.PASS, "Check box is selected ");
			} else {
				extTest.log(Status.FAIL, "Check box is not selected   ");
				extTest.addScreenCaptureFromPath(screenShot(we.getText()));
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Check box is not selectand Exception occur");
			e.printStackTrace();
			extTest.addScreenCaptureFromPath(screenShot(we.getText()));
		}
	}

	/*
	 * this method will verify that element is displayed on UI
	 * 
	 * @Param - WebElement we
	 * 
	 * 
	 * @return - not return anything
	 */
	public void verifyDisplayed(WebElement we) {
		try {
			boolean status = we.isDisplayed();
			if (status == true) {
				extTest.log(Status.PASS, "Element is Displayed on UI");
			} else {
				extTest.log(Status.FAIL, "Element is Displayed on UI ");
				extTest.addScreenCaptureFromPath(screenShot(we.getText()));
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Element is Displayed on UI and exception occur ");
			e.printStackTrace();
			extTest.addScreenCaptureFromPath(screenShot(we.getText()));
		}
	}

	public void verifyAttributeValue(WebElement we, String attributeName, String expectedValue) {
		try {
			String actualvalue = we.getAttribute(attributeName);
			if (expectedValue.equals(actualvalue)) {
				extTest.log(Status.PASS, "Successfully match " );
			} else {
				extTest.log(Status.FAIL, "Did not match");
				extTest.addScreenCaptureFromPath(screenShot(we.getText()));
			}

		} catch (Exception e) {
			extTest.log(Status.FAIL, "Did not match attribute value and Exception occur");
			e.printStackTrace();
			extTest.addScreenCaptureFromPath(screenShot(we.getText()));
		}
	}

	/*
	 * this method will verify two string
	 * 
	 * @Param - String actual
	 * 
	 * @Param - String expected
	 * 
	 * @return - not return anything
	 */

	public void verifyText(String actual, String expected) {
		if (actual.equals(expected)) {
			System.out.println("PASSED : ");
			extTest.log(Status.PASS,
					"Actual value [ " + actual + "] And Expected value  matched  [" + expected + "  ]");
		} else {
			extTest.log(Status.FAIL,
					"Actual value [ " + actual + "] And Expected value not  match [" + expected + "  ]");

		}
	}

	/*
	 * this method will verify two string
	 * 
	 * @Param - String
	 * 
	 * @Para, - WebElement we
	 * 
	 * @Param - String expected
	 * 
	 * @return - not return anything
	 */
	public void verifyInnerText( WebElement we, String expected) {
		String actual = getText( we);
		if (actual.equals(expected)) {
			extTest.log(Status.PASS,
					"Actual InnerText [ " + actual + "] And Expected InnerText  matched  [" + expected + "  ]");
		} else {
			extTest.log(Status.FAIL,
					"Actual InnerText [ " + actual + "] And Expected InnerText not  match [" + expected + "  ]");
			extTest.addScreenCaptureFromPath(screenShot(we.getText()));
		}
	}

	public void verifyTitle(String expectedTitle,String pageName) {
		try {
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				extTest.log(Status.PASS,
						"Actual title [ " + actualTitle + "] And Expected title  matched  [" + expectedTitle + "  ]");
			} else {
				extTest.log(Status.FAIL,
						"Actual title [ " + actualTitle + "] And Expected title not  match [" + expectedTitle + "  ]");

			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Due to Exception ");
			e.printStackTrace();
			extTest.addScreenCaptureFromPath(screenShot(pageName));
		}

	}
	public void verifyURL(String ExpectedURL,String pageName) {
		try {
			String actualUrl = driver.getCurrentUrl();
			if (actualUrl.equals(ExpectedURL)) {
				extTest.log(Status.PASS,
						"Actual url [ " + actualUrl + "] And Expected url  matched  [" + ExpectedURL + "  ]");
			} else {
				extTest.log(Status.FAIL,
						"Actual url [ " + actualUrl + "] And Expected url not  match [" + ExpectedURL + "  ]");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Due to Exception ");
			e.printStackTrace();
			extTest.addScreenCaptureFromPath(screenShot(pageName));
		}

	}
	/* * ------ Table scenario generic method --------------- */

	/*
	 * this method will retrieve column Number
	 * 
	 * @Param -string Table Name
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - string column Name
	 * 
	 * @return - int column Number
	 */

	public int getColumnNumberWebElementColumnName(String xpath, String columnName) {
		int columnNumber = -1;
		List<WebElement> listcolumnName = driver.findElements(By.xpath(xpath));
		int columnCount = listcolumnName.size();
		for (int i = 0; i <= columnCount - 1; i++) {
			WebElement weTableCol = listcolumnName.get(i);
			String colname = weTableCol.getText();
			if (colname.equalsIgnoreCase(columnName)) {
				columnNumber = i;
				break;
			}
		}
		return columnNumber;
	}

	public String getTableDataWebElementUniqueData(String tableXpath, String uniqueData, String uniqueDataColumnName,
			String requiredDataColumnName) {
		/// get row number of account number
		/// because account name is in same row
		int rowNumber = 0;
		List<WebElement> listRows = driver.findElements(By.xpath(tableXpath + "//tr"));
		int cn1 = getColumnNumberWebElementColumnName(tableXpath, uniqueDataColumnName);
		int cn2 = getColumnNumberWebElementColumnName(tableXpath, requiredDataColumnName);

		for (int i = 0; i <= listRows.size() - 1; i++) {
			String text = driver.findElement(By.xpath(tableXpath + "//tr[" + (i + 1) + "]//td[" + cn1 + "]")).getText();
			if (text.equalsIgnoreCase(uniqueData)) {
				rowNumber = i;
			}
		}
		String requiredText = driver.findElement(By.xpath(tableXpath + "//tr[" + rowNumber + "]//td[" + cn2 + "]"))
				.getText();
		return requiredText;

	}

	//***********************************************Action perform WebElement KeYboard Physically******************

	/*
	 * this method will send value from text box and click on'ENTER'key
	 * 
	 * @Param -string Element name
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - String value For send
	 * 
	 * 
	 * 
	 * @return - not return
	 */

	public void InputValue_PressEnterKeys( WebElement we, String value) {

		try {
			clear(we);
			we.sendKeys(value);
			we.sendKeys(Keys.ENTER);
			extTest.log(Status.INFO,
					"Successfully Value enterd in the text box  And click on Enter Button ");

		} catch (ElementNotInteractableException e) {
			jsInputValueMethod(we, value);
		} catch (Exception e) {
			extTest.log(Status.FAIL,
					"Value could not be Entered in text box  and not clicked Enter button Due to Exception ");
			throw e;
		}

	}

	/*
	 * this method will click on Tab button after that click on element
	 * 
	 * @Param -string Element name
	 * 
	 * @Param - WebElement
	 * 
	 * @return - not return
	 */

	public void pressTab_click( WebElement we) {

		try {

			we.sendKeys(Keys.TAB);
			we.click();

		} catch (ElementNotInteractableException e) {
			jsClickMethod(we);
		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 * this method will retrieve total Number of row in table
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - String element name
	 * 
	 * @return - int total row number
	 */
	public int getTableRowCoun(List<WebElement> listCheckedBoxes, String elementCollectionName) {

		int rowCount = listCheckedBoxes.size();
		return rowCount;
	}

	/*
	 * this method will retrieve total number of column in table
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - String element name
	 * 
	 * @return - int total column number
	 */
	public int getTableColumnCount(List<WebElement> listCheckedBoxes, String elementCollectionName) {

		int ColoumnCount = listCheckedBoxes.size();
		return ColoumnCount;
	}

	/*
	 * this method will retrieve all column text
	 * 
	 * @Param -string Table Name
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - int column number
	 * 
	 * @return - List of String text of column
	 */

	public List<String> getColumnDataWebElementColumnNumber(String tablename, String xpath, int columnNumber) {
		List<WebElement> columnList = driver.findElements(By.xpath(xpath));
		driver.findElements(By.xpath(xpath));
		List<String> columnNamelist = new ArrayList<>();
		for (int i = 0; i < columnList.size(); i++) {
			String columnName = columnList.get(i).getText();
			columnNamelist.add(columnName);

		}
		return columnNamelist;
	}

	/*
	 * this method will retrieve
	 * 
	 * @Param -String Table Name
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - String column name
	 * 
	 * @return - List of String text of column
	 */
	public List<String> getColumnDataWebElementName(String tableName, String xpath, String columnName) {
		//		int columnNumber=-1;
		//		List<WebElement> listcolumnName=	driver.findElements(WebElement. we(table we+"//tr[1]//td"));
		//			int columnCount=listcolumnName.size();
		//			for(int i=0;i<=columnCount-1;i++) {
		//				WebElement weTableCol=listcolumnName.get(i);
		//			String colname=	weTableCol.getText();
		//			if(colname.equalsIgnoreCase(columnName)) {
		//				columnNumber=i;
		//			break;
		//			}
		//			}
		//			List<WebElement> columnList=	driver.findElements(WebElement. we(table we+"//tr//td["+columnNumber+"]"));
		//			List<String> columnNamelist=new ArrayList<>();
		//			for(int i=0;i<columnList.size();i++) {
		//				String coluName=columnList.get(i).getText();
		//				columnNamelist.add(coluName);
		//				
		//			}
		//			return columnNamelist;

		int columncount = getColumnNumberWebElementColumnName(xpath, columnName);
		List<String> columnNamelist = getColumnDataWebElementColumnNumber(tableName, xpath, columncount);

		return columnNamelist;
	}

	/*
	 * this method will retrieve row number
	 * 
	 * @Param -string Table Name
	 * 
	 * @Param - WebElement
	 * 
	 * @Param - String unique Data
	 * 
	 * @Param - String unique Data in column
	 * 
	 * @return - int row number
	 */
	public int getRowNumberWebElementRowID(String tablewe, String tablename, String uniqueData,
			String uniqueColumnName) {
		int rowNumber = -1;
		List<String> columndat = getColumnDataWebElementName(tablename, tablewe, uniqueColumnName);
		for (int i = 0; i <= columndat.size() - 1; i++) {
			String uniqueColumnData = columndat.get(i);
			if (uniqueColumnData.equalsIgnoreCase(uniqueData)) {

				rowNumber = i;
				break;
			}
		}
		return rowNumber;

	}

	public void getRowDataListWebElementRowID(String tablewe, String tablename, String uniqueData,
			String uniqueColumnName) {
		int rowNumber = getRowNumberWebElementRowID(tablewe, tablename, uniqueData, uniqueColumnName);

	}

}
