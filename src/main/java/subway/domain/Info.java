package subway.domain;

import lombok.Getter;

@Getter
public class Info {
    String start;
    String end;
    int distance;
    int time;

    public Info(String start, String end, int distance, int time) {
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.time = time;
    }
}
