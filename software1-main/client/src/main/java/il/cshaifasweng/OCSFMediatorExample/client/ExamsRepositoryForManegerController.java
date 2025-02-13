package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.ExamInDrawer;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Subject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.Subscribe;
//import javax.swing.text.TableView;
import java.io.IOException;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class ExamsRepositoryForManegerController extends PageController {
    @FXML
    private TableColumn<ExamInDrawer, String> examAuther_column;

    @FXML
    private TableColumn<ExamInDrawer, Integer> examID_column;

    @FXML
    private TableColumn<ExamInDrawer, Integer> examTime_column;

    @FXML
    private TableView<ExamInDrawer> examsTableView;

    @FXML
    private ComboBox<Course> courseComoBox;

    @FXML
    private Label courseLabel;

    @FXML
    private ComboBox<Subject> subjectComoBox;

    @FXML
    private Label subjectLabel;


    @FXML
    private Button showexam_button;

    @FXML
    private Button show_exam_data_button;


    private boolean in_page = true;


    @FXML
    void on_show_exam_data_button_pressed(ActionEvent event) {
        Platform.runLater(() -> {

            try {
                ExamInDrawer selectedItem = examsTableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    setWindowTitle("Exam Data View");
                    //  System.out.println(selectedItem.getExam_code() + " 1");
                    this.getData().setSelected_exam_id(selectedItem.getExam_code());
                    this.getData().setSelected_course(courseComoBox.getSelectionModel().getSelectedItem());
                    this.getData().setSelected_subject(subjectComoBox.getSelectionModel().getSelectedItem());
                    this.getData().setFlag_IAmInExamRepositoryForManager(1);

                    in_page=false;
                    setContent("ExamResultsForManger", this.getData());

                } else {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("message");
                        alert.setHeaderText(null);
                        alert.setContentText("select an exam");
                        alert.showAndWait();
                    });

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void on_back_button_pressed(ActionEvent event) {

        Platform.runLater(() -> {
            setWindowTitle("Manager");
            try {
                in_page=false;
                setContent("Manager", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    @FXML
    void show_exam_button_pressed(ActionEvent event) {

        Platform.runLater(() -> {

            try {
                ExamInDrawer selectedItem = examsTableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    setWindowTitle("Exam View");
                    //  System.out.println(selectedItem.getExam_code() + " 1");
                    this.getData().setSelected_exam_id(selectedItem.getExam_code());
                    this.getData().setFlag_IAmInExamRepositoryForManager(1);

                    in_page=false;
                    setContent("ExamBody", this.getData());

                } else {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("message");
                        alert.setHeaderText(null);
                        alert.setContentText("select an exam");
                        alert.showAndWait();
                    });

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    @FXML
    void courseComoBox_pressed(ActionEvent event) {

        if(subjectComoBox.getSelectionModel().getSelectedItem()==null ||courseComoBox.getSelectionModel().getSelectedItem()==null)
        {
            System.out.println("subject or course is null");

        }else {
            Message message = new Message("Give Me The Exams After Choosing Subject And Course");
            message.setSubject_selected_for_manager(subjectComoBox.getSelectionModel().getSelectedItem());
            message.setSelcted_course_for_manager(courseComoBox.getSelectionModel().getSelectedItem());
            message.setController_name("ExamsRepositoryForManegerController");
            sendMessage(message);
        }
    }

    @FXML
    void subjectComoBox_pressed(ActionEvent event) {
        Subject selectedSubject = subjectComoBox.getSelectionModel().getSelectedItem();

        if (selectedSubject != null) {
            //   this.getData().setFlag_subject(selectedSubject);
            Message message = new Message("give Me The Subject Courses For Manager");
            message.setSubject_code_for_manager(subjectComoBox.getSelectionModel().getSelectedItem().getSubject_code());
            message.setController_name("ExamsRepositoryForManegerController");
            sendMessage(message);

        } else {
            System.out.println("selected subject is null");
        }
//        courseLabel.setVisible(true);
//        courseComoBox.setVisible(true);
        examsTableView.getItems().clear();


    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {
        if (event.getMessage().getController_name().equals("ExamsRepositoryForManegerController")) {
            if (event.getMessage().getMessage().equals("i gave you the subject list for manager")) {
                List<Subject> subjects_from_server1 = event.getMessage().getSubject_list_for_manager();
                Platform.runLater(() -> {
                    subjectComoBox.setItems(FXCollections.observableArrayList(subjects_from_server1));
                });
            }
            if (event.getMessage().getMessage().equals("i gave you the subject's Courses for manager")) {
                Platform.runLater(() -> {
                    List<Course> courses_from_server1 = event.getMessage().getCourses_list_for_manager();
                    courseComoBox.setItems(FXCollections.observableArrayList(courses_from_server1));
                });

            }
            if (event.getMessage().getMessage().equals("i gave you the The Exams After Choosing Subject And Course")) {
                List<ExamInDrawer> exam_in_drawer_list = event.getMessage().getExam_in_drawer_list_for_manager();
                Platform.runLater(() -> {
                    examsTableView.setItems(FXCollections.observableArrayList(exam_in_drawer_list));
                    setTable(exam_in_drawer_list);
                });
            }
        }
    }

    private void setTable(List<ExamInDrawer> examInDrawer_list) {
        examsTableView.getItems().clear();
        examsTableView.refresh();

        // Set up the columns
        examID_column.setCellValueFactory(new PropertyValueFactory<>("exam_code"));
        examAuther_column.setCellValueFactory(new PropertyValueFactory<>("author_name"));
        examTime_column.setCellValueFactory(new PropertyValueFactory<>("exam_time"));


        for (int i = 0; i < examInDrawer_list.size(); i++) {
            // Set the data to the table
            examsTableView.getItems().add(examInDrawer_list.get(i));
        }
        examsTableView.refresh();
    }

    //added razan 9-8
    @Subscribe
    public void refresh(UpdateMessageEvent event) {
        if (event.getMessage().getController_name().equals("ExamsRepositoryForManegerController")&&in_page) {
            System.out.println("Razan is here in controller ExamsReposotory 0");

            if (event.getMessage().getMessage().equals("exam added check if it is yours")) {
                System.out.println("Razan is here in controller ExamsReposotory 1");

                if (event.getMessage().getSubjectCode() == subjectComoBox.getSelectionModel().getSelectedItem().getSubject_code()
                        && event.getMessage().getCourse_id() == courseComoBox.getSelectionModel().getSelectedItem().getId()) {

                    Message message = new Message("Give Me The Exams After Choosing Subject And Course");
                    message.setSubject_selected_for_manager(subjectComoBox.getSelectionModel().getSelectedItem());
                    message.setSelcted_course_for_manager(courseComoBox.getSelectionModel().getSelectedItem());
                    message.setController_name("ExamsRepositoryForManegerController");
                    sendMessage(message);

                }
            }
        }
    }

    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("ExamsRepositoryForManegerController");

        in_page = true;


        if(this.getData().isIs_first_time_enter_from_manager())
        {
            this.getData().setIs_first_time_enter_from_manager(false);

            courseComoBox.getSelectionModel().clearSelection();
            subjectComoBox.getSelectionModel().clearSelection();
            courseComoBox.setItems(null);
            subjectComoBox.setItems(null);


            Message message = new Message("give me the subjects for manager");
            message.setController_name("ExamsRepositoryForManegerController");
            sendMessage(message);
        }



    }
}