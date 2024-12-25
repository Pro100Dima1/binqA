package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ResultsPage {
    private WebDriver driver;

    private final Logger LOG = LoggerFactory.getLogger(ResultsPage.class);

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    @FindBy(xpath = "//a[contains (@class, 'tilk')]")
    private List<WebElement> result;

    public void searchPage(int num, String sel) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        System.out.println(result.get(num));
        wait.until(ExpectedConditions.visibilityOfAllElements(result));
        wait.until(ExpectedConditions.and(ExpectedConditions.attributeContains(result.get(num), "href", sel),
                ExpectedConditions.elementToBeClickable(result.get(num))));
        LOG.info("Нажата первая ссылка из списка");
        result.get(num).click();
    }

    public String getCurrentUrl(String url) {
        ArrayList tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) driver.switchTo().window(tabs.get(1).toString());
        WebDriverWait waitTwo = new WebDriverWait(driver, Duration.ofSeconds(6));
        waitTwo.until(ExpectedConditions.urlContains(url));
        return driver.getCurrentUrl();
    }

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
