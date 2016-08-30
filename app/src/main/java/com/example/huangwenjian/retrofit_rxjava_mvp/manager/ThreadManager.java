package com.example.huangwenjian.retrofit_rxjava_mvp.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 作者: huangwenjian
 * -
 * 描述: 线程池管理类,用于执行线程
 * -
 * 日期: 2016/8/22
 */
public class ThreadManager {
    public static final String SINGLE_POOL = "Single_Pool";

    private static ThreadPoolProxy mLongPool = null;
    private static Object mLongLock = new Object();

    private static ThreadPoolProxy mShortPool = null;
    private static Object mShortLock = new Object();

    private static Object mSingleLock = new Object();
    private static Map<String, ThreadPoolProxy> mMap = new HashMap<String, ThreadPoolProxy>();

    public static ThreadPoolProxy getSinglePool() {
        return getSinglePool(SINGLE_POOL);
    }

    public static ThreadPoolProxy getSinglePool(String name) {
        synchronized (mSingleLock) {
            ThreadPoolProxy singlePool = mMap.get(name);
            if (singlePool == null) {
                singlePool = new ThreadPoolProxy(1, 1, 10L);
                mMap.put(name, singlePool);
            }
            return singlePool;
        }
    }

    /**
     * 获取一个用于执行短耗时任务的线程池，避免因为和耗时长的任务处在同一个队列而长时间得不到执行，通常用来执行本地的IO/SQL
     */
    public static ThreadPoolProxy getShortPool() {
        synchronized (mShortLock) {
            if (mShortPool == null) {
                mShortPool = new ThreadPoolProxy(2, 2, 10L);
            }
            return mShortPool;
        }
    }

    /**
     * 获取一个用于执行长耗时任务的线程池，避免和短耗时任务处在同一个队列 而阻塞了重要的短耗时的任务，通常用联网操作s
     */
    public static ThreadPoolProxy getLongPool() {
        synchronized (mLongLock) {
            if (mLongPool == null) {
                mLongPool = new ThreadPoolProxy(4, 4, 10L);
            }
            return mLongPool;
        }
    }

    public static class ThreadPoolProxy {
        ThreadPoolExecutor mExecutor;            // 只需创建一次
        int mCorePoolSize;
        int mMaximumPoolSize;
        long mKeepAliveTime;

        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            super();
            mCorePoolSize = corePoolSize;
            mMaximumPoolSize = maximumPoolSize;
            mKeepAliveTime = keepAliveTime;
        }

        private ThreadPoolExecutor initThreadPoolExecutor() {//双重检查加锁
            if (mExecutor == null) {
                synchronized (ThreadPoolProxy.class) {
                    if (mExecutor == null) {
                        TimeUnit unit = TimeUnit.MILLISECONDS;
                        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();// 无界队列
                        ThreadFactory threadFactory = Executors.defaultThreadFactory();
                        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();// 丢弃任务并抛出RejectedExecutionException异常。
                        mExecutor = new ThreadPoolExecutor(//
                                mCorePoolSize,// 核心的线程数
                                mMaximumPoolSize,// 最大的线程数
                                mKeepAliveTime, // 保持时间
                                unit, // 保持时间对应的单位
                                workQueue,// 缓存队列/阻塞队列
                                threadFactory, // 线程工厂
                                handler// 异常捕获器
                        );
                    }
                }
            }
            return mExecutor;
        }

        /**
         * 执行任务
         */
        public void execute(Runnable task) {
            initThreadPoolExecutor();
            if (task != null) {
                mExecutor.execute(task);
            }
        }

        /**
         * 提交任务
         */
        public Future<?> submit(Runnable task) {
            initThreadPoolExecutor();
            if (task != null) {
                return mExecutor.submit(task);
            }
            return null;
        }

        /**
         * 移除任务
         */
        public void removeTask(Runnable task) {
            initThreadPoolExecutor();
            if (task != null) {
                mExecutor.remove(task);
            }
        }

        /**
         * 平缓关闭线程池
         */
        public void stop() {
            initThreadPoolExecutor();
            mExecutor.shutdown();
        }

        /**
         * 立即关闭线程池,正在执行的任务也会被关闭
         */
        public void stopNow() {
            initThreadPoolExecutor();
            mExecutor.shutdownNow();
        }
    }
}
