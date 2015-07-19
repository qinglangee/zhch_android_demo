package com.zhch.andex.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BasicPropertyAnimation extends BaseAct{
	// TODO ZHCH  View Animation
	// http://developer.android.com/guide/topics/graphics/view-animation.html#tween-animation

    @Bind(R.id.targetText)
    TextView mTargetText;
    @Bind(R.id.targetImage)
    ImageView mTargetImage;
    @Bind(R.id.targetFrame)
    ImageView mTargetFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_basic_prop_page);
        ButterKnife.bind(this);
        mTargetFrame.setBackgroundResource(R.drawable.anim_basic_frame_anim);
    }

    public void moveAndGone(View v){
        mTargetText.setX(0);
        mTargetText.setY(0);
        AnimatorSet set = (AnimatorSet)(AnimatorInflater.loadAnimator(this, R.animator.basic_prop_01));
        set.setTarget(mTargetText);
        set.start();
    }

    public void tweenAnim(View v){
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.basic_tween_01);
        mTargetImage.startAnimation(anim);
    }

    public void frameAnim(View v){
        AnimationDrawable anim = (AnimationDrawable)mTargetFrame.getBackground();
        anim.start();
    }
}
