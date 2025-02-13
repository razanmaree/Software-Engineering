/**
 * Sample Skeleton for 'LogIn.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Student;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.*;

public class LogInController extends PageController {

    @FXML
    private Button LogInbut;


    @FXML
    private TextField passwordTF;

    @FXML
    private TextField userTF;



    /////////////////////////

    private PageData data = new PageData();


    private int username=-1;


    void sendMessage(Message message) {
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @FXML
    void on_logIn_clicked(ActionEvent event) {

        username = -1;
        try {
            username = Integer.parseInt(userTF.getText());
        } catch (Exception e) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("username must contain only numbers");
                alert.showAndWait();
            });
            userTF.setText("");
            passwordTF.setText("");
            return;
        }


        String numString = Integer.toString(username);

        if (username < 0 || numString.length() != 9) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("message");
                alert.setHeaderText(null);
                alert.setContentText("username must contain 9 digits");
                alert.showAndWait();
            });
            userTF.setText("");
            passwordTF.setText("");
            return;
        }


        Message message = new Message(0, "login");
        message.setUsername(username);
        message.setPassword(passwordTF.getText());
        message.setController_name("LogInController");
        data.setUserId(username);
        sendMessage(message);
    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

        if (event.getMessage().getController_name().equals("LogInController") &&
                event.getMessage().getUsername()==data.getUserId()) {

            System.out.println(event.getMessage().getUsername());
            System.out.println(data.getUserId());

            System.out.println(event.getMessage().getMessage());

            if (event.getMessage().getMessage().startsWith("login confirmed")) {

                String user_type = event.getMessage().getMessage().substring(16);

               // System.out.println("the user id is:"+(username));

                data.setUser_type(user_type);
                data.setUserId(username);

                Platform.runLater(() -> {
                    setWindowTitle(user_type);
                    try {

                        if (user_type.equals("Teacher")) {
                            data.setTeacher_id(username);

                        } else if (user_type.equals("student")) {
                            data.setStudent_id(username);
                        } else {
                            data.setManager_id(username);
                        }
                       // dispose();
                        Platform.runLater(() -> {
                            userTF.setText("");
                            passwordTF.setText("");
                        });
                        setContent(user_type, data);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


            }  if (event.getMessage().getMessage().startsWith("wrong password"))
            {
                passwordTF.setText("");
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("message");
                    alert.setHeaderText(null);
                    alert.setContentText(event.getMessage().getMessage());
                    alert.showAndWait();
                });

                data.setUserId(-1);
            }if(event.getMessage().getMessage().equals("user not exist"))
            {
                userTF.setText("");
                passwordTF.setText("");
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("message");
                    alert.setHeaderText(null);
                    alert.setContentText(event.getMessage().getMessage());
                    alert.showAndWait();
                });
                data.setUserId(-1);
            }if(event.getMessage().getMessage().equals("you are already loggedIn"))
            {
                userTF.setText("");
                passwordTF.setText("");
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("message");
                    alert.setHeaderText(null);
                    alert.setContentText(event.getMessage().getMessage());
                    alert.showAndWait();
                });
                data.setUserId(-1);

            }

        }

    }


    /////////////////do not remove so important to recieve the messages from server
    @Override
    void initialize() {

        System.out.println("initialize");
        if (!EventBus.getDefault().isRegistered(this)) {
            System.out.println("register");
            EventBus.getDefault().register(this);
        }
        data =new PageData();
        this.setData(data);

        sendMessage("add client");

    }
    @Override
    void initialize1() {

        System.out.println("initialize1");
        if (!EventBus.getDefault().isRegistered(this)) {
            System.out.println("register1");
            EventBus.getDefault().register(this);
        }
        data =new PageData();
        this.setData(data);

        sendMessage("add client1");

    }

}

