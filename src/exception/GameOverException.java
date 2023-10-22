package exception;

public class GameOverException extends Throwable {
    public GameOverException(String gameOver) {
        super(gameOver);
    }
}
