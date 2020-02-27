package com.bawei.zhangjiafu20200221;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.EncryptUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePageActivity extends AppCompatActivity {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.register)
    Button register;
    //1、完成图2界面（20分）
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(phone.getText().toString())&&TextUtils.isEmpty(pwd.getText().toString())){
                    Toast.makeText(HomePageActivity.this, "不可为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String user_phone= phone.getText().toString();
                String user_pwd = EncryptUtils.encryptMD5ToString(pwd.getText().toString()).substring(0, 8);
                ArrayMap<String, String> map = new ArrayMap<>();
                map.put("phone",user_phone);
                map.put("pwd",user_pwd);
                NetUlits.getInstance().create(MyService.class).getRegister(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Register_Entity>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Register_Entity register_entity) {
                                Toast.makeText(HomePageActivity.this, register_entity.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });


            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(phone.getText().toString())&&TextUtils.isEmpty(pwd.getText().toString())){
                    Toast.makeText(HomePageActivity.this, "不可为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String user_phone= phone.getText().toString();
                String user_pwd = EncryptUtils.encryptMD5ToString(pwd.getText().toString()).substring(0, 8);
                ArrayMap<String, String> map = new ArrayMap<>();
                map.put("phone",user_phone);
                map.put("pwd",user_pwd);
                NetUlits.getInstance().create(MyService.class).getLogin(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Login_Entity>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Login_Entity login_entity) {
                               if(login_entity.getStatus().equals("0000")){
                                   Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
                                   intent.putExtra("image",login_entity.getResult().getHeadPic());
                                   startActivity(intent);
                                   finish();
                               }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

    }
}
