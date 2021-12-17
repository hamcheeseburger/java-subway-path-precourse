package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class Result {
    int distance;
    int time;
    List<String> nodes;

    public Result(int distance, int time, List<String> nodes) {
        this.distance = distance;
        this.time = time;
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "Result{" +
            "distance=" + distance +
            ", time=" + time +
            ", nodes=" + nodes +
            '}';
    }
}
