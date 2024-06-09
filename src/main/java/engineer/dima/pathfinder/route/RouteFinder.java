package engineer.dima.pathfinder.route;

import engineer.dima.pathfinder.country.graph.CountryGraph;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteFinder {
    private final CountryGraph countryGraph;

    public RouteFinder(CountryGraph countryGraph) {
        this.countryGraph = countryGraph;
    }

    public List<String> findShortestRoute(String origin, String destination) {

        return countryGraph.findShortestPath(origin, destination)
                .orElseThrow(() -> RouteNotFoundException.byOriginAndDestination(origin, destination));
    }
}
