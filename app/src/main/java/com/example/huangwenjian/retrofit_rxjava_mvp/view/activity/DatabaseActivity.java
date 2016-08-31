package com.example.huangwenjian.retrofit_rxjava_mvp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.example.huangwenjian.retrofit_rxjava_mvp.R;
import com.example.huangwenjian.retrofit_rxjava_mvp.base.BaseApplication;
import com.example.huangwenjian.retrofit_rxjava_mvp.entity.db_entity.User;
import com.example.huangwenjian.retrofit_rxjava_mvp.gen.UserDao.Properties;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述:
 * <p/>
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
        User user = new User();
        user.setId(1);
        user.setName("zhangsan");
        user.setAge(21);
        BaseApplication.mUserDao.insert(user);
    }

    @OnClick(R.id.btn_delete)
    void delete() {

    }

    @OnClick(R.id.btn_update)
    void update() {

    }

    @OnClick(R.id.btn_query)
    void query() {
        List<User> list = BaseApplication.mUserDao.queryBuilder().where(Properties.Id.eq(2)).list();
        for (User user : list) {
            int age = user.getAge();
            System.out.println(age);
        }
    }
}
