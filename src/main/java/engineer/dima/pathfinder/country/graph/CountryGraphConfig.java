package engineer.dima.pathfinder.country.graph;

import engineer.dima.pathfinder.country.reader.CountryReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountryGraphConfig {
    private final CountryReader countryReader;
    private final CountryGraphFactory countryGraphFactory;

    public CountryGraphConfig(CountryReader countryReader, CountryGraphFactory countryGraphFactory) {
        this.countryReader = countryReader;
        this.countryGraphFactory = countryGraphFactory;
    }

    @Bean
    public CountryGraph countryGraph() {

        return countryGraphFactory.create(countryReader.getAll());
    }
}
