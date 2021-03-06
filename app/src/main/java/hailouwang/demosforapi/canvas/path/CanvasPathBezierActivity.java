package hailouwang.demosforapi.canvas.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import hailouwang.demosforapi.canvas.BaseCanvasActivity;

import static android.R.attr.centerX;
import static android.R.attr.centerY;
import static android.R.attr.startX;
import static android.R.attr.state_above_anchor;

/**
 *
 * 贝塞尔曲线是用一系列点来控制曲线状态的，我将这些点简单分为两类：
 * 类型	    作用
 * 数据点	确定曲线的起始和结束位置
 * 控制点	确定曲线的弯曲程度
 *
 */
public class CanvasPathBezierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new Bezier(this));
    }

    class Bezier extends View {

        private PointF startPoint,endPoint,touchPoint;
        private int width,height;
        private Paint mPaint = new Paint();

        private int mPointColor = Color.BLACK,mHelpLineColor = Color.GRAY,mBezierColor = Color.RED;

        public Bezier(Context context) {
            super(context);
            startPoint = new PointF(0,0);
            endPoint = new PointF(0,0);

            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(20);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            width = w;
            height = h;

            int centerX = width/2,centerY = height/2;

            startPoint.x = centerX - 200;
            startPoint.y = centerY;

            endPoint.x = centerX+200;
            endPoint.y = centerY;

            if(touchPoint == null){
                touchPoint = new PointF(centerX,centerY-200);
            }

            postInvalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            //画开始和结束点
            mPaint.setColor(mPointColor);
            mPaint.setStrokeWidth(20);

            canvas.drawPoint(startPoint.x,startPoint.y,mPaint);
            canvas.drawPoint(endPoint.x,endPoint.y,mPaint);

            canvas.drawPoint(touchPoint.x,touchPoint.y,mPaint);

            //画辅助线
            mPaint.setColor(mHelpLineColor);
            mPaint.setStrokeWidth(2);

            canvas.drawLine(startPoint.x,startPoint.y,touchPoint.x,touchPoint.y,mPaint);
            canvas.drawLine(endPoint.x,endPoint.y,touchPoint.x,touchPoint.y,mPaint);

//            Path helpPath = new Path();
//            helpPath.moveTo(startPoint.x,startPoint.y);
//            helpPath.lineTo(touchPoint.x,touchPoint.y);
//            helpPath.lineTo(endPoint.x,endPoint.y);

//            canvas.drawPath(helpPath,mPaint);

            //画贝塞尔曲线
            mPaint.setColor(mBezierColor);

            Path bezierPath = new Path();
            bezierPath.moveTo(startPoint.x,startPoint.y);
            bezierPath.quadTo(touchPoint.x,touchPoint.y,endPoint.x,endPoint.y);
            canvas.drawPath(bezierPath,mPaint);

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if(touchPoint==null){
                touchPoint = new PointF();
            }
            touchPoint.x = event.getX();
            touchPoint.y = event.getY();
            invalidate();
            return true;
        }
    }

}
