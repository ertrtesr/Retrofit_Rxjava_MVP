package com.example.huangwenjian.retrofit_rxjava_mvp.utils;

import android.text.TextUtils;

import com.example.huangwenjian.retrofit_rxjava_mvp.entity.db_dao.Dao;
import com.example.huangwenjian.retrofit_rxjava_mvp.entity.db_entity.ThreadInfo;
import com.example.huangwenjian.retrofit_rxjava_mvp.entity.db_entity.User;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述: 数据库工具类,应根据不同的表建立对应的CRUD方法
 * <p/>
 * 时间: 16/9/1
 */
public class DBUtils {

    public static boolean insertUser(User user) {
        long insert = Dao.mUserDao.insert(user);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param cond     查询条件
     * @param condMore 更多查询条件
     * @return 数据集合
     */
    public static List<User> queryUser(WhereCondition cond, WhereCondition... condMore) {
        List<User> users = Dao.mUserDao.queryBuilder().where(cond, condMore).build().list();
        return users;
    }

    /**
     * 查询User表中所有数据
     *
     * @return 数据集合
     */
    public static List<User> queryUserAll() {
        List<User> users = Dao.mUserDao.queryBuilder().list();
        return users;
    }

    /**
     * 更新所有满足条件的User
     *
     * @param newUser
     * @param cond
     * @param condMore
     */
    public static int updateUser(User newUser, WhereCondition cond, WhereCondition... condMore) {
        int updateCount = 0;
        List<User> oldUsers = Dao.mUserDao.queryBuilder().where(cond, condMore).build().list();
        for (User oldUser : oldUsers) {
            try {
                //判断newUser的各个字段是否为空
                if (!TextUtils.isEmpty(newUser.getName())) {
                    oldUser.setName(newUser.getName());
                }
                if (newUser.getAge() != 0) {
                    oldUser.setAge(newUser.getAge());
                }
                if (!TextUtils.isEmpty(newUser.getPhone())) {
                    oldUser.setPhone(newUser.getPhone());
                }
                if (newUser.getBoy() != 0) {
                    oldUser.setBoy(newUser.getBoy());
                }
                if (newUser.getGirl() != 0) {
                    oldUser.setGirl(newUser.getGirl());
                }
                Dao.mUserDao.update(oldUser);       //此处的更新操作必须保证对象的地址值是之前的地址值
                updateCount++;
            } catch (Exception e) {
                updateCount = -1;
            }
        }
        return updateCount;
    }

    public static int deleteUser(WhereCondition cond, WhereCondition... condMore) {
        int deleteCount = 0;
        List<User> users = queryUser(cond, condMore);
        for (User user : users) {
            try {
                Dao.mUserDao.delete(user);
                deleteCount++;
            } catch (Exception e) {
                deleteCount = -1;
            }
        }
        return deleteCount;
    }

    /*=============================== 往ThreadInfo表中插入数据 ===================================*/
    public static void insertThread(ThreadInfo threadInfo) {

    }

    public static List<ThreadInfo> queryThread(WhereCondition cond, WhereCondition... condMore) {
        List<ThreadInfo> threadInfos = Dao.mThreadInfoDao.queryBuilder().where(cond, condMore).build().list();
        return threadInfos;
    }

    public static int updateThread(ThreadInfo newThreadInfo, WhereCondition cond, WhereCondition... condMore) {
        int updateCount = 0;
        List<ThreadInfo> oldThreadInfos = Dao.mThreadInfoDao.queryBuilder().where(cond, condMore).build().list();
        for (ThreadInfo oldThreadInfo : oldThreadInfos) {
            try {
                oldThreadInfo.setUrl(TextUtils.isEmpty(newThreadInfo.getUrl()) ? oldThreadInfo.getUrl() : newThreadInfo.getUrl());
                oldThreadInfo.setStart(newThreadInfo.getStart() == 0 ? 0 : newThreadInfo.getStart());
                oldThreadInfo.setEnd(newThreadInfo.getEnd() == 0 ? 0 : newThreadInfo.getEnd());
                oldThreadInfo.setFinished(newThreadInfo.getFinished() == 0 ? 0 : newThreadInfo.getFinished());
                Dao.mThreadInfoDao.update(oldThreadInfo);
                updateCount++;
            } catch (Exception e) {
                updateCount = -1;
            }
        }
        return updateCount;
    }

    public static int deleteThread(WhereCondition cond, WhereCondition condMore) {
        int deleteCount = 0;
        List<ThreadInfo> threadInfos = queryThread(cond, condMore);
        for (ThreadInfo threadInfo : threadInfos) {
            try {
                Dao.mThreadInfoDao.delete(threadInfo);
                deleteCount++;
            } catch (Exception e) {
                deleteCount = -1;
            }
        }
        return deleteCount;
    }

    public static boolean isExistThread(WhereCondition cond, WhereCondition condMore) {
        List<ThreadInfo> threadInfos = queryThread(cond, condMore);
        if (threadInfos != null && threadInfos.size() > 0) {
            return true;
        }
        return false;
    }
}
