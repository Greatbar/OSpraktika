package com.bsbo_07_19turchenkov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    static void disable(){
        isActive=false;
    }
    public static boolean isActive = true;
    public static final LinkedList<Integer> list = new LinkedList<>();
    public static void main(String[] args) {
        Runnable KeyCheck = ()-> {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                consoleReader.readLine();
                disable();
            } catch (IOException e) {
                e.printStackTrace();

            }
        };
        Thread KeyCheckThread = new Thread(KeyCheck);
        KeyCheckThread.start();
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        Thread t1_prod = new Thread(producer);
        Thread t2_prod = new Thread(producer);
        Thread t3_prod = new Thread(producer);
        Thread t4_con = new Thread(consumer);
        Thread t5_con = new Thread(consumer);

        t1_prod.start();
        t2_prod.start();
        t3_prod.start();
        t4_con.start();
        t5_con.start();
        
    }
}
