package com.bsbo_07_19turchenkov;

public class Consumer implements Runnable {

    public void run() {
        synchronized (Main.list) {
        while (Main.isActive) {

                if (Main.list.size() <= 80) {
                    try {
                        System.out.println("Товаров меньше 80, производители за работу. Потребители ждут.");
                        Thread.sleep(1000);
                        Main.list.notify();
                        Main.list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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
                    System.out.println("Thread has been interrupted");
                }
            }
        }

        synchronized (Main.list) {
            while (!Thread.currentThread().isInterrupted()) {
                do {
                    try {
                        int value = 1 + (int) (Math.random());
                        for (int i = 0; i < value; i++) {
                            Main.list.remove(1);
                        }
                        System.out.println("Покупатель купил: " + value + " товар");
                        System.out.println("Товаров на складе: " + Main.list.size());
                        Main.list.notify();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();

                        }
                    } catch (Exception ignored) {

                    }
                } while (Main.list.size() > 1);
                if (Main.list.size() == 1) {
                    Main.list.removeFirst();
                    System.out.println("Беру последний товар, теперь их на складе: " + Main.list.size());
                }
            }
        }
    }
}