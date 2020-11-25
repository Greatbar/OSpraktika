package com.bsbo_07_19turchenkov;

public class Producer implements Runnable {
    public void run() {
        do {
            synchronized (Main.list) {
                while (Main.list.size() > 100) {
                    try {
                        System.out.println("Товаров больше 100, потребители за работу. Производители ждут.");
                        Thread.sleep(1000);
                        Main.list.notify();
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
                }
            }
        } while (Main.isActive);

    }
}