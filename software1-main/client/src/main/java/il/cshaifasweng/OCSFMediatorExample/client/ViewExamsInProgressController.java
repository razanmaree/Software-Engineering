package il.cshaifasweng.OCSFMediatorExample.client;

/**
 * Sample Skeleton for 'ViewExamsInProgress.fxml' Controller Class
 */

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;


public class ViewExamsInProgressController extends PageController {

    @FXML
    private Button backBT;

    @FXML
    private Button showstudentswhoexecutetheselectedexamBT;


    @FXML
    private TableColumn<?, String> Course_id;

    @FXML
    private TableColumn<?, String> Subject_id;

    @FXML
    private TableColumn<?, Integer> Duration_id;
    @FXML
    private TableColumn<?, Integer> exam_code_id;

    @FXML
    private TableColumn<ExamExecution, String> Ecexution_Code_id;

    @FXML
    private TableView<ExamExecution> tableofviewexamsinprogressTABLE;

    private List<LecturerExamexecutionsLink> LecturerExamexecutionsLinkList;

    private boolean in_page = true;


    @FXML
    void Back(ActionEvent event) {
        Platform.runLater(() -> {
            setWindowTitle("Teacher");
            try {
                this.getData().setLecturerExamExecutionLink_id(-1);
                in_page = false;
                setContent("Teacher", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    @FXML
    void ShowStudentsWhoExecuteTheSelectedExam(ActionEvent event) {

        ExamExecution selected = tableofviewexamsinprogressTABLE.getSelectionModel().getSelectedItem();
        int index = tableofviewexamsinprogressTABLE.getSelectionModel().getSelectedIndex();

        if (selected == null) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Choose an exam !");
                alert.showAndWait();
            });
            return;
        }

        // System.out.println(""+LecturerExamexecutionsLinkList.get(index).getLecturerExamexecutionsLink_id());

        Platform.runLater(() -> {
            setWindowTitle("List Of Students Who Execute An Exam");
            try {
                this.getData().setLecturerExamExecutionLink_id(LecturerExamexecutionsLinkList.get(index).getLecturerExamexecutionsLink_id());
                in_page = false;
                setContent("ListOfStudentsWhoExecuteAnExam", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {
        if (event.getMessage().getController_name().equals("ViewExamsInProgressController")) {
            if (event.getMessage().getMessage().equals("exams_execution link given")) {

                // System.out.println("hi0");

                LecturerExamexecutionsLinkList = event.getMessage().getLecturerExamexecutionsLinkList();//3-8

                //  System.out.println("hi1");

                List<ExamExecution> examsExecutions = event.getMessage().getExamExecutions_list_from_server();


                //set the table
                Platform.runLater(() -> {
                    tableofviewexamsinprogressTABLE.setItems(FXCollections.observableArrayList(examsExecutions));

                    tableofviewexamsinprogressTABLE.getItems().clear();
                    tableofviewexamsinprogressTABLE.refresh();

                    // Set up the columns
                    Ecexution_Code_id.setCellValueFactory(new PropertyValueFactory<>("execution_code"));

                    Subject_id.setCellValueFactory(param -> {
                        ExamExecution examExecution = (ExamExecution) param.getValue();
                        ExamInDrawer examInDrawer = examExecution.getExam();
                        String courseName = (examInDrawer != null) ? examInDrawer.getCourse_name() : "";
                        return new SimpleStringProperty(courseName);
                    });

                    Course_id.setCellValueFactory(param -> {
                        ExamExecution examExecution = (ExamExecution) param.getValue();
                        ExamInDrawer examInDrawer = examExecution.getExam();
                        String subjectName = (examInDrawer != null) ? examInDrawer.getSubject_name() : "";
                        return new SimpleStringProperty(subjectName);
                    });

                    Duration_id.setCellValueFactory(param -> {
                        ExamExecution examExecution = (ExamExecution) param.getValue();
                        ExamInDrawer examInDrawer = examExecution.getExam();
                        int examTime = (examInDrawer != null) ? examInDrawer.getExam_time() : 0;
                        return new SimpleIntegerProperty(examTime).asObject();
                    });

                    exam_code_id.setCellValueFactory(param -> {
                        ExamExecution examExecution = (ExamExecution) param.getValue();
                        ExamInDrawer examInDrawer = examExecution.getExam();
                        int examCode = (examInDrawer != null) ? examInDrawer.getExam_code() : 0;
                        return new SimpleIntegerProperty(examCode).asObject();
                    });


                    for (int i = 0; i < examsExecutions.size(); i++) {
                        // Set the data to the table
                        tableofviewexamsinprogressTABLE.getItems().add(examsExecutions.get(i));
                    }
                    tableofviewexamsinprogressTABLE.refresh();

                });
            }


        }
    }


    @Subscribe
    public void refresh(UpdateMessageEvent event) {
        System.out.println("refresh ViewExamsInProgressController");
        System.out.println(event.getMessage().getMessage());

        if (event.getMessage().getController_name().equals("ViewExamsInProgressController") && in_page) {
            System.out.println(event.getMessage().getMessage());

            if (event.getMessage().getMessage().equals("exam execution have been ended ExamsInProgress check if you execute it")) {
                if (event.getMessage().getLecturerId() == this.getData().getTeacher_id()) {
                    //////
                    Message message = new Message("get exams_execution link by Teacher id");
                    message.setLecturerId(this.getData().getTeacher_id());
                    message.setController_name("ViewExamsInProgressController");
                    sendMessage(message);
                }
            }
            if (event.getMessage().getMessage().equals("exam execution have been started ExamsInProgress check if you execute it")) {
                if (event.getMessage().getLecturerId() == this.getData().getTeacher_id()) {
                    //////
                    Message message = new Message("get exams_execution link by Teacher id");
                    message.setLecturerId(this.getData().getTeacher_id());
                    message.setController_name("ViewExamsInProgressController");
                    sendMessage(message);
                }
            }
        }

    }

    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("ViewExamsInProgressController");

        in_page = true;

        tableofviewexamsinprogressTABLE.getSelectionModel().clearSelection();

        Message message = new Message("get exams_execution link by Teacher id");
        message.setLecturerId(this.getData().getTeacher_id());
        message.setController_name("ViewExamsInProgressController");
        sendMessage(message);
    }

}
