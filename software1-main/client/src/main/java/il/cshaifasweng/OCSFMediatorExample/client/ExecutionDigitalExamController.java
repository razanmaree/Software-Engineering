package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class ExecutionDigitalExamController extends PageController {

    @FXML
    private Button BACK_id;

    @FXML
    private TextField ID_txf_id;

    @FXML
    private TextField comments_txf_id;

    @FXML
    private TextField exec_code_txf_id;


    @FXML
    private Label teacher_comments_label;

    @FXML
    private Label teacher_comments_label1;


    @FXML
    private Button first_done_bt_id;

    @FXML
    private Button second_done_bt;

    @FXML
    private Button submit_txf_id;
//    @FXML
//   // private Button start_btn_id;

    //------ADDED------------------------
    @FXML
    private Label dot;

    @FXML
    private Label remaining_time_label;

    @FXML
    private Label points_label;

    @FXML
    private Label points_value_label;

    @FXML
    private Label comments_label;
    @FXML
    private Label text_id;
    @FXML
    private Label question_num_label;
    @FXML
    private CheckBox first_check;
    @FXML
    private CheckBox second_check;
    @FXML
    private CheckBox third_check;

    @FXML
    private Label first_txtf;
    @FXML
    private Label second_txtf;
    @FXML
    private Label third_txtf;
    @FXML
    private CheckBox fourth_check;
    @FXML
    private Label fourth_txtf;

    @FXML
    private Button next_id;
    @FXML
    private Button previouse;


    @FXML
    private Label enter_id_label;


    private int counter = 0;


    private List<SolvedQuestion> solvedQuestions = new ArrayList<>();

    private List<Score> scores = new ArrayList<>();


    private int duration;

    private int timeLeft = 0;

    private boolean isSelfSubmitted = false;

    private boolean hasSubmitted = false;

    private ScheduledExecutorService executorService;

    private void handleTimeExtensionRequest(int extensionTimeMinutes) {
        int extensionTimeMilliseconds = extensionTimeMinutes * 60 * 1000; // Convert minutes to milliseconds
        duration += extensionTimeMinutes;
        timeLeft += extensionTimeMilliseconds;
        updateRemainingTimeLabel();

        if (executorService != null) {
            executorService.shutdownNow();
        }
        // Schedule a new exam submission with the updated time left
        scheduleExamSubmission(timeLeft);
    }


    private void scheduleExamSubmission(int timeLeftInMilliseconds) {
        executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(this::submitExamAutomatically, timeLeftInMilliseconds, TimeUnit.MILLISECONDS);
    }


    private void updateRemainingTimeLabel() {


        // System.out.println("here");

        long minutesLeft = timeLeft / (60 * 1000);
        long secondsLeft = (timeLeft % (60 * 1000)) / 1000;
        String timeLeftFormatted = String.format("%02d:%02d:%02d", minutesLeft / 60, minutesLeft % 60, secondsLeft);

        if (timeLeft <= 0) {
            Platform.runLater(() -> {
                remaining_time_label.setText("Time's up!");
            });
        } else {
            Platform.runLater(() -> {
                remaining_time_label.setText("Time left: " + timeLeftFormatted);
            });


        }
    }

    private void startTimerToUpdateRemainingTime() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Decrement the time left by one second
                timeLeft -= 1000; // One second in milliseconds
                // Update the remaining_time_label
                Platform.runLater(ExecutionDigitalExamController.this::updateRemainingTimeLabel);

                // Check if time is up
                if (timeLeft <= 0) {
                    // Invoke the automatic submission
                    submitExamAutomatically();

                    // Stop the timer
                    timer.cancel();
                }
            }
        };
        // Schedule the task to run every second
        timer.schedule(task, 1000, 1000); // One second in milliseconds
    }


    private void submitExamAutomatically() {

        if (!hasSubmitted) {

            if (!isSelfSubmitted) {
                isSelfSubmitted = false;

                Platform.runLater(() -> {
                    Message message = new Message(0, "add solved exam");
                    message.setController_name("ExecutionDigitalExamController");

                    message.setStudents_comment(comments_txf_id.getText());
                    message.setExecution_id(this.getData().getExamExecutionId());
                    message.setSolvedQuestions(solvedQuestions);

                    message.setIs_self_submit(false);
                    message.setExam_duration(duration);

                    sendMessage(message);
                });

                // updateRemainingTimeLabel();
            }
        }


    }


    @FXML
    void on_first_chicked(ActionEvent event) {


        if (second_check.isSelected() || third_check.isSelected() || fourth_check.isSelected()) {
            first_check.setSelected(false);

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("you can only select one answer");
                alert.showAndWait();
            });
        } else {
            solvedQuestions.get(counter).setAnswer(1);
        }

    }

    @FXML
    void on_second_chicked(ActionEvent event) {
        if (third_check.isSelected() || fourth_check.isSelected() || first_check.isSelected()) {
            second_check.setSelected(false);
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("you can only select one answer");
                alert.showAndWait();
            });
        } else {
            solvedQuestions.get(counter).setAnswer(2);
        }

    }


    @FXML
    void on_fthird_chicked(ActionEvent event) {

        if (second_check.isSelected() || fourth_check.isSelected() || first_check.isSelected()) {
            third_check.setSelected(false);

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("you can only select one answer");
                alert.showAndWait();
            });
        } else {
            solvedQuestions.get(counter).setAnswer(3);
        }

    }


    @FXML
    void on_fourth_chicked(ActionEvent event) {

        if (second_check.isSelected() || third_check.isSelected() || first_check.isSelected()) {
            fourth_check.setSelected(false);
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("you can only select one answer");
                alert.showAndWait();
            });
        } else {
            solvedQuestions.get(counter).setAnswer(4);
        }


    }


    @FXML
    void NEXT(ActionEvent event) {


        if (counter < this.scores.size() - 1) {
            counter++;

            setQuestion(scores.get(this.counter).getQuestion());

            if (counter >= 1) {
                previouse.setVisible(true);
            }

            if (this.scores.size() - 1 == counter) {
                next_id.setVisible(false);
            }
        }


    }

    @FXML
    void PREVIUOS(ActionEvent event) {

        if (counter > 0) {
            counter--;

            setQuestion(scores.get(this.counter).getQuestion());

            if (counter == 0) {
                previouse.setVisible(false);
            }

            if (this.scores.size() - 1 > counter) {
                next_id.setVisible(true);
            }


        }
    }

//            if (solvedQuestions.get(counter).getAnswer() != 0) {
//                //rr
//                if (solvedQuestions.get(counter).getAnswer() == 1) {
//                    first_check.setSelected(true);
//                } else if (solvedQuestions.get(counter).getAnswer() == 2) {
//                    second_check.setSelected(true);
//                } else if (solvedQuestions.get(counter).getAnswer() == 3) {
//                    third_check.setSelected(true);
//                } else if (solvedQuestions.get(counter).getAnswer() == 4) {
//                    fourth_check.setSelected(true);
//                }
//            }
//        }


    //----------------------------------

    @FXML
    void BACK(ActionEvent event) {
        Platform.runLater(() -> {
            setWindowTitle("student");
            try {

                counter = 0;
                scores = new ArrayList<>();
                solvedQuestions = new ArrayList<>();

                this.getData().setExamExecutionId(-1);

                // dispose();
                setContent("student", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


//    @FXML
//    void START_THE_EXAM(ActionEvent event) {
//
//        //  int exec_code=Integer.parseInt(exec_code_txf_id.getText());
//
//
//        Message message = new Message("give me the questions and the time by execution id");
//        message.setController_name("ExecutionDigitalExamController");
//        message.setExecution_id(this.getData().getExamExecutionId());
//        sendMessage(message);
//
//    }

    @FXML
    void firstDONE(ActionEvent event) {

        String codeStr = exec_code_txf_id.getText();
        if (codeStr.length() != 4) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("execution code must be 4 characters");
                alert.showAndWait();
            });
            exec_code_txf_id.setText("");
        } else {


            Message message = new Message("is the execution_code correct");
            message.setController_name("ExecutionDigitalExamController");
            message.setExecution_id(this.getData().getExamExecutionId());
            message.setExam_execution_code(codeStr);
            sendMessage(message);
        }

    }

    @FXML
    void secondDONE(ActionEvent event) {


        int username = -1;
        try {
            username = Integer.parseInt(ID_txf_id.getText());
        } catch (Exception e) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("id must contain only numbers");
                alert.showAndWait();
            });
            ID_txf_id.setText("");
            return;
        }


        String numString = Integer.toString(username);

        if (username < 0 || numString.length() != 9) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("id must contain 9 digits");
                alert.showAndWait();
            });

            ID_txf_id.setText("");
            return;

        }

        if (this.getData().getStudent_id() != username) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("wrong id");
                alert.showAndWait();
            });
            ID_txf_id.setText("");

        } else {
            Message message = new Message("give me the questions and the time by execution id");
            message.setController_name("ExecutionDigitalExamController");
            message.setExecution_id(this.getData().getExamExecutionId());
            sendMessage(message);

        }


        //


    }


    @FXML
    void submit(ActionEvent event) {

        isSelfSubmitted = true;

        Message message = new Message(0, "add solved exam");
        message.setController_name("ExecutionDigitalExamController");

        message.setStudents_comment(comments_txf_id.getText());
        message.setExecution_id(this.getData().getExamExecutionId());
        message.setSolvedQuestions(solvedQuestions);

        message.setIs_self_submit(true);

        message.setExam_duration(duration);

        sendMessage(message);


    }


    private void setQuestion(QuestionInDrawer question) {


        Platform.runLater(() -> {
            //  System.out.println("counter is : " + counter);

            question_num_label.setText("" + (counter + 1));


            first_txtf.setText(question.getFirst_answer());
            second_txtf.setText(question.getSecond_answer());
            third_txtf.setText(question.getThird_answer());
            fourth_txtf.setText(question.getFourth_answer());
            text_id.setText((question.getText()));
            points_value_label.setText(Integer.toString(scores.get(counter).getScore()));
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


    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

        //System.out.println(event.getMessage().getMessage());

        if (event.getMessage().getController_name().equals("ExecutionDigitalExamController")) {


            if (event.getMessage().getMessage().equals("wrong execution code")) {


                enter_id_label.setVisible(false);
                ID_txf_id.setVisible(false);
                second_done_bt.setVisible(false);

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong execution code !!");
                    alert.showAndWait();
                });
                exec_code_txf_id.setText("");
            }
            if (event.getMessage().getMessage().equals("correct execution code")) {

                first_done_bt_id.setVisible(false);
                enter_id_label.setVisible(true);
                ID_txf_id.setVisible(true);
                second_done_bt.setVisible(true);
            }

            if (event.getMessage().getMessage().equals("you got time extension")) {
                // System.out.println("you got time extension");

                if (event.getMessage().getExecution_id() == this.getData().getExamExecutionId()) {
                    //   System.out.println("you got time extension and it's value is: " + event.getMessage().getTimeExtensionValue());

                    handleTimeExtensionRequest(event.getMessage().getTimeExtensionValue());
                }
            }

            if (event.getMessage().getMessage().equals("got_questions_by_exec_id")) {

                //display the exam


                // System.out.println("gave you the questions");
                scores = event.getMessage().getScores();
                duration = event.getMessage().getExam_duration();


                if (this.scores.size() != 0) {

                    timeLeft = duration * 60 * 1000;
                    scheduleExamSubmission(timeLeft);
                    // Start the timer to update the remaining_time_label every second
                    startTimerToUpdateRemainingTime();


                    BACK_id.setVisible(false);

                    second_done_bt.setVisible(false);

                    //   start_btn_id.setVisible(false);
                    question_num_label.setVisible(true);
                    text_id.setVisible(true);
                    first_txtf.setVisible(true);
                    second_txtf.setVisible(true);
                    third_txtf.setVisible(true);
                    fourth_txtf.setVisible(true);
                    first_check.setVisible(true);
                    second_check.setVisible(true);
                    third_check.setVisible(true);
                    fourth_check.setVisible(true);

                    comments_label.setVisible(true);
                    comments_txf_id.setVisible(true);
                    submit_txf_id.setVisible(true);
                    points_label.setVisible(true);
                    points_value_label.setVisible(true);
                    //previouse.setVisible(true);
                    dot.setVisible(true);
                    remaining_time_label.setVisible(true);

                    teacher_comments_label.setVisible(true);
                    teacher_comments_label1.setVisible(true);

                    Platform.runLater(() -> {

                        teacher_comments_label1.setText(event.getMessage().getTeacher_comments());

                    });


                    next_id.setVisible(true);
                    previouse.setVisible(true);

                    SolvedQuestion solvedQuestion;

                    for (int i = 0; i < scores.size(); i++) {
                        solvedQuestion = new SolvedQuestion(scores.get(i).getQuestion(), 0, scores.get(i).getScore());
                        solvedQuestions.add(solvedQuestion);
                    }


                    //  System.out.println("set data from server");

                    setQuestion(scores.get(counter).getQuestion());
                    // This code will be executed after the setQuestion method is completed

                    if (this.scores.size() - 1 == counter) {
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

                    //.setVisible(false);
                    text_id.setVisible(false);
                    question_num_label.setVisible(false);

                    first_txtf.setVisible(false);
                    second_txtf.setVisible(false);
                    third_txtf.setVisible(false);
                    fourth_txtf.setVisible(false);

                    first_check.setVisible(false);
                    second_check.setVisible(false);
                    third_check.setVisible(false);
                    fourth_check.setVisible(false);

                    points_value_label.setVisible(false);
                    points_label.setVisible(false);

                    teacher_comments_label.setVisible(false);
                    teacher_comments_label1.setVisible(false);

                    Platform.runLater(() -> {

                        teacher_comments_label1.setText("");
                    });

                    previouse.setVisible(false);
                    next_id.setVisible(false);
                    remaining_time_label.setVisible(false);


                    ///there is no questions in this exam
                }

            }


            if (event.getMessage().getMessage().equals("Solved exam added")) {

                if (!hasSubmitted) {
                    hasSubmitted = true;

                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("message");
                        alert.setHeaderText(null);
                        alert.setContentText("your solution saved");
                        alert.showAndWait();
                    });


                    Platform.runLater(() -> {
                        setWindowTitle("student");
                        try {

                            counter = 0;
                            scores = new ArrayList<>();
                            solvedQuestions = new ArrayList<>();

                            this.getData().setExamExecutionId(-1);
                            // dispose();
                            setContent("student", this.getData());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }


    }


    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("ExecutionDigitalExamController");

        counter = 0;
        timeLeft = 0;

        isSelfSubmitted = false;
        hasSubmitted = false;

        scores = new ArrayList<>();
        solvedQuestions = new ArrayList<>();

        Platform.runLater(() -> {

            first_done_bt_id.setVisible(true);
            question_num_label.setVisible(false);
            text_id.setVisible(false);
            first_txtf.setVisible(false);
            second_txtf.setVisible(false);
            third_txtf.setVisible(false);
            fourth_txtf.setVisible(false);
            first_check.setVisible(false);
            second_check.setVisible(false);
            third_check.setVisible(false);
            fourth_check.setVisible(false);
            previouse.setVisible(false);
            next_id.setVisible(false);
            dot.setVisible(false);

            teacher_comments_label.setVisible(false);
            teacher_comments_label1.setVisible(false);


            teacher_comments_label1.setText("");


            enter_id_label.setVisible(false);
            ID_txf_id.setVisible(false);
            second_done_bt.setVisible(false);
            //  start_btn_id.setVisible(false);

            comments_label.setVisible(false);
            comments_txf_id.setVisible(false);
            submit_txf_id.setVisible(false);

            points_label.setVisible(false);
            points_value_label.setVisible(false);
            remaining_time_label.setVisible(false);

            exec_code_txf_id.setText("");
            ID_txf_id.setText("");
            comments_txf_id.setText("");

            BACK_id.setVisible(true);

        });

    }
    ///////////////

}