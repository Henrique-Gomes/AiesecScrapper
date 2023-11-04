package service.section;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import model.section.Logistics;
import repository.OpportunityRepository;

@Singleton
@AllArgsConstructor(onConstructor = @__(@Inject))
public class LogisticsService {

    private final OpportunityRepository opportunityRepository;

    public Logistics logisticsSection() {
        opportunityRepository.waitAndGet("#logistics > div > div.flex.flex-col:nth-child(3)");
        return new Logistics(
                provided(),
                notIncluded()
        );
    }

    private String provided() {
        return opportunityRepository.get("#logistics > div > div.flex.flex-col:nth-child(2)");
    }

    private String notIncluded() {
        return opportunityRepository.get("#logistics > div > div.flex.flex-col:nth-child(3)");
    }
}
