package org.example.tests;

import org.example.pages.MainPage;
import org.example.pages.ResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BingSearchTest {
    private WebDriver driver;
    private MainPage mp = new MainPage(driver);
    private ResultsPage rp = new ResultsPage(driver);
    String input = "selenium";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @DisplayName("Проверка поисковой строки в поисковике bing")
    @RepeatedTest(1)
    public void searchResultsTest() {
        mp.sendText(input);
        rp.searchPage(0, input);
        assertEquals("https://www.selenium.dev/", rp.getCurrentUrl(input), "Переход не по первой ссылке списка!!!");
    }
}
