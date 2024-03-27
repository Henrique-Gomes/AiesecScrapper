package service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import model.section.*;
import model.Opportunity;
import org.openqa.selenium.WebDriver;
import service.section.*;
import repository.OpportunityRepository;

@Singleton
@AllArgsConstructor(onConstructor = @__(@Inject))
public class OpportunityService {

    private final WebDriver driver;
    
    private final OpportunityRepository opportunityRepository;

    private final SummaryService summaryService;

    private final SlotsService slotsService;

    private final RoleService roleService;

    private final EligibilityService eligibilityService;

    private final LogisticsService logisticsService;

    private final VisaService visaService;

    public Opportunity getTitles() {
        return new Opportunity(
                "URL",
                "Title",
                "Group",
                "Place",
                "Global Talent",
                "Salary",
                "Backgrounds",
                "Languages",
                "Fee",
                "Slots",
                "Description",
                "Period",
                "Openings",
                "Deadline",
                "About",
                "Working Hours",
                "Remote Duration",
                "Responsibilities",
                "Processes",
                "Skills",
                "Nationality",
                "Study",
                "Provided",
                "Not Included",
                "Visa Type",
                "Visa Link",
                "Health Insurance"
        );
    }
    
    public Opportunity scrapOpportunity(String url) {
        driver.get(url);

        Summary summary = summaryService.summary();
        Slots slots = slotsService.slots();
        Role role = roleService.role();
        Eligibility eligibility = eligibilityService.eligibility();
        Logistics logistics = logisticsService.logisticsSection();
        Visa visa = visaService.visaSection();

        return new Opportunity(
                url,
                summary.title(),
                summary.group(),
                summary.place(),
                summary.globalTalent(),
                summary.salary(),
                summary.backgrounds(),
                summary.languages(),
                slots.fee(),
                slots.slots(),
                description(),
                slots.period(),
                slots.openings(),
                slots.deadline(),
                role.about(),
                role.workingHours(),
                role.remoteDuration(),
                role.responsibilities(),
                processes(),
                eligibility.skills(),
                eligibility.nationality(),
                eligibility.study(),
                logistics.provided(),
                logistics.notIncluded(),
                visa.visaType(),
                visa.visaLink(),
                visa.healthInsurance()
                );
    }

    private String processes() {
        return opportunityRepository.waitAndGet("#process > div > div.flex:nth-child(2)");
    }

    private String description() {
        return opportunityRepository.waitAndGet("#company > div > div.flex.flex-col > div > div.flex-1 > div");
    }
}
