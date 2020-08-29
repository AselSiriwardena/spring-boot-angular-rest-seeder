package com.demo.springbootmysqlrestdemo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FrontendApplication {
    public static void runApp() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "..","ls");
        builder.redirectErrorStream(true);
        final Process process = builder.start();

        watch(process);
    }

    private static void watch(final Process process) {
        new Thread() {
            public void run() {
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = null;
                try {
                    while ((line = input.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
