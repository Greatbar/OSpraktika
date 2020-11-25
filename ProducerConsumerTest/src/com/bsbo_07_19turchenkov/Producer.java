package com.bsbo_07_19turchenkov;

public class Producer implements Runnable {
    /*    PC pc = new PC();
        public void run() {
            int value = 0;
            while (true) {
                synchronized (this) {
                    // producer thread waits while list
                    // is full
                    while (pc.list.size() == pc.capacity) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("Producer produced-" + value + " capacity:" + pc.list.size());

                    // to insert the jobs in the list
                    pc.list.add(value++);

                    // notifies the consumer thread that
                    // now it can start consuming
                    notify();

                    // makes the working of program easier
                    // to  understand
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }*/

    PC store;

    Producer(PC store) {
        this.store = store;
    }


    public void run() {
        do {
            synchronized (Main.list) {
                while (Main.list.size() >= 100) {
                    try {
                        //Main.list.notify();
                        Main.list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (Main.list.size() <= 80) {

                    int value = 1 + (int) (Math.random() * 100);
                    for (int i = 0; i < value; i++) {
                        Main.list.add(1);
                    }
                    System.out.println("Производитель добавил: " + value + " товар");
                    System.out.println("Товаров на складе: " + Main.list.size());
                    Main.list.notify();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    //store.put();

                }

            }
        } while (Main.game = true);
        System.out.println("Значение game в продюсерах: " + Main.game);
        notifyAll();
    }
}