package service.botPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategy(BotPlayStrategyNames botPlayStrategyName) {
        return switch (botPlayStrategyName) {
            case RANDOM, DEFAULT -> new RandomBotPlayingStrategy();
        };
    }

}
