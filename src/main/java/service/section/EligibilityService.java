package service.section;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import model.section.Eligibility;
import repository.OpportunityRepository;

@Singleton
@AllArgsConstructor(onConstructor = @__(@Inject))
public class EligibilityService {

    final private OpportunityRepository opportunityRepository;

    public Eligibility eligibility() {
        opportunityRepository.waitAndGet("#eligibility > div:nth-child(6) > div.flex.flex-row");
        return new Eligibility(
                skills(),
                nationality(),
                study()
        );
    }

    private String skills() {
        return opportunityRepository.get("#eligibility > div:nth-child(3) > div.flex.flex-row");
    }

    private String nationality() {
        return opportunityRepository.get("#eligibility > div:nth-child(5) > div.flex.flex-row");
    }

    private String study() {
        return opportunityRepository.get("#eligibility > div:nth-child(6) > div.flex.flex-row");
    }
}
