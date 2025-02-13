package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.QuestionInDrawer;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Score;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class ExamBodyController extends PageController {


    @FXML
    private Label teacher_comments_for_her;

    @FXML
    private Label teacher_comments_for_her1;

    @FXML
    private Label teacher_comments_for_students;

    @FXML
    private Label teacher_comments_for_students1;


    @FXML
    private Button show_exam_id_but;


    @FXML
    private Label answer1_LABEL;

    @FXML
    private Label points_label;

    @FXML

    private Label question_text;

    @FXML
    private Label answer2_LABEL;

    @FXML
    private Label answer3_LABEL;

    @FXML
    private Label answer4_LABEL;

    @FXML
    private Label answer_NUM_1_LABEL;

    @FXML
    private Label answer_NUM_2_LABEL;

    @FXML
    private Label answer_NUM_3_LABEL;

    @FXML
    private Label answer_NUM_4_LABEL;
    @FXML
    private Label right_answer;

    @FXML
    private Label exam_id_label;

    @FXML
    private Label points_value_label;

    @FXML
    private Label right_answer_LABEL;

    @FXML
    private Button next_question_but;

    @FXML
    private Button return_but;

    @FXML
    private Button prev_question;

    @FXML
    private Label queston_text;

    private int counter = 0;

    private List<Score> scores_list = new ArrayList<>();




    private void setQuestion(QuestionInDrawer question) {


        Platform.runLater(() -> {
          //  System.out.println("counter is : " + counter);

            exam_id_label.setText("Question Number." + (counter + 1));


            answer_NUM_1_LABEL.setText(question.getFirst_answer());
            answer_NUM_2_LABEL.setText(question.getSecond_answer());
            answer_NUM_3_LABEL.setText(question.getThird_answer());
            answer_NUM_4_LABEL.setText(question.getFourth_answer());
            queston_text.setText((question.getText()));
            right_answer.setText(Integer.toString(question.getRightAnswer()));
            points_value_label.setText(Integer.toString(scores_list.get(counter).getScore()));
        });
    }


    @FXML
    void on_show_next_question(ActionEvent event) {

      //  System.out.println("on_show_next_question");

        if (counter < this.scores_list.size() - 1) {
            counter++;

            setQuestion(scores_list.get(this.counter).getQuestion());

            if (counter >= 1) {
                prev_question.setVisible(true);
            }

            if (this.scores_list.size() - 1 == counter) {
                next_question_but.setVisible(false);
            }


        }
//
//
//
//
//        setQuestion(scores_list.get(this.counter).getQuestion(), () -> {
//
//            if (this.scores_list.size() == counter) {
//                next_question_but.setVisible(false);
//            }
//
//            if (counter >= 1) {
//                prev_question.setVisible(true);
//            }
//        });

    }

    @FXML
    void on_show_prev_question(ActionEvent event) {

     //   System.out.println("on_show_prev_question");


        if (counter > 0) {
            counter--;

            setQuestion(scores_list.get(this.counter).getQuestion());

            if (counter == 0) {
                prev_question.setVisible(false);
            }

            if (this.scores_list.size() - 1 > counter) {
                next_question_but.setVisible(true);
            }


        }
//
//        setQuestion(scores_list.get(this.counter).getQuestion(), () -> {
//            // This code will be executed after the setQuestion method is completed
//
//            if (counter == 0) {
//                prev_question.setVisible(false);
//            }
//
//            if (this.scores_list.size() - 1 > counter) {
//                next_question_but.setVisible(true);
//            }
//        });

    }


    @FXML
    void on_show_return(ActionEvent event) {

        this.getData().setSelected_exam_id(-1);

        exam_id_label.setVisible(true);
        question_text.setVisible(true);

        answer1_LABEL.setVisible(true);
        answer2_LABEL.setVisible(true);
        answer3_LABEL.setVisible(true);
        answer4_LABEL.setVisible(true);

        answer_NUM_1_LABEL.setVisible(true);
        answer_NUM_2_LABEL.setVisible(true);
        answer_NUM_3_LABEL.setVisible(true);
        answer_NUM_4_LABEL.setVisible(true);

        right_answer_LABEL.setVisible(true);
        right_answer.setVisible(true);
        points_value_label.setVisible(true);
        points_label.setVisible(true);

        prev_question.setVisible(true);
        next_question_but.setVisible(true);

        if((this.getData().getFlag_IAmInExamRepositoryForManager()==1))
        {
            Platform.runLater(() -> {
                setWindowTitle("Exams Repository For Maneger");
                try {
                    // dispose();
                    this.getData().setFlag_IAmInExamRepositoryForManager(0);
                    setContent("ExamsRepositoryForManeger", this.getData());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }else {

            if(this.getData().isTeacherSelectExamToImplement())
            {
                Platform.runLater(() -> {
                    setWindowTitle("Teacher Select Exam To Implement");
                    try {
                        // dispose();
                        setContent("TeacherSelectExamToImplement", this.getData());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            else
            {
                if(this.getData().isExam_update())
                {
                    Platform.runLater(() -> {
                        setWindowTitle("Teacher Select Exam To Implement");
                        try {
                            // dispose();
                            setContent("TeacherSelectExamToImplement", this.getData());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                }
                else {
                    this.getData().setFlag(true);

                    Platform.runLater(() -> {
                        setWindowTitle("View Courses Data Lecturer");
                        try {
                            setContent("ViewCoursesDataLecturer", this.getData());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }


            }



        }




    }


    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

        if(event.getMessage().getController_name().equals("ExamBodyController"))
        {

            if (event.getMessage().getMessage().equals("get the scores list of the selected exam")) {
                // question=datan.getExam_selected().getQuestios().get(getCounter()+1);

                this.scores_list = event.getMessage().getScores();

                Platform.runLater(() -> {

                    teacher_comments_for_her1.setText(event.getMessage().getTeacher_comments());
                    teacher_comments_for_students1.setText(event.getMessage().getStudents_comment());
                });

                if (this.scores_list.size() != 0) {

                  //  System.out.println("set data from server");

                    setQuestion(scores_list.get(this.counter).getQuestion());
                    // This code will be executed after the setQuestion method is completed

                    if (this.scores_list.size() - 1 == counter) {
                        next_question_but.setVisible(false);
                    }

                    prev_question.setVisible(false);


                } else {

                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("message");
                        alert.setHeaderText(null);
                        alert.setContentText("there is no questions in this exam");
                        alert.showAndWait();
                    });

                    exam_id_label.setVisible(false);
                    question_text.setVisible(false);
                    queston_text.setVisible(false);

                    answer1_LABEL.setVisible(false);
                    answer2_LABEL.setVisible(false);
                    answer3_LABEL.setVisible(false);
                    answer4_LABEL.setVisible(false);

                    answer_NUM_1_LABEL.setVisible(false);
                    answer_NUM_2_LABEL.setVisible(false);
                    answer_NUM_3_LABEL.setVisible(false);
                    answer_NUM_4_LABEL.setVisible(false);

                    right_answer_LABEL.setVisible(false);
                    right_answer.setVisible(false);
                    points_value_label.setVisible(false);
                    points_label.setVisible(false);

                    prev_question.setVisible(false);
                    next_question_but.setVisible(false);


                    ///there is no questions in this exam
                }

        }


        }

    }

    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("ExamBodyController");

        exam_id_label.setWrapText(true);
        this.counter = 0;

      //  System.out.println("the exam code is: " + this.getData().getSelected_exam_id());

        Message message = new Message(0, "give me the question's list of the selected exam");
        message.setSelectedexam_id(getData().getSelected_exam_id());
        message.setController_name("ExamBodyController");
        sendMessage(message);


    }


}

