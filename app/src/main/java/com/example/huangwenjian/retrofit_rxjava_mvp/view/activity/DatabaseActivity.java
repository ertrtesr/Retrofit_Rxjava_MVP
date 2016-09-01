package com.example.huangwenjian.retrofit_rxjava_mvp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.example.huangwenjian.retrofit_rxjava_mvp.R;
import com.example.huangwenjian.retrofit_rxjava_mvp.entity.db_dao.Dao;
import com.example.huangwenjian.retrofit_rxjava_mvp.entity.db_entity.User;
import com.example.huangwenjian.retrofit_rxjava_mvp.greendao.UserDao;
import com.example.huangwenjian.retrofit_rxjava_mvp.manager.ThreadManager;
import com.example.huangwenjian.retrofit_rxjava_mvp.utils.DBUtils;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/8/31
 */
public class DatabaseActivity extends Activity {

    @BindView(R.id.btn_add)
    Button mBtn_add;

    @BindView(R.id.btn_delete)
    Button mBtn_delete;

    @BindView(R.id.btn_update)
    Button mBtn_update;

    @BindView(R.id.btn_query)
    Button mBtn_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_add)
    void add() {
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("zhangsan");
            user.setAge(11);
            DBUtils.insertUser(user);
        }
    }

    @OnClick(R.id.btn_delete)
    void delete() {
//        List<User> list = Dao.mUserDao.queryBuilder().where(UserDao.Properties.Name.eq("zhangsan")).build().list();
//        for (User user : list) {
//            Dao.mUserDao.delete(user);
//        }

        final WhereCondition condition = UserDao.Properties.Name.eq("zhangsan");
        ThreadManager.getSinglePool().execute(new Runnable() {
            @Override
            public void run() {
                int count = DBUtils.deleteUser(condition);
            }
        });
    }

    @OnClick(R.id.btn_update)
    void update() {
        WhereCondition cond = UserDao.Properties.Name.eq("zhangsan");
        User newUser = new User();
        newUser.setName("lisi");
        DBUtils.updateUser(newUser, cond);
    }

    @OnClick(R.id.btn_query)
    void query() {
        List<User> list = Dao.mUserDao.queryBuilder().where(UserDao.Properties.Id.eq(1)).list();
        for (User user : list) {
            int age = user.getAge();
            System.out.println(age);
        }
    }
}
