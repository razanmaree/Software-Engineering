package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.QuestionInDrawer;
import javafx.application.Platform;
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

public class chooseQuestionToUpdateController extends PageController {

    @FXML
    private Button cancel_but;

    @FXML
    private Button display_but;

    @FXML
    private Button done_id;

    @FXML
    private TableView<QuestionInDrawer> table_id;
    @FXML
    private TableColumn<QuestionInDrawer, String> body_id;
    @FXML
    private TableColumn<QuestionInDrawer, String> fisrt_id;
    @FXML
    private TableColumn<QuestionInDrawer, String> second_id;
    @FXML
    private TableColumn<QuestionInDrawer, String> third_id;

    @FXML
    private TableColumn<QuestionInDrawer, String> fourth_id;
    @FXML
    private TableColumn<QuestionInDrawer, Integer> rightAnswer_id;


    private boolean in_page=true;

    @FXML
    void Cancel(ActionEvent event) {

        Platform.runLater(() -> {


            this.getData().setSelected_subject(null);
            this.getData().setSelected_course(null);
            this.getData().getChoosenQuestions().removeAll(this.getData().getChoosenQuestions());

            setWindowTitle("Exam Building");
            try {
                in_page=false;
                setContent("ExamBuilding",this.getData());

            }catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    @FXML
    void OnDisplayButClicked(ActionEvent event) {

        QuestionInDrawer question = table_id.getSelectionModel().getSelectedItem();

        if (question != null) {
            Platform.runLater(() -> {
                setWindowTitle("create A question");
                try {
                    this.getData().setSelected_question_id(question.getQuestionInDrawer_id());

                    in_page=false;
                    setContent("createAquestion", this.getData());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("you have to choose a question");
                alert.showAndWait();
            });

        }
    }



    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

        if (event.getMessage().getController_name().equals("chooseQuestionToUpdateController")) {
            System.out.println(event.getMessage().getMessage());
            if (event.getMessage().getMessage().equals("i gave you the questions by subject and course")) {

                List<QuestionInDrawer> questions = event.getMessage().getAllQuestions();

                table_id.getItems().clear();
                table_id.refresh();

                // Set up the columns
                body_id.setCellValueFactory(new PropertyValueFactory<>("Text"));

                fisrt_id.setCellValueFactory(new PropertyValueFactory<>("first_answer"));
                second_id.setCellValueFactory(new PropertyValueFactory<>("second_answer"));
                third_id.setCellValueFactory(new PropertyValueFactory<>("third_answer"));
                fourth_id.setCellValueFactory(new PropertyValueFactory<>("fourth_answer"));

                rightAnswer_id.setCellValueFactory(new PropertyValueFactory<>("rightAnswer"));

                for (int i = 0; i < questions.size(); i++) {
                    // Set the data to the table
                    //   System.out.println(questions.get(i).getText());
                    table_id.getItems().add(questions.get(i));
                }
            }
        }
    }

    @Subscribe
    public void refresh(UpdateMessageEvent event) {
        System.out.println("refresh chooseQuestionToUpdateController");
        System.out.println(event.getMessage().getMessage());

        if (event.getMessage().getController_name().equals("chooseQuestionToUpdateController") && in_page) {
            System.out.println(event.getMessage().getMessage());

            if (event.getMessage().getMessage().equals("a new question is added check if you can use it")) {

                if (event.getMessage().getSubject().getSubject_code() == this.getData().getSelected_subject().getSubject_code()) {

                    boolean flag=false;

                    for (Course course : event.getMessage().getCourses_to_question()){
                        if (course.getId() == this.getData().getSelected_course().getId()) {

                            flag=true;

                        }
                    }

                    if(flag)
                    {
                        Message message = new Message("give me the questions by subject and course");
                        message.setCourse_to_exam(this.getData().getSelected_course());
                        message.setSubjectCode(this.getData().getSelected_subject().getSubject_code());
                        message.setController_name("chooseQuestionToUpdateController");
                        sendMessage(message);
                    }
                }
            }
        }
    }


    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("chooseQuestionToUpdateController");

        in_page=true;

        System.out.println("initialize1 ChooseQuestionToUpdate");

        Message message = new Message("give me the questions by subject and course");
        message.setCourse_to_exam(this.getData().getSelected_course());
        message.setSubjectCode(this.getData().getSelected_subject().getSubject_code());
        message.setController_name("chooseQuestionToUpdateController");
        sendMessage(message);

    }

}
