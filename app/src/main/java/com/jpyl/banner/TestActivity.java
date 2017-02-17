package com.jpyl.banner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dg.bannerlib.Banner;

import java.util.ArrayList;

/**
 * Created by dg on 2017/2/10.
 */

public class TestActivity extends AppCompatActivity {
    private Banner banner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        banner = (Banner) findViewById(R.id.banner);
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(R.drawable.a);
        list.add(R.drawable.b);
        list.add(R.drawable.c);
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        banner.setData(list, list1);
        banner.start();
        Log.i("M-TAG","mmmmm");

    }

//    public void click(View view) {
//        startActivity(new Intent(TestActivity.this, MainActivity.class));
//    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("M-TAG","pause");
        banner.cancel();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        banner.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        banner.cancel();
    }
}
