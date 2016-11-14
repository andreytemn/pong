package main;

import main.objects.Rocket;
import main.players.Player;

/**
 * Created by andrey on 12.11.16.
 */
public class Table extends Thread {
    private static final int H_SIZE = 12;
    private static final int V_SIZE = 20;
    private Ball ball;

    private Player player1 = new ComputerPlayer("Top", 1, new Rocket(3, '#'));

    private Player player2 = new ComputerPlayer("Bottom", V_SIZE - 2, new Rocket(3, '#'));

    private char[][] gameMap = new char[H_SIZE][V_SIZE];

    public Table() {
        eraseScreen();
        ball = new Ball();
        updateView();
        start();
    }


    private void endGame(Player player) {
        interrupt();
        System.out.println(player.getName() + " win!");

    }

    private void eraseScreen() {
        for (int i = 0; i < H_SIZE; i++) {
            for (int j = 0; j < V_SIZE; j++) {
                gameMap[i][j] = '.';
            }
        }
    }

    private void printRocket(Player player) {
        for (int i = 0; i < player.getSize(); i++) {
            gameMap[player.getPosition() + i][player.getLine()] = player.getSymbol();
        }
    }

    private void updateView() {
        eraseScreen();
        printRocket(player1);
        printRocket(player2);
        gameMap[ball.getH()][ball.getV()] = ball.getSymbol();
        View.printScreen(gameMap);
    }

    private boolean checkCollisions(int newH, int newV) {
        if (newV < 1)
            endGame(player1);
        if (newV > V_SIZE - 1)
            endGame(player2);

        return gameMap[newH][newV] != '.';
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ball.updatePosition();
                player1.update();
                player2.update();
                updateView();
                wait(200);
            } catch (InterruptedException e) {
            }
        }
    }


    private class Ball {
        private static final int CHANGE_DIR = -1;
        private int dir_v = 1;
        private int dir_h = 1;
        private int h;
        private int v;
        private char symbol = 'o';
        private int speed = 1;

        int getH() {
            return h;
        }

        int getV() {
            return v;
        }

        char getSymbol() {
            return symbol;
        }

        private void updatePosition() {
            int newH = h + speed * dir_h;
            if (newH < 0 || newH > H_SIZE) {
                dir_h *= CHANGE_DIR;
            }
            h += speed * dir_h;

            int newV = v + speed * dir_v;
            if (checkCollisions(newH, newV)) {
                dir_v *= CHANGE_DIR;
            }
            v += speed * dir_v;
        }

    }

    private class ComputerPlayer implements Player {
        private Rocket rocket;
        private int line;
        private String name;

        public ComputerPlayer(String name, int line, Rocket rocket) {
            this.name = name;
            this.line = line;
            this.rocket = rocket;
        }

        public String getName() {
            return name;
        }

        public void update() {
            rocket.setPosition(ball.getH());
        }

        public int getPosition() {
            return rocket.getPosition();
        }

        public int getSize() {
            return rocket.getSize();
        }

        public char getSymbol() {
            return rocket.getSymbol();
        }

        public int getLine() {
            return line;
        }
    }
}
