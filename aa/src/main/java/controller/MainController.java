package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.Player;
import view.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainController {
    public void startGame() throws Exception {
        Player player = Player.getCurrentPlayer();
        new Game(player.getStartBallsNum(), player.getDifficulty());
        new GameView(Player.getCurrentPlayer().getMap());
        new GameMenu().start(LoginMenu.stage);
    }

    public void loadSavedGame() {
    }

    public ObservableList<TableRow> getTopPlayers(boolean sortByWeightedScore) {

        ArrayList<Player> players = (ArrayList<Player>) Menu.getLoginController().getAllPlayers().clone();
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                int playerOneScore = sortByWeightedScore ? players.get(i).getWeightedScore() : players.get(i).getScore();
                int playerTwoScore = sortByWeightedScore ? players.get(j).getWeightedScore() : players.get(j).getScore();
                if (playerOneScore < playerTwoScore ||
                        (playerOneScore == playerTwoScore &&
                         players.get(i).getPlayTime() < players.get(j).getPlayTime()))
                    Collections.swap(players, i, j);
            }
        }
        final ObservableList<TableRow> topPlayers = FXCollections.observableArrayList();
        for (int i = 0; i < Math.min(10, players.size()); i++) {
            Player player = players.get(i);
            topPlayers.add(new TableRow(i + 1, player.getUsername(), player.getScore(),
                    player.getWeightedScore(), player.getPlayTime()));
        }
        return topPlayers;
    }
}
