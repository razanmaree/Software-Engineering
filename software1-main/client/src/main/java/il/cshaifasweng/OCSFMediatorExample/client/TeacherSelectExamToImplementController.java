package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.*;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.*;

public class TeacherSelectExamToImplementController extends PageController {

    @FXML
    private CheckBox subjects_check_box;
    @FXML
    private CheckBox range_check_box;
    @FXML
    private CheckBox courses_check_box;
    @FXML
    private CheckBox authors_check_box;

    @FXML
    private ListView<Lecturer> authors_list;

    @FXML
    private Button back_but;

    @FXML
    private Button clear_all_but;

    @FXML
    private ListView<Course> courses_list;

    @FXML
    private TableColumn<ExamInDrawer, String> exam_authoor_column;

    @FXML
    private TableColumn<ExamInDrawer, Integer> exam_id_column;


    @FXML
    private TableColumn<ExamInDrawer, String> exam_subject_column;

    @FXML
    private TableColumn<ExamInDrawer, Integer> exam_time_column;

    @FXML
    private TableColumn<ExamInDrawer, String> exam_course_column;

    @FXML
    private TableView<ExamInDrawer> exams_table;

    @FXML
    private Button filter_but;

    @FXML
    private Slider points_range_slider;

    @FXML
    private Label range_label;

    @FXML
    private Button selectstudentsBT;

    @FXML
    private Button show_exam_but;

    @FXML
    private ListView<Subject> subjects_list;

    private boolean in_page = true;

    private int range;
    private ObservableList<Course> selectedCourses;
    private ObservableList<Lecturer> selectedLecturers;
    private ObservableList<Subject> selectedSubjects;

    private List<Course> teacher_courses;

    private List<Subject> teacher_subjects;


    private void setTable(List<ExamInDrawer> examsInDrawer) {

        Platform.runLater(() -> {
            exams_table.setItems(FXCollections.observableArrayList(examsInDrawer));
            exams_table.refresh();
        });
    }


    @FXML
    void on_back_but_clicked(ActionEvent event) {

        Platform.runLater(() -> {
            setWindowTitle("Teacher");
            try {


                courses_list.getSelectionModel().clearSelection();
                courses_list.setItems(null);
                subjects_list.getSelectionModel().clearSelection();
                subjects_list.setItems(null);
                authors_list.setItems(null);
                authors_list.getSelectionModel().setSelectionMode(null);
                exams_table.setItems(null);


                authors_check_box.setSelected(false);
                courses_check_box.setSelected(false);
                range_check_box.setSelected(false);
                subjects_check_box.setSelected(false);

                in_page = false;
                setContent("Teacher", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void on_clear_all_but_pressed(ActionEvent event) {

        courses_list.getSelectionModel().clearSelection();
        subjects_list.getSelectionModel().clearSelection();
        authors_list.getSelectionModel().clearSelection();
        points_range_slider.setValue(500);

        range_check_box.setSelected(false);
        courses_check_box.setSelected(false);
        subjects_check_box.setSelected(false);
        authors_check_box.setSelected(false);

        filter();
    }

    @FXML
    void on_drag_range(MouseEvent event) {
        range_label.setText("" + points_range_slider.getValue());
    }


    @FXML
    void on_show_exam_presses(ActionEvent event) {


        Platform.runLater(() -> {

            try {

                ExamInDrawer selectedItem = exams_table.getSelectionModel().getSelectedItem();

                if (selectedItem != null) {

                    setWindowTitle("Exam View");
                    this.getData().setSelected_exam_id(selectedItem.getExam_code());
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

    @FXML
    void OnSelectStudentsClicked(ActionEvent event) {

        Platform.runLater(() -> {

            try {
                ExamInDrawer selectedItem = exams_table.getSelectionModel().getSelectedItem();

                if (selectedItem != null) {


                    this.getData().setSelected_exam_id(selectedItem.getExam_code());
                    if (this.getData().isExam_update()) {
                        setWindowTitle("choose Questions");
                        this.getData().setSelected_course(selectedItem.getCourse());
                        this.getData().setSelected_subject(selectedItem.getSubject());
                        this.getData().setFirst_time_enter_choose_questions(true);

                        in_page = false;
                        setContent("chooseQuestions", this.getData());

                    } else {
                        setWindowTitle("Choose Students To Exam");

                        in_page = false;
                        setContent("ChooseStudentsToExam", this.getData());
                    }


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


    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

        if (event.getMessage().getController_name().equals("TeacherSelectExamToImplementController")) {

            System.out.println(event.getMessage().getMessage());

            List<ExamInDrawer> examsInDrawer = event.getMessage().getExams_list_from_server();


            if (event.getMessage().getMessage().equals("gave you the courses by subjects for teacher")) {
                List<Course> courses_from_server = event.getMessage().getCourses_list_from_server();
                Platform.runLater(() -> {
                    courses_list.setItems(FXCollections.observableArrayList(courses_from_server));
                    courses_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                });
            }


            if (event.getMessage().getMessage().equals("i gave you the teacher's subjects")) {
                List<Subject> subjects_from_server = event.getMessage().getSubjects_list_from_server();
                Platform.runLater(() -> {
                    subjects_list.setItems(FXCollections.observableArrayList(subjects_from_server));
                    subjects_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

                    teacher_subjects = subjects_from_server;
                });
            }
            if (event.getMessage().getMessage().equals("i gave you the teacher's courses")) {
                List<Course> courses_from_server = event.getMessage().getCourses_list_from_server();
                Platform.runLater(() -> {
                    courses_list.setItems(FXCollections.observableArrayList(courses_from_server));
                    courses_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

                    teacher_courses = courses_from_server;
                });
            }


            if (event.getMessage().getMessage().equals("i will give you the lecturers")) {

                List<Lecturer> lecturers_from_server = event.getMessage().getLecturers_list_from_server();

                Platform.runLater(() -> {
                    authors_list.setItems(FXCollections.observableArrayList(lecturers_from_server));
                    authors_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                });
            }

            if (event.getMessage().getMessage().equals("i gave you the filtered exams")) {
                examsInDrawer = event.getMessage().getExams_list_from_server();
                setTable(examsInDrawer);
            }
        }
    }


    private void filter() {

        this.range = (int) points_range_slider.getValue();
        this.selectedCourses = courses_list.getSelectionModel().getSelectedItems();
        this.selectedLecturers = authors_list.getSelectionModel().getSelectedItems();
        this.selectedSubjects = subjects_list.getSelectionModel().getSelectedItems();


        Message message = new Message("filterBy");
        message.setFilter_by_Subject(new ArrayList<>(this.selectedSubjects));
        message.setFilter_by_courses(new ArrayList<>(this.selectedCourses));
        message.setFilter_by_Lecturers(new ArrayList<>(this.selectedLecturers));
        message.setFilter_by_range(this.range);


        if (!authors_check_box.isSelected()) {
            message.setFilter_by_Lecturers(null);
            this.selectedLecturers = null;

        }

        if (!courses_check_box.isSelected()) {
            message.setFilter_by_courses(null);
            this.selectedCourses = null;

        }

        if (!subjects_check_box.isSelected()) {
            message.setFilter_by_Subject(null);
            this.selectedSubjects = null;
        }

        if (!range_check_box.isSelected()) {
            message.setFilter_by_range(-1);
            this.range = -1;
        }


        message.setLecturerId(this.getData().getTeacher_id());
        message.setController_name("TeacherSelectExamToImplementController");
        sendMessage(message);
    }


    @FXML
    void on_filter(ActionEvent event) {

        filter();
    }


    @Subscribe
    public void refresh(UpdateMessageEvent event) {

        System.out.println("refresh TeacherSelectExamToImplementController");
        System.out.println(event.getMessage().getMessage());

        if (event.getMessage().getController_name().equals("TeacherSelectExamToImplementController") && in_page) {
            System.out.println(event.getMessage().getMessage());

            if (event.getMessage().getMessage().equals("exam added check if it is related to you")) {

                boolean is_relevant = false;
                boolean is_filter = false;


                if (this.selectedSubjects == null && this.selectedCourses == null) {

                    for (Subject subject : this.teacher_subjects) {
                        if (subject.getSubject_code() == event.getMessage().getSubjectCode()) {
                            is_relevant = true;
                        }
                    }

                    if (is_relevant) {

                        is_relevant = false;

                        for (Course course : this.teacher_courses) {
                            if (course.getId() == event.getMessage().getCourse_id()) {
                                is_relevant = true;
                            }
                        }

                    }
                } else {

                    if (this.selectedSubjects != null && this.selectedCourses == null) {
                        for (Subject subject : this.selectedSubjects) {
                            if (subject.getSubject_code() == event.getMessage().getSubjectCode()) {
                                is_relevant = true;
                            }
                        }
                    } else if (this.selectedSubjects == null && this.selectedCourses != null) {
                        for (Course course : this.selectedCourses) {
                            if (course.getId() == event.getMessage().getCourse_id()) {
                                is_relevant = true;
                            }
                        }
                    } else { // two !null

                        is_relevant = false;
                        for (Subject subject : this.selectedSubjects) {
                            if (subject.getSubject_code() == event.getMessage().getSubjectCode()) {
                                is_relevant = true;
                            }
                        }
                        if (is_relevant) {
                            is_relevant=false;
                            for (Course course : this.selectedCourses) {
                                if (course.getId() == event.getMessage().getCourse_id()) {
                                    is_relevant = true;
                                }
                            }
                        }
                    }
                }


                if (is_relevant) {
                    is_filter = true;

                    if (this.selectedLecturers != null) {

                        boolean doFilter = false;

                        for (Lecturer lecturer : this.selectedLecturers) {
                            if (lecturer.getUser_id() == event.getMessage().getAuthor_id()) {
                                doFilter = true;
                            }
                        }

                        if (!doFilter) {
                            is_filter = false;
                        }

                    }
                    if (this.range != -1) {
                        if (event.getMessage().getExam_time() > this.range) {
                            is_filter = false;
                        }

                    }

                }


                if (is_filter) {

                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("message");
                        alert.setHeaderText(null);
                        alert.setContentText("exam added to the drawer you might want to see it");
                        alert.showAndWait();
                    });


                    Message message = new Message("filterBy");

                    if (!authors_check_box.isSelected()) {
                        message.setFilter_by_Lecturers(null);
                        this.selectedLecturers = null;

                    }else
                    {
                        message.setFilter_by_Lecturers(new ArrayList<>(this.selectedLecturers));
                    }

                    if (!courses_check_box.isSelected()) {
                        message.setFilter_by_courses(null);
                        this.selectedCourses = null;

                    }else {
                        message.setFilter_by_courses(new ArrayList<>(this.selectedCourses));
                    }

                    if (!subjects_check_box.isSelected()) {
                        message.setFilter_by_Subject(null);
                        this.selectedSubjects = null;
                    }else {
                        message.setFilter_by_Subject(new ArrayList<>(this.selectedSubjects));
                    }

                    if (!range_check_box.isSelected()) {
                        message.setFilter_by_range(-1);
                        this.range = -1;
                    }else {

                        message.setFilter_by_range(this.range);
                    }


                    message.setLecturerId(this.getData().getTeacher_id());
                    message.setController_name("TeacherSelectExamToImplementController");
                    sendMessage(message);

                }


            }
        }

    }


    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("TeacherSelectExamToImplementController");

        System.out.println("initialize1");

        in_page = true;


        if (this.getData().isExam_update()) {
            selectstudentsBT.setText("Update Exam");
        } else {
            selectstudentsBT.setText("Select Students");
        }

        Platform.runLater(() -> {
            points_range_slider.setMin(0);
            points_range_slider.setMax(500);
            points_range_slider.setBlockIncrement(1);
            points_range_slider.setValue(500);


            courses_list.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
            authors_list.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
            subjects_list.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);

            // Set up the columns
            exam_authoor_column.setCellValueFactory(new PropertyValueFactory<>("author_name"));
            exam_id_column.setCellValueFactory(new PropertyValueFactory<>("exam_code"));
            exam_subject_column.setCellValueFactory(new PropertyValueFactory<>("subject_name"));
            exam_course_column.setCellValueFactory(new PropertyValueFactory<>("course_name"));
            exam_time_column.setCellValueFactory(new PropertyValueFactory<>("exam_time"));
        });

        // Add a listener to the slider value property
        points_range_slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            range_label.setText(String.valueOf(newValue.intValue()));
        });


        subjects_list.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Subject>) change -> {
            ObservableList<Subject> selectedSubjects = subjects_list.getSelectionModel().getSelectedItems();
            if (!selectedSubjects.isEmpty()) {
                // Send a message to the server to get the courses for the selected subjects
                Message message = new Message("give me the courses by subjects for teacher");
                message.setLecturerId(this.getData().getTeacher_id());
                message.setSubjects(new ArrayList<>(selectedSubjects));
                message.setController_name("TeacherSelectExamToImplementController");
                sendMessage(message);
            }
        });


        Message message;

        if (!authors_check_box.isSelected()) {

            message = new Message("give me the lecturers");
            message.setController_name("TeacherSelectExamToImplementController");
            sendMessage(message);

        }

        if (!courses_check_box.isSelected()) {

            message = new Message("give me the courses for teacher");
            message.setLecturerId(this.getData().getTeacher_id());
            message.setController_name("TeacherSelectExamToImplementController");
            sendMessage(message);

        }

        if (!subjects_check_box.isSelected()) {
            message = new Message("give me the subjects for teacher");
            message.setLecturerId(this.getData().getTeacher_id());
            message.setController_name("TeacherSelectExamToImplementController");
            sendMessage(message);
        }

        if (!range_check_box.isSelected()) {
            points_range_slider.setValue(500);
        }

        filter();


    }


}
