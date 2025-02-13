package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import org.greenrobot.eventbus.EventBus;

public class SimpleClient extends AbstractClient {

    private static SimpleClient client = null;

    private SimpleClient(String host, int port) {
        super(host, port);
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        Message message = (Message) msg;

       // System.out.println("the event being triggered is for: "+message.getController_name());

        if (message.getMessage().equals("client added successfully")) {
            //do nothing!!
        } else if (message.getMessage().equals("i will give you the students")) {
            EventBus.getDefault().post(new MessageEvent(message));
        } else if (message.getMessage().equals("i will give you the student grades")) {
            EventBus.getDefault().post(new MessageEvent(message));
        }else if (message.getMessage().equals("i will give you the subjects")) {
            EventBus.getDefault().post(new MessageEvent(message));
        } else if (message.getMessage().equals("i will give you the courses")) {
            // System.out.println("4");
            EventBus.getDefault().post(new MessageEvent(message));
            // System.out.println("5");
        }else if (message.getMessage().equals("I get course statistical information")) {
            EventBus.getDefault().post(new MessageEvent(message));
        } else if (message.getMessage().equals("i changed the grade")) {
            EventBus.getDefault().post(new MessageEvent(message));
        }
        else if (message.getMessage().equals("logOutConfirmed")) {
            EventBus.getDefault().post(new ErrorEvent(message));
        }

        else if (message.isForRefresh()) {
            EventBus.getDefault().post(new UpdateMessageEvent(message));

        }

        else
        {
            EventBus.getDefault().post(new MessageEvent(message));
        }
    }


    public static SimpleClient getClient() {
        if (client == null) {

            client = new SimpleClient("localhost", 3000);
        }
        return client;
    }

}