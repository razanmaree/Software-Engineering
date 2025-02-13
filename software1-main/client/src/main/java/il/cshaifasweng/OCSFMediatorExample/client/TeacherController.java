package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.*;

public class TeacherController extends PageController {

    @FXML
    private Button CreateAnexam_id;

    @FXML
    private Button CreateAquestion_id;

    @FXML
    private Button ExamExecution_id;

    @FXML
    private Button ExamsWaitingToApprove_id;

    @FXML
    private Button ShowExamsInExecution_id;

    @FXML
    private Button ViewCoursesData_id;

    @FXML
    private Button back_id;


    @FXML
    private Button update_an_exam;

    @FXML
    void UpdateExam(ActionEvent event) {

        Platform.runLater(() -> {
            setWindowTitle("Teacher select exam to update");
            try {
                this.getData().setExam_update(true);
                setContent("TeacherSelectExamToImplement", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    @FXML
    void UpdateQuestion(ActionEvent event) {

        Platform.runLater(() -> {
            setWindowTitle("Building an exam");
            try {
                this.getData().setQuestion_update(true);
                setContent("ExamBuilding", this.getData());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void CreateAnExam(ActionEvent event) {


        Platform.runLater(() -> {
            setWindowTitle("Building an exam");
            try {
                this.getData().setExam_update(false);
                this.getData().setFirst_time_enter_choose_questions(false);
                setContent("ExamBuilding", this.getData());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void CreateAquestion(ActionEvent event) {

        Platform.runLater(() -> {
            setWindowTitle("create a question");
            try {
                this.getData().setQuestion_update(false);
                setContent("createAquestion", this.getData());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void ExamExecution(ActionEvent event) {

        Platform.runLater(() -> {
            setWindowTitle("Teacher Select Exam To Implement");
            try {
                this.getData().setTeacherSelectExamToImplement(true);
                this.getData().setExam_update(false);
                setContent("TeacherSelectExamToImplement", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    @FXML
    void ExamsWaitingToApprove(ActionEvent event) {
        Platform.runLater(() -> {
            setWindowTitle("Exams to approve");
            try {
              //  dispose();
                setContent("ViewExamsToApprove", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void ShowExamsInExecution(ActionEvent event) {
        Platform.runLater(() -> {
            setWindowTitle("Exams in progress");
            try {
              //  dispose();
                setContent("ViewExamsInProgress", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void ViewCoursesData(ActionEvent event) {
        Platform.runLater(() -> {
            setWindowTitle("ViewCoursesDataLecturer");
            try {
                this.getData().setFirst_time_enter_viewCoursesDataLecturer(true);
                setContent("ViewCoursesDataLecturer", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {
    }


    @FXML
    public void initialize1() {

        this.getData().setCurrentControllerName("TeacherController");

        System.out.println("initialize1 Teacher");
       // System.out.println(this.getData().getTeacher_id());
    }



}