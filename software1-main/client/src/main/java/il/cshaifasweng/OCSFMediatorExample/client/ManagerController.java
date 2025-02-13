package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class ManagerController extends PageController{


    @FXML
    private Button examresults_button;

    @FXML
    private Button examsrepository_button;



    @FXML
    private Button questionrepository_button;

    @FXML
    private Button show_time_extension_but;


    @FXML
    void examresults_button_pressed(ActionEvent event) {

    }

    @FXML
    void examsrepository_button_pressed(ActionEvent event) {/**this ya nawras***/
        Platform.runLater(() -> {
            setWindowTitle("Exam Repository");
            try {

                this.getData().setIs_first_time_enter_from_manager(true);
                setContent("ExamsRepositoryForManeger",this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }




    @FXML
    void on_show_time_extension_pressed(ActionEvent event) {

        Platform.runLater(() -> {
            setWindowTitle("Time extension requests");
            try {
              //  dispose();
                setContent("ManagerTimeExtension",this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    void questionrepository_buttton_pressed(ActionEvent event) {
        Platform.runLater(() -> {
            setWindowTitle("Show questions repository");
            try {
                setContent("ShowQuestionForManger", this.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {
    }

    void initialize1() {

        this.getData().setCurrentControllerName("ManagerController");


    }
}
