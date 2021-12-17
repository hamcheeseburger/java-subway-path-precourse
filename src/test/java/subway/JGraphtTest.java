package subway;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import org.junit.jupiter.api.Test;

import java.util.List;
import subway.domain.GraphRepository;
import subway.domain.Result;
import subway.domain.Station;
import subway.domain.StationRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class JGraphtTest {
//    @Test
    public void getDijkstraShortestPath() {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        graph.addVertex("v1");
        graph.addVertex("v2");
        graph.addVertex("v3");
        graph.setEdgeWeight(graph.addEdge("v1", "v2"), 2);
        graph.setEdgeWeight(graph.addEdge("v2", "v3"), 2);
        graph.setEdgeWeight(graph.addEdge("v1", "v3"), 100);

        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        List<String> shortestPath = dijkstraShortestPath.getPath("v3", "v1").getVertexList();
        for(String path : shortestPath) {
            System.out.println(path);
        }
        assertThat(shortestPath.size()).isEqualTo(3);
    }

    @Test
    public void test1() {
        String [] stations = {"교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역"};
        for (String s : stations) {
            StationRepository.addStation(new Station(s));
        }
        GraphRepository pathRepository = new GraphRepository();
        Result result = pathRepository.getShortestPath("교대역", "양재역");

        System.out.println(result.toString());
    }
}
