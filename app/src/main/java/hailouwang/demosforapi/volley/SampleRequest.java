package hailouwang.demosforapi.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import hailouwang.demosforapi.R;

public class SampleRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Instantiate the RequestQueue. 1、初始化RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://www.jianshu.com/";/*http://www.google.com*/

        // Request a string response from the provided URL.
        //2、通过GET的方式，向URL初始化字符串請求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //3、显示请求响应字符串
//                        mTextView.setText("Response is: " + response.substring(0, 500));
                        Log.d("hlwang","VolleySimpleRequest onResponse response is:"+response);
                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //                mTextView.setText("That didn't work!");
                //4、显示请求响应错误信息
                Log.d("hlwang","VolleySimpleRequest onErrorResponse error is:"+error);
            }
        });
        // Add the request to the RequestQueue.
        // 5、将请求加入 请求队列
        queue.add(stringRequest);
    }
}