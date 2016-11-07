package hackathon;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import keytorc.TestKeytorc;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestHackathon {
	private FirefoxDriver driver;
	private String baseUrl;

	@BeforeMethod
	public void init() throws MalformedURLException {
		System.out
				.println("--------------------Test Baslatildi-------------------");
		driver = new FirefoxDriver();
		baseUrl = "http://www.keytorc.com/hackathon-test-otomasyonu/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// browseri tam ekran yapar
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}

	@Test
	public void testMimeTipiSorgulama() {
		System.out.println("Calistirilan Test: TestKeytorc");

		// on yuz sinifini yarat
		final TestKeytorc testKeytorc = new TestKeytorc(driver);

		// tum senaryoyu kostur
		testKeytorc.kontrolSystem();

	}

	@AfterMethod
	public void destroy() {
		driver.quit();
		System.out.println("--------------------Test Bitti-------------------");
	}

}
