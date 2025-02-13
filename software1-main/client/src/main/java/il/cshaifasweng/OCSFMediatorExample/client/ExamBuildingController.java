package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.QuestionInDrawer;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Subject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.*;


public class ExamBuildingController extends PageController {

    @FXML // fx:id="backBT"
    private Button backBT; // Value injected by FXMLLoader



    @FXML
    private Button choosexamBT;

    @FXML
    private Label courseLab;



    @FXML
    private Label subjectLab;


    @FXML
    private ComboBox<Course> courseComboBox;


    @FXML
    private ComboBox<Subject> subjectComboBox;




    void sendMessage(Message message) {
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Back(ActionEvent event) {

        Platform.runLater(() -> {
            setWindowTitle("Teacher");
            try {
                this.getData().getChoosenQuestions().removeAll(this.getData().getChoosenQuestions());
                this.getData().setSelected_course(null);
                this.getData().setSelected_subject(null);
                //this.getData().setExam_Time(-1);
               // dispose();
                setContent("Teacher",this.getData());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    @FXML
    void ChooseQuestion(ActionEvent event) {


        if (subjectComboBox.getSelectionModel().getSelectedItem() == null ||
                courseComboBox.getSelectionModel().getSelectedItem() == null) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("you have to choose the subject and the course");
                alert.showAndWait();
            });
        } else {

                this.getData().setSelected_course(courseComboBox.getSelectionModel().getSelectedItem());
                this.getData().setSelected_subject(subjectComboBox.getSelectionModel().getSelectedItem());
              //  this.getData().setExam_Time(time);

                if(!this.getData().isQuestion_update())
                {
                    Platform.runLater(() -> {
                        setWindowTitle("choose questions");
                        try {
                            setContent("chooseQuestions",this.getData());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }else {

                    Platform.runLater(() -> {
                        setWindowTitle("choose question to update");
                        try {
                            setContent("chooseQuestionToUpdate",this.getData());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                }


            }

    }



    @FXML
    public void onSubjectSelected(ActionEvent event) {

        System.out.println("onSubjectSelected");

        this.getData().getChoosenQuestions().removeAll(this.getData().getChoosenQuestions());

        Subject selectedSubject = subjectComboBox.getSelectionModel().getSelectedItem();

        if (selectedSubject != null) {
            Message message = new Message("give Me The Subject Courses");

            message.setSubjectCode(subjectComboBox.getSelectionModel().getSelectedItem().getSubject_code());
            message.setLecturerId(this.getData().getTeacher_id());
            message.setController_name("ExamBuildingController");
            sendMessage(message);
        } else {
            System.out.println("selected subject is null");
        }
    }


    @FXML
    void onCourseSelected(ActionEvent event) {

        System.out.println("onCourseSelected");

        this.getData().getChoosenQuestions().removeAll(this.getData().getChoosenQuestions());
    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

        if(event.getMessage().getController_name().equals("ExamBuildingController"))
        {
            System.out.println(event.getMessage().getMessage());

            if (event.getMessage().getMessage().equals("i gave you the teacher's subjects")) {

                List<Subject> subjects_from_server = event.getMessage().getSubjects_list_from_server();
                Platform.runLater(() -> {
                    subjectComboBox.setItems(FXCollections.observableArrayList(subjects_from_server));
                });

            }
            if ((event.getMessage().getMessage().equals("i gave you the courses by subject"))) {

                Platform.runLater(() -> {
                    // Update your UI components here
                    List<Course> courses_from_server = event.getMessage().getCourses_list_from_server();
                    courseComboBox.setItems(FXCollections.observableArrayList(courses_from_server));
                });

            }
        }

    }

    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("ExamBuildingController");


        System.out.println("initialize1 examBuilding");
        System.out.println(this.getData().getTeacher_id());


        if(this.getData().getSelected_subject()==null)
        {
            Message message = new Message("give me the subjects for teacher");
            message.setLecturerId(this.getData().getTeacher_id());
            message.setController_name("ExamBuildingController");
            sendMessage(message);

        }

        if(this.getData().getSelected_course()==null)
        {
            Platform.runLater(() -> {
                subjectComboBox.getSelectionModel().clearSelection();
                courseComboBox.getSelectionModel().clearSelection();
            });
        }



    }

//    @FXML
//    void initialize() {
//
//        System.out.println("initialize examBuilding");
//
//        if (!EventBus.getDefault().isRegistered(this)) {
//            System.out.println("register");
//            EventBus.getDefault().register(this);
//        }
//
//
//    }

}







