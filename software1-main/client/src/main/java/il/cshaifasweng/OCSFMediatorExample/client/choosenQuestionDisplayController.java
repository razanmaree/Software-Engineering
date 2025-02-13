package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.QuestionInDrawer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.*;

public class choosenQuestionDisplayController extends PageController {


    @FXML
    private Button RetBut;

    @FXML
    private Button deleteQuesBut;

    @FXML
    private Button updateBut;

    @FXML
    private Label score_id;

    @FXML
    private TextField txf_id;
    @FXML
    private Label total_score_label;

    @FXML
    private TableView<QuestionInDrawer> table_id;
    @FXML
    private TableColumn<QuestionInDrawer, String> body_id;
    @FXML
    private TableColumn<QuestionInDrawer, String> fisrt_id;
    @FXML
    private TableColumn<QuestionInDrawer, String> second_id;
    @FXML
    private TableColumn<QuestionInDrawer, String> third_id;

    @FXML
    private TableColumn<QuestionInDrawer, String> fourth_id;
    @FXML
    private TableColumn<QuestionInDrawer, Integer> rightAnswer_id;

    @FXML
    private TableColumn<QuestionInDrawer, Integer> score_column;




    @FXML
    void OnDeleteClicked(ActionEvent event) {

        QuestionInDrawer question = table_id.getSelectionModel().getSelectedItem();
        if (question == null) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a question to update it's score");
                alert.showAndWait();
            });
        } else {
            this.getData().getChoosenQuestions().remove(question);
            table_id.getItems().remove(question);
            table_id.refresh();
            table_id.getSelectionModel().clearSelection();
            Total_score_update();
        }
    }

    @FXML
    void OnRetButClicked(ActionEvent event) {


        Platform.runLater(() -> {
            setWindowTitle("Choose Questions");
            try {
               // dispose();
                setContent("chooseQuestions",this.getData());


            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    @FXML
    void onUpdateScoreClicked(ActionEvent event) {

        int score = 0;

        if (table_id.getSelectionModel().getSelectedItem() == null) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a question to update it's score");
                alert.showAndWait();
            });
        } else {
            try {
                score = Integer.parseInt(txf_id.getText());

            } catch (NumberFormatException e) {

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter an integer value.");
                    alert.showAndWait();
                });

                txf_id.clear();
                return;
            }

            if (score<=0)
            {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter value bigger than 0");
                    alert.showAndWait();
                });
                txf_id.clear();
                return;
            }


            table_id.getSelectionModel().getSelectedItem().setTemporary_score(score);
            table_id.refresh();

            Platform.runLater(() -> {
                table_id.getSelectionModel().clearSelection();
            });
            txf_id.clear();

            Total_score_update();
        }
    }


    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

    }

    private void Total_score_update() {
        int tot_score = 0;
        for (QuestionInDrawer question : this.getData().getChoosenQuestions()) {
            tot_score += question.getTemporary_score();
        }

        total_score_label.setText("" + tot_score);
    }



    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("choosenQuestionDisplayController");

        System.out.println("initialize1 ChoosenQuestionDisplay");

        Total_score_update();

        //List<QuestionInDrawer> questions = choosenQuestions;
        table_id.getItems().clear();
        table_id.refresh();

        // Set up the columns
        body_id.setCellValueFactory(new PropertyValueFactory<>("Text"));

        fisrt_id.setCellValueFactory(new PropertyValueFactory<>("first_answer"));
        second_id.setCellValueFactory(new PropertyValueFactory<>("second_answer"));
        third_id.setCellValueFactory(new PropertyValueFactory<>("third_answer"));
        fourth_id.setCellValueFactory(new PropertyValueFactory<>("fourth_answer"));

        rightAnswer_id.setCellValueFactory(new PropertyValueFactory<>("rightAnswer"));
        score_column.setCellValueFactory(new PropertyValueFactory<>("temporary_score"));


        for (int i = 0; i < this.getData().getChoosenQuestions().size(); i++) {
            // Set the data to the table
            System.out.println(this.getData().getChoosenQuestions().get(i).getText());
            table_id.getItems().add(this.getData().getChoosenQuestions().get(i));
        }

    }


}