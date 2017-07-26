package hailouwang.demosforapi.volley;

import android.graphics.Bitmap;
import android.media.Image;
import android.media.ImageReader;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import hailouwang.demosforapi.R;

public class ImageReqestTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_reqest);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        //1、得到RequestQueue
        RequestQueue requestQueue = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        //2、ImageRequest
        String URL= "http://cdn2.jianshu.io/assets/web/logo-58fd04f6f0de908401aa561cda6a0688.png";
        ImageRequest imageReqest = new ImageRequest(URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Log.d("hlwang","ImageReqestTest onResponse...");
                imageView.setImageBitmap(response);
            }
        }, 1080, 500, ImageView.ScaleType.CENTER,
                Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("hlwang","ImageReqestTest onErrorResponse error :"+error);
            }
        });
        //3、加入RequestQueue
        requestQueue.add(imageReqest);
    }
}
