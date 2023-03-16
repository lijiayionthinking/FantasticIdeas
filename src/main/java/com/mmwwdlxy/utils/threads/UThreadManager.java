package com.mmwwdlxy.utils.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理uthread调度
 * <p>
 * 仅体现思维逻辑，不可做任何使用，未经任何验证
 */
public class UThreadManager {

    private static final Map<UThread, Byte> uThreads;
    private static final ThreadPool threadPool;
    private static Thread loopThread;
    private static final Object loopLock = new Object();
    private static final Runnable loopTask;

    private static final byte suspendedLimits = 10;
    private static final byte one = 1;
    private static final byte SUSPENDED = 1;
    private static final byte RUNNING = 2;

    static {
        uThreads = new ConcurrentHashMap<>();
        threadPool = new ThreadPool();
        loopTask = () -> {
            Worker worker;
            while (!uThreads.isEmpty()) {
                for (Map.Entry<UThread, Byte> entry : uThreads.entrySet()) {
                    if (entry.getValue().equals(SUSPENDED) && (worker = threadPool.getWorker()) != null) {
                        uThreads.put(entry.getKey(), RUNNING);
                        worker.work(() -> entry.getKey().run());
                    } else if (entry.getValue().equals(RUNNING)) {
                        synchronized (entry.getKey()) {
                            byte tByte = (byte) (entry.getValue() + one);
                            if (tByte > suspendedLimits){
                                uThreads.put(entry.getKey(), SUSPENDED);
                            }else {
                                uThreads.put(entry.getKey(), tByte);
                            }
                        }
                    }
                }
            }
            synchronized (loopLock) {
                if (uThreads.isEmpty()) {
                    loopThread = null;
                }
            }
        };
    }

    public static boolean suspended(UThread uThread) {
        return uThreads.getOrDefault(uThread, SUSPENDED).equals(SUSPENDED);
    }

    public static void register(UThread uThread) {
        uThreads.put(uThread, SUSPENDED);
        synchronized (loopLock) {
            if (loopThread != null) {
                loopThread = new Thread(loopTask);
                loopThread.start();
            }
        }
    }

    public static void unregister(UThread uThread) {
        uThreads.remove(uThread);
    }

    public static void refresh(UThread uThread) {
        synchronized (uThread) {
            uThreads.put(uThread, RUNNING);
        }
    }

    public static boolean isRunning(UThread uThread) {
        return uThreads.getOrDefault(uThread, SUSPENDED) > SUSPENDED;
    }

    public static class ThreadPool {

        private final int workerNumbers = 4;
        List<Worker> workers = new ArrayList<>(workerNumbers);

        public ThreadPool() {
            for (int i = 0; i < workerNumbers; i++) {
                workers.add(new Worker(null));
            }
        }

        public Worker getWorker() {
            for (Worker worker : workers) {
                if (worker.task == null) {
                    return worker;
                }
            }
            return null;
        }
    }

    public static class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        public void work(Runnable task) {
            this.task = task;
            start();
        }

        public void work() {
            start();
        }

        @Override
        public void run() {
            task.run();
            task = null;
        }
    }
}
