package com.bsbo_07_19turchenkov;

public class Consumer implements Runnable {
 /*   PC pc = new PC();
    public void run() {
        while (true) {

            synchronized (this) {
                System.out.println("Запустился вайл в консумер");
                // consumer thread waits while list
                // is empty

                while (pc.list.size() == 0) {
                    System.out.println("Вайл с сайзом запустился");
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // to retrieve the first job in the list

                int val = pc.list.removeFirst();
                System.out.println("Дошел до удаления");
                System.out.println("Consumer consumed-" + val + " capacity:" + pc.list.size());

                // Wake up producer thread
                notify();

                // and sleep
                try {
                    System.out.println("В трае ждем");
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}*/

    PC store;
    PC pc = new PC();

    Consumer(PC store) {
        this.store = store;
    }
    public boolean isActive;
    void disable(){
        isActive=false;
    }
    Consumer(){
        isActive = true;
    }
    public void run() {
        do {
            synchronized (Main.list) {
                if (Main.list.size() < 80) {
                    try {
                        //Main.list.notify();
                        Main.list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //while (Main.list.size() > 0) {
                //System.out.println(pc.list.size());
            /*while (Main.list.size() < 80) {
                try {
                    Main.list.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
                int value = 1 + (int) (Math.random() * 10);
                for (int i = 0; i < value; i++) {
                    Main.list.remove(1);
                }
                System.out.println("Покупатель купил: " + value + " товар");
                System.out.println("Товаров на складе: " + Main.list.size());
                Main.list.notify();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //store.take();
        } while (Main.game = true);
        int value = 1 + (int) (Math.random() * 10);
        for (int i = 0; i < value; i++) {
            Main.list.remove(1);
        }
        System.out.println("Покупатель купил: " + value + " товар");
        System.out.println("Товаров на складе: " + Main.list.size());
        Main.list.notify();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}