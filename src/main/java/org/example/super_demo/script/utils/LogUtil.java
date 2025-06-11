package org.example.super_demo.script.utils;

import java.util.logging.Logger;
import java.util.logging.Level;

public final class LogUtil {
    private static final Logger logger = Logger.getLogger(LogUtil.class.getName());

    private LogUtil() {
        // 私有构造函数，防止实例化
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void info(String message, Object... args) {
        logger.info(String.format(message, args));
    }

    public static void error(String message) {
        logger.log(Level.SEVERE, message);
    }

    public static void error(String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }

    public static void error(String message, Object... args) {
        logger.log(Level.SEVERE, String.format(message, args));
    }
}
