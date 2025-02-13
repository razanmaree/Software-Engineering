package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.QuestionInDrawer;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Subject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class ShowQuestionForMangerController extends PageController {
    @FXML
    private ComboBox<Subject> subjectComboBox;

    @FXML
    private Button backB;

    @FXML
    private TableColumn<QuestionInDrawer, String> questionBodyC;

    @FXML
    private TableColumn<QuestionInDrawer, String> firstAnswerC;

    @FXML
    private TableColumn<QuestionInDrawer, String> secondAnswerC;

    @FXML
    private TableColumn<QuestionInDrawer, String> thirdAnswerC;

    @FXML
    private TableColumn<QuestionInDrawer, String> fourthAnswerC;

    @FXML
    private TableColumn<QuestionInDrawer, Integer> rightAnswerC;

    @FXML
    private TableView<QuestionInDrawer> questionsT;

    private boolean in_page =true;


    @FXML
    void back(ActionEvent event) {
        Platform.runLater(() -> {
            setWindowTitle("Manger");
            try {
                this.getData().setSelected_course(null);
                this.getData().setSelected_subject(null);
                in_page =false;
                setContent("Manager", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void onClickSubjectCB (ActionEvent event) {
      //  System.out.println("onSubjectSelected");

        Subject selectedSubject = subjectComboBox.getSelectionModel().getSelectedItem();
        if (selectedSubject != null) {
            Message message = new Message("give me all the questions of this subject");
            message.setSubjectCode(selectedSubject.getSubject_code());
            message.setController_name("ShowQuestionForMangerController");
            sendMessage(message);
        } else {
            System.out.println("selected subject is null");
        }
    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {
    //    System.out.println("test test test 4");
        if(event.getMessage().getController_name().equals("ShowQuestionForMangerController"))
        {
         //   System.out.println(event.getMessage().getMessage());
       //     System.out.println("test test test 6");

            if (event.getMessage().getMessage().equals("i gave you all the subjects")) {
              //  System.out.println("test test test 7");

                List<Subject> subjects_from_server = event.getMessage().getSubjects_list_from_server();
             //   System.out.println("test test test 8");
//                for (Subject subject : subjects_from_server) {
//                    System.out.println("subject: " + subject.getSubject_name());
//                }
                Platform.runLater(() -> {
                    subjectComboBox.setItems(FXCollections.observableArrayList(subjects_from_server));
                });

            }
            if ((event.getMessage().getMessage().equals("i gave you the questions by subject"))) {
             //   System.out.println("test test test test test test test");
                List<QuestionInDrawer> questions_from_server = event.getMessage().getQuestions_list_from_server();
//                for (QuestionInDrawer question : questions_from_server) {
//                    System.out.println("question: " + question.getQuestionInDrawer_id());
//                }
                Platform.runLater(() -> {
                    questionsT.setItems(FXCollections.observableArrayList(questions_from_server));
                    setTable(questions_from_server);
                    questionsT.setVisible(true);
                });
            }
        }

    }

    private void setTable(List<QuestionInDrawer> questions) {
        questionsT.getItems().clear();
        questionsT.refresh();


        // Set up the columns
        questionBodyC.setCellValueFactory(new PropertyValueFactory<>("text"));
        firstAnswerC.setCellValueFactory(new PropertyValueFactory<>("first_answer"));
        secondAnswerC.setCellValueFactory(new PropertyValueFactory<>("second_answer"));
        thirdAnswerC.setCellValueFactory(new PropertyValueFactory<>("third_answer"));
        fourthAnswerC.setCellValueFactory(new PropertyValueFactory<>("fourth_answer"));
        rightAnswerC.setCellValueFactory(new PropertyValueFactory<>("rightAnswer"));

        // Populate the table
        questionsT.getItems().addAll(questions);
        questionsT.refresh();
    }

    //added razan 9-8
    @Subscribe
    public void refresh(UpdateMessageEvent event) {
        if (event.getMessage().getController_name().equals("ShowQuestionForMangerController")&&in_page) {
            System.out.println("Razan is here in controller show question maneger 0");

            if (event.getMessage().getMessage().equals("question added check if it is for you")) {
                System.out.println("Razan is here in controller show question maneger 1");

                if (event.getMessage().getSubjectCode() == subjectComboBox.getSelectionModel().getSelectedItem().getSubject_code()) {

                    Subject selectedSubject = subjectComboBox.getSelectionModel().getSelectedItem();
                    if (selectedSubject != null) {
                        Message message = new Message("give me all the questions of this subject");
                        message.setSubjectCode(selectedSubject.getSubject_code());
                        message.setController_name("ShowQuestionForMangerController");
                        sendMessage(message);
                    } else {
                        System.out.println("selected subject is null");
                    }

                }
            }
        }
    }
////////////////////////////////////

    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("ShowQuestionForMangerController");

        in_page =true;
        System.out.println("initialize ShowQuestionForManger");
        Message message = new Message("give me the subjects list for the manager");
        message.setController_name("ShowQuestionForMangerController");
        sendMessage(message);
        questionsT.setVisible(false);
    }
}
