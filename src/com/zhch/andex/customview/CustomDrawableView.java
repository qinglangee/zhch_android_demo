package com.zhch.andex.customview;

import android.R;
import android.content.Context;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

public class CustomDrawableView extends View {
	private ShapeDrawable mDrawable;

	public CustomDrawableView(Context context) {
		super(context);
		custom();
	}

	
	/**
	 * View(Context, AttributeSet) constructor, which is called when instantiating a View via inflation from XML
	 * @param context
	 * @param set
	 */
	public CustomDrawableView(Context context, AttributeSet set) {
		super(context, set);
		custom();
	}


	private void custom() {
		int x = 10;
		int y = 10;
		int width = 300;
		int height = 50;

		mDrawable = new ShapeDrawable(new OvalShape());
		mDrawable.getPaint().setColor(0xff74AC23);
		mDrawable.setBounds(x, y, x + width, y + height);
		
		
//		Bitmap bitmap = getResources().getDrawable(R.drawable)
//		BitmapShader shader;
//		shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//
//		Paint paint = new Paint();
//		paint.setAntiAlias(true);
//		paint.setShader(shader);
//
//		RectF rect = new RectF(0.0f, 0.0f, width, height);
//
//		// rect contains the bounds of the shape
//		// radius is the radius in pixels of the rounded corners
//		// paint contains the shader that will texture the shape
//		canvas.drawRoundRect(rect, radius, radius, paint);
	}
	
	protected void onDraw(Canvas canvas) {
		mDrawable.draw(canvas);
	}
}
