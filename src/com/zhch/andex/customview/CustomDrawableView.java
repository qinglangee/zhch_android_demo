package com.zhch.andex.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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

    Path path;

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

    // 计算边框位置
	private int top(int index){
		return index * (height + 20);
	}
	private int r(){  // right
		return x + width;
	}
	private int b(int index){  // bottom
		return top(index) + height;
	}
	/**
	 * 画圆角图片
	 * http://www.curious-creature.com/2012/12/11/android-recipe-1-image-with-rounded-corners/
	 */
	private void custom() {

        // 一个椭圆
		mDrawable = new ShapeDrawable(new OvalShape());
		mDrawable.getPaint().setColor(0xff74AC23);
		mDrawable.setBounds(x, y, x + width, y + height);
		

        // 一个图片
		bitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.image_example_small)).getBitmap();
		shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setShader(shader);

		// index 控制　top() 大小
		rectF = new RectF(x, top(1), r(), b(1));
		
		// index 控制　top() 大小
		rect = new Rect(x, top(2), r(), b(2));


        // 一个自定义图形
        path = createPath();
	}

    // 一个自定义图形 path
    private Path createPath(){
        Path path = new Path();
//        Paint paintN = new Paint();
//        paintN.setAntiAlias(true);
//        paintN.setStyle(Paint.Style.FILL_AND_STROKE);
//        paintN.setColor(Color.YELLOW);
        int pTop = top(4) ;
        int radius = 50; // radius

        // 起始点
        int px = 40;
        int py = top(4) + radius;

        // 控制点１
        int c1x = px;
        int c1y = py - radius;

        // 第二个点　（左上弯角终点）
        int p2x = px + radius;
        int p2y = c1y;

        // 第三个点
        int p3x = p2x + 120;
        int p3y = p2y;

        // 第二个控制点
        int c2x = p3x + radius;
        int c2y = p3y;

        // 第四个点
        int p4x = c2x;
        int p4y = c2y + radius;

        // 第五个点
        int p5x = p4x;
        int p5y = p4y + 30;

        // 终点　（第６个点）
        int p6x = px;
        int p6y = py + 150;

        path.moveTo(px, py);
        path.quadTo(c1x,c1y, p2x, p2y);
        path.lineTo(p3x, p3y);
        path.quadTo(c2x, c2y, p4x, p4y);
        path.lineTo(p5x, p5y);
        path.lineTo(p6x, p6y);
        path.close();

        return path;
    }
	
	protected void onDraw(Canvas canvas) {

        // 画椭圆
		mDrawable.draw(canvas);
		


		float radius = 10.0f;
		// 画方形, paint　中包含了要画的图片
		canvas.drawRoundRect(rectF, radius, radius, paint);

        // 画另一个方形
		canvas.drawRect(rect, paint);


        canvas.drawPath(path, paint);
	}
}
