package subway.view;

import camp.nextstep.edu.missionutils.Console;
import subway.constants.InputConstants;

public class InputView {

    public static String printMain() {
        System.out.println("## 메인 화면");
        System.out.println(InputConstants.MAIN_SEARCH + ". 경로 조회");
        System.out.println(InputConstants.MAIN_QUIT + ". 종료");
        System.out.println("\n## 원하는 기능을 선택하세요.");
        String answer = read();
        validateMainAnswer(answer);
        return answer;
    }

    private static void validateMainAnswer(String answer) {
        if(!answer.equals(InputConstants.MAIN_SEARCH) && !answer.equals(InputConstants.MAIN_QUIT)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 옵션입니다.");
        }
    }

    public static String printFunction() {
        System.out.println("\n## 경로 기준");
        System.out.println(InputConstants.FUNCTION_DISTANCE + ". 최단 거리");
        System.out.println(InputConstants.FUNCTION_TIME + ". 최소 시간");
        System.out.println(InputConstants.FUNCTION_BACK + ". 돌아가기");
        System.out.println("\n## 원하는 기능을 선택하세요.");
        String answer = read();
        validateFunctionAnswer(answer);
        return answer;
    }

    private static void validateFunctionAnswer(String answer){
        if(!answer.equals(InputConstants.FUNCTION_DISTANCE) && !answer.equals(InputConstants.FUNCTION_TIME) && !answer.equals(InputConstants.FUNCTION_BACK)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 옵션입니다.");
        }
    }

    public static String getStart() {
        System.out.println("\n## 출발역을 입력하세요.");
        return read();
    }

    public static String getEnd() {
        System.out.println("\n## 도착역을 입력하세요.");
        return read();
    }

    public static String read() {
        return Console.readLine();
    }


}
