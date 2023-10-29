package service.makeMoveStrategy;

import exception.GameOverException;
import models.Board;
import models.Cell;
import models.Move;
import models.Player;
import models.enums.CellStatus;
import service.makeMoveStrategy.MakeMoveStrategy;

import java.util.Scanner;

public class HumanInputMakeMoveStrategy implements MakeMoveStrategy {
    @Override
    public Move makeMove(Board board, Player player) throws GameOverException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the row for your move, " + player.getName());
        int row = scanner.nextInt();
        System.out.println("Enter the column for your move, " + player.getName());
        int column = scanner.nextInt();

        //TODO: validation for the move, check row and column, and cell status

        Cell cell = board.getBoard().get(row).get(column);
        cell.setPlayer(player);
        cell.setCellStatus(CellStatus.FILLED);
        return new Move(row, column, cell);
    }
}
