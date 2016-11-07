package page;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Test edilecek ortak methodlarin bulundugu siniftir
 * 
 * @author ekarabacakoglu
 */
public abstract class Page {
	private WebDriver driver;
	public String testVerisi;

	public Page(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Verilen i milisaniye kadar beklemeyi saglar
	 * 
	 * @param i
	 */
	public void bekle(long i) {

		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * xpath degerine sahip elementin, verilen regex kalibina uyup uymadigini
	 * kontrol eder. Bulamadiginda notFoundText mesajini yazar
	 * 
	 * @param xpath
	 * @param regex
	 * @param notFoundText
	 */
	public void checkTextOfElement(String xpath, String regex,
			String notFoundText) {
		WebDriverWait wait2 = new WebDriverWait(driver, 60);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(xpath)));
		String text1 = driver.findElement(By.xpath(xpath)).getText();

		boolean bool = text1.matches(regex);
		if (!bool) {
			Assert.assertEquals(text1, notFoundText);
		}
	}

	/**
	 * toBeNotExpectedText verisinin ekranda bulunmamasina kadar bekler. Max 60
	 * sn bekler
	 * 
	 * @param toBeNotExpectedText
	 * @param foundMessage
	 * @throws InterruptedException
	 */
	public void waitTextNotPresent(final String toBeNotExpectedText,
			String foundMessage) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.withMessage("\nGitmesi beklenen metin:" + toBeNotExpectedText);
		wait.until(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				return !(driver.findElement(By.cssSelector("BODY")).getText()
						.matches(toBeNotExpectedText));
			}
		});
	}

	/**
	 * toBeExpectedText verisinin ekranda bulunmasina kadar bekler. Max 60 sn
	 * bekler
	 * 
	 * @param toBeExpectedText
	 * @param notFoundMessage
	 * @throws InterruptedException
	 */
	public void waitTextPresent(final String toBeExpectedText,
			String notFoundMessage) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.withMessage("\n Bulunamayan metin:" + toBeExpectedText);
		wait.until(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("BODY")).getText()
						.matches(toBeExpectedText);
			}
		});
	}

	/**
	 * 
	 * Verilen xpath degerine sahip elemanin olup olmadigini kontrol eder
	 * 
	 * @param xpath
	 * @param regex
	 * @param notFoundText
	 * @return doesExist
	 */
	public boolean checkElementExists(String xpath, String regex,
			String notFoundText) {
		boolean doesExist = false;
		bekle(3000);
		int size = driver.findElements(By.xpath(xpath)).size();
		if (size > 0) {
			String text1 = driver.findElement(By.xpath(xpath)).getText();

			boolean bool = text1.matches(regex);
			if (bool) {
				doesExist = true;
			}
		}

		return doesExist;

	}

	/**
	 * Unique test verisi olusturur
	 */
	public void testVerisiOlustur() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String dateFrmt = dateFormat.format(date);
		testVerisi = "T" + dateFrmt;
	}

	/**
	 * Verilen option list'te verilen option'un bulunup bulunmadigini kontrol
	 * eder
	 * 
	 * @param optListXPATH
	 * @param optText
	 */
	public void checkOptionListExist(String optListXPATH, String optText) {
		boolean res = false;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(optListXPATH)));
		bekle(3000);

		List<WebElement> allElements = driver.findElements(By
				.xpath(optListXPATH));
		for (WebElement element : allElements) {
			String txt = element.getAttribute("data-item-label");
			if (txt.contains(optText)) {
				res = true;
			}
		}

		if (!res) {
			Assert.assertTrue(false, "Pasife alinan veri listede goruntulendi");
		}
	}

	/**
	 * Verilen option list'te verilen option'un bulunup bulunmadigini kontrol
	 * eder
	 * 
	 * @param optListXPATH
	 * @param optText
	 */
	public void checkOptionListNotExist(String optListXPATH, String optText) {
		boolean res = true;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(optListXPATH)));
		bekle(3000);

		List<WebElement> allElements = driver.findElements(By
				.xpath(optListXPATH));
		for (WebElement element : allElements) {
			String txt = element.getAttribute("data-item-label");
			if (txt.contains(optText)) {
				res = false;
			}
		}

		if (res) {
			Assert.assertTrue(false, "Pasife alinan veri listede goruntulendi");
		}
	}

	/**
	 * Verilen xpath'e sahip select eleamninda ilgili index secilir
	 * 
	 * @param selectItemXPATH
	 * @param index
	 */
	public void selectItem(String selectItemXPATH, int index) {
		Select select = new Select(
				driver.findElement(By.xpath(selectItemXPATH)));
		select.selectByIndex(index);
	}
	
	
	protected abstract void sayfayiAc();

}
