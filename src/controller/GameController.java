package controller;

import exception.*;
import models.Board;
import models.Game;
import models.Move;
import models.Player;
import models.enums.CellStatus;
import models.enums.GameStatus;
import service.winningStrategy.WinningStrategy;
import service.winningStrategy.WinningStrategyFactory;
import service.winningStrategy.WinningStrategyNames;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players, WinningStrategyNames winningStrategyName) {

        try{
            return new Game.Builder().dimension(dimension)
                    .players(players)
                    .winningStrategy(WinningStrategyFactory.getWinningStrategy(winningStrategyName, dimension))
                    .build();
        } catch (InvalidBotPlayerCountException e) {
            System.out.println("Invalid bot player count");
        } catch (DuplicateSymbolException e) {
            System.out.println("Duplicate symbol found");
        } catch (InvalidBoardDimensionException e) {
            System.out.println("Invalid board dimension");
        } catch (InvalidPlayerCountException e) {
            System.out.println("Invalid player count");
        } catch (Exception e){
            System.out.println("Something went wrong");
        }

        return null;
    }

    public void displayBoard(Game game){
        game.getBoard().displayBoard();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public Player checkWinner(Game game, Move lastMove){
        return game.getWinningStrategy().checkWinner(game.getBoard(), lastMove);
    }

    public void updateGameStatus(Game game){
        int numberOfSymbols = game.getNumberOfSymbols();
        int dimension = game.getBoard().getSize();
    }

    public Move makeMove(Game game, Player player) throws GameOverException {
        Move move = player.makeMove(game.getBoard());
        game.setNumberOfSymbols(game.getNumberOfSymbols() + 1);
        updateGameStatus(game);
        updateGameMoves(game, move);
        updateGameBoardStates(game);
        return move;
    }

    private void updateGameBoardStates(Game game) {
        game.getBoardStates().add(game.getBoard());
    }

    private void updateGameMoves(Game game, Move move) {
        game.getMoves().add(move);
    }

    public void undoMove(Game game){
        Move lastMove = game.getMoves().remove(game.getMoves().size() - 1);
        lastMove.getCell().setPlayer(null);
        lastMove.getCell().setCellStatus(CellStatus.EMPTY);
        Board prevBoard = game.getBoardStates().remove(game.getBoardStates().size() - 1);
        game.setBoard(prevBoard);
    }



}
