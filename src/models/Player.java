package models;

import exception.EmptyNameException;
import exception.InvalidSymbolException;
import exception.InvalidTypeException;
import models.enums.PlayerType;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Player {

    private static int idCounter = 0;
    private static final Lock lock = new ReentrantLock();

    private final int id;
    private String name;
    private char symbol;
    private PlayerType type;

    private Player(){
        this.id = getNewId();
    }

    private static int getNewId(){
        int newId;
        lock.lock();
        newId = ++idCounter;
        lock.unlock();
        return newId;
    }

    public Player(String name, char symbol, PlayerType type) {
        this.id = getNewId();
        this.name = name;
        this.symbol = symbol;
        this.type = type;
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

    public static class Builder{

        private String name;
        private char symbol;
        private PlayerType type;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder symbol(char symbol){
            this.symbol = symbol;
            return this;
        }

        public Builder type(PlayerType type){
            this.type = type;
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
            if (type == null){
                throw new InvalidTypeException("Type cannot be null");
            }
        }

        public void validate() throws InvalidSymbolException, EmptyNameException, InvalidTypeException {
            validateName();
            validateSymbol();
            validateType();
        }

        public Player build() throws EmptyNameException, InvalidSymbolException, InvalidTypeException {
            validate();
            return new Player(name, symbol, type);
        }

    }

}
