package WiselyInsure.Gmail_WiselyInsure_Task;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseBuild extends Variables {
	ExtentReports extent;
	ChromeDriver driver ;

	@BeforeSuite
	public void globalSetup () throws IOException, InterruptedException {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");

		// create Extent Reports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);


		String chrome = System.getProperty("user.dir")+"\\recourse\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chrome);
		driver = new ChromeDriver();
		driver.navigate().to(Gmail_URL);
		driver.manage().window().maximize();

		ExtentTest test = extent.createTest("Navigate to Gmail Website", "Openning Gmail website and appears The Login page");
		// Assertion 
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		WebElement loginLable = driver.findElement(By.xpath(Login_lable_ID));
		Assert.assertTrue(loginLable.getText().contains("تسجيل الدخول" ));

		test.log(Status.PASS, "Navigate to Gmail Website successfully");
	}

	@AfterSuite
	public void globalTearDown () {
		extent.flush();
		driver.close();
	}

}
