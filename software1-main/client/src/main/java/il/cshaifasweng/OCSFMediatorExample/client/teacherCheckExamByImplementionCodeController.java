package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class teacherCheckExamByImplementionCodeController extends PageController {


    @FXML
    private Button back_button;

    @FXML
    private Button prove_button;

    @FXML
    private Button show_button;

    @FXML
    private TableView<SolvedExam> solved_exams_table;

    @FXML
    private TableColumn<SolvedExam, String> student_comments_column;

    @FXML
    private TableColumn<SolvedExam, Integer> studentid_column;


    @FXML
    private TableColumn<SolvedExam, String> firstname_column;

    @FXML
    private TableColumn<SolvedExam, Integer> grade_column;

    @FXML
    private TableColumn<SolvedExam, String> lastname_column;


    private List<SolvedExam> solvedExams = new ArrayList<>();

    private List<ExamExecution> examExecutions = new ArrayList<>();

    private boolean in_page = true;



    private void setTable(List<SolvedExam> solvedExams) {

        solved_exams_table.getItems().clear();
        solved_exams_table.refresh();

        // Set up the columns
        studentid_column.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        firstname_column.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastname_column.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        grade_column.setCellValueFactory(new PropertyValueFactory<>("finalScore"));
        student_comments_column.setCellValueFactory(new PropertyValueFactory<>("studentText"));


        for (int i = 0; i < solvedExams.size(); i++) {
            solved_exams_table.getItems().add(solvedExams.get(i));
        }
        solved_exams_table.refresh();

    }

    @FXML
    void prove_button_pressed(ActionEvent event) {

        SolvedExam selected = solved_exams_table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Choose a student to approve it's grade!");
                alert.showAndWait();
            });
            return;
        } else {

            Message message = new Message("approve_solved_exam");
            message.setController_name("teacherCheckExamByImplementionCodeController");
            message.setSolved_exam_id(selected.getId());
            message.setSolvedExams(solvedExams);//added razan 9-8
            sendMessage(message);
        }

    }


    @FXML
    void back_button_pressed(ActionEvent event) {

        Platform.runLater(() -> {
            setWindowTitle("view Exams To Approve");
            try {
                this.getData().setSolved_exam_id(-1);
                this.getData().setLecturerExamExecutionLink_id(-1);
                in_page = false;
                setContent("viewExamsToApprove", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void show_button_pressed(ActionEvent event) {


        SolvedExam selected = solved_exams_table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Choose a student !");
                alert.showAndWait();
            });
            return;
        } else {
            Platform.runLater(() -> {
                setWindowTitle("Checked Exam");
                try {
                    this.getData().setSolved_exam_id(selected.getId());
                    in_page = false;
                    setContent("TestedExam", this.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

        if (event.getMessage().getController_name().equals("teacherCheckExamByImplementionCodeController")) {

            //System.out.println(event.getMessage().getMessage());

            if (event.getMessage().getMessage().equals("solved exams by link_id given")) {

                solvedExams = event.getMessage().getSolvedExams();

//               for (SolvedExam solvedExam:solvedExams)
//               {
//                   System.out.println(""+solvedExam.getId());
//               }

                setTable(solvedExams);
            }  if (event.getMessage().getMessage().equals("solved exam approved"))
            {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("message");
                    alert.setHeaderText(null);
                    alert.setContentText("the grade approved");
                    alert.showAndWait();
                });

                Message message = new Message("get solved Exams list by link_id");
                message.setController_name("teacherCheckExamByImplementionCodeController");
                message.setLink_id(this.getData().lecturerExamExecutionLink_id);
                sendMessage(message);
            }




        }
    }

    @Subscribe
    public void refresh(UpdateMessageEvent event) {
        System.out.println("refresh teacherCheckExamByImplementionCodeController");
        System.out.println(event.getMessage().getMessage());

        if (event.getMessage().getController_name().equals("teacherCheckExamByImplementionCodeController") && in_page) {
            System.out.println(event.getMessage().getMessage());

            if (event.getMessage().getMessage().equals("digital exam execution have been ended check if you execute it")) {
                if (event.getMessage().getLecturerExamExecutionLink_id() == this.getData().getLecturerExamExecutionLink_id()) {
                    Message message = new Message("get solved Exams list by link_id");
                    message.setController_name("teacherCheckExamByImplementionCodeController");
                    message.setLink_id(this.getData().lecturerExamExecutionLink_id);
                    sendMessage(message);
                }
            }
        }
    }

    @FXML
    void initialize1() {

        in_page = true;

        this.getData().setCurrentControllerName("teacherCheckExamByImplementionCodeController");

        solvedExams = new ArrayList<>();
        examExecutions = new ArrayList<>();

        Message message = new Message("get solved Exams list by link_id");
        message.setController_name("teacherCheckExamByImplementionCodeController");
        message.setLink_id(this.getData().lecturerExamExecutionLink_id);
        sendMessage(message);


    }

}
