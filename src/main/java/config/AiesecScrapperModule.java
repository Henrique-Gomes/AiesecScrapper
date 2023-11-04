package config;

import com.google.inject.AbstractModule;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AiesecScrapperModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WebDriver.class).toInstance(getDriver());
    }

    private WebDriver getDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        return driver;
    }
}
