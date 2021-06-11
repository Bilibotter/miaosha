package com.example.miaosha.limit;

import java.io.IOException;

/**
 * @Author: YHM
 * @Date: 2021/6/10 10:45
 */
public class LimitStream {
    private static Integer limitValue = 5;
    private static String script;
    static {
        try{
            script = ScriptReader.getScript("limit.lua");
        } catch (IOException e) {
            System.out.println("Fail to load script!");
            e.printStackTrace();
        }
    }
}
