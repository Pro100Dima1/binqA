package org.example.tests;

import org.example.pages.MainPage;
import org.example.pages.ResultsPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BingSearchTest {
    private WebDriver driver;

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
        String input = "selenium";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);
        ResultsPage rp = new ResultsPage(driver);
        rp.searchPage(0, input);
        assertEquals("https://www.selenium.dev/", rp.getCurrentUrll(input), "Переход не по первой ссылке списка!!!");
    }


}
