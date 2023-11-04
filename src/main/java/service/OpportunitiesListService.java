package service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
@AllArgsConstructor(onConstructor = @__(@Inject))
public class OpportunitiesListService {
    
    final private WebDriver driver;
    
    public List<String> getOpportunities() throws IOException {
        driver.get("https://aiesec.org/search?earliest_start_date=2023-10-09&programmes=8&regions=1629");

        acceptCookies();
        scrollAllTheWayDown();

        return driver.findElements(By.cssSelector("a[href^=\"/opportunity/global-talent/\"]"))
                .stream()
                .map(e -> e.getAttribute("href"))
                .toList();
    }

    private void acceptCookies() throws IOException {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(60*1000));
        click(driver, "Accept all cookies");
        wait.until(d -> getButton(driver, "Accept all cookies") == null);
    }

    private void scrollAllTheWayDown() throws IOException {
        final AtomicInteger opportunities = new AtomicInteger(-1);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        while (getButton(driver, "Load more") != null) {
            click(driver, "Load more");
            AtomicInteger newOpportunitiesCount = new AtomicInteger();
            wait.until(d -> {
                newOpportunitiesCount.set(getOpportunitiesCount());
                return newOpportunitiesCount.get() > opportunities.get();
            });
            opportunities.set(newOpportunitiesCount.get());
        }
    }

    private void click(WebDriver driver, String text) {
        Optional.ofNullable(getButton(driver, text)).ifPresent(WebElement::click);
    }

    private WebElement getButton(WebDriver driver, String text) {
        List<WebElement> items = driver.findElements(By.cssSelector("button.ant-btn.ant-btn-primary"));
        for(WebElement item : items) {
            if (item.findElement(By.tagName("span")).getText().equals(text)) {
                return item;
            }
        }
        return null;
    }

    private int getOpportunitiesCount() {
        return driver.findElements(By.cssSelector("a[href^=\"/opportunity/global-talent/\"]")).size();
    }
}
