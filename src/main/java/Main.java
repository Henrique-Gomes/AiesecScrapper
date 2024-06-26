import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AiesecScrapperModule;
import config.Config;
import org.openqa.selenium.WebDriver;
import service.AiesecScrapperService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Injector injector = Guice.createInjector(new AiesecScrapperModule(args));
        AiesecScrapperService aiesecScrapperService = injector.getInstance(AiesecScrapperService.class);
        try {
            aiesecScrapperService.getCSV();
        } finally {
            injector.getInstance(WebDriver.class).close();
            injector.getInstance(Config.class).getOutput().close();
        }
    }
}