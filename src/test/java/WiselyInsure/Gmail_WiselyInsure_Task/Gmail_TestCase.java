package WiselyInsure.Gmail_WiselyInsure_Task;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Gmail_TestCase extends BaseBuild{
	
	@Test(priority = 1)
	public void Login_valid() throws InterruptedException{
		ExtentTest test = extent.createTest("Inserting Email & pass", "Adding user mail and password");

		//Insert Email & pass
		WebElement Email_field = driver.findElement(By.xpath(Email_ID));
		Email_field.sendKeys("peter.wiselyinsure@gmail.com");
		
		WebElement next_btn = driver.findElement(By.xpath(Next_btn));
		next_btn.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pass_ID)));;
		
		WebElement pass_field = driver.findElement(By.xpath(Pass_ID));
		pass_field.sendKeys("kacanape");
		
		WebElement nextpass_btn = driver.findElement(By.xpath(nextPass_btn));
		nextpass_btn.click();		
		
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UserICon)));
		WebElement user_icon = driver.findElement(By.xpath(UserICon));
		user_icon.click();
		WebElement emailCheck = driver.findElement(By.xpath(Email_assert));
		Assert.assertTrue(emailCheck.getText().contains("peter.wiselyinsure@gmail.com"));
		
		test.log(Status.PASS, "Email & pass Added successfully with a valid data");
	}
	
	@Test(priority = 2)
	public void Logout() throws InterruptedException{
		ExtentTest test = extent.createTest("Logout from Gmail", "sign out from the inbox page");

		//Sign out 
		WebElement logoutBTN = driver.findElement(By.xpath(logout_btn));
		Thread.sleep(500);
		logoutBTN.click();
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		WebElement account_lable = driver.findElement(By.xpath(choose_account));
		Assert.assertTrue(account_lable.getText().contains("اختيار حساب"));	
			
		test.log(Status.PASS, "User logout successfully with a valid data");
	}
	
	@Test(priority = 3)
	public void login_Invalid() throws InterruptedException{
		ExtentTest test = extent.createTest("Inserting invalid Email & pass", "Login with invalid data");

		//login with invalid data
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		WebElement select_anthor = driver.findElement(By.xpath(another_account));
		Thread.sleep(500);
		select_anthor.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Login_lable_ID)));;
		
		WebElement Email_Field = driver.findElement(By.xpath(Email_ID));
		Email_Field.sendKeys("invalid Email");
		
		WebElement next_btn = driver.findElement(By.xpath(Next_btn));
		next_btn.click();
		
		WebElement alert = driver.findElement(By.xpath(Alert_ID));
		Assert.assertTrue(alert.getText().contains("يجب إدخال بريد إلكتروني أو رقم هاتف صالح"));	
			
		test.log(Status.PASS, "User inserted an invalid data");
	}

}
