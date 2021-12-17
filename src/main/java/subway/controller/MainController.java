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
        Result result = graphRepository.getShortestPath(InputView.getStart(), InputView.getEnd());
        OutputView.printResult(result);
    }

    private void doTime() {
        Result result = graphRepository.getShortestTime(InputView.getStart(), InputView.getEnd());
        OutputView.printResult(result);
    }
}
