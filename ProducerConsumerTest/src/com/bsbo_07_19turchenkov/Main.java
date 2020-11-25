package com.bsbo_07_19turchenkov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    public static boolean game = true;

    public static final LinkedList<Integer> list = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        PC store = new PC();
        /*Runnable r = ()-> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                reader.readLine();
                game = false;
                System.out.println("ЗначениFFFFFFFFFFFFFFе game теперь: " + game);
            } catch (IOException e) {
                e.printStackTrace();
                game = false;
                System.out.println("Значение game теперь: " + game);
            }
        };
        Thread myThread = new Thread(r);
        myThread.start();*/
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
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

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        consoleReader.readLine();

    }
}
