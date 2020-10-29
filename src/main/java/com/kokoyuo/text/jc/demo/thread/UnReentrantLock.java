package com.kokoyuo.text.jc.demo.thread;

import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author lixuanwen
 * @date 2020-10-28 10:21
 */
public class UnReentrantLock extends AbstractQueuedSynchronizer {

    public void lock(){
        acquire(1);
    }

    public void unlock(){
        release(1);
    }

    @Override
    protected boolean tryAcquire(int arg) {
        int state = getState();
        final Thread thread = Thread.currentThread();
        if (state == 0){
            compareAndSetState(0, arg);
            setExclusiveOwnerThread(thread);
            return true;
        } else if (thread == getExclusiveOwnerThread()){
            throw new RuntimeException("不可重入");
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        final Thread thread = Thread.currentThread();
        if (thread != getExclusiveOwnerThread()){
            throw new IllegalMonitorStateException("UnReentrantLock unlock error");
        }
        setState(0);
        setExclusiveOwnerThread(null);
        return true;
    }

}
