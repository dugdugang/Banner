//package com.jpyl.banner;
//
//import android.os.Handler;
//import android.os.Message;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
//
//    private ViewPager viewPager;
//    private int[] ims;
//    private ArrayList<ImageView> imageList;
//    private LinearLayout ll;
//    private String[] str;
//    private TextView tv;
//    private int prePosition;
//    private Timer timer;
//    private Banner banner;
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 0x1111)
//                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
//
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        //banner = (Banner) findViewById(R.id.banner);
//        initViews();
//        initDatas();
//        initAdapter();
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//               // handler.sendEmptyMessage(0x1111);
//                Log.i("MYTAG", "MYTAG----sss");
//            }
//        }, 2000, 5000);
////        ArrayList<Integer> list = new ArrayList<Integer>();
////        list.add(R.drawable.a);
////        list.add(R.drawable.b);
////        list.add(R.drawable.c);
////        list.add(R.drawable.d);
////        list.add(R.drawable.e);
////        banner.setIms(list);
////        ArrayList<String> list1 = new ArrayList<String>();
////        list1.add("1");
////        list1.add("2");
////        list1.add("3");
////        list1.add("4");
////        list1.add("5");
////        banner.setDescribes(list1);
////        banner.init();
//
//    }
//
//    private void initAdapter() {
//        ll.getChildAt(0).setEnabled(true);
//        tv.setText(str[0]);
//        viewPager.setAdapter(new MyPageAdapter());
//        int posi = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageList.size();
//        viewPager.setCurrentItem(posi);
//    }
//
//    private void initDatas() {
//        ims = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};
//        str = new String[]{"A", "B", "C", "D", "E"};
//        imageList = new ArrayList<ImageView>();
//        ImageView imageView;
//        View view;
//        for (int i = 0; i < ims.length; i++) {
//            imageView = new ImageView(this);
//            imageView.setImageResource(ims[i]);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageList.add(imageView);
//            view = new View(this);
//            view.setBackgroundResource(R.drawable.selector);
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(10, 10);
//            if (i != 0)
//                lp.leftMargin = 10;
//            view.setEnabled(false);
//            ll.addView(view, lp);
//        }
//    }
//
//    private void initViews() {
//        viewPager = (ViewPager) findViewById(R.id.viewPager);
//        ll = (LinearLayout) findViewById(R.id.ll);
//        tv = (TextView) findViewById(R.id.tv);
//        viewPager.setOnPageChangeListener(this);
//
//    }
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//        int newPosition = position % imageList.size();
//        tv.setText(str[newPosition]);
//        ll.getChildAt(prePosition).setEnabled(false);
//        ll.getChildAt(newPosition).setEnabled(true);
//        prePosition = newPosition;
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//
//    class MyPageAdapter extends PagerAdapter {
//
//        @Override
//        public int getCount() {
//            return Integer.MAX_VALUE;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            int newPosition = position % imageList.size();
//            ImageView imageView = imageList.get(newPosition);
//            container.addView(imageView);
//
//            return imageView;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        timer.cancel();
//    }
//}
