package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LoadInfo implements Runnable{
    public static ArrayList<String> eightBalls;
    private static ArrayList<String> privateEightBalls;

    public static ArrayList<String> wisdoms;
    private static ArrayList<String> privateWisdoms;

    @Override
    public void run() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    privateWisdoms = new ArrayList<>();
                    privateEightBalls = new ArrayList<>();

                    BufferedReader eightBallReader = new BufferedReader(new FileReader("eightballs.txt"));
                    eightBallReader.lines().forEach(privateEightBalls::add);

                    BufferedReader wisdomReader = new BufferedReader(new FileReader("wisdoms.txt"));
                    wisdomReader.lines().forEach(privateWisdoms::add);

                    if(!privateEightBalls.equals(eightBalls)){
                        eightBalls = privateEightBalls;
                    }

                    if(!privateWisdoms.equals(wisdoms)){
                        wisdoms = privateWisdoms;
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        new Timer().schedule(task, 0, 60_000);
    }
}
