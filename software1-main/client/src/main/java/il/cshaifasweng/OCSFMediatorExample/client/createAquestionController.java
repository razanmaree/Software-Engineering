package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.QuestionInDrawer;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Subject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.*;

public class createAquestionController extends PageController {

    @FXML
    private Button BACK_id;

    @FXML
    private TextField body_txf_id;

    @FXML
    private TextField first_answer_txf_id;

    @FXML
    private TextField fourth_answer_txf_id;

    @FXML
    private TextField second_answer_txf_id;
    @FXML
    private Button done_id;

    @FXML
    private TextField third_answer_txf_id;


    @FXML
    private ComboBox<Integer> rightAnswerComboBox;

    @FXML
    private ComboBox<Subject> subjectComboBox;

    @FXML
    private ListView<Course> courses_list;


    private List<Course> question_to_update_courses=new ArrayList<>();


    /////////////////////////

//    private PageData data = new PageData();
//
//    public void setData(PageData data) {
//        this.data = data;
//    }


    ////////////////////////////


    void sendMessage(Message message) {
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void BACK(ActionEvent event) {

        Platform.runLater(() -> {

            body_txf_id.setText("");
            first_answer_txf_id.setText("");
            second_answer_txf_id.setText("");
            third_answer_txf_id.setText("");
            fourth_answer_txf_id.setText("");

            rightAnswerComboBox.getSelectionModel().clearSelection();
            subjectComboBox.getSelectionModel().clearSelection();
            subjectComboBox.setItems(null);
            courses_list.getSelectionModel().clearSelection();

            courses_list.setItems(null);
            courses_list.refresh();

        });

        if (this.getData().isQuestion_update()) {

            Platform.runLater(() -> {
                setWindowTitle("choose Question To Update");
                try {
                    this.getData().setSelected_question_id(-1);
                    setContent("chooseQuestionToUpdate", this.getData());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {

            Platform.runLater(() -> {
                setWindowTitle("Teacher");
                try {
                    // dispose();
                    setContent("Teacher", this.getData());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }


    }

    @FXML
    void Done(ActionEvent event) {

        ObservableList<Course> selectedItems = courses_list.getSelectionModel().getSelectedItems();

        if (rightAnswerComboBox.getSelectionModel().getSelectedItem() == null ||
                subjectComboBox.getSelectionModel().getSelectedItem() == null
                || selectedItems.size() == 0
                || body_txf_id.getText().equals("") || first_answer_txf_id.getText().equals("")
                || second_answer_txf_id.getText().equals("") || third_answer_txf_id.getText().equals("")
                || fourth_answer_txf_id.getText().equals("")) {

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("one or more fields are missing");
                alert.show();
            });

        } else {
            Message message = new Message("CreateAquestion");

            message.setSubject(subjectComboBox.getSelectionModel().getSelectedItem());
            message.setRight_answer(rightAnswerComboBox.getSelectionModel().getSelectedItem());

            message.setCourses_to_question(new ArrayList<>(selectedItems));

            message.setBody(body_txf_id.getText());
            message.setFirst(first_answer_txf_id.getText());
            message.setSecond(second_answer_txf_id.getText());
            message.setThird(third_answer_txf_id.getText());
            message.setFourth(fourth_answer_txf_id.getText());
            message.setController_name("createAquestionController");
            sendMessage(message);
        }
    }


    @FXML
    public void onSubjectSelected(ActionEvent event) {
        System.out.println("selected");

        Subject selectedSubject = subjectComboBox.getSelectionModel().getSelectedItem();
        if (selectedSubject != null) {
            Message message = new Message("give Me The Subject Courses");

            message.setSubjectCode(subjectComboBox.getSelectionModel().getSelectedItem().getSubject_code());
            message.setLecturerId(this.getData().getTeacher_id());
            message.setController_name("createAquestionController");
            sendMessage(message);
        } else {
            // Handle the case when no subject is selected
        }

    }


    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

        if (event.getMessage().getController_name().equals("createAquestionController")) {

            System.out.println(event.getMessage().getMessage());

            if ((event.getMessage().getMessage().equals("i gave you the question by it's id"))) {
                QuestionInDrawer question = event.getMessage().getQuestion();
                Platform.runLater(() -> {

                    subjectComboBox.getSelectionModel().select(question.getSubject());

                    body_txf_id.setText(question.getText());
                    first_answer_txf_id.setText(question.getFirst_answer());
                    second_answer_txf_id.setText(question.getSecond_answer());
                    third_answer_txf_id.setText(question.getThird_answer());
                    fourth_answer_txf_id.setText(question.getFourth_answer());
                    rightAnswerComboBox.getSelectionModel().select(question.getRightAnswer() - 1);

                  ///  question_to_update_courses =new ArrayList<>(event.getMessage().getCourses_list_from_server());



                });

            }

            if ((event.getMessage().getMessage().equals("i gave you the courses by subject"))) {

                Platform.runLater(() -> {
                    List<Course> courses_from_server = event.getMessage().getCourses_list_from_server();

                    courses_list.setItems(FXCollections.observableArrayList(courses_from_server));
                    courses_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                });

//                if (this.getData().isQuestion_update()) {
//                    ObservableList<Course> coursesObservableList = FXCollections.observableArrayList(question_to_update_courses);
//                    courses_list.getSelectionModel().selectIndices(
//                            courses_list.getItems().indexOf(coursesObservableList.get(0)),
//                            courses_list.getItems().indexOf(coursesObservableList.get(coursesObservableList.size() - 1)) + 1
//                    );
//                }


            }

            if (event.getMessage().getMessage().equals("DoneCreateTheQuestion")) {

                body_txf_id.setText("");
                first_answer_txf_id.setText("");
                second_answer_txf_id.setText("");
                third_answer_txf_id.setText("");
                fourth_answer_txf_id.setText("");

                Platform.runLater(() -> {
                    rightAnswerComboBox.getSelectionModel().clearSelection();
                    subjectComboBox.getSelectionModel().clearSelection();
                    courses_list.getSelectionModel().clearSelection();
                    subjectComboBox.setItems(null);
                    courses_list.setItems(null);
                    courses_list.refresh();

                });

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("question added to the drawer");

                    alert.show();
                });



                if (this.getData().isQuestion_update()) {

                    Platform.runLater(() -> {
                        setWindowTitle("choose Question To Update");
                        try {
                            body_txf_id.setText("");
                            first_answer_txf_id.setText("");
                            second_answer_txf_id.setText("");
                            third_answer_txf_id.setText("");
                            fourth_answer_txf_id.setText("");

                            Platform.runLater(() -> {
                                rightAnswerComboBox.getSelectionModel().clearSelection();
                                subjectComboBox.getSelectionModel().clearSelection();
                                courses_list.getSelectionModel().clearSelection();
                                subjectComboBox.setItems(null);
                                courses_list.setItems(null);
                                courses_list.refresh();

                            });

                            this.getData().setSelected_question_id(-1);
                            setContent("chooseQuestionToUpdate", this.getData());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                }else {
                    initialize1();
                }

            }

            if (event.getMessage().getMessage().equals("i gave you the teacher's subjects")) {

                List<Subject> subjects_from_server = event.getMessage().getSubjects_list_from_server();
                Platform.runLater(() -> {
                    subjectComboBox.setItems(FXCollections.observableArrayList(subjects_from_server));
                });
            }

        }


    }

    @FXML
    public void initialize1() {

        this.getData().setCurrentControllerName("createAquestionController");

        System.out.println("initialize1 createAQuestion");

        System.out.println(this.getData().getTeacher_id());

        Platform.runLater(() -> {
            done_id.setText("Done");


            Message message = new Message("give me the subjects for teacher");
            message.setLecturerId(this.getData().getTeacher_id());
            message.setController_name("createAquestionController");
            sendMessage(message);


            List<Integer> options = new ArrayList<>();
            options.add(1);
            options.add(2);
            options.add(3);
            options.add(4);

            rightAnswerComboBox.setItems(FXCollections.observableArrayList(options));

            if (this.getData().isQuestion_update()) {
                message = new Message("give me the question by it's id");
                message.setQuestion_number(getData().getSelected_question_id());
                message.setController_name("createAquestionController");
                sendMessage(message);

                done_id.setText("update");
            }
        });
    }


//    @FXML
//    public void initialize() {
//
//        System.out.println("initialize createAQuestion");
//
//        if (!EventBus.getDefault().isRegistered(this)) {
//
//            System.out.println("register");
//            EventBus.getDefault().register(this);
//        }
//
//
//    }

}