package models;

import exception.DuplicateSymbolException;
import exception.InvalidBoardDimensionException;
import exception.InvalidBotPlayerCountException;
import exception.InvalidPlayerCountException;
import models.enums.GameStatus;
import models.enums.PlayerType;
import service.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;
    private List<Player> players;
    private Player currentPlayer;
    private Player winner;
    private GameStatus gameStatus;
    private List<Move> moves;
    private List<Board> boardStates;
    private int numberOfSymbols;
    private WinningStrategy winningStrategy;

    private Game() {
    }

    public Game(Board board, List<Player> players, WinningStrategy winningStrategy) {
        this.board = board;
        this.players = players;
        this.winningStrategy = winningStrategy;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.boardStates = new ArrayList<>();
        this.numberOfSymbols = 0;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public List<Board> getBoardStates() {
        return boardStates;
    }

    public int getNumberOfSymbols() {
        return numberOfSymbols;
    }

    public WinningStrategy getWinningStrategy() {
        return winningStrategy;
    }

    public static class Builder{

        private int dimension;
        private List<Player> players;
        private WinningStrategy winningStrategy;


        public Builder dimension(int dimension){
            this.dimension = dimension;
            return this;
        }

        public Builder players(List<Player> players){
            this.players = players;
            return this;
        }

        public Builder winningStrategy(WinningStrategy winningStrategy){
            this.winningStrategy = winningStrategy;
            return this;
        }

        private void verifyDimension() throws InvalidBoardDimensionException {
            if(dimension < 3 || dimension > 10){
                throw new InvalidBoardDimensionException("Dimension must be between 3 and 10");
            }
        }

        private void verifyPlayerCount() throws InvalidPlayerCountException {
            if(players.size() != dimension-1){
                throw new InvalidPlayerCountException("There must be at least 2 players");
            }
        }

        private void verifyBotCount() throws InvalidBotPlayerCountException {
            if (players.stream().filter(player -> player.getType().equals(PlayerType.BOT)).count() > 1)
                throw new InvalidBotPlayerCountException("There can be at most 1 bot");
        }

        private void verifyPlayerSymbols() throws DuplicateSymbolException {
            List<Character> symbols = new ArrayList<>();
            for (Player player : players) {
                if (symbols.contains(player.getSymbol()))
                    throw new DuplicateSymbolException("Symbols must be unique");
                symbols.add(player.getSymbol());
            }
        }

        private void verify() throws InvalidBotPlayerCountException, InvalidBoardDimensionException, InvalidPlayerCountException, DuplicateSymbolException {
            verifyBotCount();
            verifyDimension();
            verifyPlayerCount();
            verifyPlayerSymbols();
        }

        public Game build() throws InvalidBotPlayerCountException, DuplicateSymbolException, InvalidBoardDimensionException, InvalidPlayerCountException {
            verify();
            return new Game(new Board(dimension), players, winningStrategy);
        }

    }

}
