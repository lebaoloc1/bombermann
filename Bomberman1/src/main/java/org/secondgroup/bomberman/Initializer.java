package org.secondgroup.bomberman;

import org.secondgroup.bomberman.common.AppConfig;
import org.secondgroup.bomberman.common.ErrorCode;
import org.secondgroup.bomberman.common.Logger;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Initializer {
    private final AppConfig appConfig;
    private final AtomicReference<Logger> loggerReference;

    public Initializer(AtomicReference<Logger> loggerReference) {
        appConfig = AppConfig.getInstance();
        this.loggerReference = loggerReference;
    }

    private void exitByCannotCreateLogFile() {
        Logger.error("Cannot initialize log file", "Initializer");
        System.exit(ErrorCode.UNABLE_TO_INITIALIZE);
    }

    private void mergeOldLogs(Path oldPath, Path newPath) {
        File newLogFile = new File(newPath.toString());

        if (!newLogFile.exists())
            return;

        if (newLogFile.isDirectory()) {
            Logger.error("Log file name duplicated", "Initializer");
            System.exit(ErrorCode.ENTRY_EXISTED);
        }

        File oldLogFile = new File(oldPath.toString());

        if (oldLogFile.isDirectory()) {
            Logger.error("Old log file name duplicated", "Initializer");
            System.exit(ErrorCode.ENTRY_INVALID);
        }

        try {
            var writer = new FileWriter(oldLogFile, true);
            var reader = new Scanner(new FileReader(newLogFile));

            while (reader.hasNextLine())
                writer.write(reader.nextLine() + "\n");

            reader.close();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        var appProps = appConfig.getAppProperties();

        Path oldLogPath = Paths.get(
                appConfig.getAppDataPath().toString(),
                appProps.getProperty("LOG_DIR"),
                appProps.getProperty("OLD_LOG_FILE")
        );
        Path newLogPath = Paths.get(
                appConfig.getAppDataPath().toString(),
                appProps.getProperty("LOG_DIR"),
                appProps.getProperty("LOG_FILE")
        );

        mergeOldLogs(oldLogPath, newLogPath);

        File logFile = new File(newLogPath.toString());

        try {
            assert logFile.exists() || logFile.createNewFile();

            loggerReference.set(new Logger(new FileOutputStream(newLogPath.toString()), "App"));
        } catch (IOException exception) {
            exitByCannotCreateLogFile();
        }

        var dataInitializer = new DataInitializer();
        dataInitializer.initialize();
    }
}
