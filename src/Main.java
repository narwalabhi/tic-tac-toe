import exception.EmptyNameException;
import exception.InvalidSymbolException;
import exception.InvalidTypeException;
import models.Player;
import models.enums.PlayerType;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Player player = null;
        try {
            player = new Player.Builder()
                    .name("Abc")
                    .symbol('X')
                    .type(PlayerType.HUMAN)
                    .build();
        } catch (InvalidSymbolException | InvalidTypeException | Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(player);

    }

    public void creatUrl(String... args){
        String url = "https://www.google.com/search?q=";
        for (String arg : args) {
            url += arg + "+";
        }
        url = url.substring(0, url.length() - 1);
        System.out.println(url);
    }

}