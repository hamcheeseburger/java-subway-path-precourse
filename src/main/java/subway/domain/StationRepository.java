package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static void deleteAll() {
        stations.clear();
    }

    public static void checkExist(String name) {
        Station station = searchStation(name);

        if(station == null) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 역입니다.");
        }
//        System.out.println(station.getName() + "found");
    }

    public static Station searchStation(String name) {
        return stations
            .stream().filter(s -> s.getName().equals(name))
            .findAny()
            .orElse(null);
    }
}
