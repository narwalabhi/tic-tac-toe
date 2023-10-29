package service.winningStrategy;

public class WinningStrategyFactory {

    public static WinningStrategy getWinningStrategy(WinningStrategyNames winningStrategyName, int dimension){

        return switch (winningStrategyName){
            case ORDER_ONE, DEFAULT -> new OrderOneStrategy(dimension);
        };

    }

}
