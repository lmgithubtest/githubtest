package githubtest.m.engine;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.prefs.Preferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leo
 */
public final class Control {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String FILE_EXTENTION = ".m";

    public static final String FILE_EXTENTION_FILTER = "*.m";

    private static final String DEFAULT_FILE_NAME = "noname";

    private static final String DEFAULT_DIR = System.getProperty("user.home");

    private static Control instance;
    
    public static final Preferences PREFERENCES = Preferences.userNodeForPackage(Control.class);

    private static final Preferences SAVE_FILE_PREFERENCES = Control.PREFERENCES.node("saveFiles");

    private File projectDirectory = new File(DEFAULT_DIR);

    // only used to write to, so if we really want to use this, then refactor
//    public Lookup<MidiInOut> lookup = Lookup.create();

    private Control() {
    }

    public File getProjectDirectory() {
        return projectDirectory;
    }

    public static Control getInstance() {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }

}
