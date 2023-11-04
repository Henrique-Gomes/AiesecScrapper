package service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.List;

@Singleton
@AllArgsConstructor(onConstructor = @__(@Inject))
public class AiesecScrapperService {
    final private OpportunitiesListService opportunitiesListService;
    final private OpportunityService opportunityService;

    public void getCSV() throws IOException {
        List<String> opportunitiesStr = opportunitiesListService.getOpportunities();
        // System.out.println(opportunityService.scrapOpportunity("https://aiesec.org/opportunity/global-talent/1264148"));
        opportunitiesStr.forEach(e -> System.out.println(opportunityService.scrapOpportunity(e)));
    }
}
