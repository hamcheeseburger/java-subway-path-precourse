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
        init();
    }

    public void init() {
        initGraphs();
        initEdges();
        initPaths();
    }

    private void initGraphs() {
        distanceGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        for(Station station : StationRepository.stations()) {
            distanceGraph.addVertex(station.getName());
        }
        timeGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        for(Station station : StationRepository.stations()) {
            timeGraph.addVertex(station.getName());
        }
    }

    private void initEdges() {
        for(Info info : InfoRepository.infos()) {
            distanceGraph.setEdgeWeight(distanceGraph.addEdge(info.getStart(), info.getEnd()), info.getDistance());
            timeGraph.setEdgeWeight(timeGraph.addEdge(info.getStart(), info.getEnd()), info.getTime());
        }
    }

    private void initPaths() {
        distanceShortest = new DijkstraShortestPath(distanceGraph);
        timeShortest = new DijkstraShortestPath(timeGraph);
    }

    public Result getShortestPath(String start, String end) {
        validateStartAndEnd(start, end);
        try {
            List<String> nodes = distanceShortest.getPath(start, end).getVertexList();
            return calculate(nodes);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 경로를 찾을 수 없습니다.");
        }
    }

    public Result getShortestTime(String start, String end) {
        validateStartAndEnd(start, end);
        try {
            List<String> nodes = timeShortest.getPath(start, end).getVertexList();
            return calculate(nodes);
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 경로를 찾을 수 없습니다.");
        }
    }

    private void validateStartAndEnd(String start, String end) {
        if(start.equals(end)) {
            throw new IllegalArgumentException("[ERROR] 출발지와 도착지는 동일할 수 없습니다.");
        }
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
