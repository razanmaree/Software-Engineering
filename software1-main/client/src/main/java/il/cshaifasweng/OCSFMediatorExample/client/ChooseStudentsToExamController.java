package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class ChooseStudentsToExamController extends PageController {

    @FXML
    private AnchorPane SelectAllBu;

    @FXML
    private CheckBox SelectAllCheckBox;

    @FXML
    private RadioButton computerizedexamRB;

    @FXML
    private Button executeBT;

    @FXML
    private Button executeBT1;

    @FXML
    private RadioButton manualexamRB;

    @FXML
    private TableColumn<Student, String> studentFirstNameColumn;

    @FXML
    private TableColumn<Student, Integer> studentIdColumn;

    @FXML
    private TableColumn<Student, String> studentLastNameColumn;

    @FXML
    private TableView<Student> students_list_to_exam;

    @FXML
    private Label typeoftheexamLAB;

    @FXML
    private TextField execution_code_tf;

    private ObservableList<Student> selectedStudents = FXCollections.observableArrayList();

    private void setTable(List<Student> students) {
        students_list_to_exam.getItems().clear();
        students_list_to_exam.refresh();

        // Set up the columns
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        studentFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        studentLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));


        for (int i = 0; i < students.size(); i++) {
            // Set the data to the table
            students_list_to_exam.getItems().add(students.get(i));
        }
        students_list_to_exam.refresh();
    }


    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

        if (event.getMessage().getController_name().equals("ChooseStudentsToExamController")) {
            System.out.println(event.getMessage().getMessage());

            if (event.getMessage().getMessage().equals("i gave you all the students that learn this course")) {

                List<Student> students = event.getMessage().getStudents_list_by_course();
//
//                for (Student student : students) {
//                    System.out.println("student ID: " + student.getStudent_id());
//                }
                Platform.runLater(() -> {
                    students_list_to_exam.setItems(FXCollections.observableArrayList(students));
                    setTable(students);

                });
            } else if (event.getMessage().getMessage().equals("examExecution added to students list")) {

                //  System.out.println("we are here !!!");

                Platform.runLater(() -> {

                    students_list_to_exam.getSelectionModel().clearSelection();
                    manualexamRB.setSelected(false);
                    computerizedexamRB.setSelected(false);
                    SelectAllCheckBox.setSelected(false);

                    execution_code_tf.setText("");

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("the exam you selected have been executed");
                    alert.showAndWait();


                    setWindowTitle("Teacher");
                    try {


                        students_list_to_exam.getSelectionModel().clearSelection();
                        students_list_to_exam.setItems(null);

                        manualexamRB.setSelected(false);
                        computerizedexamRB.setSelected(false);
                        SelectAllCheckBox.setSelected(false);

                        execution_code_tf.setText("");

                        // dispose();
                        setContent("Teacher", this.getData());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });


            }

        }
    }


    @FXML
    void OnCancelClicked(ActionEvent event) {
        //DONE
        //the list of the selected students will be null
        Platform.runLater(() -> {
            setWindowTitle("Teacher Select Exam To Implement");
            try {
                //  this.getData().getSelectedStudents().removeAll(this.getData().getSelectedStudents());
                students_list_to_exam.getSelectionModel().clearSelection();
                students_list_to_exam.setItems(null);

                manualexamRB.setSelected(false);
                computerizedexamRB.setSelected(false);
                SelectAllCheckBox.setSelected(false);
                execution_code_tf.setText("");

                this.getData().setSelected_exam_id(-1);
                // dispose();
                setContent("TeacherSelectExamToImplement", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void OnExecuteClicked(ActionEvent event) {
        //here we must check if student are selected, if not send error message
        ObservableList<Student> selectedStudents = students_list_to_exam.getSelectionModel().getSelectedItems();

        if (selectedStudents.size() == 0) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("you did not select any student");
                alert.showAndWait();
            });
            return;
        }
        //DONE
        //check if the user choose a way to implement the exam, if not send error message
        if (!computerizedexamRB.isSelected() && !manualexamRB.isSelected()) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("you did not select execution way");
                alert.showAndWait();
            });
            return;
        }


        String codeStr = execution_code_tf.getText();
        if (codeStr.length() == 4) {
            Message message = new Message("Add examExecution to students list");

            message.setSelectedexam_id(this.getData().getSelected_exam_id());
            message.setLecturerId(this.getData().getTeacher_id());

            if (computerizedexamRB.isSelected()) {
                message.setExam_type("digital");
            } else {
                message.setExam_type("Manual");
            }

            message.setStudents_list_by_course(new ArrayList<>(selectedStudents));

            message.setExam_execution_code(codeStr);

            message.setController_name("ChooseStudentsToExamController");

            sendMessage(message);


        } else {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("execution code must be 4 characters");
                alert.showAndWait();
            });
            execution_code_tf.setText("");
        }
    }


    @FXML
    void SelectAll(ActionEvent event) {
        //DONE
        // Check if the CheckBox is selected
        if (SelectAllCheckBox.isSelected()) {
            // Add all students in the table to the selectedStudents list
            selectedStudents.removeAll(selectedStudents);
            selectedStudents.addAll(students_list_to_exam.getItems());

            // Select all rows in the TableView
            students_list_to_exam.getSelectionModel().selectAll();
        } else {
            // If the CheckBox is not selected, clear the selectedStudents list
            selectedStudents.removeAll(selectedStudents);

            // Clear the selection in the TableView
            students_list_to_exam.getSelectionModel().clearSelection();
        }
    }


    @FXML
    void on_computerizedexamRB_selected(ActionEvent event) {
        if (computerizedexamRB.isSelected()) {

            manualexamRB.setDisable(true);
        } else {

            manualexamRB.setDisable(false);
        }
    }

    @FXML
    void on_manualexamRB_selected(ActionEvent event) {

        if (manualexamRB.isSelected()) {

            computerizedexamRB.setDisable(true);
        } else {

            computerizedexamRB.setDisable(false);
        }
    }



    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("ChooseStudentsToExamController");

        System.out.println("initialize1");
        computerizedexamRB.setDisable(false);
        manualexamRB.setDisable(false);

        students_list_to_exam.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // students_list_to_exam.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        Message message = new Message("give me the students by course");
        message.setController_name("ChooseStudentsToExamController");
        message.setSelectedexam_id(this.getData().getSelected_exam_id());

        sendMessage(message);

    }
}














