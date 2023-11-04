package service.section;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import model.section.Visa;
import repository.OpportunityRepository;

@Singleton
@AllArgsConstructor(onConstructor = @__(@Inject))
public class VisaService {

    final private OpportunityRepository opportunityRepository;

    public Visa visaSection() {
        opportunityRepository.waitAndGet("#visa > div > div.flex.flex-col > div:nth-child(2) > div.ant-card-body");
        return new Visa(
                visaType(),
                visaLink(),
                healthInsurance()
        );
    }

    private String visaType() {
        return opportunityRepository.get("#visa > div > div.flex.flex-col > div:nth-child(1) > div.ant-card-body > div > div");
    }

    private String visaLink() {
        return opportunityRepository.get(
                "#visa > div > div.flex.flex-col > div:nth-child(1) > div.ant-card-body > div > a",
                "href");
    }

    private String healthInsurance() {
        return opportunityRepository.get("#visa > div > div.flex.flex-col > div:nth-child(2) > div.ant-card-body");
    }
}
