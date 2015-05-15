package com.zhch.andex.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

import com.zhch.andex.R;

public class CustomDrawableView extends View {
	private ShapeDrawable mDrawable;
	Paint paint;
	Bitmap bitmap;
	BitmapShader shader;
	Rect rect;
	RectF rectF;

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


	int x = 10;
	int y = 10;
	int width = 300;
	int height = 100;
	int index = 0;
	
	private int top(){
		return index * (height + 20);
	}
	private int r(){  // right
		return x + width;
	}
	private int b(){  // bottom
		return top() + height;
	}
	private void custom() {

		mDrawable = new ShapeDrawable(new OvalShape());
		mDrawable.getPaint().setColor(0xff74AC23);
		mDrawable.setBounds(x, y, x + width, y + height);
		

		bitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.image_example_small)).getBitmap();
		shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setShader(shader);

		index++;
		rectF = new RectF(x, top(), r(), b());
		
		index++;
		rect = new Rect(x, top(), r(), b());
	}
	
	protected void onDraw(Canvas canvas) {
		mDrawable.draw(canvas);
		


		float radius = 10.0f;
		// rect contains the bounds of the shape
		// radius is the radius in pixels of the rounded corners
		// paint contains the shader that will texture the shape
		canvas.drawRoundRect(rectF, radius, radius, paint);
		
		canvas.drawRect(rect, paint);;
	}
}
