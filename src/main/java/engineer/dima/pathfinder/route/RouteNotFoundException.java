package engineer.dima.pathfinder.route;

public class RouteNotFoundException extends RuntimeException {

    public RouteNotFoundException(String message) {
        super(message);
    }

    public static RouteNotFoundException byOriginAndDestination(String origin, String destination) {
        String message = "Route not found from %s to %s.".formatted(origin, destination);

        return new RouteNotFoundException(message);
    }
}
