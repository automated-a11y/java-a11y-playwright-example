package com.automated.a11y;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.github.sridharbandi.pw.AxeRunner;
import org.junit.jupiter.api.*;

import java.io.IOException;

public class AxeTest {
    private static AxeRunner axeRunner;
    static Playwright playwright;
    static Browser browser;

    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() throws IOException {
        playwright.close();
        axeRunner.generateHtmlReport();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        axeRunner = new AxeRunner(page);
    }

    @AfterEach
    void closeContext() throws IOException {
        axeRunner.execute();
        context.close();
    }

    @Test
    public void googleTest() {
        page.navigate("https://www.google.com/");
    }

    @Test
    public void stockTest() {
        page.navigate("https://www.istockphoto.com/");
    }
}
