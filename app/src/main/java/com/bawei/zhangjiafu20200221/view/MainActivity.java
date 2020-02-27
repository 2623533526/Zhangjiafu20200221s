package com.bawei.zhangjiafu20200221;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.homepage_rb)
    RadioButton homepageRb;
    @BindView(R.id.search_rb)
    RadioButton searchRb;
    @BindView(R.id.shopping_rb)
    RadioButton shoppingRb;
    @BindView(R.id.order_rb)
    RadioButton orderRb;
    @BindView(R.id.my_rb)
    RadioButton myRb;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.bt)
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String user_image = getIntent().getStringExtra("image");
        Glide.with(MainActivity.this).load(user_image).into(image);
        ArrayList<Fragment> list = new ArrayList<>();
        //4、创建5个Fragment，每个Fragment上都有一个不同的文字如首页，我的等（20分）
        HomePage_Fragment fragment_1 = new HomePage_Fragment();
        Search_Fragment fragment_2 = new Search_Fragment();
        Shopping_Fragment fragment_3 = new Shopping_Fragment();
        Order_Fragment fragment_4 = new Order_Fragment();
        My_Fragment fragment_5 = new My_Fragment();
        list.add(fragment_1);
        list.add(fragment_2);
        list.add(fragment_3);
        list.add(fragment_4);
        list.add(fragment_5);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        //5、把5个Fragment和底部导航关联起来（20分）
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.homepage_rb: {
                        vp.setCurrentItem(0);
                    }
                    break;
                    case R.id.search_rb: {
                        vp.setCurrentItem(1);
                    }
                    break;
                    case R.id.shopping_rb: {
                        vp.setCurrentItem(2);
                    }
                    break;
                    case R.id.order_rb: {
                        vp.setCurrentItem(3);
                    }
                    break;
                    case R.id.my_rb: {
                        vp.setCurrentItem(4);
                    }
                    break;
                }
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg.check(rg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }
}
