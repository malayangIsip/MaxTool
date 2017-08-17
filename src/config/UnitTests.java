package config;

import static executionEngine.DriverScript.OR;
import static executionEngine.DriverScript.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import executionEngine.DriverScript;
import utility.Log;

public class UnitTests extends ActionKeywords {
    static WebDriverWait wait = new WebDriverWait(driver, 3);
		
	public static void unitTests(String object, String data) {
//		commTemplateEmail1("SLEEPERS");

	}
	
	//Scenario 1 - user has primary email
	static void commTemplateEmail1(String feature) {
		try {
			Log.info("Start commTemplateEmail..................");
			driver.findElement(By.xpath(OR.getProperty("lnk_Home"))).click();
			waitForElementDisplayed("hvr_Asset", "1");
			hover("hvr_Asset","lnk_Feature");
			driver.findElement(By.xpath(OR.getProperty("txtbx_QuickSearch"))).sendKeys(feature);
			enter("txtbx_QuickSearch","1");
			waitForElementDisplayed("txtbx_Feature", "1");

			boolean checked = driver.findElement(By.xpath(OR.getProperty("chkbx_ApplyGapsandOverlaps"))).getAttribute("aria-checked").equals("true");
			if (!checked) {
				driver.findElement(By.xpath(OR.getProperty("chkbx_ApplyGapsandOverlaps"))).click();
			}
	    } catch (NoSuchElementException e) {
	    	Log.error("Element not found --- " + e.getMessage());
 			DriverScript.bResult = false;
	    } catch (Exception e) {
	    	Log.error("Exception --- " + e.getMessage());
 			DriverScript.bResult = false;
	    }	
	}
	
	//Scenario 2 - user has no primary email
		static void commTemplateEmail2(String feature) {
			try {
				Log.info("Start commTemplateEmail..................");
				driver.findElement(By.xpath(OR.getProperty("lnk_Home"))).click();
				waitForElementDisplayed("hvr_Asset", "1");
				hover("hvr_Asset","lnk_Feature");
				driver.findElement(By.xpath(OR.getProperty("txtbx_QuickSearch"))).sendKeys(feature);
				enter("txtbx_QuickSearch","1");
				waitForElementDisplayed("txtbx_Feature", "1");

				boolean checked = driver.findElement(By.xpath(OR.getProperty("chkbx_ApplyGapsandOverlaps"))).getAttribute("aria-checked").equals("true");
				if (!checked) {
					driver.findElement(By.xpath(OR.getProperty("chkbx_ApplyGapsandOverlaps"))).click();
				}
		    } catch (NoSuchElementException e) {
		    	Log.error("Element not found --- " + e.getMessage());
	 			DriverScript.bResult = false;
		    } catch (Exception e) {
		    	Log.error("Exception --- " + e.getMessage());
	 			DriverScript.bResult = false;
		    }	
		}
}
