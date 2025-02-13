package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class ExecutionOfAManualExamController extends PageController {


    @FXML
    private Button checkBT;


    @FXML
    private Button back_but;


    @FXML
    private Label enterexecutioncodeLAB;

    @FXML
    private Label time_label;

    @FXML
    private TextField enterexecutioncodeTF;

    @FXML
    private Button receivinganexamformBT;

    @FXML
    private Button submitBT;

    @FXML
    private Button uploadfileBT;


    @FXML
    private TextArea uploadfiletextarea;

    private int duration;

    private int timeLeft = 0;

    private boolean isSelfSubmitted = false;

    private ExamInDrawer exam;

    private String base64Content = "";

    private String fileName = "";

    private final FileChooser fc = new FileChooser();

    private boolean hasSubmitted = false;


    @FXML
    void UploadFile(ActionEvent event) {
        fc.setTitle("My File Chooser");
        File file = fc.showOpenDialog(null);
        if (file != null) {
            try {
                byte[] fileContent = Files.readAllBytes(file.toPath());
                base64Content = Base64.getEncoder().encodeToString(fileContent);
                uploadfiletextarea.setText(file.getName());
                fileName = file.getName();


            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            // Handle the case where the user didn't select any file
            // ...
        }
    }


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
                time_label.setText("Time's up!");
            });
        } else {
            Platform.runLater(() -> {
                time_label.setText("Time left: " + timeLeftFormatted);
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
                Platform.runLater(ExecutionOfAManualExamController.this::updateRemainingTimeLabel);

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


    @FXML
    void EnterExecutionCode(ActionEvent event) {

    }

    @FXML
    void ReceivingAnExamForm(ActionEvent event) {

        Message message = new Message("give me the exam In drawer and the time by execution id");
        message.setController_name("ExecutionOfAManualExamController");
        message.setExecution_id(this.getData().getExamExecutionId());
        sendMessage(message);


    }

    @FXML
    void onBackClicked(ActionEvent event) {

        Platform.runLater(() -> {
            setWindowTitle("student");
            try {
                this.getData().setExamExecutionId(-1);
              //  dispose();
                setContent("student", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    @FXML
    void Submit(ActionEvent event) {


        if (fileName.equals("")) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("you have not uploaded any file");
                alert.showAndWait();
            });

        } else {
            isSelfSubmitted = true;
            Message message = new Message("upload the file to database");
            message.setExecution_id(this.getData().getExamExecutionId());
            message.setIs_self_submit(true);
            message.setController_name("ExecutionOfAManualExamController");
            message.setFileContent(base64Content);
            sendMessage(message);
        }

    }

    private void submitExamAutomatically() {

        if (!hasSubmitted) {

            if (!isSelfSubmitted) {
                isSelfSubmitted = false;

                if (fileName.equals("")) {
                    base64Content = "";
                }

                Message message = new Message("upload the file to database");
                message.setExecution_id(this.getData().getExamExecutionId());
                message.setIs_self_submit(false);
                message.setController_name("ExecutionOfAManualExamController");
                message.setFileContent(base64Content);
                sendMessage(message);

            }
        }
    }

    @FXML
    void check_clicked(ActionEvent event) {

        String codeStr = enterexecutioncodeTF.getText();
        if (codeStr.length() != 4) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("execution code must be 4 characters");
                alert.showAndWait();
            });
            enterexecutioncodeTF.setText("");
        } else {


            Message message = new Message("is the execution_code correct");
            message.setController_name("ExecutionOfAManualExamController");
            message.setExecution_id(this.getData().getExamExecutionId());
            message.setExam_execution_code(codeStr);
            sendMessage(message);
        }

    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

        if (event.getMessage().getController_name().equals("ExecutionOfAManualExamController")) {


            if (event.getMessage().getMessage().equals("wrong execution code")) {

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong execution code !!");
                    alert.showAndWait();
                });
                enterexecutioncodeTF.setText("");
            }
            if (event.getMessage().getMessage().equals("correct execution code")) {
                receivinganexamformBT.setVisible(true);
                checkBT.setVisible(false);
            }


            if (event.getMessage().getMessage().equals("got_exam_and_duration_by_exec_id")) {
                duration = event.getMessage().getExam_duration();
                exam = event.getMessage().getExam();

                List<Score> scores = event.getMessage().getScores();

                submitBT.setVisible(true);
                uploadfiletextarea.setVisible(true);
                uploadfiletextarea.setText("");
                uploadfileBT.setVisible(true);
                time_label.setVisible(true);


                timeLeft = duration * 60 * 1000;
                scheduleExamSubmission(timeLeft);
                // Start the timer to update the remaining_time_label every second
                startTimerToUpdateRemainingTime();


                back_but.setVisible(false);
                receivinganexamformBT.setVisible(false);


                if (exam != null) {
                    // Get the user's desktop path
                    String desktopPath = System.getProperty("user.home") + "/Desktop";
                    File desktopDir = new File(desktopPath);
                    if (!desktopDir.exists()) {
                        desktopDir.mkdirs(); // Create the directory if it doesn't exist
                    }

                    WordConverter wordConverter = new WordConverter();
                    String filePath = wordConverter.generateWordFile(exam, scores, desktopPath);

                    if (filePath != null) {
                        System.out.println("Word file generated at: " + filePath);
                    } else {
                        System.out.println("Failed to generate the Word file.");
                    }

                    // Open the directory where the generated Word file is located
                    try {
                        File directory = new File(desktopPath);
                        Desktop.getDesktop().open(directory);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Failed to open the directory.");
                    }
                } else {
                    System.out.println("Exam not found.");
                }

            }

            if (event.getMessage().getMessage().equals("you got time extension")) {
                // System.out.println("you got time extension");

                if (event.getMessage().getExecution_id() == this.getData().getExamExecutionId()) {
                    System.out.println("you got time extension and it's value is: " + event.getMessage().getTimeExtensionValue());

                    handleTimeExtensionRequest(event.getMessage().getTimeExtensionValue());
                }
            }

            if (event.getMessage().getMessage().equals("file uploaded")) {

                if ( !hasSubmitted )
                {
                    hasSubmitted=true;

                    String messageContent = "";

                    if (fileName.equals("")) {
                        messageContent = "your solution saved but you haven't uploaded any file";
                    } else {
                        messageContent = "your solution saved and the file uploaded successfully";
                    }

                    final String finalMessageContent = messageContent;

                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("message");
                        alert.setHeaderText(null);
                        alert.setContentText(finalMessageContent);
                        alert.showAndWait();
                    });

                    Platform.runLater(() -> {
                        setWindowTitle("student");
                        try {
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

    void initialize1() {

        this.getData().setCurrentControllerName("ExecutionOfAManualExamController");

        isSelfSubmitted = false;
        hasSubmitted=false;

        checkBT.setVisible(true);

        enterexecutioncodeTF.setText("");
        receivinganexamformBT.setVisible(false);
        time_label.setVisible(false);
        time_label.setText("");
        submitBT.setVisible(false);
        uploadfiletextarea.setVisible(false);
        uploadfiletextarea.setEditable(false);
        uploadfiletextarea.setText("");

        uploadfileBT.setVisible(false);
        back_but.setVisible(true);

        base64Content = "";
        fileName = "";

    }


}




















