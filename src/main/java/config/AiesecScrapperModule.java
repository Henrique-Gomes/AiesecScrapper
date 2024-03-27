package config;

import com.google.inject.AbstractModule;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class AiesecScrapperModule extends AbstractModule {

    private final String[] args;

    @Override
    protected void configure() {
        bind(WebDriver.class).toInstance(getDriver());
        try {
            bind(Config.class).toInstance(getParamParser());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AiesecScrapperModule(String[] args) {
        this.args = args;
    }

    private WebDriver getDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        return driver;
    }

    private Config getParamParser() throws IOException {
        return new Config(args);
    }
}
