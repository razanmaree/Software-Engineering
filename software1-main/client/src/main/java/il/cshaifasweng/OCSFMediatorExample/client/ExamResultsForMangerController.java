package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.Subscribe;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class ExamResultsForMangerController extends PageController{

    @FXML
    private Button backB;

    @FXML
    private Label examCodeTF;

    @FXML
    private Label subjectTF;

    @FXML
    private Label courseTF;

    @FXML
    private TableView resultsTable;

    @FXML
    private TableColumn<SolvedExam, String> firstNameC;

    @FXML
    private TableColumn<SolvedExam, String> lastNameC;

    @FXML
    private TableColumn<SolvedExam, Integer> scoreC;

    @FXML
    private TableColumn<SolvedExam, Integer> durationC;

    @FXML
    private TableColumn<SolvedExam, String> timeC;

    @FXML
    private TableColumn<SolvedExam, String> exec_code;

    private boolean in_page = true;


    @FXML
    void back_button_clicked(ActionEvent event) {
        Platform.runLater(() -> {
            setWindowTitle("Exams Repository For Manger");
            try {
                subjectTF.setText("");
                courseTF.setText("");
                this.getData().setSelected_course(null);
                this.getData().setSelected_subject(null);
                this.getData().setSelected_exam_id(-1);
                in_page = false;
                setContent("ExamsRepositoryForManeger", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setTable(List<SolvedExam> questions) {
        resultsTable.getItems().clear();
        resultsTable.refresh();


        // Set up the columns
        firstNameC.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameC.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        scoreC.setCellValueFactory(new PropertyValueFactory<>("finalScore"));
        durationC.setCellValueFactory(new PropertyValueFactory<>("duration"));
        timeC.setCellValueFactory(new PropertyValueFactory<>("exam_actual_time"));
        exec_code.setCellValueFactory(new PropertyValueFactory<>("exam_execution_code"));

        // Populate the table
        resultsTable.getItems().addAll(questions);
        resultsTable.refresh();
    }


    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {
     //   System.out.println("test test test 4");
        if (event.getMessage().getController_name().equals("ExamResultsForMangerController")) {
            System.out.println(event.getMessage().getMessage());
          //  System.out.println("test test test 6");
            if ((event.getMessage().getMessage().equals("all solved exams for this execution are given"))) {
             //   System.out.println("test test test test test test test");
                List<SolvedExam> solved_exams_from_server = event.getMessage().getSolved_exams_from_server();
//                for (SolvedExam exam : solved_exams_from_server) {
//                    System.out.println("exam: " + exam.getId());
//                }
                Platform.runLater(() -> {
                    resultsTable.setItems(FXCollections.observableArrayList(solved_exams_from_server));
                    setTable(solved_exams_from_server);
                });
            }
        }
    }

    @Subscribe
    public void refresh(UpdateMessageEvent event) {
        if (event.getMessage().getController_name().equals("ExamResultsForMangerController")&&in_page) {
            System.out.println("Razan is here in controller Exam results 0");

            if (event.getMessage().getMessage().equals("exam aproved check if it is yours Maneger")) {
                System.out.println("Razan is here in controller Exam results 1");

                System.out.println("message exam_code "+event.getMessage().getExamCode());
                System.out.println("your page exam_code "+this.getData().getSelected_exam_id());

                if (event.getMessage().getExamCode() == this.getData().getSelected_exam_id()) {
                    System.out.println("Razan is here in controller Exam results IN IF");

                    Message message = new Message("give me the solved exams by exam id");
                    message.setController_name("ExamResultsForMangerController");
                    message.setSelectedexam_id(this.getData().getSelected_exam_id());
                    sendMessage(message);

                }
            }
        }
    }

    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("ExamResultsForMangerController");

        in_page = true;

        //first ask the server for the solved exam by exam id
        //bring subject name and course name to this page from the former page
        System.out.println("initialize ExamResultsForMangerController");
        Message message = new Message("give me the solved exams by exam id");
        message.setController_name("ExamResultsForMangerController");
        message.setSelectedexam_id(this.getData().getSelected_exam_id());
        sendMessage(message);
        examCodeTF.setText(Integer.toString(this.getData().getSelected_exam_id()));
        subjectTF.setText(this.getData().getSelected_subject().getSubject_name());
        courseTF.setText(this.getData().getSelected_course().getName());

    }

}

