package engineer.dima.pathfinder.country.graph;

import engineer.dima.pathfinder.country.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CountryGraphTest {
    private CountryGraph countryGraph;

    @BeforeEach
    void setUp() {
        Set<Country> countries = Set.of(
                new Country("BRA", Set.of("ARG", "PER", "COL")), // Brazil
                new Country("ARG", Set.of("BRA", "CHL")), // Argentina
                new Country("CHL", Set.of("ARG", "PER")), // Chile
                new Country("PER", Set.of("BRA", "CHL", "COL")), // Peru
                new Country("COL", Set.of("BRA", "PER")), // Colombia
                new Country("AUS", Set.of()) // Australia
        );

        CountryGraphFactory countryGraphFactory = new CountryGraphFactory();

        countryGraph = countryGraphFactory.create(countries);
    }

    @Test
    void testFindShortestPathReturnsEmptyWhenNoPathExists() {
        Optional<List<String>> shortestPath = countryGraph.findShortestPath("ARG", "AUS");

        assertTrue(shortestPath.isEmpty());
    }

    @Test
    void testFindShortestPathWhenMultiplePathsExist() {
        Optional<List<String>> shortestPath = countryGraph.findShortestPath("ARG", "COL");

        assertTrue(shortestPath.isPresent());
        assertEquals(List.of("ARG", "BRA", "COL"), shortestPath.get());

        Optional<List<String>> shortestPathBack = countryGraph.findShortestPath("COL", "ARG");

        assertTrue(shortestPathBack.isPresent());
        assertEquals(List.of("COL", "BRA", "ARG"), shortestPathBack.get());
    }
}
