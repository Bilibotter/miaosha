package com.example.miaosha.redis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author: YHM
 * @Date: 2021/6/10 10:34
 */
public class ScriptReader {
    public static String getScript(String path) throws IOException {
        StringBuffer buffer = new StringBuffer();
        InputStream stream = ScriptReader.class.getClassLoader().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        while ((line = reader.readLine())!=null) {
            buffer.append(line).append(System.lineSeparator());
        }
        return buffer.toString();
    }
}
