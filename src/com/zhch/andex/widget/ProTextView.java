package com.zhch.andex.widget;


import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.zhch.andex.R;
import com.zhch.andex.util.LL;

/**
 * Created by zhch on 15-6-17.
 * 自定义TextView, 可以设置边框和图片大小
 */
public class ProTextView extends TextView {
    Paint fillpaint, strokepaint;

    private int leftHeight = -1;
    private int leftWidth = -1;
    private int rightHeight = -1;
    private int rightWidth = -1;
    private int topHeight = -1;
    private int topWidth = -1;
    private int bottomHeight = -1;
    private int bottomWidth = -1;

    private float borderWidth = -1;
    private float borderRadius = -1;
    private int borderColor;
    private final RectF mDrawableRect = new RectF();
    private final RectF mBorderRect = new RectF();

    public ProTextView(Context context) {
        super(context);
    }

    public ProTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ProTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // 一定要先调用　super　的构造函数
        init(context, attrs, defStyle);
    }

    /**
     * 初始化读取参数
     */
    private void init(Context context, AttributeSet attrs, int defStyle) {
        // TypeArray中含有我们需要使用的参数
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ProTextView, defStyle, 0);
        if (a != null) {
            // 获得参数个数
            int count = a.getIndexCount();
            int index = 0;
            // 遍历参数。先将index从TypedArray中读出来，
            // 得到的这个index对应于attrs.xml中设置的参数名称在R中编译得到的数
            // 这里会得到各个方向的宽和高
            for (int i = 0; i < count; i++) {
                index = a.getIndex(i);
                switch (index) {
                    case R.styleable.ProTextView_bottom_height:
                        bottomHeight = a.getDimensionPixelSize(index, -1);
                        break;
                    case R.styleable.ProTextView_bottom_width:
                        bottomWidth = a.getDimensionPixelSize(index, -1);
                        break;
                    case R.styleable.ProTextView_left_height:
                        leftHeight = a.getDimensionPixelSize(index, -1);
                        break;
                    case R.styleable.ProTextView_left_width:
                        leftWidth = a.getDimensionPixelSize(index, -1);
                        break;
                    case R.styleable.ProTextView_right_height:
                        rightHeight = a.getDimensionPixelSize(index, -1);
                        break;
                    case R.styleable.ProTextView_right_width:
                        rightWidth = a.getDimensionPixelSize(index, -1);
                        break;
                    case R.styleable.ProTextView_top_height:
                        topHeight = a.getDimensionPixelSize(index, -1);
                        break;
                    case R.styleable.ProTextView_top_width:
                        topWidth = a.getDimensionPixelSize(index, -1);
                        break;
                    case R.styleable.ProTextView_border_width:
                        borderWidth = a.getDimensionPixelSize(index, -1);
                        break;
                    case R.styleable.ProTextView_border_radius:
                        borderRadius = a.getDimensionPixelSize(index, -1);
                        break;
                    case R.styleable.ProTextView_border_color:
                        borderColor = a.getColor(index, 0);
                        break;
                }
            }

            // 获取各个方向的图片，按照：左-上-右-下 的顺序存于数组中
            Drawable[] drawables = getCompoundDrawables();
            int dir = 0;
            // 0-left; 1-top; 2-right; 3-bottom;
            for (Drawable drawable : drawables) {
                // 设定图片大小
                setImageSize(drawable, dir++);
            }
            // 将图片放回到TextView中
            setCompoundDrawables(drawables[0], drawables[1], drawables[2],
                    drawables[3]);

        }

    }

    /**
     * 设定图片的大小
     */
    private void setImageSize(Drawable d, int dir) {
        if (d == null) {
            return;
        }

        int height = -1;
        int width = -1;
        // 根据方向给宽和高赋值，高度没有赋值就默认使用宽度值
        switch (dir) {
            case 0:
                // left
                width = leftWidth;
                height = leftHeight > 0 ? leftHeight : leftWidth;
                break;
            case 1:
                // top
                width = topWidth;
                height = topHeight > 0 ? topHeight : topWidth;
                break;
            case 2:
                // right
                width = rightWidth;
                height = rightHeight > 0 ? rightHeight : rightWidth;
                break;
            case 3:
                // bottom
                width = bottomWidth;
                height = bottomHeight > 0 ? bottomHeight : bottomWidth;
                break;
        }
        // 如果有某个方向的宽或者高没有设定值，则不去设定图片大小
        if (width != -1 && height != -1) {
            d.setBounds(0, 0, width, height);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(borderColor != 0){ // 设置了边框就画边框
            fillpaint = this.getPaint();
            strokepaint = new Paint(fillpaint);
            strokepaint.setStyle(Paint.Style.STROKE);  // 画线条
            strokepaint.setStrokeWidth(borderWidth); // 宽度
            strokepaint.setColor(borderColor);  // 颜色

            mBorderRect.set(0,0,getWidth(), getHeight());
            mBorderRect.inset((borderWidth) / 2, (borderWidth) / 2);

            canvas.drawRoundRect(mBorderRect, borderRadius, borderRadius, strokepaint);
        }

        super.onDraw(canvas);
    }
}
