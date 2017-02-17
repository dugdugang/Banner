package com.dg.bannerlib;

import android.content.Context;
import android.graphics.Color;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dg on 2017/2/10.
 * 广告轮播图
 */

public class Banner extends RelativeLayout implements ViewPager.OnPageChangeListener {
    private LinearLayout btmContainer;//banner底部容器
    private TextView describle;//文字描述
    private LinearLayout indicator;//指示器容器
    private ViewPager viewpager;
    private float density;//屏幕密度
    private ArrayList<Integer> imgs;//图片
    private ArrayList<String> dess;//描述
    private ArrayList<ImageView> imageViews;
    private MyPageAdapter adapter;
    private int prePosition;//前一个位置
    private Timer timer;
    private int duration = 5000;//轮播间隔时间
    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x1111)
                viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);

        }
    };

    public Banner(Context context) {
        super(context);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 初始化
     */
    public void init() {
        initViews();
        initData();
        initAdapter();
    }

    public void start() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x1111);
                Log.i("M-TAG", "" + viewpager.getCurrentItem());
            }
        }, 2000, duration);
    }

    public void cancel() {
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        adapter = new MyPageAdapter();
        viewpager.setAdapter(adapter);
        viewpager.setOnPageChangeListener(this);
        int middle = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imgs.size();
        viewpager.setCurrentItem(middle);
        indicator.getChildAt(0).setEnabled(true);
        describle.setText(dess.get(0));
    }

    /**
     * 设置时间间隔
     *
     * @param duration
     */
    private void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        imageViews = new ArrayList<ImageView>();
        ImageView iv;
        View view;
        for (int i = 0; i < imgs.size(); i++) {
            iv = new ImageView(getContext());
            iv.setImageResource(imgs.get(i));
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(iv);
            view = new View(getContext());
            view.setBackgroundResource(R.drawable.selector);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(10, 10);
            if (i != 0)
                lp.leftMargin = 10;
            view.setEnabled(false);
            indicator.addView(view, lp);
        }
    }

    /**
     * 初始化views
     */
    private void initViews() {
        this.measure(0, 0);
        //获取屏幕密度
        DisplayMetrics metric = getContext().getResources().getDisplayMetrics();
        density = metric.density;
        //viewpager
        ViewPager.LayoutParams lp_viewpager = new ViewPager.LayoutParams();
        lp_viewpager.width = ViewPager.LayoutParams.MATCH_PARENT;
        lp_viewpager.height = ViewPager.LayoutParams.MATCH_PARENT;
        viewpager = new ViewPager(getContext());
        viewpager.setLayoutParams(lp_viewpager);
        addView(viewpager);
        if (imgs.size() < 4) {
            viewpager.setOffscreenPageLimit(0);
        }
        // btmContainer
        LayoutParams lp_btmContainer = new LayoutParams(LayoutParams.MATCH_PARENT, (int) (40 * density));
        lp_btmContainer.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        lp_btmContainer.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        btmContainer = new LinearLayout(getContext());
        btmContainer.setGravity(Gravity.CENTER);
        btmContainer.setBackgroundColor(Color.parseColor("#66000000"));
        btmContainer.setOrientation(LinearLayout.VERTICAL);
        addView(btmContainer, lp_btmContainer);
        //describle
        LinearLayout.LayoutParams lp_tv = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_tv.gravity = Gravity.CENTER;
        lp_tv.setMargins(0, 0, 0, (int) (5 * density));
        describle = new TextView(getContext());
        describle.setGravity(Gravity.CENTER);
        describle.setTextSize(8 * density);
        describle.setTextColor(Color.WHITE);
        describle.setSingleLine(true);
        btmContainer.addView(describle, lp_tv);
        //indicator
        LinearLayout.LayoutParams lp_indicator = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_indicator.gravity = Gravity.CENTER;
        indicator = new LinearLayout(getContext());
        btmContainer.addView(indicator, lp_indicator);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int truePosition = getTruePosition(position);
        describle.setText(dess.get(truePosition));
        indicator.getChildAt(prePosition).setEnabled(false);
        indicator.getChildAt(truePosition).setEnabled(true);
        prePosition = truePosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 设置数据
     *
     * @param imgs
     * @param dess
     */
    public void setData(ArrayList<Integer> imgs, ArrayList<String> dess) {
        this.imgs = imgs;
        this.dess = dess;
        init();
    }

    /**
     * 获取真实位置
     *
     * @param position
     * @return
     */
    private int getTruePosition(int position) {
        return position % imgs.size();
    }

    /**
     * ViewPager的适配器
     */
    class MyPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;//复用
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //加载当前imageview
            ImageView imageView = imageViews.get(getTruePosition(position));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //移除+2项或-2项
            container.removeView((View) object);

        }
    }

}
