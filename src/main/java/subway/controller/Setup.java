package subway.controller;

import java.util.ArrayList;
import subway.domain.Info;
import subway.domain.InfoRepository;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class Setup {
    private String [] lines = {"2호선", "3호선", "신분당선"};
    private String [] stationNames = {"교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역"};
    public void setup() {
        setupLine();
        setupStations();
        setupInfos();
    }

    private void setupLine() {
        for(String line : lines) {
            LineRepository.addLine(new Line(line));
        }
    }

    private void setupStations() {
        for(String station : stationNames) {
            StationRepository.addStation(new Station(station));
        }
    }

    private void setupInfos() {
        InfoRepository.addInfo(new Info("교대역","강남역", 2, 3));
        InfoRepository.addInfo(new Info("강남역", "역삼역", 2, 3));
        InfoRepository.addInfo(new Info("교대역", "남부터미널역", 3, 2));
        InfoRepository.addInfo(new Info("남부터미널역", "양재역", 6, 5));
        InfoRepository.addInfo(new Info("양재역", "매봉역", 1, 1));
        InfoRepository.addInfo(new Info("강남역", "양재역", 2, 8));
        InfoRepository.addInfo(new Info("양재역", "양재시민의숲역", 10, 3));
    }
}
