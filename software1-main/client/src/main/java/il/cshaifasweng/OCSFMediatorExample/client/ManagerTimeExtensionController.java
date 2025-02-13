/**
 * Sample Skeleton for 'ManagerTimeExtension.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Grade;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.TimeExtensionRequest;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javassist.expr.NewArray;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class ManagerTimeExtensionController extends PageController {

    @FXML // fx:id="confirm_but"
    private Button confirm_but; // Value injected by FXMLLoader

    @FXML // fx:id="exams_data_table"
    private TableView<TimeExtensionRequest> exams_data_table; // Value injected by FXMLLoader

    @FXML // fx:id="execution_code_column"
    private TableColumn<TimeExtensionRequest, String> execution_code_column; // Value injected by FXMLLoader

    @FXML // fx:id="extension_time_column"
    private TableColumn<TimeExtensionRequest, Integer> extension_time_column; // Value injected by FXMLLoader
    @FXML // fx:id="name_column"
    private TableColumn<TimeExtensionRequest, String> name_column; // Value injected by FXMLLoader
    @FXML // fx:id="student_id_column"
    private TableColumn<TimeExtensionRequest, Integer> student_id_column; // Value injected by FXMLLoader

    @FXML // fx:id="message_TF"
    private TextField message_TF; // Value injected by FXMLLoader

    @FXML // fx:id="message_label"
    private Label message_label; // Value injected by FXMLLoader


    @FXML
    private Button back_but;

    @FXML
    private CheckBox select_all_check_box;

    @FXML // fx:id="reject_but"
    private Button reject_but; // Value injected by FXMLLoader


    private ObservableList<TimeExtensionRequest> timeExtensionRequests = FXCollections.observableArrayList();

    private boolean in_page = true;


    @FXML
    void on_select_all_checked(ActionEvent event) {

        if (select_all_check_box.isSelected()) {
            timeExtensionRequests = FXCollections.observableArrayList();
            timeExtensionRequests.addAll(exams_data_table.getItems());
            exams_data_table.getSelectionModel().selectAll();
        } else {
            timeExtensionRequests = FXCollections.observableArrayList();
            exams_data_table.getSelectionModel().clearSelection();
        }

    }

    @FXML
    void on_back_but_pressed(ActionEvent event) {

        Platform.runLater(() -> {
            setWindowTitle("Manager");
            try {
                in_page = false;
                setContent("Manager", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    @FXML
    void on_but_reject_pressed(ActionEvent event) {

        timeExtensionRequests = FXCollections.observableArrayList();
        timeExtensionRequests = exams_data_table.getSelectionModel().getSelectedItems();

        if (timeExtensionRequests.size() == 0) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("you did not select any time extension request");
                alert.showAndWait();
            });
            return;
        }

        Message message = new Message("reject the selected extensions requests");
        message.setController_name("ManagerTimeExtensionController");
        message.setManager_id(this.getData().getManager_id());
        message.setTimeExtensionRequests_from_server(new ArrayList<>(timeExtensionRequests));
        sendMessage(message);



    }

    @FXML
    void on_confirm_but_pressed(ActionEvent event) {

        timeExtensionRequests = FXCollections.observableArrayList();
        timeExtensionRequests = exams_data_table.getSelectionModel().getSelectedItems();

        if (timeExtensionRequests.size() == 0) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("you did not select any time extension request");
                alert.showAndWait();
            });
            return;
        }

        Message message = new Message("confirm the selected extensions requests");
        message.setController_name("ManagerTimeExtensionController");
        message.setManager_id(this.getData().getManager_id());
        message.setTimeExtensionRequests_from_server(new ArrayList<>(timeExtensionRequests));
        sendMessage(message);
    }


    void on_show_message_pressed() {
        List<TimeExtensionRequest> requests = exams_data_table.getSelectionModel().getSelectedItems();

        if (requests == null || requests.isEmpty()) {
            message_TF.setText("");
        } else {
            TimeExtensionRequest selectedRequest = requests.get(0);
            message_TF.setText(selectedRequest.getRequest_body());

        }

    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

        if (event.getMessage().getController_name().equals("ManagerTimeExtensionController")) {

            if (event.getMessage().getMessage().startsWith("i gave you all time extension requests")) {

                List<TimeExtensionRequest> timeExtensionRequests = event.getMessage().getTimeExtensionRequests_from_server();

                exams_data_table.getItems().clear();
                exams_data_table.refresh();

                // Set up the columns
                execution_code_column.setCellValueFactory(new PropertyValueFactory<>("execution_code"));
                student_id_column.setCellValueFactory(new PropertyValueFactory<>("student_id"));
                //student_id_column.setCellValueFactory(new PropertyValueFactory<>("exam_duration"));
                name_column.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                extension_time_column.setCellValueFactory(new PropertyValueFactory<>("extension_time"));

                for (int i = 0; i < timeExtensionRequests.size(); i++) {
                    // Set the data to the table
                    exams_data_table.getItems().add(timeExtensionRequests.get(i));
                }
            }
            if (event.getMessage().getMessage().equals("the Extension requests Confirmed successfully")) {

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("message");
                    alert.setHeaderText(null);
                    alert.setContentText("the time extension request have been confirmed");
                    alert.showAndWait();
                });

                timeExtensionRequests = FXCollections.observableArrayList();
                Platform.runLater(() -> {

                    exams_data_table.getSelectionModel().clearSelection();
                    select_all_check_box.setSelected(false);
                    message_TF.setText("");
                    exams_data_table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                });

                Message message = new Message("give me time extension requests");
                message.setController_name("ManagerTimeExtensionController");
                sendMessage(message);


            } if (event.getMessage().getMessage().equals("the Extension requests rejected successfully")) {

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("message");
                    alert.setHeaderText(null);
                    alert.setContentText("the time extension request have been rejected");
                    alert.showAndWait();
                });

                timeExtensionRequests = FXCollections.observableArrayList();
                Platform.runLater(() -> {

                    exams_data_table.getSelectionModel().clearSelection();
                    select_all_check_box.setSelected(false);
                    message_TF.setText("");
                    exams_data_table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                });

                Message message = new Message("give me time extension requests");
                message.setController_name("ManagerTimeExtensionController");
                sendMessage(message);


            }


        }
    }

    @Subscribe
    public void refresh(UpdateMessageEvent event) {
        System.out.println("refresh ManagerTimeExtensionController");

        System.out.println(event.getMessage().getMessage());

        if (event.getMessage().getController_name().equals("ManagerTimeExtensionController") && in_page) {
            System.out.println(event.getMessage().getMessage());

            if (event.getMessage().getMessage().equals("time extension request were added")) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("message");
                    alert.setHeaderText(null);
                    alert.setContentText("time extension request were added");
                    alert.showAndWait();
                });

                Message message = new Message("give me time extension requests");
                message.setController_name("ManagerTimeExtensionController");
                sendMessage(message);

                Platform.runLater(() -> {
                    message_TF.setText("");
                });
            }
        }

    }


    @FXML
    void initialize1() {

        this.getData().setCurrentControllerName("ManagerTimeExtensionController");

        in_page = true;

        timeExtensionRequests = FXCollections.observableArrayList();

        Platform.runLater(() -> {

            exams_data_table.getSelectionModel().clearSelection();
            select_all_check_box.setSelected(false);
            message_TF.setText("");
            exams_data_table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        });

        exams_data_table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            on_show_message_pressed();
        });

        Message message = new Message("give me time extension requests");
        message.setController_name("ManagerTimeExtensionController");
        sendMessage(message);

    }


}
