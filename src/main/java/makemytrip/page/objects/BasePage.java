package makemytrip.page.objects;

import web.libraries.WebElementFinder;
import web.libraries.WebPageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public abstract class BasePage {

	@Autowired
	protected WebDriver driver;

	@Autowired
	protected WebElementFinder webElementFinder;

	@Autowired
	protected WebPageFactory webPageFactory;

	@Value("${site.url}")
	private String siteBase;

	protected void loadPage(String path) {
		driver.get(siteBase + path);
	}

	public WebElement findElementByLocator(String property) {
		return webElementFinder.findElementByLocatorID(property);
	}

	public String title() {
		return driver.getTitle();
	}
}