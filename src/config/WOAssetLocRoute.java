package config;

import static executionEngine.DriverScript.OR;
import static executionEngine.DriverScript.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import executionEngine.DriverScript;
import utility.Log;

public class WOAssetLocRoute extends ActionKeywords {
	
	static WebDriverWait wait = new WebDriverWait(driver, 5);

	public static void woAssetLocRoute() {
		scenario1("1000014");
		scenario2Task("1000014");
		scenario2Child("1000014");
		scenario3();
	}
	
    /*053.01. Any WO that is submitted for approval via the WO workflow must reference an Asset, Location or Route*/
	static void scenario1(String asset) {
		try {
			Log.info("Go to WO scenario1........................");
//			driver.findElement(By.xpath(OR.getProperty("lnk_Home"))).click();
			waitForElementDisplayed("hvr_WO", "1");
			hover("hvr_WO","lnk_WO");
			waitForElementDisplayed("btn_New", "1");
			createWOwithPlans("WOAssetLocRoute scenario1", "CM");
//			remove reference to asset, location or route
			Log.info("remove reference to asset, location or route");
			driver.findElement(By.xpath(OR.getProperty("tab_Main"))).click();
			driver.findElement(By.xpath(OR.getProperty("txtbx_AssetNum"))).clear();
			driver.findElement(By.xpath(OR.getProperty("txtbx_Location"))).clear();
			save("1", "1");
			driver.findElement(By.xpath(OR.getProperty("btn_Route"))).click();
			verifyAlert("msg_Popup", "This Work Order cannot be submitted for approval as it does not reference an Asset, Location or Route, or one or more of its children or tasks does not reference an Asset, Location or Route."); 
			driver.findElement(By.xpath(OR.getProperty("btn_Close"))).click();
			waitFor();
//          populate only assetnum  
			Log.info("populate only assetnum");
			driver.findElement(By.xpath(OR.getProperty("txtbx_AssetNum"))).sendKeys(asset);
			save("1", "1");
			driver.findElement(By.xpath(OR.getProperty("btn_Route"))).click();
			waitForElementDisplayed("radio_WF_DFA", "1");
			driver.findElement(By.xpath(OR.getProperty("btn_OK"))).click();
//			verify routed in workflow
			Log.info("verify routed in workflow");
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(OR.getProperty("hvr_Workflow")))));
            hover("hvr_Workflow","lnk_WFAssignment");
            isEmpty("tbl_WFAssignment", "False");
            driver.findElement(By.xpath(OR.getProperty("btn_OK"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(OR.getProperty("hvr_Workflow")))));
//          populate only location 
            Log.info("populate only location ");
            hover("hvr_Workflow","stop_Workflow");
            driver.findElement(By.xpath(OR.getProperty("btn_OK"))).click();
            waitFor();
//            wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath(OR.getProperty("titlebar_message"))), "Process KR_WO stopped."));
            changeStatus("", "Create");
            waitFor();
            driver.findElement(By.xpath(OR.getProperty("txtbx_AssetNum"))).clear();
            driver.findElement(By.xpath(OR.getProperty("txtbx_Location"))).sendKeys("9002086");
//            driver.findElement(By.xpath(OR.getProperty("btn_Yes"))).click();
            waitFor();
            save("1", "1");
            driver.findElement(By.xpath(OR.getProperty("btn_Route"))).click();
			waitForElementDisplayed("radio_WF_DFA", "1");
			driver.findElement(By.xpath(OR.getProperty("btn_OK"))).click();
//			verify routed in workflow
			Log.info("verify routed in workflow");
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(OR.getProperty("hvr_Workflow")))));
            hover("hvr_Workflow","lnk_WFAssignment");
            isEmpty("tbl_WFAssignment", "False");
            driver.findElement(By.xpath(OR.getProperty("btn_OK"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(OR.getProperty("hvr_Workflow")))));
            hover("hvr_Workflow","stop_Workflow");
            driver.findElement(By.xpath(OR.getProperty("btn_OK"))).click();
            waitFor();
//            wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath(OR.getProperty("titlebar_message"))), "Process KR_WO stopped."));
            changeStatus("", "Create");
            driver.findElement(By.xpath(OR.getProperty("txtbx_AssetNum"))).clear();
            driver.findElement(By.xpath(OR.getProperty("txtbx_Location"))).clear();
//          populate only route
            Log.info("populate only route");
            driver.findElement(By.xpath(OR.getProperty("lnk_ApplyRoute"))).click();
            waitForElementDisplayed("tbl_Routes", "1");
            driver.findElement(By.xpath("//td[contains(@id,'_tdrow_[C:0]-c[R:0]')]")).click();
            waitForElementDisplayed("btn_Close", "1");
            driver.findElement(By.xpath(OR.getProperty("btn_Close"))).click();
            waitFor();
            driver.findElement(By.xpath(OR.getProperty("tab_Plans"))).click();
            waitForElementDisplayed("tbl_WO_Child", "1");
            Log.info("Verify table not empty...");
            tableNotEmpty("Children of Work Order", "True");
            waitFor();
            driver.findElement(By.xpath(OR.getProperty("tab_Main"))).click();
            waitForElementDisplayed("btn_Route", "1");
            driver.findElement(By.xpath(OR.getProperty("btn_Route"))).click();
			waitForElementDisplayed("radio_WF_DFA", "1");
			driver.findElement(By.xpath(OR.getProperty("btn_OK"))).click();
			Thread.sleep(3000);
//			verify routed in workflow
			Log.info("verify routed in workflow");
			waitForElementDisplayed("icon_Routed", "1");
			scrollUp("hvr_Workflow","");
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(OR.getProperty("hvr_Workflow")))));
			hover("hvr_Workflow","lnk_WFAssignment");
            isEmpty("tbl_WFAssignment", "False");
            driver.findElement(By.xpath(OR.getProperty("btn_OK"))).click();
		} catch(AssertionError ae){
			Log.error("Assertion failed --- " + ae.getMessage());
			DriverScript.bResult = false;
	    } catch (NoSuchElementException e) {
	    	Log.error("Element not found --- " + e.getMessage());
 			DriverScript.bResult = false;
	    } catch (Exception e) {
	    	Log.error("Exception --- " + e.getMessage());
 			DriverScript.bResult = false;
	    }	
	}
	
	/*053.02. Any WO that is a child of a WO being submitted for approval must have an Asset, Location or Route on it*/
	static void scenario2Task(String asset) {
		try {
//			String assetVal="//input[@id=(//label[text()='Asset:'][1]/@for)]";
			String assetVal="//input[@id='mx400-tb']"; //TODO
      		Log.info("Go to scenario2Task.......................");
      		driver.findElement(By.xpath(OR.getProperty("lnk_Home"))).click();
			waitForElementDisplayed("hvr_WO", "1");
			hover("hvr_WO","lnk_WO");
			waitForElementDisplayed("btn_New", "1");
			createWOwithPlans("WOAssetLocRoute scenario2", "CM");
//			--------------
//			driver.findElement(By.xpath(OR.getProperty("txtbx_QuickSearch"))).sendKeys("2796197");
//			enter("txtbx_QuickSearch","1");
//			waitForElementDisplayed("tab_Plans", "1");
//			driver.findElement(By.xpath(OR.getProperty("tab_Plans"))).click();
//			-----------------
			Log.info("create task WO");
			waitForElementDisplayed("btn_NewRow_TasksWO", "1");
			driver.findElement(By.xpath(OR.getProperty("btn_NewRow_TasksWO"))).click();
			waitForElementDisplayed("txtbx_WOTaskDescription", "1");
			waitFor();
			driver.findElement(By.xpath(OR.getProperty("txtbx_WOTaskDescription"))).sendKeys("WOAssetLocRoute scenario2 Task");
			Log.info("asset..........."+driver.findElement(By.xpath(assetVal)).getAttribute("value"));
			scrollDown(assetVal);
			Assert.assertTrue(driver.findElement(By.xpath(assetVal)).getAttribute("value").equals(asset));
			save("1", "1");
			Log.info("Clear asset");
			Log.info("Go to Task WO");
			driver.findElement(By.xpath(OR.getProperty("btn_ReferenceWO_chevron"))).click();
			driver.findElement(By.xpath(OR.getProperty("lnk_ActivitiesAndTasks"))).click();
			waitForElementDisplayed("btn_Return", "1");
			driver.findElement(By.xpath(OR.getProperty("txtbx_AssetNum"))).clear();
	        save("1", "1");
	        Thread.sleep(1000);
	        driver.findElement(By.xpath(OR.getProperty("btn_Return"))).click();
	        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(OR.getProperty("app_name")), "Work Order Tracking"));
            Log.info("route to workflow");
            driver.findElement(By.xpath(OR.getProperty("btn_Route"))).click();
            Thread.sleep(1000);
            verifyAlert("msg_Popup", "This Work Order cannot be submitted for approval as it does not reference an Asset, Location or Route, or one or more of its children or tasks does not reference an Asset, Location or Route."); 
			driver.findElement(By.xpath(OR.getProperty("btn_Close"))).click();
			waitFor();
			Log.info("populate asset");
			Log.info("Go to Task WO");
			driver.findElement(By.xpath(OR.getProperty("btn_ReferenceWO_chevron"))).click();
			driver.findElement(By.xpath(OR.getProperty("lnk_ActivitiesAndTasks"))).click();
			waitForElementDisplayed("btn_Return", "1");
			driver.findElement(By.xpath(OR.getProperty("txtbx_AssetNum"))).sendKeys("1000014");
			Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("txtbx_AssetNum"))).getAttribute("value").equals("1000014"));
	        save("1", "1");
	        Thread.sleep(1000);
	        driver.findElement(By.xpath(OR.getProperty("btn_Return"))).click();
	        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(OR.getProperty("app_name")), "Work Order Tracking"));
            Log.info("route to workflow");
            driver.findElement(By.xpath(OR.getProperty("btn_Route"))).click();            
			waitForElementDisplayed("radio_WF_DFA", "1");
			driver.findElement(By.xpath(OR.getProperty("btn_OK"))).click();
			Log.info("verify routed in workflow");
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(OR.getProperty("hvr_Workflow")))));
            hover("hvr_Workflow","lnk_WFAssignment");
            isEmpty("tbl_WFAssignment", "False");
            driver.findElement(By.xpath(OR.getProperty("btn_OK"))).click();
		}catch(AssertionError ae){
			Log.error("Assertion failed --- " + ae.getMessage());
			DriverScript.bResult = false;
	    } catch (NoSuchElementException e) {
	    	Log.error("Element not found --- " + e.getMessage());
 			DriverScript.bResult = false;
	    } catch (Exception e) {
	    	Log.error("Exception --- " + e.getMessage());
 			DriverScript.bResult = false;
	    }	
	}
	

	/*053.02. Any WO that is a child of a WO being submitted for approval must have an Asset, Location or Route on it*/
	static void scenario2Child(String asset) {
		try {
			String assetVal="//input[@id=(//label[text()='Asset:'][1]/@for)]";
			
			Log.info("Go to scenario2Child...............................");
			driver.findElement(By.xpath(OR.getProperty("lnk_Home"))).click();
			waitForElementDisplayed("hvr_WO", "1");
			hover("hvr_WO","lnk_WO");
			waitForElementDisplayed("btn_New", "1");
			createWOwithPlans("WOAssetLocRoute scenario2", "CM");
			Log.info("create Child WO");
			waitForElementDisplayed("btn_NewRow_ChildrenWO", "1");
			driver.findElement(By.xpath(OR.getProperty("btn_NewRow_ChildrenWO"))).click();
			waitForElementDisplayed("txtbx_WOChildDescription", "1");
			waitFor();
			driver.findElement(By.xpath(OR.getProperty("txtbx_WOChildDescription"))).sendKeys("WOAssetLocRoute scenario2 Child");
			Assert.assertEquals(driver.findElement(By.xpath(assetVal)).getAttribute("value"), "");
			Log.info("route to workflow");
            driver.findElement(By.xpath(OR.getProperty("btn_Route"))).click();
            verifyAlert("msg_Popup", "This Work Order cannot be submitted for approval as it does not reference an Asset, Location or Route, or one or more of its children or tasks does not reference an Asset, Location or Route."); 
			driver.findElement(By.xpath(OR.getProperty("btn_Close"))).click();
			waitFor();
            Log.info("populate asset");
			driver.findElement(By.xpath(assetVal)).sendKeys("1000014");
			Assert.assertTrue(driver.findElement(By.xpath(assetVal)).getAttribute("value").equals("1000014"));
			save("1", "1");
            Log.info("route to workflow");
            driver.findElement(By.xpath(OR.getProperty("btn_Route"))).click();            
			waitForElementDisplayed("radio_WF_DFA", "1");
			driver.findElement(By.xpath(OR.getProperty("btn_OK"))).click();
			Log.info("verify routed in workflow");
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(OR.getProperty("hvr_Workflow")))));
            hover("hvr_Workflow","lnk_WFAssignment");
            isEmpty("tbl_WFAssignment", "False");
            driver.findElement(By.xpath(OR.getProperty("btn_OK"))).click();
		}catch(AssertionError ae){
			Log.error("Assertion failed --- " + ae.getMessage());
			DriverScript.bResult = false;
	    } catch (NoSuchElementException e) {
	    	Log.error("Element not found --- " + e.getMessage());
 			DriverScript.bResult = false;
	    } catch (Exception e) {
	    	Log.error("Exception --- " + e.getMessage());
 			DriverScript.bResult = false;
	    }	
	}
	
	/*053.02. WOs that do not get approved via this mechanism (e.g. PMs and 155 high priority SR generated WOs) will not be subjected to these requirements*/
	static void scenario3() {
		try {
			Log.info("Go to WO scenario3................................");
			logout("1", "1");
			openBrowser("", "Mozilla");
			driver.findElement(By.xpath(".//*[@id='username']")).sendKeys("mx155");
			driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("Kiwirail123");
			driver.findElement(By.xpath(".//*[@id='loginbutton']")).click();
			waitForElementDisplayed("hvr_WO", "1");
			hover("hvr_WO","lnk_SR");
			waitForElementDisplayed("btn_New", "1");
			createSR("WOAssetLocRoute scenario3", "KRNETWORK");
			waitFor();
			Log.info("clear asset");
			driver.findElement(By.xpath(OR.getProperty("txtbx_AssetNum"))).clear();
			Log.info("clear location");
			driver.findElement(By.xpath(OR.getProperty("txtbx_AssetNum"))).clear();
			Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("txtbx_AssetNum"))).getAttribute("value").equals(""));
			Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("txtbx_Location"))).getAttribute("value").equals(""));
			Log.info("Set priority to 1");
			driver.findElement(By.xpath(OR.getProperty("txtbx_Priority"))).clear();
			driver.findElement(By.xpath(OR.getProperty("txtbx_Priority"))).sendKeys("1");
			Assert.assertTrue(driver.findElement(By.xpath(OR.getProperty("txtbx_Priority"))).getAttribute("value").equals("1"));
			driver.findElement(By.xpath(OR.getProperty("txtbx_ReporterType"))).clear();
			driver.findElement(By.xpath(OR.getProperty("txtbx_ReporterType"))).sendKeys("EMERGENCY");
			save("1", "1");
			Log.info("route to workflow");
            driver.findElement(By.xpath(OR.getProperty("btn_Route"))).click();
            wait.until(ExpectedConditions.textToBePresentInElementValue(driver.findElement(By.xpath(OR.getProperty("txtbx_Status"))), "INPROG"));
            Log.info("Verify generated WO...");
            driver.findElement(By.xpath(OR.getProperty("tab_RelatedRecord"))).click();
            isEmpty("tbl_Related_WO", "False");
            driver.findElement(By.xpath(OR.getProperty("btn_Related_Chevron_Row1"))).click();
            driver.findElement(By.xpath(OR.getProperty("lnk_Related_WO"))).click();
            assertValue("txtbx_Status", "APPR");
            assertValue("txtbx_Worktype", "EM");
            driver.findElement(By.xpath(OR.getProperty("btn_Return"))).click();
		}catch(AssertionError ae){
			Log.error("Assertion failed --- " + ae.getMessage());
			DriverScript.bResult = false;
	    } catch (NoSuchElementException e) {
	    	Log.error("Element not found --- " + e.getMessage());
 			DriverScript.bResult = false;
	    } catch (Exception e) {
	    	Log.error("Exception --- " + e.getMessage());
 			DriverScript.bResult = false;
	    }	
	}
	
	static void scrollDown(String object) throws Exception {
		  Log.info("scrolling down to field............");
		  WebElement element = driver.findElement(By.xpath(object));
		  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		  Thread.sleep(500); 
	  }
}
