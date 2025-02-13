package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class StudentController extends PageController {


    @FXML
    private ComboBox<Course> coursesComboBox;

    @FXML
    private TableView<ExamExecution> exams_to_exec_table;

    @FXML
    private TableColumn<ExamExecution, String> course_column;

    @FXML
    private TableColumn<ExamExecution, String> lecturer_column;

    @FXML
    private TableColumn<ExamExecution, String> subject_column;

    @FXML
    private TableColumn<ExamExecution, Integer> time_column;

    @FXML
    private Button perform_but;
    @FXML
    private Button view_course_but;

    private boolean in_page=true;



    @FXML
    void onPerformClicked(ActionEvent event) {


        ExamExecution selected_exam = exams_to_exec_table.getSelectionModel().getSelectedItem();

        if (selected_exam != null) {
            if (selected_exam instanceof ExamExecutionDigital) {

                ExamExecutionDigital digitalExam = (ExamExecutionDigital) selected_exam;

                Platform.runLater(() -> {

                    try {


                        setWindowTitle("Digital Exam Execution");
                        this.getData().setExamExecutionId(digitalExam.getExecution_id());
                        in_page=false;
                        setContent("executionDigitalExam", this.getData());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


            } else if (selected_exam instanceof ExamExecutionManual) {

                ExamExecutionManual manualExam = (ExamExecutionManual) selected_exam;


                ExamExecution selectedItem = exams_to_exec_table.getSelectionModel().getSelectedItem();

                Platform.runLater(() -> {

                    try {

                        setWindowTitle("Manual Exam Execution");
                        this.getData().setExamExecutionId(selectedItem.getExecution_id());
                        in_page=false;
                        setContent("ExecutionOfAManualExam", this.getData());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


            }

        } else {
            // No exam selected, show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select an exam to perform.");
            alert.showAndWait();
        }


    }

    @FXML
    void onSubjectSelected(ActionEvent event) {

    }

    @FXML
    void on_view_clicked(ActionEvent event) {
        //added razan 6-8
        Platform.runLater(() -> {
            try {
                Course selected = coursesComboBox.getSelectionModel().getSelectedItem();

                if (selected != null) {
                    setWindowTitle("ViewDataStudent");
                    this.getData().setStudent_id(this.getData().getStudent_id());
                    this.getData().setSelected_course(selected);
                    this.getData().setFlag(false);
                    in_page=false;
                    setContent("ViewDataStudent", this.getData());

                } else {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("message");
                        alert.setHeaderText(null);
                        alert.setContentText("SELECT A COURSE !");
                        alert.showAndWait();
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        ///////////////////////////////
    }


    private void setTable(List<ExamExecution> examExecutions) {
        exams_to_exec_table.getItems().clear();
        exams_to_exec_table.refresh();

        // Set up the columns
        subject_column.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        course_column.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        lecturer_column.setCellValueFactory(new PropertyValueFactory<>("lecturerExecutedTheExamName"));
        time_column.setCellValueFactory(new PropertyValueFactory<>("examTime"));

        exams_to_exec_table.setItems(FXCollections.observableArrayList(examExecutions));
        exams_to_exec_table.refresh();
    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {
        if (event.getMessage().getController_name().equals("StudentController")) {

            //System.out.println(event.getMessage().getMessage());
            if (event.getMessage().getMessage().equals("gave you the courses by student and the examExecutions by student")) {

                Platform.runLater(() -> {
                    List<Course> courses_from_server = event.getMessage().getCourses_list_from_server();
                    coursesComboBox.setItems(FXCollections.observableArrayList(courses_from_server));
                });

                List<ExamExecution> examExecutions = event.getMessage().getExamExecutions_list_from_server();

                for (ExamExecution e : examExecutions) {
                    System.out.println(e.getExam_duration());
                }

                Platform.runLater(() -> setTable(examExecutions));
            }
        }
    }

    @Subscribe
    public void refresh(UpdateMessageEvent event) {
        System.out.println("refresh StudentController");
        System.out.println(event.getMessage().getMessage());

        if(event.getMessage().getController_name().equals("StudentController") && in_page)
        {
            System.out.println(event.getMessage().getMessage());

            if(event.getMessage().getMessage().equals("exam execution added check if it is for you"))
            {
                if(event.getMessage().getStudentId()==this.getData().getStudent_id())
                {
                    System.out.println("the execution includes you");

                    Message message = new Message("give me the courses by student and give me the exams execute by student");
                    message.setStudentId(this.getData().getStudent_id());
                    message.setController_name("StudentController");
                    sendMessage(message);

                }
            }
        }

    }


    @FXML
    public void initialize1() {

        this.getData().setCurrentControllerName("StudentController");

        in_page=true;

        Message message = new Message("give me the courses by student and give me the exams execute by student");
        message.setStudentId(this.getData().getStudent_id());
        message.setController_name("StudentController");
        sendMessage(message);

    }






}
