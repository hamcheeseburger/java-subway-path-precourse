package subway.domain;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class GraphRepository {
    WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph;
    WeightedMultigraph<String, DefaultWeightedEdge> timeGraph;
    private DijkstraShortestPath distanceShortest;
    private DijkstraShortestPath timeShortest;

    public GraphRepository() {
        initGraph();
    }

    public void initGraph() {
        initDistanceGraph();
        initTimeGraph();
    }

    private void initDistanceGraph() {
        distanceGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        for(Station station : StationRepository.stations()) {
            distanceGraph.addVertex(station.getName());
        }
        initDistanceEdges();
        distanceShortest = new DijkstraShortestPath(distanceGraph);
    }

    private void initDistanceEdges() {
        distanceGraph.setEdgeWeight(distanceGraph.addEdge("교대역", "강남역"), 2);
        distanceGraph.setEdgeWeight(distanceGraph.addEdge("강남역", "역삼역"), 2);
        distanceGraph.setEdgeWeight(distanceGraph.addEdge("교대역", "남부터미널역"), 3);
        distanceGraph.setEdgeWeight(distanceGraph.addEdge("남부터미널역", "양재역"), 6);
        distanceGraph.setEdgeWeight(distanceGraph.addEdge("양재역", "매봉역"), 1);
        distanceGraph.setEdgeWeight(distanceGraph.addEdge("강남역", "양재역"), 2);
        distanceGraph.setEdgeWeight(distanceGraph.addEdge("양재역", "양재시민의숲역"), 10);
    }

    private void initTimeGraph() {
        timeGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        for(Station station : StationRepository.stations()) {
            timeGraph.addVertex(station.getName());
        }
        initTimeEdges();
        timeShortest = new DijkstraShortestPath(timeGraph);
    }

    private void initTimeEdges() {
        timeGraph.setEdgeWeight(timeGraph.addEdge("교대역", "강남역"), 3);
        timeGraph.setEdgeWeight(timeGraph.addEdge("강남역", "역삼역"), 3);
        timeGraph.setEdgeWeight(timeGraph.addEdge("교대역", "남부터미널역"), 2);
        timeGraph.setEdgeWeight(timeGraph.addEdge("남부터미널역", "양재역"), 5);
        timeGraph.setEdgeWeight(timeGraph.addEdge("양재역", "매봉역"), 1);
        timeGraph.setEdgeWeight(timeGraph.addEdge("강남역", "양재역"), 8);
        timeGraph.setEdgeWeight(timeGraph.addEdge("양재역", "양재시민의숲역"), 3);
    }

    public Result getShortestPath(String start, String end) {
        List<String> nodes = distanceShortest.getPath(start, end).getVertexList();
        return calculate(nodes);
    }

    public Result getShortestTime(String start, String end) {
        List<String> nodes = timeShortest.getPath(start, end).getVertexList();
        return calculate(nodes);
    }

    private Result calculate(List<String> nodes) {
        int distance = 0;
        int time = 0;
        for(int i = 0; i < nodes.size() - 1; i++) {
            DefaultWeightedEdge edge = distanceGraph.getEdge(nodes.get(i), nodes.get(i + 1));
            distance += distanceGraph.getEdgeWeight(edge);
            DefaultWeightedEdge timeEdge = timeGraph.getEdge(nodes.get(i), nodes.get(i + 1));
            time += timeGraph.getEdgeWeight(timeEdge);
        }
        return new Result(distance, time, nodes);
    }
}
