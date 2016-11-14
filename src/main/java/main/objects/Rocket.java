package main.objects;

/**
 * Created by andrey on 12.11.16.
 */
public class Rocket {
    private int size;
    private int position = 5;
    private char symbol;

    public Rocket(int size, char symbol) {
        if (size > 9) {
            size = 9;
        }
        if (size < 1) {
            size = 1;
        }
        this.size = size;
        this.symbol = symbol;
    }

    public int getSize() {
        return size;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        if (position < 0) position = 0;
        if (position > 10 - size)
            position = 10 - size;
        this.position = position;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
