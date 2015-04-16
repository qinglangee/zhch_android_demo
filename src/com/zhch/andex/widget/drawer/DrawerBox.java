package com.zhch.andex.widget.drawer;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.zhch.andex.R;


public class DrawerBox extends FrameLayout {

	public static final int DIRECTION_LEFT = 0;
	public static final int DIRECTION_RIGHT = 1;
	private static final int PRESSED_MOVE_HORIZONTAL = 2;
	private static final int PRESSED_DOWN = 3;
	private static final int PRESSED_DONE = 4;
	private static final int PRESSED_MOVE_VERTICAL = 5;

	private LinearLayout scrollViewMenu;
	/** Current attaching activity. */
	private Activity activity;
	/** The DecorView of current activity. */
	private ViewGroup viewDecor;
	/** The flag of menu opening status. */

	TouchDisableView viewActivity;
	private boolean isOpened;

	private DisplayMetrics displayMetrics = new DisplayMetrics();
	private int pressedState = PRESSED_DOWN;

	private float lastRawX;
	// Valid scale factor is between 0.0f and 1.0f.
	private float mScaleValue = 0.5f;

	private float lastActionDownX, lastActionDownY;
	private float pressedX = 0;

	public DrawerBox(Context context) {
		super(context);
		this.setBackgroundColor(getResources().getColor(R.color.black));
		initViews(context);
	}

	private void initViews(Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.widget_drawer_box, this);
		scrollViewMenu = (LinearLayout) findViewById(R.id.sv_left_menu);
	}

	/**
	 * Set up the activity;
	 *
	 * @param activity
	 */
	public void attachToActivity(Activity activity) {
		initValue(activity);
		viewDecor.addView(this, 0);
	}

	private void initValue(Activity activity) {
		this.activity = activity;
		viewDecor = (ViewGroup) activity.getWindow().getDecorView();
		viewActivity = new TouchDisableView(this.activity);

		View first = viewDecor.getChildAt(0);
		viewDecor.removeViewAt(0);
		viewActivity.setContent(first);
		addView(viewActivity);
		// ViewGroup parent = (ViewGroup) scrollViewMenu.getParent();
	}

	public void setMenuView(int viewId) {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(viewId, scrollViewMenu);
	}

	public void setMenuView(View view) {
		scrollViewMenu.addView(view);
		view.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;
		view.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		float currentActivityScaleX = viewActivity.getScaleX(); // ViewHelper.getScaleX(viewActivity);

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastActionDownX = ev.getX();
			lastActionDownY = ev.getY();
			pressedState = PRESSED_DOWN;
			pressedX = viewActivity.getX();
			break;

		case MotionEvent.ACTION_MOVE:

			if (pressedState != PRESSED_DOWN && pressedState != PRESSED_MOVE_HORIZONTAL)
				break;

			int xOffset = (int) (ev.getX() - lastActionDownX);
			int yOffset = (int) (ev.getY() - lastActionDownY);

			if (pressedState == PRESSED_DOWN) {
				if (yOffset > 25 || yOffset < -25) {
					pressedState = PRESSED_MOVE_VERTICAL;
					break;
				}
				if (xOffset < -3 || xOffset > 3) {
					pressedState = PRESSED_MOVE_HORIZONTAL;
					ev.setAction(MotionEvent.ACTION_CANCEL);
				}
			} else if (pressedState == PRESSED_MOVE_HORIZONTAL) {
				// if (currentActivityScaleX < 0.95)
				// showScrollViewMenu(scrollViewMenu);

				viewActivity.setX(pressedX + ev.getX() - lastActionDownX);

				lastRawX = ev.getRawX();
				return true;
			}

			break;

		case MotionEvent.ACTION_UP:
			if (pressedState != PRESSED_MOVE_HORIZONTAL)
				break;

			pressedState = PRESSED_DONE;
			if (isOpened) {
				if ((ev.getX() - lastActionDownX) < -50)
					closeMenu();
				else
					openMenu();
			} else {
				if ((ev.getX() - lastActionDownX) > 50) {
					openMenu();
				} else {
					closeMenu();
				}
			}

			break;

		}
		lastRawX = ev.getRawX();
		return super.dispatchTouchEvent(ev);
	}

    private OnClickListener viewActivityOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isOpened) closeMenu();
        }
    };

    private Animator.AnimatorListener animationListener = new AnimatorListenerAdapter() {

        @Override
        public void onAnimationEnd(Animator animation) {
            if(isOpened){
                viewActivity.setTouchDisable(true);
                viewActivity.setOnClickListener(viewActivityOnClickListener);
            }else{
                viewActivity.setTouchDisable(false);
                viewActivity.setOnClickListener(null);
            }
        }
    };
	
	/**
	 * Show the menu;
	 */
	public void toggleMenu() {
		if(isOpened){
			closeMenu();
		}else{
			openMenu();
		}
	}

	/**
	 * Show the menu;
	 */
	public void openMenu() {

		isOpened = true;
		AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.anim.widget_drawer_open);
		set.addListener(animationListener);
		set.setTarget(viewActivity);
		set.start();
	}

	/**
	 * Close the menu;
	 */
	public void closeMenu() {

		isOpened = false;

		AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.anim.widget_drawer_close);
		set.addListener(animationListener);
		set.setTarget(viewActivity);
		set.start();
	}

}
