package org.secondgroup.bomberman.common;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
    private final OutputStream out;
    private final OutputStream err;
    private final String context;

    public Logger(String context) {
        this.out = System.out;
        this.err = System.err;

        if (context == null || context.isEmpty())
            this.context = "NoContext";
        else
            this.context = context;
    }

    public Logger(OutputStream out, String context) {
        this.out = this.err = out;

        if (context == null || context.isEmpty())
            this.context = "NoContext";
        else
            this.context = context;
    }

    public Logger(OutputStream out, OutputStream err, String context) {
        this.out = out;
        this.err = err;

        if (context == null || context.isEmpty())
            this.context = "NoContext";
        else
            this.context = context;
    }

    private static String now() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
    }

    private static String generateMessage(String context, String message, String level) {
        return String.format("[%s][%s][%s] :: %s\n", now(), context, level, message);
    }

    public static void info(String message, String context) {
        var data = generateMessage(context, message, "info");

        System.out.print(data);
    }

    public static void error(String message, String context) {
        var data = generateMessage(context, message, "error");

        System.err.print(data);
    }

    public static void warn(String message, String context) {
        var data = generateMessage(context, message, "warn");

        System.out.print(data);
    }

    public void info(String message) {
        var data = generateMessage(context, message, "info").getBytes();

        try {
            out.write(data);
        } catch (IOException exception) {
            error("Cannot write log to stream", "Default");
            exception.printStackTrace();
        }
    }

    public void error(String message) {
        var data = generateMessage(context, message, "error").getBytes();

        try {
            err.write(data);
        } catch (IOException exception) {
            error("Cannot write log to stream", "Default");
            exception.printStackTrace();
        }
    }

    public void warn(String message) {
        var data = generateMessage(context, message, "warn").getBytes();

        try {
            out.write(data);
        } catch (IOException exception) {
            error("Cannot write log to stream", "Default");
            exception.printStackTrace();
        }
    }
}
