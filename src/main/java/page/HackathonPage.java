package page;

import org.openqa.selenium.WebDriver;

public class HackathonPage extends Page {

	public HackathonPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sayfayiAc() {
		// regex kontrolu

		checkTextOfElement(
				"html/body/div[3]/div[2]/div/div/div/div/div[2]/div[2]/div/div/div/h1",
				"^[\\s\\S]*T.rkiye.nin .lk Test Otomasyon Hackathon.u[\\s\\S]*$",
				"Islem basarilidir");
		bekle(3000);
		System.out.println("Hackaton sayfasi acildi");

	}

}
