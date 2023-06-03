package model;

import view.Ball;

import java.util.ArrayList;

public class Game {

    private static Game currentGame;
    private final ArrayList<Double> ballsAngle;
    private final int startBallsNum;
    private int leftBalls;
    private final int difficulty;
    private int frozenDegree;
    private double rotationSpeed;


    public Game(int startBallsNum, int difficulty) {
        this.ballsAngle = new ArrayList<>();
        this.startBallsNum = startBallsNum;
        this.leftBalls = startBallsNum;
        this.difficulty = difficulty;
        this.rotationSpeed = 0.002 * difficulty;
        this.frozenDegree = 0;
        currentGame = this;
    }

    public Game(ArrayList<Double> balls,int startBallsNum, int leftBalls, int difficulty, int frozenDegree, double speed) {
        this.ballsAngle = balls;
        this.startBallsNum = startBallsNum;
        this.leftBalls = leftBalls;
        this.difficulty = difficulty;
        this.frozenDegree = frozenDegree;
        this.rotationSpeed = speed;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        Game.currentGame = currentGame;
    }

    public ArrayList<Double> getBalls() {
        return ballsAngle;
    }

    public void addBall(double deg) {
        this.ballsAngle.add(deg);
    }

    public void removeBall(double deg) {
        this.ballsAngle.remove(deg);
    }

    public int getLeftBalls() {
        return leftBalls;
    }

    public void changeLeftBallsNum(int leftBalls) {
        this.leftBalls += leftBalls;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public int getStartBallsNum() {
        return startBallsNum;
    }

    public int getFrozenDegree() {
        return frozenDegree;
    }

    public void setFrozenDegree(int frozenDegree) {
        this.frozenDegree = frozenDegree;
    }

}
