package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.QuestionInDrawer;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Score;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Subject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.*;

public class ChooseQuestionsController extends PageController {

    @FXML
    private Button add_id;

    @FXML
    private Button done_id;

    @FXML
    private Button display_but;
    @FXML
    private Label score_id;

    @FXML
    private TextField txf_id;

    @FXML
    private Label total_score_label;


    @FXML
    private Button cancel_but;

    @FXML
    private TextField examTimeTf;

    @FXML
    private TextField teacherCommentTf;


    @FXML
    private TextField studentCommentTf;

    private boolean in_page = true;

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


    @FXML
    void Cancel(ActionEvent event) {

        Platform.runLater(() -> {


            this.getData().setSelected_subject(null);
            this.getData().setSelected_course(null);
            this.getData().getChoosenQuestions().removeAll(this.getData().getChoosenQuestions());


            examTimeTf.setText("");
            studentCommentTf.setText("");
            teacherCommentTf.setText("");
            txf_id.setText("");

            if (this.getData().isExam_update()) {
                Platform.runLater(() -> {
                    setWindowTitle("Teacher select exam to update");
                    try {
                        in_page = false;
                        setContent("TeacherSelectExamToImplement", this.getData());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                setWindowTitle("Exam Building");
                try {
                    in_page = false;
                    setContent("ExamBuilding", this.getData());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @FXML
    void OnDisplayButClicked(ActionEvent event) {

        Platform.runLater(() -> {
            setWindowTitle("chosen Questions Display");
            try {
                in_page = false;
                setContent("choosenQuestionsDisplay", this.getData());


            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    @FXML
    void ADD(ActionEvent event) {

        QuestionInDrawer question = table_id.getSelectionModel().getSelectedItem();
        int score = 0;

        if (table_id.getSelectionModel().getSelectedItem() == null) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a question to add");
                alert.showAndWait();
            });
        } else {
            try {
                score = Integer.parseInt(txf_id.getText());

            } catch (NumberFormatException e) {

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter an integer value.");
                    alert.showAndWait();
                });


                txf_id.clear();
                return;
            }

            if (score <= 0) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter value bigger than 0");
                    alert.showAndWait();
                });
                txf_id.clear();
                return;
            }

            for (QuestionInDrawer ques : this.getData().getChoosenQuestions()) {
                if (question.getQuestionInDrawer_id() == ques.getQuestionInDrawer_id()) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText(null);
                        alert.setContentText("you have selected this question!!");
                        alert.showAndWait();
                    });

                    txf_id.clear();
                    Platform.runLater(() -> {
                        table_id.getSelectionModel().clearSelection();
                    });
                    return;

                }
            }


            //   System.out.println("jhgfdcfvgbhnjhbgvfgvbhnjhbgvfg");

            question.setTemporary_score(score);
            this.getData().getChoosenQuestions().add(question);

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("QuestionAdded successfully");
                alert.showAndWait();
            });

            Platform.runLater(() -> {
                table_id.getSelectionModel().clearSelection();
            });
            txf_id.clear();
            Total_score_update();

        }
    }

    @FXML
    void DONE(ActionEvent event) {

        int total_score = 0;
        for (QuestionInDrawer question : this.getData().getChoosenQuestions()) {
            total_score += question.getTemporary_score();
        }

        if (total_score != 100) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("the total score of the exam is not 100");
                alert.showAndWait();
            });

            //we will move to the page of display the choosen questions

        } else {

            int time;

            try {
                time = Integer.parseInt(examTimeTf.getText());

            } catch (NumberFormatException e) {

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter an integer value for the time.");
                    alert.showAndWait();
                });

                examTimeTf.clear();
                return;
            }

            if (time <= 0 || time > 300) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter time between 0 and 300.");
                    alert.showAndWait();
                });

            } else {

                Message message = new Message("add an exam");

                message.setQuestions_to_exam(this.getData().getChoosenQuestions());
                message.setSubject(this.getData().getSelected_subject());
                message.setCourse_to_exam(this.getData().getSelected_course());
                message.setExam_time(time);
                message.setAuthor_id(this.getData().getTeacher_id());
                message.setTeacher_comments(teacherCommentTf.getText());
                message.setStudents_comment(studentCommentTf.getText());

                message.setController_name("ChooseQuestionsController");
                sendMessage(message);


            }
        }
    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

        if (event.getMessage().getController_name().equals("ChooseQuestionsController")) {
            System.out.println(event.getMessage().getMessage());

            if (event.getMessage().getMessage().equals("gave you the exam data by exam_id")) {

                List<QuestionInDrawer> questions = new ArrayList<>();

                for (Score score : event.getMessage().getScores()) {
                    score.getQuestion().setTemporary_score(score.getScore());
                    questions.add(score.getQuestion());

                }

                this.getData().setChoosenQuestions(questions);

                Platform.runLater(() -> {
                    studentCommentTf.setText(event.getMessage().getStudents_comment());
                    teacherCommentTf.setText(event.getMessage().getTeacher_comments());
                    examTimeTf.setText("" + event.getMessage().getExam_time());
                });

                Total_score_update();

            }

            if (event.getMessage().getMessage().equals("i gave you the questions by subject and course")) {

                List<QuestionInDrawer> questions = event.getMessage().getAllQuestions();

                //System.out.println("there is "+questions.size()+" questions of this subject and course");


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
            if (event.getMessage().getMessage().equals("i added the exam")) {
                Platform.runLater(() -> {

                    this.getData().setSelected_subject(null);
                    this.getData().setSelected_course(null);
                    this.getData().getChoosenQuestions().removeAll(this.getData().getChoosenQuestions());

                    examTimeTf.setText("");
                    studentCommentTf.setText("");
                    teacherCommentTf.setText("");


                    if (this.getData().isExam_update()) {

                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("message");
                            alert.setHeaderText(null);
                            alert.setContentText("exam updated successfully.");
                            alert.showAndWait();
                        });

                        Platform.runLater(() -> {
                            setWindowTitle("Teacher select exam to update");
                            try {
                                setContent("TeacherSelectExamToImplement", this.getData());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    } else {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("message");
                            alert.setHeaderText(null);
                            alert.setContentText("exam added to the drawer.");
                            alert.showAndWait();
                        });

                        Platform.runLater(() -> {
                            setWindowTitle("Exam Building");
                            try {
                                setContent("ExamBuilding", this.getData());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                    }
                });

            }
        }
    }


    private void Total_score_update() {
        Platform.runLater(() -> {
            int tot_score = 0;

            if (this.getData().getChoosenQuestions() != null) {
                for (QuestionInDrawer question : this.getData().getChoosenQuestions()) {
                    tot_score += question.getTemporary_score();
                }
            }
            total_score_label.setText("" + tot_score);
        });

    }

    @Subscribe
    public void refresh(UpdateMessageEvent event) {
        System.out.println("refresh ChooseQuestionsController");
        System.out.println(event.getMessage().getMessage());

        if (event.getMessage().getController_name().equals("ChooseQuestionsController") && in_page) {
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
                        message.setController_name("ChooseQuestionsController");
                        sendMessage(message);
                    }
                }
            }
        }
    }



    @FXML
    void initialize1() {

        in_page = true;
        this.getData().setCurrentControllerName("ChooseQuestionsController");

        System.out.println("initialize1 ChooseQuestions");

        if (!this.getData().isExam_update()) {
            Platform.runLater(() -> {
                done_id.setText("Add");
            });

        }

        if (this.getData().isExam_update() && this.getData().isFirst_time_enter_choose_questions()) {
            this.getData().setFirst_time_enter_choose_questions(false);
            Platform.runLater(() -> {
                done_id.setText("Update");
            });

            Message message = new Message("give me the exam data by exam_id");
            message.setSelectedexam_id(getData().getSelected_exam_id());
            message.setController_name("ChooseQuestionsController");
            sendMessage(message);

        }

        Total_score_update();


        Message message = new Message("give me the questions by subject and course");

        message.setCourse_to_exam(this.getData().getSelected_course());
        message.setSubjectCode(this.getData().getSelected_subject().getSubject_code());

        message.setController_name("ChooseQuestionsController");
        sendMessage(message);

    }


}