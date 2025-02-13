package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JavaFX App
 */
public class SimpleChatClient extends Application {

    private static FXMLLoader loader;
    private static Scene scene;
    private SimpleClient client;

    private static Stage stage;

    private static Map<String, Pair<Parent, PageController>> fxmlCache = new HashMap<>();
    private static Map<String, FXMLLoader> fxmlLoaders = new HashMap<>();
    private static  Parent currentRoot;


    @Override
    public void start(Stage stage) throws IOException {

        ////
        this.stage = stage;
        /////

        EventBus.getDefault().register(this);
        client = SimpleClient.getClient();
        client.openConnection();

        scene = new Scene(loadFXML("LogIn"), 936, 680);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    /////added /////////
    public static void setWindowTitle(String title) {

        stage.setTitle(title);
    }

    public static void setContent(String fxmlFileName,PageData data) throws IOException {

        Pair<Parent, PageController> cachedFXML = fxmlCache.get(fxmlFileName);

        if (cachedFXML != null) {

            System.out.println("the page: "+fxmlFileName+" is already exist");

            // FXML is already cached, retrieve it

            loader = fxmlLoaders.get(fxmlFileName);

            if (loader == null) {
                System.out.println("the page: "+fxmlFileName+" loader is not exist");

                loader = new FXMLLoader(Main.class.getResource(fxmlFileName + ".fxml"));
                fxmlLoaders.put(fxmlFileName, loader);
            }
            PageController controller = loader.getController();
            controller.setData(data);

            ////================
            controller.initialize1();


            currentRoot = cachedFXML.getKey();
        } else {

            System.out.println("the page: "+fxmlFileName+" is not exist");
            // FXML is not cached, load it
            loader = fxmlLoaders.get(fxmlFileName);
            if (loader == null) {
                System.out.println("the page: "+fxmlFileName+" loader is not exist");
                loader = new FXMLLoader(Main.class.getResource(fxmlFileName + ".fxml"));
                fxmlLoaders.put(fxmlFileName, loader);
            }
            currentRoot = loader.load();
            PageController controller = loader.getController();
            controller.setData(data);

            ////================
            controller.initialize1();


            // Cache the FXML and its controller
            fxmlCache.put(fxmlFileName, new Pair<>(currentRoot, controller));


        }
        scene.setRoot(currentRoot);

    }


    public static FXMLLoader getLoader() throws IOException {
        return loader;
    }


    /////////////////////////////////////


    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SimpleChatClient.class.getResource(fxml + ".fxml"));
            loader = fxmlLoader;
            return fxmlLoader.load();
        } catch (IOException e) {
            System.err.println("Error loading FXML: " + e.getMessage());
            throw e;
        }
    }


    @Override
    public void stop() throws Exception {
        // TODO Auto-generated method stub
        EventBus.getDefault().unregister(this);
        super.stop();
    }


    @Subscribe
    public void onMessageEvent(MessageEvent message) {

    }


    public static void main(String[] args) {
        launch();

    }

}