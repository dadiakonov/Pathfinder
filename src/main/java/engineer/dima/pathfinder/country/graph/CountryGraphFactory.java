package engineer.dima.pathfinder.country.graph;

import engineer.dima.pathfinder.country.Country;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CountryGraphFactory {

    public CountryGraph create(Set<Country> countries) {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // It's not possible to add vertices and edges in one loop.
        // Vertices must be added first. Otherwise, an exception will be thrown.
        // It's possible only by utilizing additional "if contains" statements.
        // So both options are more or less equivalent, but this one looks shorter.
        countries.forEach(country -> graph.addVertex(country.cca3()));
        countries.forEach(country -> addEdgesToGraph(country, graph));

        return new CountryGraph(graph);
    }

    private void addEdgesToGraph(Country country, Graph<String, DefaultEdge> graph) {
        country.borders().forEach(border -> graph.addEdge(country.cca3(), border));
    }
}
