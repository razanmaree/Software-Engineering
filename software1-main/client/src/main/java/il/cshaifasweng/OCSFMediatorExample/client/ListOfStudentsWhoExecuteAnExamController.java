package il.cshaifasweng.OCSFMediatorExample.client;

/**
 * Sample Skeleton for 'ListOfStudentsWhoExecuteAnExam.fxml' Controller Class
 */


import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.ExamExecution;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Student;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.Subscribe;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;


public class ListOfStudentsWhoExecuteAnExamController extends PageController {


    @FXML
    private Label addLAB;


    @FXML
    private Button cancelBT;

    @FXML
    private Label minutestotheexamLAB;

    @FXML
    private CheckBox SelectAllCheckBox;


    @FXML
    private Button sendamessageBT;

    @FXML
    private TableView<ExamExecution> studentT;

    @FXML
    private TableColumn<ExamExecution, Integer> studentIdColumn;

    @FXML
    private TableColumn<ExamExecution, String> studentFirstNameColumn;

    @FXML
    private TableColumn<ExamExecution, String> studentLastNameColumn;

//    @FXML
//    private TableColumn<ExamExecution, String> timeLiftColumn;

    @FXML
    private TableColumn<ExamExecution, String> endTimeColumn;

    @FXML
    private TableColumn<ExamExecution, String> StartTimeColumn;


    @FXML
    private TextField timeinminuteTF;

    @FXML
    private TextField RequestBodyTF;


    private ObservableList<ExamExecution> selectedExecutions = FXCollections.observableArrayList();

    private boolean in_page = true;


    @FXML
    void Cancel(ActionEvent event) {
        //the list of the selected executions will be null
        Platform.runLater(() -> {
            setWindowTitle("View Exams In Progress");
            try {
                //  this.getData().getSelectedStudents().removeAll(this.getData().getSelectedStudents());
                studentT.getSelectionModel().clearSelection();
                studentT.setItems(null);
                SelectAllCheckBox.setSelected(false);
                timeinminuteTF.setText("");
                this.getData().setLecturerExamExecutionLink_id(-1);
                in_page = false;
                setContent("ViewExamsInProgress", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void SelectAll(ActionEvent event) {
        //Done
        // Check if the CheckBox is selected
        if (SelectAllCheckBox.isSelected()) {
            // Add all students in the table to the selectedStudents list
            selectedExecutions.removeAll(selectedExecutions);
            selectedExecutions.addAll(studentT.getItems());

            // Select all rows in the TableView
            studentT.getSelectionModel().selectAll();
        } else {
            // If the CheckBox is not selected, clear the selectedStudents list
            selectedExecutions.removeAll(selectedExecutions);

            // Clear the selection in the TableView
            studentT.getSelectionModel().clearSelection();
        }
    }

    @FXML
    void SendAMessage(ActionEvent event) {
        //first check if the students were selected

        selectedExecutions = studentT.getSelectionModel().getSelectedItems();
        if (selectedExecutions.size() == 0) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("you did not select any exam execution");
                alert.showAndWait();
            });
            return;
        }
        try {
            int time = Integer.parseInt(timeinminuteTF.getText());
            String timeStr = Integer.toString(time); // Convert integer to string
            if (time <= 0) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("extension time must be positive");
                    alert.showAndWait();
                });
            } else if (time > 180) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("extension time must be less than 180 minute");
                    alert.showAndWait();
                });
            } else {
                Message message = new Message("send time extension request");
                String requestBody = RequestBodyTF.getText();
                message.setRequestBody(requestBody);
                message.setExamExecutions_list_from_server(new ArrayList<>(selectedExecutions));
                message.setExtensionTime(time);
                message.setController_name("ListOfStudentsWhoExecuteAnExamController");
                sendMessage(message);
            }

        } catch (Exception e) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("extension time must be in number");
                alert.showAndWait();
            });

        }
    }

    @FXML
    void TimeInMinute(ActionEvent event) {

    }


    private void setTable(List<ExamExecution> executions) {
        studentT.getItems().clear();
        studentT.refresh();

        // Set up the columns
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        studentFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        studentLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        // timeLiftColumn.setCellValueFactory(new PropertyValueFactory<>("timeLift"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        StartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));


        for (int i = 0; i < executions.size(); i++) {
            // Set the data to the table
            studentT.getItems().add(executions.get(i));
        }
        studentT.refresh();
    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

        if (event.getMessage().getController_name().equals("ListOfStudentsWhoExecuteAnExamController")) {
            System.out.println(event.getMessage().getMessage());

            if (event.getMessage().getMessage().equals("Exam executions list by link_id given")) {
                List<ExamExecution> executions = event.getMessage().getExamExecutions_list_from_server();

                Platform.runLater(() -> {
                    studentT.setItems(FXCollections.observableArrayList(executions));
                    setTable(executions);

                });
            } else if (event.getMessage().getMessage().equals("time extension request sent to manger")) {
                Platform.runLater(() -> {
                    studentT.getSelectionModel().clearSelection();
                    timeinminuteTF.setText("");
                    RequestBodyTF.setText("");

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("Your time extension request have been sent to the manger");
                    alert.showAndWait();


//                    Message message = new Message("get Exam Executions list by link_id");
//                    message.setController_name("ListOfStudentsWhoExecuteAnExamController");
//                    message.setLink_id(this.getData().getLecturerExamExecutionLink_id());
//                    sendMessage(message);
                });
            }
        }
    }

    @Subscribe
    public void refresh(UpdateMessageEvent event) {
        System.out.println("refresh ListOfStudentsWhoExecuteAnExamController");
        System.out.println(event.getMessage().getMessage());

        if (event.getMessage().getController_name().equals("ListOfStudentsWhoExecuteAnExamController")) {
            System.out.println(event.getMessage().getMessage());

            if (event.getMessage().getMessage().equals("exam execution have been started") && in_page) {

                if (event.getMessage().getLecturerExamExecutionLink_id() == this.getData().getLecturerExamExecutionLink_id()) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("message");
                        alert.setHeaderText(null);
                        alert.setContentText("exam execution have been started");
                        alert.showAndWait();
                    });

                    initialize_body();
                }
            }
            if (event.getMessage().getMessage().equals("time extension have been confirmed")) {
                if (event.getMessage().getLecturerExamExecutionLink_id() == this.getData().getLecturerExamExecutionLink_id()) {

//                    Platform.runLater(() -> {
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("message");
//                        alert.setHeaderText(null);
//                        alert.setContentText("time extension have been confirmed");
//                        alert.showAndWait();
//                    });

                    initialize_body();
                }
            }
            if (event.getMessage().getMessage().equals("exam execution have been ended")) {


                if (event.getMessage().getLecturerExamExecutionLink_id() == this.getData().getLecturerExamExecutionLink_id()) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("message");
                        alert.setHeaderText(null);
                        alert.setContentText("exam execution have been ended");
                        alert.showAndWait();
                    });

                    initialize_body();
                }

            }
        }

    }

    @FXML
    void initialize1() {

        timeinminuteTF.setText("");
        RequestBodyTF.setText("");

        initialize_body();

    }

    public void initialize_body() {

        this.getData().setCurrentControllerName("ListOfStudentsWhoExecuteAnExamController");

        in_page = true;
        studentT.getSelectionModel().clearSelection();
        studentT.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        studentT.setItems(null);

        Message message = new Message("get Exam Executions list by link_id");
        message.setController_name("ListOfStudentsWhoExecuteAnExamController");
        message.setLink_id(this.getData().getLecturerExamExecutionLink_id());
        sendMessage(message);
    }


}
