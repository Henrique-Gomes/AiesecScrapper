import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AiesecScrapperModule;
import org.openqa.selenium.WebDriver;
import service.AiesecScrapperService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Injector injector = Guice.createInjector(new AiesecScrapperModule());
        AiesecScrapperService aiesecScrapperService = injector.getInstance(AiesecScrapperService.class);
        try {
            aiesecScrapperService.getCSV(args.length > 0 ? args[0] : null);
        } finally {
            injector.getInstance(WebDriver.class).close();
        }
    }
}