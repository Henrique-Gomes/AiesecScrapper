package service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import config.Config;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AiesecScrapperService {
    final private OpportunitiesListService opportunitiesListService;
    final private OpportunityService opportunityService;
    final private Config config;

    public void getCSV() throws IOException {
        config.getOutput().write(opportunityService.getTitles().toString() + "\n");
        List<String> opportunitiesStr = opportunitiesListService.getOpportunities();

        boolean waitingToResume = config.getResumeFrom() != null;

        for (int i = 0; i < opportunitiesStr.size(); i++) {
            if (opportunitiesStr.get(i).equals(config.getResumeFrom()))
                waitingToResume = false;

            if (waitingToResume)
                continue;

            System.out.printf("Starting opportunity %d/%d (%f%%): %s\n", i+1, opportunitiesStr.size(), 100.*(i+1)/opportunitiesStr.size(), opportunitiesStr.get(i));
            try {
                config.getOutput().write(opportunityService.scrapOpportunity(opportunitiesStr.get(i)).toString() + "\n");
            } catch (Exception e) {
                System.out.printf("FAILED opportunity %d/%d (%f%%): %s\n", i+1, opportunitiesStr.size(), 100.*(i+1)/opportunitiesStr.size(), opportunitiesStr.get(i));
            }
        }
    }
}
