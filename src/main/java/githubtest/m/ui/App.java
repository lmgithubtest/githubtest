package githubtest.m.ui;

import java.awt.Toolkit;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import javax.sound.midi.MidiDevice;
import githubtest.m.engine.Control;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Starting point. TODO: rename to FxApp and move up one package.
 */
public class App extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static ResourceBundle MESSAGES = ResourceBundle.getBundle("githubtest.m.Messages");

    public static final String DEFAULT_CSS_FILENAME = "dark.css";

    public static final String DEFAULT_STYLE_SHEET = App.class.getResource(DEFAULT_CSS_FILENAME).toExternalForm();

    private static final String STYLE_SHEET_PREF = "css";

    private static String styleSheet;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        LOGGER.info("initialize");
        // Not prefetching at the moment, since CoreMidi4J hangs if we do.
        //preLoadMidiDeviceInfo();
    }

    @Override
    public void start(Stage stage) throws IOException {
        LOGGER.info("starting");
        try {
            setStyleSheet(Control.PREFERENCES.get(STYLE_SHEET_PREF, DEFAULT_STYLE_SHEET));
            ControlUi controlUi = new ControlUi();
            Scene scene = new Scene(controlUi);
            addStyleSheet(scene);
            stage.setScene(scene);
            stage.setOnCloseRequest(onCloseRequest(controlUi));
            controlUi.restoreWindowPositionAndSetAutosave();
            stage.show();
        } catch (RuntimeException e) {
            LOGGER.error("cannot start", e);
            e.printStackTrace(System.out);
            throw e;
        }
    }

    @Override
    public void stop() throws Exception {
        LOGGER.info("stopping");
        ForkJoinPool.commonPool().awaitQuiescence(1, TimeUnit.SECONDS);
        System.exit(0); // needed because the midi system will prevent proper shutdown
        LOGGER.info("this line should not be executed anymore");
    }

    private EventHandler<WindowEvent> onCloseRequest(ControlUi controlUi) {
        return event -> {
            if (!controlUi.closeWindow()) {
                event.consume();
            }
        };
    }

    public static void setStyleSheet(String styleSheet) {
        App.styleSheet = styleSheet;
        Control.PREFERENCES.put(STYLE_SHEET_PREF, styleSheet);
    }

    public static void replaceStyleSheetOnAllWindows() {
        for (Window window : Stage.getWindows()) {
            ObservableList<String> styleSheets = window.getScene().getStylesheets();
            styleSheets.set(0, styleSheet);
            window.sizeToScene();
        }
    }

    public static String getStyleSheet() {
        return styleSheet;
    }

    protected static void addStyleSheet(Scene scene) {
        LOGGER.debug("using style sheet {}", styleSheet);
        scene.getStylesheets().add(styleSheet);
    }

    public static Stage createStage(Parent root) {
        Scene scene = new Scene(root);
        addStyleSheet(scene);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        return stage;
    }


}
