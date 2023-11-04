package repository;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Singleton
@AllArgsConstructor(onConstructor = @__(@Inject))
public class OpportunityRepository {
    private final WebDriver driver;

    public String waitAndGet(String selector) {
        List<WebElement> elements = waitAndGetElements(selector);
        return elements.isEmpty() ? "" : elements.get(0).getText();
    }

    public String waitAndGet(String conditionSelector, String extractorSelector) {
        return waitAndGet(conditionSelector, extractorSelector, null);
    }

    public String waitAndGet(String conditionSelector, String extractorSelector, String attribute) {
        waitAndGetElements(conditionSelector);
        return get(extractorSelector, attribute);
    }

    public String get(String selector) {
        return get(selector, null);
    }

    public String get(String selector, String attribute) {
        List<WebElement> elements = driver.findElements(By.cssSelector(selector));
        if (elements.isEmpty()) {
            return "";
        }
        return attribute == null ? elements.get(0).getText() :
                                   elements.get(0).getAttribute(attribute);
    }

    private List<WebElement> waitAndGetElements(String selector) {
        //System.out.println(" ================================= " + selector);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        AtomicReference<List<WebElement>> elements = new AtomicReference<>();
        try {
            wait.until(d -> {
                elements.set(driver.findElements(By.cssSelector(selector)));
                return !elements.get().isEmpty() && !elements.get().get(0).getText().trim().equals("");
            });
        } catch (TimeoutException e) {
            return List.of();
        }
        return elements.get();
    }
}
