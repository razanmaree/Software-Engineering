package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.QuestionInDrawer;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Score;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.SolvedQuestion;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class TestedExamController extends PageController {

    @FXML
    private Label grade_label;

    @FXML
    private Label grade_value_label;

    @FXML
    private Label student_id;

    @FXML
    private Label student_id_value;

    @FXML
    private Button SaveChangesBT;

    @FXML
    private Label addcommentsLAB;

    @FXML
    private TextField addcommentsTF;

    @FXML
    private Button backBT;

    @FXML
    private Label changegradeLAB;

    @FXML
    private TextField changegradeTF;

    @FXML
    private Label dot;

    @FXML
    private CheckBox first_check;

    @FXML
    private Label first_txtf;

    @FXML
    private CheckBox fourth_check;

    @FXML
    private Label fourth_txtf;

    @FXML
    private Button next_id;

    @FXML
    private Label points_label;

    @FXML
    private Label points_value_label;

    @FXML
    private Button previouse;

    @FXML
    private Label question_num_label;

    @FXML
    private Label rightAnswer_value_label1;

    @FXML
    private Label right_answer_label;

    @FXML
    private CheckBox second_check;

    @FXML
    private Label second_txtf;

    @FXML
    private Label text_id;

    @FXML
    private CheckBox third_check;

    @FXML
    private Label third_txtf;

    @FXML
    private Label points_label1;

    @FXML
    private Label points_value_label1;

    @FXML
    private Button ChangesBT;


    @FXML
    private Label teacher_comments_for_her;

    @FXML
    private Label teacher_comments_for_her1;

    @FXML
    private Label teacher_comments_for_students;

    @FXML
    private Label teacher_comments_for_students1;

    private int counter = 0;


    private List<SolvedQuestion> solvedQuestions = new ArrayList<>();

    private List<QuestionInDrawer> questions = new ArrayList<>();


    private void setQuestion(QuestionInDrawer question) {


        Platform.runLater(() -> {
            //  System.out.println("counter is : " + counter);

            question_num_label.setText("" + (counter + 1));
            text_id.setText((question.getText()));

            first_txtf.setText(question.getFirst_answer());
            second_txtf.setText(question.getSecond_answer());
            third_txtf.setText(question.getThird_answer());
            fourth_txtf.setText(question.getFourth_answer());

            points_value_label.setText(Integer.toString(solvedQuestions.get(counter).getScore()));
            points_value_label1.setText(Integer.toString(solvedQuestions.get(counter).getGrade()));
            rightAnswer_value_label1.setText(Integer.toString(questions.get(counter).getRightAnswer()));

        });

        first_check.setSelected(false);
        second_check.setSelected(false);
        third_check.setSelected(false);
        fourth_check.setSelected(false);

        if (solvedQuestions.get(counter).getAnswer() != 0) {
            //rr
            if (solvedQuestions.get(counter).getAnswer() == 1) {
                first_check.setSelected(true);
            } else if (solvedQuestions.get(counter).getAnswer() == 2) {
                second_check.setSelected(true);
            } else if (solvedQuestions.get(counter).getAnswer() == 3) {
                third_check.setSelected(true);
            } else if (solvedQuestions.get(counter).getAnswer() == 4) {
                fourth_check.setSelected(true);
            }
        }
    }

    @FXML
    void NEXT(ActionEvent event) {

        if (counter < this.solvedQuestions.size() - 1) {
            counter++;

            setQuestion(questions.get(this.counter));

            if (counter >= 1) {
                previouse.setVisible(true);
            }

            if (this.solvedQuestions.size() - 1 == counter) {
                next_id.setVisible(false);
            }
        }

    }

    @FXML
    void PREVIUOS(ActionEvent event) {

        if (counter > 0) {
            counter--;

            setQuestion(questions.get(this.counter));

            if (counter == 0) {
                previouse.setVisible(false);
            }

            if (this.solvedQuestions.size() - 1 > counter) {
                next_id.setVisible(true);
            }


        }

    }

    @FXML
    void on_Change_clicked(ActionEvent event) {

        try {
            int  grade =Integer.parseInt(changegradeTF.getText());

            if(grade<0 || grade>100)
            {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("message");
                    alert.setHeaderText(null);
                    alert.setContentText("grade must be between 0 and 100");
                    alert.showAndWait();
                });
                changegradeTF.setText("");
            }else {

               // String comments = addcommentsTF.getText();

                Message message = new Message("change solved exam grade");
                message.setSolved_exam_id(this.getData().getSolved_exam_id());
                message.setGrade_to_change(grade);
             //   message.setTeacher_comments(comments);

                message.setController_name("TestedExamController");

                sendMessage(message);

            }

        }catch (Exception e)
        {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("select an exam");
                alert.showAndWait();
            });
            changegradeTF.setText("");
        }

    }

    @FXML
    void on_SaveChanges_clicked(ActionEvent event) {

        String comments = addcommentsTF.getText();

        Message message = new Message("add comments to solve exam");
        message.setSolved_exam_id(this.getData().getSolved_exam_id());
        message.setTeacher_comments(comments);

        message.setController_name("TestedExamController");

        sendMessage(message);

    }

    @FXML
    void on_back_clicked(ActionEvent event) {

        Platform.runLater(() -> {
            setWindowTitle("teacher Check Exam By implementation Code");
            try {
                this.getData().setStudent_id(-1);
                this.getData().setSolved_exam_id(-1);
                setContent("teacherCheckExamByImplementionCode", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

        Platform.runLater(() -> {

            if (event.getMessage().getController_name().equals("TestedExamController")) {

                if (event.getMessage().getMessage().equals("got_solved_questions_and questions by_solvedExam_id")) {

                    solvedQuestions = event.getMessage().getSolvedQuestions();
                    questions = event.getMessage().getQuestion_list();

                    Platform.runLater(() -> {

                        grade_value_label.setText("" + event.getMessage().getSolved_exam_grade());
                        teacher_comments_for_students1.setText(""+event.getMessage().getStudents_comment());
                        teacher_comments_for_her1.setText(""+event.getMessage().getTeacher_comments());
                    });


                    if (this.solvedQuestions.size() != 0) {

                        System.out.println("hiiiiii0");

                        Visible(true);
                        next_id.setVisible(true);
                        previouse.setVisible(true);



                        setQuestion(questions.get(counter));

                        if (this.solvedQuestions.size() - 1 == counter) {
                            next_id.setVisible(false);
                        }

                        previouse.setVisible(false);


                    } else {

                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("message");
                            alert.setHeaderText(null);
                            alert.setContentText("there is no questions in this exam");
                            alert.showAndWait();
                        });

                        Visible(false);
                    }

                }
            }

            if(event.getMessage().getMessage().equals("solved exam grade Changed"))
            {
                grade_value_label.setText(""+event.getMessage().getGrade_to_change());
                changegradeTF.setText("");

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("message");
                    alert.setHeaderText(null);
                    alert.setContentText("the grade changed successfully");
                    alert.showAndWait();
                });

            }
            if(event.getMessage().getMessage().equals("comments were added"))
            {
                addcommentsTF.setText("");

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("message");
                    alert.setHeaderText(null);
                    alert.setContentText("your comment were added");
                    alert.showAndWait();
                });

            }




        });



    }


    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("TestedExamController");

        counter = 0;
        solvedQuestions = new ArrayList<>();
        questions = new ArrayList<>();

        student_id_value.setText(""+this.getData().getStudent_id());

        Visible(false);

        addcommentsTF.setText("");
        changegradeTF.setText("");

        first_check.setDisable(true);
        second_check.setDisable(true);
        third_check.setDisable(true);
        fourth_check.setDisable(true);


        Message message = new Message("give me solved questions and questions by soved_exam id");
        message.setController_name("TestedExamController");
        message.setSolved_exam_id(this.getData().getSolved_exam_id());
        sendMessage(message);


    }


    private void Visible(boolean visible) {


        question_num_label.setVisible(visible);
        text_id.setVisible(visible);
        first_txtf.setVisible(visible);
        second_txtf.setVisible(visible);
        third_txtf.setVisible(visible);
        fourth_txtf.setVisible(visible);
        first_check.setVisible(visible);
        second_check.setVisible(visible);
        third_check.setVisible(visible);
        fourth_check.setVisible(visible);
        previouse.setVisible(visible);
        next_id.setVisible(visible);
        dot.setVisible(visible);
        points_label1.setVisible(visible);
        points_value_label1.setVisible(visible);
        points_label.setVisible(visible);
        points_value_label.setVisible(visible);
        right_answer_label.setVisible(visible);
        rightAnswer_value_label1.setVisible(visible);

    }

}
