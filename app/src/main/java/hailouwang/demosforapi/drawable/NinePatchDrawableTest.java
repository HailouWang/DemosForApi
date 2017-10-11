package hailouwang.demosforapi.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import hailouwang.demosforapi.R;

/**
 *
 *
 * <nine-patch xmlns:android="http://schemas.android.com/apk/res/android"
 android:src=""
 android:dither=""/>
 */
public class NinePatchDrawableTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_patch_drawable);

        TextView tvIncoming = (TextView) findViewById(R.id.nine_patch_incoming_tv);
        tvIncoming.setBackgroundResource(R.drawable.msg_bubble_incoming);
        tvIncoming.setText("接收的信息\n 17:11:26");


        TextView tvSending = (TextView) findViewById(R.id.nine_patch_sending_tv);
        tvSending.setBackgroundResource(R.drawable.msg_bubble_outgoing);
        tvSending.setText("发送的信息\n 17:13:35");
    }
}
