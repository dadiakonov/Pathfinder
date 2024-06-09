package engineer.dima.pathfinder.country.graph;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;
import java.util.Optional;

public class CountryGraph {
    private final Graph<String, DefaultEdge> graph;

    public CountryGraph(Graph<String, DefaultEdge> graph) {
        this.graph = graph;
    }

    public Optional<List<String>> findShortestPath(String origin, String destination) {
        DijkstraShortestPath<String, DefaultEdge> dijkstraAlgorithm = new DijkstraShortestPath<>(graph);

        GraphPath<String, DefaultEdge> shortestPath = dijkstraAlgorithm.getPath(origin, destination);

        return shortestPath != null
                ? Optional.ofNullable(shortestPath.getVertexList())
                : Optional.empty();
    }
}
