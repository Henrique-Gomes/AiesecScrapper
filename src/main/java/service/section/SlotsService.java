package service.section;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import model.section.Slots;
import repository.OpportunityRepository;

@Singleton
@AllArgsConstructor(onConstructor = @__(@Inject))
public class SlotsService {

    final private OpportunityRepository opportunityRepository;

    public Slots slots() {
        opportunityRepository.waitAndGet("#slots > div.flex.flex-col > div > div.flex.flex-row > div > div.flex.flex-col > div:nth-child(3)");
        return new Slots(
                fee(),
                slotsSlots(),
                period(),
                openings(),
                deadline()
        );
    }

    private String fee() {
        return opportunityRepository.get("#slots > div.flex.flex-row > h3");
    }

    private String slotsSlots() {
        return opportunityRepository.get("#slots > div.text-grey-dark > span");
    }

    private String period() {
        return opportunityRepository.get("#slots > div.flex.flex-col > div > div.flex.flex-row > div > div.flex.flex-col > div.font-bold");
    }

    private String openings() {
        return opportunityRepository.get("#slots > div.flex.flex-col > div > div.flex.flex-row > div > div.flex.flex-col > div:nth-child(2)");
    }

    private String deadline() {
        return opportunityRepository.get("#slots > div.flex.flex-col > div > div.flex.flex-row > div > div.flex.flex-col > div:nth-child(3)");
    }
}
