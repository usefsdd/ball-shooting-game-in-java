package view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableRow {
    private SimpleIntegerProperty rank;
    private SimpleStringProperty username;
    private SimpleIntegerProperty score;
    private SimpleIntegerProperty weightedScore;
    private SimpleLongProperty playTime;

    public TableRow(int rank, String username, int score,
                    int weightedScore, long playTime) {
        this.rank = new SimpleIntegerProperty(rank);
        this.username = new SimpleStringProperty(username);
        this.score = new SimpleIntegerProperty(score);
        this.weightedScore = new SimpleIntegerProperty(weightedScore);
        this.playTime = new SimpleLongProperty(playTime);
    }



    public int getRank() {
        return rank.get();
    }

    public String getUsername() {
        return username.get();
    }

    public int getScore() {
        return score.get();
    }

    public int getWeightedScore() {
        return weightedScore.get();
    }

    public long getPlayTime() {
        return playTime.get();
    }

}
