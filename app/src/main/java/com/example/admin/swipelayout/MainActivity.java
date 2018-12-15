package com.example.admin.swipelayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.admin.swipelayout.refresh.HeaderLayout;

public class MainActivity extends AppCompatActivity {
    private HeaderLayout mSwipeRefreshHeader;
    private TextView mSwipeTarget;
    private SwipeToLoadLayout mSwipeToLoadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindListener();



    }


    private void initView() {
        mSwipeRefreshHeader = findViewById(R.id.swipe_refresh_header);
        mSwipeTarget = findViewById(R.id.swipe_target);
        mSwipeToLoadLayout = findViewById(R.id.swipeToLoadLayout);
    }

    private void bindListener() {
        mSwipeToLoadLayout.setRefreshEnabled(true);
        mSwipeToLoadLayout.setLoadMoreEnabled(true);
        mSwipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clearLoadLayoutState();
                    }
                }, 2000);
            }
        });

        mSwipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                clearLoadLayoutState();
            }
        });

    }

    private void clearLoadLayoutState() {
        mSwipeToLoadLayout.setRefreshCompleteDelayDuration(1000);
        mSwipeToLoadLayout.setLoadMoreCompleteDelayDuration(650);
        mSwipeToLoadLayout.setRefreshing(false);
    }
}
