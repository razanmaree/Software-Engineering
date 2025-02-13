package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.ExamExecution;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.ExamExecutionManual;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.WordFile;
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

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class TeacherGetSolvedWordFileController extends PageController {

    @FXML
    private Button btn_id;
    @FXML
    private Button back_id;
    @FXML
    private TableColumn<Student, Integer> ID_id;
    @FXML
    private TableColumn<Student, String> first_name_id;

    @FXML
    private TableColumn<Student, String> last_name_id;
    @FXML
    private TableView<Student> table;

    private List<ExamExecutionManual> examExecutionManual;

    private boolean in_page = true;

    @FXML
    void Back(ActionEvent event) {
        Platform.runLater(() -> {
            setWindowTitle("viewExamsToApprove");
            try {
                in_page = false;
                setContent("viewExamsToApprove", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void dawnloadButton(ActionEvent event) {
        Student selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Choose a Student !");
                alert.showAndWait();
            });
            return;
        }


        for(ExamExecutionManual exam_ex_manu:examExecutionManual)
        {
            if(exam_ex_manu.getStudent().getStudent_id()==selected.getStudent_id())
            {
                WordFile wordFile=exam_ex_manu.getWordFile();

                // Get the user's desktop path
                String desktopPath = System.getProperty("user.home") + "/Desktop";
                File desktopDir = new File(desktopPath);
                if (!desktopDir.exists()) {
                    desktopDir.mkdirs(); // Create the directory if it doesn't exist
                }

                //convert from array bytes to word file
                // Provide a specific file name and extension for the Word file
                String fileName = "word_file.docx"; // You can change the name and extension as needed
                File outputFile = new File(desktopDir, fileName);

                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    fos.write(wordFile.getFileContent());
                    System.out.println("Word file successfully written to: " + outputFile.getAbsolutePath());
                } catch (IOException e) {
                    System.err.println("Error writing Word file: " + e.getMessage());
                }

                // Open the directory where the generated Word file is located
                try {
                    //File directory = new File(desktopPath);
                    //Desktop.getDesktop().open(directory);
                    Desktop.getDesktop().open(desktopDir);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Failed to open the directory.");
                }

            }
        }

    }
    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {
        if (event.getMessage().getController_name().equals("TeacherGetSolvedWordFileController")) {
            if (event.getMessage().getMessage().equals("EXAMS EXECUTION MANUAL by link_id given")) {
                List<Student> students = new ArrayList<>();
                List<ExamExecutionManual> exams_exec_manual=event.getMessage().getExam_exec_manual();
                examExecutionManual=exams_exec_manual;
                for(ExamExecutionManual exam_ex:exams_exec_manual)
                {
                    students.add(exam_ex.getStudent());
                }

                //set the table
                Platform.runLater(() -> {
                    table.setItems(FXCollections.observableArrayList(students));
                    table.getItems().clear();
                    table.refresh();

                    // Set up the columns
                    ID_id.setCellValueFactory(new PropertyValueFactory<>("student_id"));
                    first_name_id.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                    last_name_id.setCellValueFactory(new PropertyValueFactory<>("lastName"));


                    for (int i = 0; i < students.size(); i++) {
                        // Set the data to the table
                        table.getItems().add(students.get(i));
                    }
                    table.refresh();
                });

            }

        }

    }

    @Subscribe
    public void refresh(UpdateMessageEvent event) {
        System.out.println("refresh TeacherGetSolvedWordFileController");
        System.out.println(event.getMessage().getMessage());

        if (event.getMessage().getController_name().equals("TeacherGetSolvedWordFileController") && in_page) {
            System.out.println(event.getMessage().getMessage());

            if (event.getMessage().getMessage().equals("manual exam execution have been ended check if you execute it")) {
                if (event.getMessage().getLecturerExamExecutionLink_id() == this.getData().getLecturerExamExecutionLink_id()) {
                    Message message = new Message("GIVE ME THE EXAMS EXECUTION MANUAL BY LINK ID");
                    message.setLink_id(this.getData().getLecturerExamExecutionLink_id());
                    message.setController_name("TeacherGetSolvedWordFileController");
                    sendMessage(message);
                }
            }
        }
    }

    @FXML
    void initialize1() {
        in_page = true;
        this.getData().setCurrentControllerName("TeacherGetSolvedWordFileController");

        int lecturerExamexecutionsLink_id=this.getData().getLecturerExamExecutionLink_id();

        Message message = new Message("GIVE ME THE EXAMS EXECUTION MANUAL BY LINK ID");
        message.setLink_id(lecturerExamexecutionsLink_id);
        message.setController_name("TeacherGetSolvedWordFileController");
        sendMessage(message);

    }
}
