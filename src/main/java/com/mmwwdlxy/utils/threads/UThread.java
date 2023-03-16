package com.mmwwdlxy.utils.threads;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1、多个uthread可以并发执行
 * 2、uthread执行可以被暂停
 * 3、uthread暂停可以被唤起
 * 4、在jvm中启动调度程序
 * 5、使用java的thread为执行载体
 * 6、可以实现少数thread执行多个uthread
 * <p>
 * 仅体现思维逻辑，未经任何验证
 */
public class UThread {

    private final Queue<CheckPoint> methodQueue;

    public UThread() {
        methodQueue = new LinkedList<>();
        UThreadManager.register(this);
    }

    public void mark(CheckPoint checkPoint) {
        synchronized (this) {
            if (UThreadManager.isRunning(this)) {
                throw new UnsupportedOperationException("UThread is running");
            }
            methodQueue.add(checkPoint);
        }
    }

    public void run() {
        if (isOver()) return;
        synchronized (this) {
            UThreadManager.refresh(this);
            while (!UThreadManager.suspended(this) && !methodQueue.isEmpty()) {
                methodQueue.poll().run();
                UThreadManager.refresh(this);
            }
        }
        isOver();
    }

    private boolean isOver() {
        if (methodQueue.isEmpty()) {
            UThreadManager.unregister(this);
            return true;
        }
        return false;
    }
}
