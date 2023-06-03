package controller;

import javafx.scene.layout.Pane;
import model.Game;
import model.Player;
import view.Ball;
import view.GameView;

public class GameController {

    public void addBall(Ball ball) {
        Game.getCurrentGame().addBall(ball.getAngle());
    }

    public void changeBallPos(double angle1, double angle2) {
        Game game = Game.getCurrentGame();
        game.removeBall(angle1);
        game.addBall(angle2);
    }

    public int getLeftBalls() {
        return Game.getCurrentGame().getLeftBalls();
    }

    public void decreaseLeftBalls() {
        Game.getCurrentGame().changeLeftBallsNum(-1);
        int frozenDegree = Game.getCurrentGame().getFrozenDegree();
        if (frozenDegree < 10)
            Game.getCurrentGame().setFrozenDegree( frozenDegree + 1);
    }

    public void savePlayers() {
        Player.savePlayers();
    }

    public int frozenReadyDegree() {
        Game game = Game.getCurrentGame();
        return game.getStartBallsNum() - game.getLeftBalls();
    }

    public int getFreezeTime() {
        return 9 - 2 * Game.getCurrentGame().getDifficulty();
    }

    public int getPhase() {
        Game game = Game.getCurrentGame();
        int shootBalls = game.getStartBallsNum() - game.getLeftBalls();
        for (int i = 3; i >= 0; i--)
            if (4 * shootBalls > i * game.getStartBallsNum() && 4 * (shootBalls - 1) <= i * game.getStartBallsNum())
                return (i + 1);
        return 0;
    }

    public int currentPhase() {
        Game game = Game.getCurrentGame();
        int shootBalls = game.getStartBallsNum() - game.getLeftBalls();
        for (int i = 3; i >= 0; i--)
            if (4 * shootBalls > i * game.getStartBallsNum()) return (i + 1);
        return 0;
    }

    public void resetFrozenDegree() {
        Game.getCurrentGame().setFrozenDegree(0);
    }

    public  int getFrozenDegree() {
        return Game.getCurrentGame().getFrozenDegree();
    }

    public double getSpeed() {
        return Game.getCurrentGame().getRotationSpeed();
    }

    public void slowSpeed() {
        Game game = Game.getCurrentGame();
        game.setRotationSpeed(game.getDifficulty() * 0.0005);
    }

    public void resetSpeed() {
        Game game = Game.getCurrentGame();
        game.setRotationSpeed(game.getDifficulty() * 0.002);
    }

    public void reverseRotation() {
        Game game = Game.getCurrentGame();
        game.setRotationSpeed(-1 * game.getRotationSpeed());
    }

    public boolean mute() {
        return Player.getCurrentPlayer().isMute();
    }

    public String[] getControlKeys() {
        if (Player.getCurrentPlayer() == null)
            return new String[]{"Space", "Tab"};
        return Player.getCurrentPlayer().getControlKeys();
    }

    public void updateScore() {
        Game game = Game.getCurrentGame();
        Player player = Player.getCurrentPlayer();
        if (player == null) return;
        int shootedBallsNum = game.getStartBallsNum() - game.getLeftBalls();
        int score = shootedBallsNum;
        player.addScore(score);
        int weightedScore = score;
        if (game.getLeftBalls() == 0) weightedScore += 3;
        else weightedScore -= 3;
        weightedScore *= game.getDifficulty();
        player.addWeightedScore(weightedScore);
        Player.savePlayers();
    }

    public int getScore() {
        return Game.getCurrentGame().getStartBallsNum() - Game.getCurrentGame().getLeftBalls();
    }

}


