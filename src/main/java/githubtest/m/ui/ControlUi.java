package githubtest.m.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.prefs.Preferences;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import githubtest.javafxutil.FxmlController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main window.
 *
 * @author leo
 */
public class ControlUi extends BorderPane implements FxmlController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @FXML
    private MenuBar systemMenuBar;

    @FXML
    private Menu projectsMenu;

    @FXML
    private MenuItem newProject;

    private static ControlUi instance;

    public ControlUi() {
        loadFxml(App.MESSAGES);

        if (instance != null) {
            throw new IllegalStateException("cannot have two ControlControllers...");
        }
        instance = this;

    }

    @FXML
    private void newProject() {
    }

    public void openProject(Path path) throws FileNotFoundException, IOException {
    }

    @FXML
    private void openProject() throws FileNotFoundException, IOException {
    }

    @FXML
    private void settings() {
    }

    public void restoreWindowPositionAndSetAutosave() {
    }

    public static void restoreWindowPositionAndSetAutosave(Stage stage, Preferences preferences) {
    }

    @FXML
    public boolean closeWindow() {
        boolean result = false;
            ((Stage) getScene().getWindow()).close();
            result = true;
        return result;
    }

}
