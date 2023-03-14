package com.mmwwdlxy.utils.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理uthread调度
 * <p>
 * 仅体现思维逻辑，调度算法写的太仓促，不可做任何使用，未经任何验证
 */
public class UThreadManager {

    private static final Map<UThread, Boolean> uThreads;
    private static final ThreadPool threadPool;
    private static Thread loopThread;
    private static final Runnable loopTask;

    static {
        uThreads = new ConcurrentHashMap<>();
        threadPool = new ThreadPool();
        loopTask = () -> {
            Worker worker;
            while (!uThreads.isEmpty()) {
                for (Map.Entry<UThread, Boolean> entry : uThreads.entrySet()) {
                    if (!entry.getValue() && (worker = threadPool.getWorker()) != null) {
                        uThreads.put(entry.getKey(), true);
                        worker.work(() -> entry.getKey().run());
                    }
                }
            }
            loopThread = null;
        };
    }

    public static boolean suspended(UThread uThread) {
        return uThreads.getOrDefault(uThread, true);
    }

    public static synchronized void register(UThread uThread) {
        uThreads.put(uThread, true);
        if (loopThread != null) {
            loopThread = new Thread(loopTask);
            loopThread.start();
        }
    }

    public static void unregister(UThread uThread) {
        uThreads.remove(uThread);
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
