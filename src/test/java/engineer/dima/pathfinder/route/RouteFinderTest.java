package engineer.dima.pathfinder.route;

import engineer.dima.pathfinder.country.graph.CountryGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RouteFinderTest {
    @Mock
    private CountryGraph countryGraph;

    private RouteFinder routeFinder;

    @BeforeEach
    void setUp() {
        routeFinder = new RouteFinder(countryGraph);
    }

    @Test
    void testFindShortestRouteSuccessfully() {
        String origin = "CAN";
        String destination = "MEX";
        List<String> expectedRoute = List.of("CAN", "USA", "MEX");

        when(countryGraph.findShortestPath(origin, destination)).thenReturn(Optional.of(expectedRoute));

        List<String> fetchedRoute = routeFinder.findShortestRoute(origin, destination);

        assertEquals(expectedRoute, fetchedRoute);
    }

    @Test
    void testFindShortestRouteThrowsRouteNotFoundException() {
        String origin = "USA";
        String destination = "CHN";

        when(countryGraph.findShortestPath(origin, destination)).thenReturn(Optional.empty());

        String expectedMessage = String.format("Route not found from %s to %s", origin, destination);

        assertThrows(RouteNotFoundException.class, () -> routeFinder.findShortestRoute(origin, destination), expectedMessage);
    }
}
