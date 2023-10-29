package models;

import exception.*;
import models.enums.CellStatus;
import models.enums.PlayerType;
import service.makeMoveStrategy.MakeMoveStrategy;

import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Player {

    private static final Lock lock = new ReentrantLock();
    private static int idCounter = 0;
    private final int id;
    private String name;
    private char symbol;
    private PlayerType type;

    private MakeMoveStrategy makeMoveStrategy;

    private Player(String name, char symbol, PlayerType type, MakeMoveStrategy makeMoveStrategy) {
        this(name, symbol, type);
        this.makeMoveStrategy = makeMoveStrategy;
    }

    private Player(String name, char symbol, PlayerType type) {
        this.id = getNewId();
        this.name = name;
        this.symbol = symbol;
        this.type = type;
    }

    private static int getNewId() {
        int newId;
        lock.lock();
        newId = ++idCounter;
        lock.unlock();
        return newId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public PlayerType getType() {
        return type;
    }

    public Move makeMove(Board board) throws GameOverException {
        return makeMoveStrategy.makeMove(board, this);
    }

    public static class Builder {

        private String name;
        private char symbol;
        private PlayerType type;
        private MakeMoveStrategy makeMoveStrategy;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder symbol(char symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder type(PlayerType type) {
            this.type = type;
            return this;
        }

        public Builder makeMoveStrategy(MakeMoveStrategy makeMoveStrategy) {
            this.makeMoveStrategy = makeMoveStrategy;
            return this;
        }

        private void validateName() throws EmptyNameException {
            if (name == null || name.isEmpty())
                throw new EmptyNameException("Name cannot be null or empty");
        }

        private void validateSymbol() throws InvalidSymbolException {
            if (symbol == '\u0000')
                throw new InvalidSymbolException("Symbol cannot be null or empty");
        }

        private void validateType() throws InvalidTypeException {
            if (type == null) {
                throw new InvalidTypeException("Type cannot be null");
            }
        }

        public void validateMakeMoveStrategy() throws InvalidMakeMoveStrategy {
            if (makeMoveStrategy == null) {
                throw new InvalidMakeMoveStrategy("MakeMoveStrategy cannot be null");
            }
        }

        public void validate() throws InvalidSymbolException, EmptyNameException, InvalidTypeException, InvalidMakeMoveStrategy {
            validateName();
            validateSymbol();
            validateType();
            validateMakeMoveStrategy();
        }

        public Player build() throws EmptyNameException, InvalidSymbolException, InvalidTypeException, InvalidMakeMoveStrategy {
            validate();
            return new Player(name, symbol, type, makeMoveStrategy);
        }

    }

}
