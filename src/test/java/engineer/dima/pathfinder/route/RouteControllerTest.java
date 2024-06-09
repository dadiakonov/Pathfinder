package engineer.dima.pathfinder.route;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RouteControllerTest {
    @Mock
    private RouteFinder routeFinder;

    private RouteController routeController;

    @BeforeEach
    void setUp() {
        routeController = new RouteController(routeFinder);
    }

    @ParameterizedTest
    @MethodSource("existedRouteDataProvider")
    void testGetRouteSuccessfully(List<String> expectedRoute, String origin, String destination) {
        when(routeFinder.findShortestRoute(origin.toUpperCase(), destination.toUpperCase()))
                .thenReturn(expectedRoute);

        RouteView routeView = routeController.getRoute(origin, destination);

        assertEquals(new RouteView(expectedRoute), routeView);
    }

    private static Stream<Arguments> existedRouteDataProvider() {
        return Stream.of(
                Arguments.of(List.of("CAN", "USA", "MEX"), "CAN", "MEX"),
                Arguments.of(List.of("CAN", "USA", "MEX"), "cAn", "mex"),
                Arguments.of(List.of("AUS"), "AUS", "aus")
        );
    }

    @Test
    void testGetRouteThrowsRouteNotFoundException() {
        String origin = "USA";
        String destination = "CHN";

        when(routeFinder.findShortestRoute(origin, destination))
                .thenThrow(RouteNotFoundException.byOriginAndDestination(origin, destination));

        String expectedMessage = String.format("Route not found from %s to %s", origin, destination);

        assertThrows(RouteNotFoundException.class, () -> routeController.getRoute(origin, destination), expectedMessage);
    }
}
