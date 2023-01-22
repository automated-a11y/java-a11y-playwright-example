package com.automated.a11y;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.github.sridharbandi.pw.HtmlCsRunner;
import org.junit.jupiter.api.*;

import java.io.IOException;

public class HtmlcsTest {
    private static HtmlCsRunner htmlCsRunner;
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
        htmlCsRunner.generateHtmlReport();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        htmlCsRunner = new HtmlCsRunner(page);
    }

    @AfterEach
    void closeContext() throws IOException {
        htmlCsRunner.execute();
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
