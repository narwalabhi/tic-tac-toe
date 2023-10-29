package service.makeMoveStrategy;

import models.enums.PlayerType;


public class MakeMoveStrategyFactory {

    public static MakeMoveStrategy getMakeMoveStrategy(MakeMoveStrategyNames makeMoveStrategyName) {
        return switch (makeMoveStrategyName) {
            case HUMAN_INPUT, DEFAULT -> new HumanInputMakeMoveStrategy();
            case BOT_RANDOM -> new RandomBotPlayingStrategy();
        };
    }

}
