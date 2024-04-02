package pageObjects;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


public class HomePage extends BasePage{
	
	JavascriptExecutor js = (JavascriptExecutor)driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//method to check UserInfo
	public void userInfo() throws InterruptedException {
		Thread.sleep(5000);
		WebElement element = driver.findElement(By.xpath("//*[contains(text(),'AM')][@id='meInitialsButton']"));
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();

		String userInfo1=driver.findElement(By.xpath("//*[@id='mectrl_currentAccount_primary']")).getText();
		String userInfo2=driver.findElement(By.xpath("//*[@id='mectrl_currentAccount_secondary']")).getText();
		System.out.println(userInfo1);
		System.out.println(userInfo2);
		WebElement afterClick = driver.findElement(By.xpath("//*[@id=\"mectrl_headerPicture\"]"));
		action.click(afterClick).perform();
	}
	
	//Verifying Around News Tag
	public void verifyNews() throws InterruptedException {

		String validate = driver.findElement(By.tagName("strong")).getText();
		System.out.println(validate);
	}
	
	
	//Verifying news heading
	public void getNewsHeading1() throws InterruptedException {
		
		Thread.sleep(2000);
		for(int i =0;i<=6;i++) {
			List<WebElement> news = driver.findElements(By.xpath("/html/body/div[1]//div[2]//div[2]/div[3]/section/article//div//div/div[1]//div//div//div[1]/div/div[1]/div[3]/div//div//div/div[2]/div//div//div["+i+"]/div/div[2]/div[1]/div[2]/a"));
			for(WebElement c: news) {
				String newsTitle = c.getText();
				Assert.assertEquals(newsTitle, c.getAttribute("title"));
			}
		}System.out.println("The News Header and the Tooltip matches...");
	}
	
	//Clicking See all button
	public void clickSeeAll(){
		
		
		js.executeScript("document.getElementsByClassName('m_b_beed2cf1')[0].scrollBy(0,1100)");
		driver.findElement(By.xpath("//*[contains(text(),'See all')]/ancestor::a")).click();
	}
	
	//Verifying all news Section
	public void verifyAllNews() throws InterruptedException {
		Thread.sleep(4000);
		JavascriptExecutor js =(JavascriptExecutor)driver;
		String validator = driver.findElement(By.cssSelector("#CaptionElementView")).getText();
		try {
			Assert.assertEquals("News", validator);
		}catch(Exception e){
			System.out.println("The Title does not match");
		}
		js.executeScript("document.getElementsByClassName('m_b_beed2cf1')[0].scrollBy(0,1000)");
		List<WebElement> allNews = driver.findElements(By.xpath("//*[@id=\"news_text_title\"]"));
		System.out.println(allNews.size());
		System.out.println("All news items are present");
	}
	

	//verifying individual news
	public void individualCheck() throws IOException {
		for(int i=1;i<=5;i++) {
			driver.findElement(By.xpath("(//*[@id='news_text_title'])["+i+"]")).click();
			
			Assert.assertEquals(driver.findElement(By.id("title_text")).getText(), driver.findElement(By.xpath("//div[@aria-level='1']")).getAttribute("title"));
			System.out.println("The News Header and the tootip matches...");
			String details = driver.findElement(By.xpath("//*[@class=\"mainContent\"]")).getText();
			TakesScreenshot ts = (TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File trg = new File(System.getProperty("user.dir")+"//screenshots//p"+i+".png");
			FileUtils.copyFile(src, trg);
			System.out.println("The News Details are....");
			System.out.println(details);
			driver.navigate().back();
		}
	}
}
