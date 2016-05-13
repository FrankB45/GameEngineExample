package net.Andrewcpu.engine.utils;

/**
 * Created by stein on 5/12/2016.
 */
public class Log {
    private static boolean DEBUG = true;
    public static void setDebugMode(boolean debug) {
        DEBUG = debug;
    }
    public static boolean isDebug(){
        return DEBUG;
    }
    public static void i(String msg){
        m("INFO",msg);
    }
    public static void d(String msg){
        if(DEBUG)
            m("DEBUG",msg);
    }
    public static void m(String header, String msg){
        System.out.println("[" + header + "] " + msg);
    }
}

