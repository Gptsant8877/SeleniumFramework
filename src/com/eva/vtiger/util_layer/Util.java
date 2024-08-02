package com.eva.vtiger.util_layer;

import java.io.File;
import java.time.Duration;

import javax.naming.ldap.ExtendedRequest;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;



public class Util{
	public WebDriver driver;
	public ExtentReports exrep;
	public ExtentTest  exetest;

	public  ExtentTest  getExtentTest() {
		return exetest;
	}



	public WebDriver launchBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			//driverPool.set(d);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		return driver;
	}
	public ExtentReports  extentReport() {


		exrep	=new ExtentReports();
		ExtentSparkReporter exteRepo=	new  ExtentSparkReporter("C:\\Users\\dell\\Desktop\\New folder (3)\\Framwork_001\\Report.html");
		exrep.attachReporter(exteRepo);
		return exrep;

	}

	public void reportflush() {

		exrep.flush();

	}
	public void snapshot(String name) {
		TakesScreenshot TSS=  (TakesScreenshot) driver;
		File file	=TSS.getScreenshotAs(OutputType.FILE);
		File Fieof =  new File("FileResult"+name+".png");
		try {
			Files.copy(file, Fieof);
		} catch (Exception e) {
			e.printStackTrace();
		}}


	public WebElement elementFind(By xpath) {

		WebElement web=	driver.findElement(xpath);

		return web;

	}
	public void  click(By xpath) {
		elementFind(xpath).click();


	}
	public void sendKeys(By xpath,String ElementName) {

		elementFind(xpath).sendKeys(ElementName);

	}
	public void dropDownselectByValueMethod(By xpath,String ElementName ) {
		WebElement webelemet=	elementFind(xpath);
		Select select=	new Select(webelemet);
		select.selectByValue(ElementName);
		exetest.log(Status.INFO, "if value is matched");



	}

	public void dropDownselectByIndexMethod(By xpath,int ElementName ) {
		WebElement webelemet=	elementFind(xpath);
		Select select=	new Select(webelemet);
		select.selectByIndex(ElementName);
		exetest.log(Status.INFO, "index is number is select or not");

	}


	public void dropDownselectByVisibleTextMethod(By xpath,String ElementName ) {
		WebElement webelemet=	elementFind(xpath);
		Select select=	new Select(webelemet);
		select.selectByVisibleText(ElementName);
		exetest.log(Status.INFO, "text is visible or not");
	}

	public String  getTittle() {
		String tittle=	driver.getTitle();
		exetest.log(Status.INFO, "Information is the pass or Failled");
		return tittle;

	}
	public void getUrl(String ElementName) {
		driver.get(ElementName);
		click(null);
		exetest.log(Status.INFO, "button is clickble or not");

	}



}


