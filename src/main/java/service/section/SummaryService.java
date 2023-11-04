package service.section;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import model.section.Summary;
import repository.OpportunityRepository;

@Singleton
@AllArgsConstructor(onConstructor = @__(@Inject))
public class SummaryService {

    final private OpportunityRepository opportunityRepository;

    public Summary summary() {
        opportunityRepository.waitAndGet("#summary > div:nth-child(4) > div.flex-1 > h3");

        String[] groupAndPlace = groupAndPlace().split("·");
        return new Summary(
                title(),
                groupAndPlace[0],
                groupAndPlace[1],
                globalTalent(),
                salary(),
                backgrounds(),
                languages()
        );
    }

    private String title() {
        return opportunityRepository.get("#summary > div > div > div.flex.flex-col > div:nth-child(1) > h3");
    }

    private String groupAndPlace() {
        return opportunityRepository.get("#summary > div > div > div.flex.flex-col > div.text-grey-dark");
    }

    private String globalTalent() {
        return opportunityRepository.get("#summary > div > div > div.flex.flex-row > div.flex-none");
    }

    private String salary() {
        return opportunityRepository.get("#summary > div:nth-child(2) > div.flex-1.self-baseline > h3");
    }

    private String backgrounds() {
        return opportunityRepository.get("#summary > div:nth-child(3) > div.flex-1.self-baseline > h3");
    }

    private String languages() {
        return opportunityRepository.get("#summary > div:nth-child(4) > div.flex-1 > h3");
    }
}
