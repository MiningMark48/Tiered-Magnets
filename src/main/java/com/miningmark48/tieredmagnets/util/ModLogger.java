package com.miningmark48.tieredmagnets.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModLogger {

    private static Logger LOG = LogManager.getLogger();

    public static void debug(String msg) {
        LOG.debug(msg);
    }

    public static void error(String msg) {
        LOG.error(msg);
    }

    public static void fatal(String msg) {
        LOG.fatal(msg);
    }

    public static void info(String msg) {
        LOG.info(msg);
    }

    public static void trace(String msg) {
        LOG.trace(msg);
    }

    public static void warn(String msg) {
        LOG.warn(msg);
    }



    public static void debug(String msg, Object... args) {
        LOG.debug(String.format(msg, args));
    }

    public static void error(String msg, Object... args) {
        LOG.error(String.format(msg, args));
    }

    public static void fatal(String msg, Object... args) {
        LOG.fatal(String.format(msg, args));
    }

    public static void info(String msg, Object... args) {
        LOG.info(String.format(msg, args));
    }

    public static void trace(String msg, Object... args) {
        LOG.trace(String.format(msg, args));
    }

    public static void warn(String msg, Object... args) {
        LOG.warn(String.format(msg, args));
    }

}
