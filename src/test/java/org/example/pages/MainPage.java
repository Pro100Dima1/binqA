package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.bing.com/
public class MainPage {
    private WebDriver driver;

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;


    public void sendText(String text) {
        searchField.sendKeys(text);
        searchField.submit();
        System.out.println("Введен текст: " + text);
    }

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
