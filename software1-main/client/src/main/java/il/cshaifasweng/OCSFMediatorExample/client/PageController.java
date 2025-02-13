package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.QuestionInDrawer;
import il.cshaifasweng.OCSFMediatorExample.entities.entities.Subject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setContent;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setWindowTitle;

public class PageController {


    //////wesal///////
    private PageData data;

//    ///////////to refresh pages /////////////////////
//
//    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//
//    public void startScheduledTask(Runnable task, long initialDelay, long period, TimeUnit unit) {
//        System.out.println("start scheduled task");
//        executorService.scheduleAtFixedRate(task, initialDelay, period, unit);
//    }
//
//    public void dispose() {
//        System.out.println("shutdown executorService");
//        executorService.shutdown();
//    }
//
//
//    ////////////////////////////////////////////////////


    public PageData getData() {
        return data;
    }

    public void setData(PageData data) {
        this.data = data;
    }

    @FXML
    void initialize() {

        if (!EventBus.getDefault().isRegistered(this)) {
            System.out.println("initialize register");
            EventBus.getDefault().register(this);
        }

    }


    void sendMessage(String messageBody) {
        try {
            Message message = new Message(0, messageBody);

            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void sendMessage(Message message) {
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void initialize1() {

    }


    @FXML
    void on_logOut_clicked(ActionEvent event) {

        Message message = new Message("LogOut");
        message.setController_name(this.getData().getCurrentControllerName());
        message.setUsername(this.getData().getUserId());
        sendMessage(message);
    }

    @Subscribe
    public void setDataFromServerTF(ErrorEvent event) throws IOException {

        if (event.getMessage().getMessage().equals("logOutConfirmed")) {

            System.out.println("user who wants to logOut "+event.getMessage().getUsername());
            System.out.println("and his id "+event.getMessage().getController_name());

            System.out.println("this.getData().getCurrentControllerName() "+this.getData().getCurrentControllerName());
            System.out.println("this.getData().getUserId() "+this.getData().getUserId());

        if(event.getMessage().getController_name().equals(this.getData().getCurrentControllerName())

            && this.getData().getUserId()==event.getMessage().getUsername())
        {

            System.out.println("here");

            Platform.runLater(() -> {
                setWindowTitle("Log In");
                try {
                    setContent("LogIn", new PageData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            if (EventBus.getDefault().isRegistered(this)) {
                System.out.println("register");
                EventBus.getDefault().unregister(this);
            }


            }
        }
    }

    @Subscribe
    public void refresh(UpdateMessageEvent event) {

    }

    @Subscribe
    public void setDataFromServerTF(MessageEvent event) throws IOException {

    }

    public PageController() {
    }

}
