package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InfoRepository {
    private static final List<Info> stationsInfos = new ArrayList<>();

    public static List<Info> infos() {
        return Collections.unmodifiableList(stationsInfos);
    }

    public static void addInfo(Info info) {
        stationsInfos.add(info);
    }
}
