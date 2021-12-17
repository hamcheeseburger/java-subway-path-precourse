package subway.controller;

import subway.constants.InputConstants;
import subway.domain.GraphRepository;
import subway.domain.Result;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.view.InputView;
import subway.view.OutputView;

public class MainController {
    private GraphRepository graphRepository;
    private Setup setup;
    public MainController() {
        init();
        graphRepository = new GraphRepository();
    }

    private void init() {
        setup = new Setup();
        setup.setup();
    }

    public void start() {
        String answer = "";
        while(true) {
            try {
                answer = InputView.printMain();
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if(answer.equals(InputConstants.MAIN_QUIT)) {
                break;
            }
            startFunction();
        }
    }

    private void startFunction() {
        String answer = "";
        while(true) {
            try {
                answer = InputView.printFunction();
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if(answer.equals(InputConstants.FUNCTION_BACK)) {
                break;
            }
            figureFunction(answer);
        }
    }

    private void figureFunction(String answer) {
        if(answer.equals(InputConstants.FUNCTION_DISTANCE)) {
            doDistance();
            return;
        }
        if(answer.equals(InputConstants.FUNCTION_TIME)) {
            doTime();
            return;
        }
    }

    private void doDistance() {
        try {
            Result result = graphRepository.getShortestPath(getStart(),
                getEnd());
            OutputView.printResult(result);
        } catch (IllegalArgumentException e) {
            System.out.print(e.getMessage());
        }
    }

    private void doTime() {
        try {
            Result result = graphRepository.getShortestTime(getStart(),
                    getEnd());
            OutputView.printResult(result);
        } catch (IllegalArgumentException e) {
            System.out.print(e.getMessage());
        }
    }

    private String getStart() {
        while(true) {
            String start = InputView.getStart();
            if(checkExist(start)) {
                return start;
            }
        }
    }

    private String getEnd() {
        while(true) {
            String end = InputView.getEnd();
            if(checkExist(end)) {
                return end;
            }
        }
    }

    private boolean checkExist(String name) {
        try {
            StationRepository.checkExist(name);
            return true;
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
