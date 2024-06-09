package engineer.dima.pathfinder.route;

import engineer.dima.pathfinder.country.code.CountryAlpha3Code;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/routing")
public class RouteController {
    private final RouteFinder routeFinder;

    public RouteController(RouteFinder routeFinder) {
        this.routeFinder = routeFinder;
    }

    @GetMapping(path = "/{origin}/{destination}")
    public RouteView getRoute(@PathVariable @NotNull @CountryAlpha3Code String origin,
                              @PathVariable @NotNull @CountryAlpha3Code String destination) {

        List<String> route = routeFinder.findShortestRoute(origin.toUpperCase(), destination.toUpperCase());

        return new RouteView(route);
    }
}
