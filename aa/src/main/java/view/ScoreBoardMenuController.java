package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Player;

public class ScoreBoardMenuController {
    public HBox tableBox;
    public Button sortButton;

    @FXML
    public void initialize() {
        TableView tableView = new TableView();
        TableColumn rank = new TableColumn<>("Rank");
        rank.setPrefWidth(100);
        TableColumn username = new TableColumn<>("Username");
        username.setPrefWidth(280);
        TableColumn score = new TableColumn<>("Score");
        score.setPrefWidth(160);
        TableColumn weightedScore = new TableColumn<>("Weighted Score");
        weightedScore.setPrefWidth(160);
        TableColumn playTime = new TableColumn<>("Play Time");
        playTime.setPrefWidth(160);
        tableView.getColumns().addAll(rank, username, score, weightedScore, playTime);
        rank.setCellValueFactory(new PropertyValueFactory<Player, String>("rank"));
        username.setCellValueFactory(new PropertyValueFactory<Player, String>("username"));
        score.setCellValueFactory(new PropertyValueFactory<Player, String>("score"));
        weightedScore.setCellValueFactory(new PropertyValueFactory<Player, String>("weightedScore"));
        playTime.setCellValueFactory(new PropertyValueFactory<Player, String>("playTime"));
        tableView.setItems(Menu.getMainController().getTopPlayers(sortButton.getText().contains("Weighted Score")));
        while (tableBox.getChildren().size() != 0) {
         tableBox.getChildren().remove(0);
        }
        tableBox.getChildren().add(0,tableView);
    }

    public void switchSort(MouseEvent mouseEvent) {
        if (sortButton.getText().contains("Weighted Score"))
            sortButton.setText(sortButton.getText().replace("Weighted Score","Score"));
        else sortButton.setText(sortButton.getText().replace("Score","Weighted Score"));
        initialize();
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }
}
