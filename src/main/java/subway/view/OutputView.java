package subway.view;

import java.util.logging.Logger;
import subway.domain.Result;
import subway.domain.Station;

public class OutputView {
    public static void printResult(Result result) {
        System.out.println("\n## 조회 결과");
        System.out.println("[INFO] ---");
        System.out.println("[INFO] 총 거리: " + result.getDistance() + "km");
        System.out.println("[INFO] 총 소요 시간: " + result.getTime() + "분");
        System.out.println("[INFO] ---");
        for(String node : result.getNodes()) {
            System.out.println("[INFO] " + node);
        }
    }
}
