package service.botPlayingStrategy;

import exception.GameOverException;
import models.Board;
import models.Cell;
import models.Move;
import models.Player;
import models.enums.CellStatus;

import java.util.List;

public class RandomBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move makeMove(Board board, Player player) throws GameOverException {

        List<List<Cell>> cells = board.getBoard();

        for(int i = 0; i < cells.size(); i++){
            for(int j = 0; j < cells.size(); j++){
                if(cells.get(i).get(j).getCellStatus() == CellStatus.EMPTY){
                    cells.get(i).get(j).setPlayer(player);
                    cells.get(i).get(j).setCellStatus(CellStatus.FILLED);
                    return new Move(i, j, player);
                }
            }
        }

        throw new GameOverException("No new cells to play with, GAME OVER");
    }
}