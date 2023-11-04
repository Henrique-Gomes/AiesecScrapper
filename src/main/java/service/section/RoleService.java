package service.section;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import model.section.Role;
import repository.OpportunityRepository;

@Singleton
@AllArgsConstructor(onConstructor = @__(@Inject))
public class RoleService {

    final private OpportunityRepository opportunityRepository;

    public Role role() {
        opportunityRepository.waitAndGet("#role > div > div:nth-child(4) > div:nth-child(2)");
        return new Role(
                about(),
                workingHours(),
                responsibilities()
        );
    }

    private String about() {
        return opportunityRepository.get("#role > div > div:nth-child(2) > div");
    }

    private String workingHours() {
        return opportunityRepository.get("#role > div > div:nth-child(3) > div");
    }

    private String responsibilities() {
        return opportunityRepository.get("#role > div > div:nth-child(4) > div:nth-child(2)");
    }
}
