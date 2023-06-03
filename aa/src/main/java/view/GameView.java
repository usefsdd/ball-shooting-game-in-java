package view;

import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class GameView {

    public static final int MainCircleCenterX = 250;
    public static final int MainCircleCenterY = 300;
    public static final int MainCircleRadius = 60;
    public static final int ballsRotationRadius = 130;
    public static final int MainCircleFontSize = 50;
    public static final int ballsFontSize = 13;
    public static final int shooterY = 600;
    public static final double shootBallsRadius = 10;
    private static GameView currentGameView;
    private ArrayList<Ball> balls;
    private double wind;
    private LabeledCircle[] shooters;
    private LabeledCircle shooter2;
    private boolean shootAvailable;
    private boolean moveAvailable;
    private double ballsRadius;
    private BallRotation ballRotation;
    private ChangeSizeAnimation changeSizeAnimation;
    private ReverseAnimation reverseAnimation;
    private FadeInOutAnimation fadeInOutAnimation;
    private MediaPlayer mediaPlayer;
    private int track;
    private Pane pane;
    private Label remainBallsLabel;
    private Label timerLabel;
    private Label scoreLabel;
    private boolean pause;
    private final Instant start;
    private boolean multiPlayer;

    public GameView(int map) {
        this.shootAvailable = true;
        this.moveAvailable = false;
        this.balls = new ArrayList<>();
        this.wind = 0;
        this.ballsRadius = 10;
        this.track = 1;
        this.start = Instant.now();
        this.pause = false;
        multiPlayer = false;
        currentGameView = this;
        initBallRotation();
        initChangeSizeAnimation();
        initReverseAnimation();
        initFadeInOutAnimation();
        setMusicPlayer(0);
        int startingBallNum = 4 + map;
        for (int i = 0; i < startingBallNum; i++) {
            Ball ball = new Ball(2 * Math.PI / startingBallNum * i, "");
            balls.add(ball);
            Menu.getGameController().addBall(ball);
        }
    }

    public static GameView getCurrentGameView() {
        return currentGameView;
    }

    public static void setCurrentGameView(GameView currentGameView) {
        GameView.currentGameView = currentGameView;
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    public void addBall(Ball ball) {
        this.balls.add(ball);
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public LabeledCircle[] getShooters() {
        return shooters;
    }

    public void setShooters(LabeledCircle[] shooters) {
        this.shooters = shooters;
    }

    public LabeledCircle getShooter2() {
        return shooter2;
    }

    public void setShooter2(LabeledCircle shooter2) {
        this.shooter2 = shooter2;
    }

    public boolean isShootAvailable() {
        return shootAvailable;
    }

    public void setShootAvailable(boolean shootAvailable) {
        this.shootAvailable = shootAvailable;
    }

    public double getBallsRadius() {
        return ballsRadius;
    }

    public void changeBallsRadius(double amount) {
        for (Ball ball : balls) ball.changeRadius(amount);
        this.ballsRadius += amount;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public int getTrack() {
        return track;
    }

    public void changeTrack() {
        this.track ++;
        if (this.track == 5) this.track = 1;
    }

    public void setMusicPlayer(int index) {
        Media media;
        if (index == -1)
            media = new Media((GameView.class.getResource("/soundEffects/lose.wav")).toExternalForm());
        else if (index == -2)
            media = new Media((GameView.class.getResource("/soundEffects/win.wav")).toExternalForm());
        else {
            String[] songs = {"Super_Mario", "New-Moon-Electronic", "PatOMat", "Pink-Panther"};
            media = new Media(GameMenu.class.getResource("/music/" + songs[index] + ".mp3").toExternalForm());
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(-1);
        if (index == -1 || index == -2) mediaPlayer.setCycleCount(1);
    }

    public BallRotation getBallRotation() {
        return ballRotation;
    }

    public ChangeSizeAnimation getChangeSizeAnimation() {
        return changeSizeAnimation;
    }

    public ReverseAnimation getReverseAnimation() {
        return reverseAnimation;
    }

    public FadeInOutAnimation getFadeInOutAnimation() {
        return fadeInOutAnimation;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public boolean isMoveAvailable() {
        return moveAvailable;
    }

    public void setMoveAvailable(boolean moveAvailable) {
        this.moveAvailable = moveAvailable;
    }

    public void setInfoBar(Label remainBallsLabel, Label timerLabel, Label scoreLabel) {
        this.remainBallsLabel = remainBallsLabel;
        this.timerLabel = timerLabel;
        this.scoreLabel = scoreLabel;
        remainBallsLabel.setTranslateX(30);
        remainBallsLabel.setTranslateY(690);
        scoreLabel.setTranslateX(30);
        scoreLabel.setTranslateY(720);
        timerLabel.setTranslateX(30);
        timerLabel.setTranslateY(750);
        remainBallsLabel.setStyle("-fx-font-size: 20px");
        timerLabel.setStyle("-fx-font-size: 20px");
        scoreLabel.setStyle("-fx-font-size: 20px");
    }

    public Label getRemainBallsLabel() {
        return remainBallsLabel;
    }

    public Label getTimerLabel() {
        return timerLabel;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public Instant getStart() {
        return start;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void freeze() {
        if (Menu.getGameController().getFrozenDegree() == 10) {
            Menu.getGameController().resetFrozenDegree();
            Menu.getGameController().slowSpeed();
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(Menu.getGameController().getFreezeTime()));
            pauseTransition.setOnFinished(e -> {
                Menu.getGameController().resetSpeed();
            });
            pauseTransition.play();
        }
    }

    public boolean isMultiPlayer() {
        return multiPlayer;
    }

    public void setMultiPlayer(boolean multiPlayer) {
        this.multiPlayer = multiPlayer;
    }

    private void initBallRotation() {
        BallRotation ballRotation = new BallRotation();
        ballRotation.setInterpolator(Interpolator.LINEAR);
        this.ballRotation = ballRotation;
    }

    public void initChangeSizeAnimation() {
        this.changeSizeAnimation = new ChangeSizeAnimation(pane);
    }

    public void initReverseAnimation() {
        Random random = new Random();
        double reverseTime = random.nextDouble(4) + 8;
        this.reverseAnimation = new ReverseAnimation(reverseTime);
    }

    public void initFadeInOutAnimation() {
        this.fadeInOutAnimation = new FadeInOutAnimation();
    }

    public void lose() {
        this.setShootAvailable(false);
        this.getMediaPlayer().stop();
        this.setMusicPlayer(-1);
        this.getMediaPlayer().play();
        LoseAnimation loseAnimation = new LoseAnimation(pane);
        loseAnimation.play();
        loseAnimation.setOnFinished(actionEvent -> {
            try {
                this.ballRotation.stop();
                this.reverseAnimation.stop();
                this.changeSizeAnimation.stop();
                this.fadeInOutAnimation.stop();
                Menu.getGameController().updateScore();
                if (Menu.getLoginController().isGuest()) new LoginMenu().start(LoginMenu.stage);
                else new ScoreBoardMenu().start(LoginMenu.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void win() {
        this.shootAvailable = false;
        this.mediaPlayer.stop();
        this.setMusicPlayer(-2);
        this.mediaPlayer.play();
        WinAnimation winAnimation = new WinAnimation(pane);
        winAnimation.play();
        winAnimation.setOnFinished(actionEvent -> {
            try {
                this.ballRotation.stop();
                this.reverseAnimation.stop();
                this.changeSizeAnimation.stop();
                this.fadeInOutAnimation.stop();
                Menu.getGameController().updateScore();
                if (Menu.getLoginController().isGuest()) new LoginMenu().start(LoginMenu.stage);
                else new ScoreBoardMenu().start(LoginMenu.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


}
