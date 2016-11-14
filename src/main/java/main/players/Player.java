package main.players;

/**
 * Created by andrey on 13.11.16.
 */
public interface Player {
    int getPosition();

    int getSize();

    char getSymbol();

    int getLine();

    String getName();

    void update();
}
