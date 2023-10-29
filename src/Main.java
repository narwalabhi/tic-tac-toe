import controller.GameController;
import exception.*;
import models.Game;
import models.Move;
import models.Player;
import models.enums.GameStatus;
import models.enums.PlayerType;
import service.makeMoveStrategy.MakeMoveStrategy;
import service.makeMoveStrategy.MakeMoveStrategyFactory;
import service.makeMoveStrategy.MakeMoveStrategyNames;
import service.winningStrategy.WinningStrategyNames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();
        MakeMoveStrategy makeMoveStrategy = MakeMoveStrategyFactory.getMakeMoveStrategy(MakeMoveStrategyNames.HUMAN_INPUT);

        System.out.println("Enter the dimension of the board(mininum: 3): ");
        int dimension = scanner.nextInt();
        int numOfPlayers = dimension-1;

        System.out.println("Do you want a bot player? (y/n)");
        String isBotPresent = scanner.next();

        List<Player> players = new ArrayList<>(dimension);


        if (isBotPresent.equals("Y") || isBotPresent.equals("y")) {
            numOfPlayers--;

            System.out.println("What is the name of BOT");
            String botName = scanner.next();

            System.out.println("What is the symbol of BOT");
            String botSymbol = scanner.next();

            MakeMoveStrategy botMakeMoveStrategy = MakeMoveStrategyFactory.getMakeMoveStrategy(MakeMoveStrategyNames.BOT_RANDOM);

            try {
                players.add(new Player.Builder()
                        .name(botName)
                        .type(PlayerType.BOT)
                        .symbol(botSymbol.charAt(0))
                        .makeMoveStrategy(botMakeMoveStrategy)
                        .build()
                );
            } catch (EmptyNameException e) {
                System.out.println("Name cannot be empty");
            } catch (InvalidSymbolException e) {
                System.out.println("Symbol cannot be empty");
            } catch (InvalidTypeException e) {
                System.out.println("Type cannot be empty");
            } catch (InvalidMakeMoveStrategy e) {
                System.out.println("MakeMoveStrategy cannot be empty");
            }
        }


        for (int i = 1; i <= numOfPlayers; i++) {
            System.out.println("What is the name of player " + i);
            String name = scanner.next();

            System.out.println("What is the symbol of player " + i);
            String symbol = scanner.next();

            try {
                players.add(new Player.Builder()
                        .name(name)
                        .type(PlayerType.HUMAN)
                        .symbol(symbol.charAt(0))
                        .makeMoveStrategy(makeMoveStrategy)
                        .build()
                );
            } catch (EmptyNameException e) {
                System.out.println("Name cannot be empty");
            } catch (InvalidSymbolException e) {
                System.out.println("Symbol cannot be empty");
            } catch (InvalidTypeException e) {
                System.out.println("Type cannot be empty");
            } catch (InvalidMakeMoveStrategy e) {
                System.out.println("MakeMoveStrategy cannot be empty");
            }
        }

        Collections.shuffle(players);

        Game game = gameController.createGame(dimension, players, WinningStrategyNames.ORDER_ONE);
        int playerIndex = -1;

        while (game.getGameStatus() == GameStatus.IN_PROGRESS) {
            playerIndex = (playerIndex + 1) % players.size();
            Player player = players.get(playerIndex);

            System.out.println("Current player is " + player.getName());
            System.out.println("Current board state : ");
            gameController.displayBoard(game);
            Move lastMove = null;
            try {
                lastMove = gameController.makeMove(game, player);
            } catch (GameOverException e) {
                System.out.println("Game is over");
                break;
            }

            System.out.println("Do you want to undo the last move? (y/n)");
            String isUndo = scanner.next();

            if (isUndo.equals("Y") || isUndo.equals("y")) {
                gameController.undoMove(game);
                try {
                    lastMove = gameController.makeMove(game, player);
                } catch (GameOverException e) {
                    System.out.println("Game is over");
                    break;
                }
            }

            Player winner = gameController.checkWinner(game, lastMove);
            if(winner != null){
                System.out.println("WINNER IS : " + winner.getName());
                break;
            }
        }

        System.out.println("Final board status");
        gameController.displayBoard(game);


    }

}