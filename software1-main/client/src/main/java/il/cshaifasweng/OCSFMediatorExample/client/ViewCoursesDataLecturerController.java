package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class ViewCoursesDataLecturerController extends PageController {

    @FXML
    private BarChart<String, Number> barChat;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @FXML
    private ComboBox<Course> course_id;
    @FXML
    private ComboBox<Subject> subject_id;

    @FXML
    private TableColumn<ExamInDrawer, Integer> duration_id;

    @FXML
    private TableColumn<ExamInDrawer, Integer> exam_code_id;

    @FXML
    private TableView<ExamInDrawer> table;

    @FXML
    private Label distribution_label;

    @FXML
    private Label grades_id;

    @FXML
    private Label grades_label;

    @FXML
    private Label median_id;

    @FXML
    private Label median_label;


    @FXML
    private Button ShowExam_id;

    @FXML
    private Label time_id;

    @FXML
    private Label time_label;

    @FXML
    private Label course_label;

    @FXML
    private Label count;

    @FXML
    private Label count_label;

    //  private Subject selected_subject;

    private List<String> categories = new ArrayList<>();

    private boolean in_page = true;


    @FXML
    private Label label1;
    private StringProperty LabelText1 = new SimpleStringProperty();//ad
    @FXML
    private Label label10;
    private StringProperty LabelText10 = new SimpleStringProperty();//ad
    @FXML
    private Label label2;
    private StringProperty LabelText2 = new SimpleStringProperty();//ad
    @FXML
    private Label label3;
    private StringProperty LabelText3 = new SimpleStringProperty();//ad
    @FXML
    private Label label4;
    private StringProperty LabelText4 = new SimpleStringProperty();//ad
    @FXML
    private Label label5;
    private StringProperty LabelText5 = new SimpleStringProperty();//ad
    @FXML
    private Label label6;
    private StringProperty LabelText6 = new SimpleStringProperty();//ad
    @FXML
    private Label label7;
    private StringProperty LabelText7 = new SimpleStringProperty();//ad
    @FXML
    private Label label8;
    private StringProperty LabelText8 = new SimpleStringProperty();//ad
    @FXML
    private Label label9;
    private StringProperty LabelText9 = new SimpleStringProperty();//ad


    @FXML
    void Back(ActionEvent event) {

        Platform.runLater(() -> {
            subject_id.getSelectionModel().clearSelection();
            course_id.getSelectionModel().clearSelection();
            table.getSelectionModel().clearSelection();
            table.setItems(null);
            count.setText("");
            time_id.setText("");
            grades_id.setText("");
            median_id.setText("");
            LabelText1.set("");
            LabelText2.set("");
            LabelText3.set("");
            LabelText4.set("");
            LabelText5.set("");
            LabelText6.set("");
            LabelText7.set("");
            LabelText8.set("");
            LabelText9.set("");
            LabelText10.set("");
        });

        Platform.runLater(() -> {
            setWindowTitle("Teacher");
            try {
                in_page = false;
                setContent("Teacher", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void courseClicked(ActionEvent event) {

        Platform.runLater(() -> {
            table.getSelectionModel().clearSelection();
            table.setItems(null);
            count.setText("");
            time_id.setText("");
            grades_id.setText("");
            median_id.setText("");
            LabelText1.set("");
            LabelText2.set("");
            LabelText3.set("");
            LabelText4.set("");
            LabelText5.set("");
            LabelText6.set("");
            LabelText7.set("");
            LabelText8.set("");
            LabelText9.set("");
            LabelText10.set("");
        });

        Subject selectedSubject = subject_id.getSelectionModel().getSelectedItem();
        if (selectedSubject != null) {

            System.out.println("here");
            Message message = new Message("Give me the examInDrawers by subject and course and teacher ID");
            message.setSubjectCode(subject_id.getSelectionModel().getSelectedItem().getSubject_code());
            message.setLecturerId(this.getData().getTeacher_id());

            Course selectedCourse = course_id.getSelectionModel().getSelectedItem();

            if (selectedCourse != null) {
                message.setCourse_id(course_id.getSelectionModel().getSelectedItem().getId());
                message.setController_name("ViewCoursesDataLecturerController");
                sendMessage(message);
            } else {
                System.out.println("Selected course is null");
            }

        } else {
            System.out.println("Selected subject is null");
        }


    }

    @FXML
    void subjectClicked(ActionEvent event) {

        Platform.runLater(() -> {
            course_id.getSelectionModel().clearSelection();
            table.getSelectionModel().clearSelection();
            table.setItems(null);
            count.setText("");
            time_id.setText("");
            grades_id.setText("");
            median_id.setText("");
            LabelText1.set("");
            LabelText2.set("");
            LabelText3.set("");
            LabelText4.set("");
            LabelText5.set("");
            LabelText6.set("");
            LabelText7.set("");
            LabelText8.set("");
            LabelText9.set("");
            LabelText10.set("");
        });

        this.getData().getChoosenQuestions().removeAll(this.getData().getChoosenQuestions());
        Subject selectedSubject = subject_id.getSelectionModel().getSelectedItem();

        if (selectedSubject != null) {
            //selected_subject=selectedSubject;
            Message message = new Message("give Me The Subject Courses");

            message.setSubjectCode(subject_id.getSelectionModel().getSelectedItem().getSubject_code());
            message.setLecturerId(this.getData().getTeacher_id());
            message.setController_name("ViewCoursesDataLecturerController");
            sendMessage(message);
        } else {
            System.out.println("selected subject is null");
        }
    }

    @FXML
    void ShowExam(ActionEvent event) {

        Platform.runLater(() -> {
            try {
                //ExamInDrawer selectedItem=table.getSelectionModel().getSelectedItem();
                ExamInDrawer selectedItem = table.getSelectionModel().getSelectedItem();

                if (selectedItem != null) {
                    setWindowTitle("Exam View");
                    // this.getData().setSelected_exam_id(selectedItem.getExam_code());
                    this.getData().setSelected_exam_id(selectedItem.getExam_code());
                    //+
                    this.getData().setTeacherSelectExamToImplement(false);
//                    this.getData().setSelected_course(course_id.getSelectionModel().getSelectedItem());
//                    this.getData().setSelected_subject(subject_id.getSelectionModel().getSelectedItem());
                    in_page = false;
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


//    @FXML
//    void ShowStatistics(ActionEvent event) {
//
//
//
//    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {
        System.out.println("AGAIN RAZAN IN CONTROLLER 0 ");

        if (event.getMessage().getController_name().equals("ViewCoursesDataLecturerController")) {
            System.out.println("AGAIN RAZAN IN CONTROLLER 1 ");

            if (event.getMessage().getMessage().equals("i gave you the teacher's subjects")) {

                List<Subject> subjects_from_server = event.getMessage().getSubjects_list_from_server();
                Platform.runLater(() -> {
                    subject_id.setItems(FXCollections.observableArrayList(subjects_from_server));
                });

            }
            if ((event.getMessage().getMessage().equals("i gave you the courses by subject"))) {

                Platform.runLater(() -> {
                    // Update your UI components here
                    List<Course> courses_from_server = event.getMessage().getCourses_list_from_server();
                    course_id.setItems(FXCollections.observableArrayList(courses_from_server));
                });

            }
            if (event.getMessage().getMessage().equals("examInDrawers by subject and course and teacher ID")) {
                System.out.println("setDtaFromServer table first");
                // List<ExamInDrawer> examsInDrawer =event.getMessage().getExams_list_from_server();
                // List<SolvedExam> solvedExams =event.getMessage().getSolvedExams();
                List<ExamInDrawer> exams = event.getMessage().getExams_list_from_server();

                System.out.println("HELLO IT'S ME IN CONTROLLER");
                System.out.println("HELLO IT'S ME IN CONTROLLER check " + exams.size());

                //set the table
                Platform.runLater(() -> {
                    table.setItems(FXCollections.observableArrayList(exams));
                    table.getItems().clear();
                    table.refresh();

                    duration_id.setCellValueFactory(new PropertyValueFactory<>("exam_time"));
                    exam_code_id.setCellValueFactory(new PropertyValueFactory<>("exam_code"));


                    for (int i = 0; i < exams.size(); i++) {
                        // Set the data to the table
                        table.getItems().add(exams.get(i));
                    }
                    table.refresh();
                });

            }
            if (event.getMessage().getMessage().equals("solvedExams statistics of ExamInDrawer by subject and course and teacher ID")) {


                System.out.println("AGAIN RAZAN IN CONTROLLER 2 ");

                List<Integer> statistics = event.getMessage().getStatistics();

                System.out.println("AGAIN RAZAN IN CONTROLLER 3 ");
                System.out.println("AGAIN RAZAN IN CONTROLLER 3 CHECK " + statistics.size());

                Long time = Long.valueOf(statistics.get(1));

                Platform.runLater(() -> {

                    System.out.println("EXECUTIONS " + Integer.toString(statistics.get(0)));
                    System.out.println("GRADES " + Integer.toString(statistics.get(2)));
                    System.out.println("TIME " + Long.toString(time));
                    System.out.println("MEDIAN " + Integer.toString(statistics.get(3)));

                    count.setText(Integer.toString(statistics.get(0)));
                    time_id.setText(Long.toString(time));
                    grades_id.setText(Integer.toString(statistics.get(2)));
                    median_id.setText(Integer.toString(statistics.get(3)));


//                    countLabelText.set(Integer.toString(statistics.get(0)));
//                    timeLabelText.set(Long.toString(time));
//                    gradesLabelText.set(Integer.toString(statistics.get(2)));
//                    medianLabelText.set(Integer.toString(statistics.get(3)));

                    LabelText1.set(Integer.toString(statistics.get(4)));
                    LabelText2.set(Integer.toString(statistics.get(5)));
                    LabelText3.set(Integer.toString(statistics.get(6)));
                    LabelText4.set(Integer.toString(statistics.get(7)));
                    LabelText5.set(Integer.toString(statistics.get(8)));
                    LabelText6.set(Integer.toString(statistics.get(9)));
                    LabelText7.set(Integer.toString(statistics.get(10)));
                    LabelText8.set(Integer.toString(statistics.get(11)));
                    LabelText9.set(Integer.toString(statistics.get(12)));
                    LabelText10.set(Integer.toString(statistics.get(13)));

                    // Clear existing data from the bar chart
                    barChat.getData().clear();

                    List<Integer> values = new ArrayList<>();
                    for (int i = 4; i <= 13; i++) {
                        values.add(statistics.get(i));
                    }

                    //fill the StackedBarChart
                    XYChart.Series<String, Number> series = new XYChart.Series<>();
                    for (int i = 0; i < categories.size(); i++) {
                        XYChart.Data<String, Number> data = new XYChart.Data<>(categories.get(i), values.get(i));
                        series.getData().add(data);
                    }
                    barChat.getData().add(series);
                });

            }
        }

    }

    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("ViewCoursesDataLecturerController");

        in_page = true;


        if (this.getData().isFirst_time_enter_viewCoursesDataLecturer()) {
            Platform.runLater(() -> {
                subject_id.getSelectionModel().clearSelection();
                course_id.getSelectionModel().clearSelection();
                table.getSelectionModel().clearSelection();
                table.setItems(null);
                count.setText("");
                time_id.setText("");
                grades_id.setText("");
                median_id.setText("");
                LabelText1.set("");
                LabelText2.set("");
                LabelText3.set("");
                LabelText4.set("");
                LabelText5.set("");
                LabelText6.set("");
                LabelText7.set("");
                LabelText8.set("");
                LabelText9.set("");
                LabelText10.set("");


                label1.textProperty().bind(LabelText1);//ad
                label2.textProperty().bind(LabelText2);//ad
                label3.textProperty().bind(LabelText3);//ad
                label4.textProperty().bind(LabelText4);//ad
                label5.textProperty().bind(LabelText5);//ad
                label6.textProperty().bind(LabelText6);//ad
                label7.textProperty().bind(LabelText7);//ad
                label8.textProperty().bind(LabelText8);//ad
                label9.textProperty().bind(LabelText9);//ad
                label10.textProperty().bind(LabelText10);//ad

                barChat.getData().clear();


                categories = Arrays.asList(
                        "0-10", "11-20", "21-30", "31-40", "41-50", "51-60", "61-70", "71-80", "81-90", "91-100"
                );

                // Set custom tick labels using the categories list
                xAxis.setCategories(FXCollections.observableArrayList(categories));
                xAxis.setTickLabelRotation(80); // Rotate the labels by 45 degrees
                xAxis.setTickLabelGap(10);

                yAxis.setAutoRanging(false);
                yAxis.setLowerBound(0);
                yAxis.setUpperBound(20);
                yAxis.setTickUnit(5);
            });


            table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    showStatistics();
                }
            });


            this.getData().setFirst_time_enter_viewCoursesDataLecturer(false);
            Message message = new Message("give me the subjects for teacher");
            message.setLecturerId(this.getData().getTeacher_id());
            message.setController_name("ViewCoursesDataLecturerController");
            sendMessage(message);

        }

    }


    @Subscribe
    public void refresh(UpdateMessageEvent event) {
        if (event.getMessage().getController_name().equals("ViewCoursesDataLecturerController")&&in_page) {

            if (event.getMessage().getMessage().equals("exam aproved check if it is yours Lecturer")) {

                if (event.getMessage().getAuthor_id() == this.getData().getTeacher_id()) {

                    Message message = new Message("Give me the solvedExams statistics of ExamInDrawer by subject and course and teacher ID");
                    message.setSubjectCode(subject_id.getSelectionModel().getSelectedItem().getSubject_code());
                    message.setLecturerId(this.getData().getTeacher_id());
                    message.setCourse_id(course_id.getSelectionModel().getSelectedItem().getId());
                    message.setExamCode(table.getSelectionModel().getSelectedItem().getExam_code());
                    message.setController_name("ViewCoursesDataLecturerController");
                    sendMessage(message);
                }
            }
        }
    }

    private void showStatistics() {


        if (table.getSelectionModel().getSelectedItem() == null) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("select an exam");
                alert.showAndWait();
            });
            return;
        }

        Message message = new Message("Give me the solvedExams statistics of ExamInDrawer by subject and course and teacher ID");
        message.setSubjectCode(subject_id.getSelectionModel().getSelectedItem().getSubject_code());
        message.setLecturerId(this.getData().getTeacher_id());
        message.setCourse_id(course_id.getSelectionModel().getSelectedItem().getId());
        //message.setExamCode(table.getSelectionModel().getSelectedItem().getExam_code());
        message.setExamCode(table.getSelectionModel().getSelectedItem().getExam_code());
        message.setController_name("ViewCoursesDataLecturerController");
        sendMessage(message);
    }

}
