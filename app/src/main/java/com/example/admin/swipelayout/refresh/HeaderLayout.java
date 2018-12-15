package com.example.admin.swipelayout.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.example.admin.swipelayout.R;

public class HeaderLayout extends RelativeLayout implements SwipeRefreshTrigger, SwipeTrigger {
    private ImageView mImgArrow;
    private ImageView mImgComplet;
    private TextView mTxtRefresh;
    private AnimationDrawable mAnimationDrawable;
    private AnimationDrawable mAnimationDrawableComplet;
    private int mHeight;

    public HeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mHeight = 80;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mImgArrow = findViewById(R.id.img_arrow);
        this.mTxtRefresh = findViewById(R.id.txt_refresh);
        this.mImgComplet = findViewById(R.id.img_complet);
        this.mAnimationDrawable = (AnimationDrawable) mImgArrow.getBackground();
        this.mAnimationDrawableComplet = (AnimationDrawable) mImgComplet.getBackground();

    }

    @Override
    public void onRefresh() {
        mTxtRefresh.setText("正在刷新");
        mAnimationDrawable.start();

    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            mImgComplet.setVisibility(GONE);
            mImgArrow.setVisibility(VISIBLE);
            mTxtRefresh.setVisibility(VISIBLE);
            mAnimationDrawable.stop();
            if (y > mHeight) {
                mTxtRefresh.setText("松开刷新");
            } else {
                mTxtRefresh.setText("下拉刷新");
            }

        }


    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
        mImgArrow.setVisibility(GONE);
        mImgComplet.setVisibility(VISIBLE);
        mTxtRefresh.setText("刷新完成");
        mAnimationDrawable.stop();
        startEndAnimation();
    }

    private void startEndAnimation() {
        mAnimationDrawableComplet.stop();
        mAnimationDrawableComplet.start();
    }

    @Override
    public void onReset() {
        mTxtRefresh.setText("下拉刷新");
        mAnimationDrawable.stop();
        mAnimationDrawableComplet.stop();
    }


}
