package com.example.huangwenjian.retrofit_rxjava_mvp.utils;

import com.example.huangwenjian.retrofit_rxjava_mvp.entity.db_dao.Dao;
import com.example.huangwenjian.retrofit_rxjava_mvp.entity.db_entity.User;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
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
     * @param cond
     * @param condMore
     * @return
     */
    public static List<User> queryUser(WhereCondition cond, WhereCondition... condMore) {
        List<User> users = Dao.mUserDao.queryBuilder().where(cond, condMore).build().list();
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
        List<User> oldUsers = Dao.mUserDao.queryBuilder().where(cond).build().list();
        for (User oldUser : oldUsers) {
            try {
                //不能设置id,否则变更不了
                oldUser.setName(newUser.getName());
                oldUser.setAge(newUser.getAge());
                oldUser.setPhone(newUser.getPhone());
                oldUser.setBoy(newUser.getBoy());
                oldUser.setGirl(newUser.getGirl());
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
}
