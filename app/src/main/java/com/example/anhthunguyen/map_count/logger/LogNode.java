package com.example.anhthunguyen.map_count.logger;

/**
 * Created by 12130 on 1/9/2016.
 */
public interface LogNode {

    public void println(int priority, String tag, String msg, Throwable tr);
}
