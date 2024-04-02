package testCase;

import java.io.IOException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_01_aroundCognizant extends BaseClass{
	
	
	//initializing HomePage instance
	HomePage hp;
	@BeforeSuite
	public void setupDriver() {
		hp = new HomePage(driver);
	}
	
	// running test to verify userinfo present in the website
	@Test(priority=1)
	public void start() throws InterruptedException {
		
		hp.userInfo();
		
	}
	
	//verifying Around News Tag present or not
	@Test(priority=2)
	public void verifyAroundNews() throws InterruptedException {
		hp.verifyNews();
	}
	
	
	//getting all the news heading under the Around Cognizant Section
	@Test(priority=3)
	public void getNewsHeading() throws InterruptedException {
		hp.getNewsHeading1();
	}
	
	//clicking see all button below Around News section
	@Test(priority=4)
	public void click() {
		hp.clickSeeAll();
	}
	//Verifying all news under the Around Cognizant Section
	@Test(priority=5)
	public void verifyAllNews() throws InterruptedException {
		hp.verifyAllNews();
	}
	
	//Checking individual news and the tooltip
	@Test(priority=6)
	public void individualClick() throws IOException {
		hp.individualCheck();
	}
	
}
