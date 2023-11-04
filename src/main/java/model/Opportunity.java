package model;

import java.util.List;
import java.util.stream.Collectors;

public class Opportunity {
    private final List<String> properties;

    public Opportunity(String... properties) {
        this.properties = List.of(properties);
    }

    @Override
    public String toString() {
        return properties.stream()
                         .map(s -> "\"" + s.replace("\"", "\"\"") + "\"")
                         .collect(Collectors.joining(", "));
    }
}
