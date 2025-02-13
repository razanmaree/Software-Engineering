package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.SolvedExam;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Student;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class ViewDataStudentController extends PageController{

    @FXML
    private Button show_id;
    @FXML
    private Button back_id;
    @FXML
    private TableColumn<SolvedExam, String> Lecturers_comment_id;

    @FXML
    private TableColumn<SolvedExam, String> Student_comment;

    @FXML
    private TableColumn<SolvedExam, Integer> grade_id;


    private boolean in_page=true;


    @FXML
    private TableView<SolvedExam> table;

    @FXML
    void Back(ActionEvent event) {
        Platform.runLater(() -> {
            setWindowTitle("student");
            try {
                //table.getItems().clear();
                //this.getData().setSolvedExam(null);
                in_page=false;
                setContent("student", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void ShowExam(ActionEvent event) {
        SolvedExam selected = table.getSelectionModel().getSelectedItem();
        if(selected!=null) {
            Platform.runLater(() -> {
                setWindowTitle("ViewSolvedExamByStudent");
                try {
                    this.getData().setSolved_exam_id(selected.getId());
                    //this.getData().setSolvedExam(selected);
                    in_page=false;
                    setContent("ViewSolvedExamByStudent", this.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        else {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("select an exam !");
                alert.showAndWait();
            });
        }

    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {
        if (event.getMessage().getController_name().equals("ViewDataStudentController")) {
            if (event.getMessage().getMessage().equals("SOLVED EXAMS BY STUDENT_ID AND COURSE_ID GIVEN")) {
                List<SolvedExam> solvedExams=event.getMessage().getSolvedExams();
                System.out.println("hello there FINAL SCORE "+solvedExams.get(0).getFinalScore());
                //set the table
                Platform.runLater(() -> {
                    table.setItems(FXCollections.observableArrayList(solvedExams));
                    table.getItems().clear();
                    table.refresh();

                    // Set up the columns
                    Lecturers_comment_id.setCellValueFactory(new PropertyValueFactory<>("lecturerText"));
                    Student_comment.setCellValueFactory(new PropertyValueFactory<>("studentText"));
                    grade_id.setCellValueFactory(new PropertyValueFactory<>("finalScore"));


                    for (int i = 0; i < solvedExams.size(); i++) {
                        // Set the data to the table
                        table.getItems().add(solvedExams.get(i));
                    }
                    table.refresh();
                });
            }
        }
    }

    //added razan 9-8
    @Subscribe
    public void refresh(UpdateMessageEvent event) {
        if(event.getMessage().getController_name().equals("ViewDataStudentController")&&in_page) {
            System.out.println("Razan is here in controller 0");
            if (event.getMessage().getMessage().equals("exam aproved check if it is yours")) {
                System.out.println("Razan is here in controller 1");
                if (event.getMessage().getStudentId() == this.getData().getStudent_id()) {
                    System.out.println("Razan is here in controller 2");
                    Message message = new Message("GIVE ME THE SOLVED EXAM BY STUDENT_ID AND COURSE_ID");
                    message.setStudentID(this.getData().getStudent_id());
                    message.setCourse_id(this.getData().getSelected_course().getId());
                    message.setController_name("ViewDataStudentController");
                    sendMessage(message);
                }
            }
        }
    }

    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("ViewDataStudentController");

        in_page=true;
        System.out.println("i am in initialize rrr");

        if(this.getData().isFlag()==false) {
            table.getItems().clear();
            Message message = new Message("GIVE ME THE SOLVED EXAM BY STUDENT_ID AND COURSE_ID");
            message.setStudentID(this.getData().getStudent_id());//edited 8-8
            message.setCourse_id(this.getData().getSelected_course().getId());
            System.out.println("check name "+this.getData().getSelected_course().getName());
            message.setController_name("ViewDataStudentController");
            sendMessage(message);
        }
    }

}