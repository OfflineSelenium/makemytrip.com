package web.libraries;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WebElementFinder {

    @Autowired
    private WebDriver driver;

    /**
     * Find element is visible on the page based on the locator and property value provided.
     */
    public WebElement findElementByLocatorID(String id) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        } catch (Exception e) {
            return null;
        }
    }

    public WebElement findElementByLocatorXPath(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find elements are visible on the page based on the locator and property value provided.
     */
    public List<WebElement> findElementsByLocatorXPath(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
        } catch (Exception e) {
            return null;
        }
    }

//	private By getByElement(String locator, String value) {
//		By byElement = null;
//		switch (locator) {
//			case "id": {
//				byElement = By.id(value);
//				break;
//			}
//			case "xpath": {
//				byElement = By.xpath(value);
//				break;
//			}
//			case "name": {
//				byElement = By.name(value);
//				break;
//			}
//			case "className": {
//				byElement = By.className(value);
//				break;
//			}
//			case "linkText": {
//				byElement = By.linkText(value);
//				break;
//			}
//			case "partialLinkText": {
//				byElement = By.partialLinkText(value);
//				break;
//			}
//			case "tagName": {
//				byElement = By.tagName(value);
//				break;
//			}
//			case "cssSelector": {
//				byElement = By.cssSelector(value);
//				break;
//			}
//		}
//		return byElement;
//	}

}
