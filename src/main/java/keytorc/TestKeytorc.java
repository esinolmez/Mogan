package keytorc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import page.HackathonPage;


public class TestKeytorc {

	private WebDriver driver;
	private WebDriverWait wait;

	public TestKeytorc(FirefoxDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 60);
	}



	public void kontrolSystem() {
		try {

			HackathonPage page = new HackathonPage(driver);
			page.sayfayiAc();

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}
	
	private void bekle(long i) {

		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	


}
