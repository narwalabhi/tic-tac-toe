package service.makeMoveStrategy;

import exception.GameOverException;
import models.Board;
import models.Move;
import models.Player;

public interface MakeMoveStrategy {

    Move makeMove(Board board, Player player) throws GameOverException;

}
