package engineer.dima.pathfinder.route;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouteControllerE2ETest {
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @ParameterizedTest
    @MethodSource("existedRouteDataProvider")
    public void testGetRouteSuccessfully(List<String> expectedRoute, String origin, String destination) {
        given()
                .pathParam("origin", origin)
                .pathParam("destination", destination)
                .when()
                .get("/routing/{origin}/{destination}")
                .then()
                .statusCode(200)
                .body("route", equalTo(expectedRoute));
    }

    private static Stream<Arguments> existedRouteDataProvider() {
        return Stream.of(
                Arguments.of(List.of("CHN", "AFG", "IRN", "IRQ", "JOR", "PSE", "EGY", "SDN", "CAF", "COD", "ZMB", "BWA", "ZAF"), "CHN", "ZAF"),
                Arguments.of(List.of("ZAF", "BWA", "ZMB", "COD", "SSD", "SDN", "EGY", "ISR", "SYR", "TUR", "GEO", "RUS", "CHN"), "zaf", "cHn"),
                Arguments.of(List.of("AUS"), "AUS", "AUS")
        );
    }

    @ParameterizedTest
    @MethodSource("notExistedRouteDataProvider")
    public void testGetRouteReturnsBadRequestStatusCode(String origin, String destination) {
        given()
                .pathParam("origin", origin)
                .pathParam("destination", destination)
                .when()
                .get("/routing/{origin}/{destination}")
                .then()
                .statusCode(400);
    }

    private static Stream<Arguments> notExistedRouteDataProvider() {
        return Stream.of(
                Arguments.of("USA", "CHN"),
                Arguments.of("CAN", " MEX"),
                Arguments.of(" CAN", "MEX"),
                Arguments.of("CAN", "Mexico"),
                Arguments.of("Canada", "MEX")
        );
    }
}
