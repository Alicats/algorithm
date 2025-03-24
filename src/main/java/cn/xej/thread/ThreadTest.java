package cn.xej.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ThreadTest {
    /**
     * 有A、B、C 三个线程，如何保证三个线程同时执行？
     * 思路：通过CountDownLatch实现
     * 调用await方法的线程会进入等待状态，直到CountDownLatch的计数器值减到0
     * 当计数器减到0时，所有因调用await方法而阻塞的线程会被唤醒
     */
    public static void main2(String[] args) throws InterruptedException {
        int size = 3;
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("wait");
        Thread.sleep(5000);
        latch.countDown();
    }


    /**
     *  有A、B、C 三个线程，如何在并发情况下保证三个线程依次执行？
     *  思路：通过使用volatile 强制线程每次读写volatile变量时都直接操作主内存，确保修改后的值对其他线程可见
     */

    private static volatile int ticket = 1;

    public static void main3(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (ticket == 1) {
                    try {
                        Thread.sleep(100);
                        for (int i = 0; i < 10; i++) {
                            System.out.println("a" + i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticket = 2;
                    return;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                if (ticket == 2) {
                    try {
                        Thread.sleep(100);
                        for (int i = 0; i < 10; i++) {
                            System.out.println("b" + i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticket = 3;
                    return;
                }
            }
        });

        Thread t3 = new Thread(() -> {
            while (true) {
                if (ticket == 3) {
                    try {
                        Thread.sleep(100);
                        for (int i = 0; i < 10; i++) {
                            System.out.println("c" + i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }


    /**
     * 有A、B、C 三个线程，如何保证三个线程有序交错进行？
     * 思路：通过信号量来控制同一时间最多允许1个线程访问受保护的资源
     * acquire方法尝试获取许可证，若信号量的许可证数量大于0，线程会立即获取并继续执行
     * 如果许可证数量为0，线程则会阻塞，直到其他线程调用release方法释放许可证或线程被中断
     */
    // 利用信号量来限制
    private static Semaphore s1 = new Semaphore(1);
    private static Semaphore s2 = new Semaphore(1);
    private static Semaphore s3 = new Semaphore(1);
    public static void main4(String[] args) {
        try {
            s1.acquire();
            s2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (true) {
                try {
                    s1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A");
                s2.release();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    s2.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B");
                s3.release();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    s3.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("C");
                s1.release();
            }
        }).start();
    }

}
