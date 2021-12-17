package subway.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import lombok.Getter;

@Getter
public class Result {
    int distance;
    int time;
    List<String> nodes;

    public Result(int distance, int time, List<String> nodes) {
        this.distance = distance;
        this.time = time;
        this.nodes = nodes;
    }
}
