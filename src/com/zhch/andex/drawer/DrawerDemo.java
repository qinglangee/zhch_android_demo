package com.zhch.andex.drawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.zhch.andex.R;
import com.zhch.andex.widget.drawerbox.DrawerBox;

public class DrawerDemo extends Activity{

	DrawerBox drawer;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout_panel);
        ((TextView)findViewById(R.id.title)).setText("DrawerDemo Activity");
        setUpMenu();
    }
	
	private void setUpMenu() {

        // attach to current activity;
		drawer = new DrawerBox(this);
        drawer.attachToActivity(this);
        drawer.setMenuView(R.layout.drawer_layout_menu);
    }
	
	@Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return drawer.dispatchTouchEvent(ev);
    }
}
