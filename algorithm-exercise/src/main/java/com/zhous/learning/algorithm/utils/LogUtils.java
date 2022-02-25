package com.zhous.learning.algorithm.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日志打印工具类
 *
 * @author Zhou Changjian
 * @version 1.0
 * @since 2021/4/8
 */
public class LogUtils {

    private static final String INFO = "INFO";
    private static final String ERROR = "ERROR";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void out(String msg) {
        System.out.println(msg);
    }

    public static void out(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    public static void outError(String msg) {
        System.err.println(msg);
    }

    public static void outError(String msg, Object... args) {
        System.err.println(String.format(msg, args));
    }

    public static void info(String msg) {
        System.out.println(getPrefix(INFO) + msg);
    }

    public static void info(String msg, Object... args) {
        System.out.println(getPrefix(INFO) + String.format(msg, args));
    }

    public static void error(String msg) {
        System.out.println(getPrefix(ERROR) + msg);
    }

    public static void error(String msg, Object... args) {
        System.out.println(getPrefix(ERROR) + String.format(msg, args));
    }

    private static String getPrefix(String level) {
        return time() + " " + level + " ";
    }

    private static String time() {
        return FORMAT.format(LocalDateTime.now());
    }
}