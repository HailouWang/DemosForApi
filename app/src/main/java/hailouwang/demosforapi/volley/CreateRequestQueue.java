package hailouwang.demosforapi.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import hailouwang.demosforapi.R;

public class CreateRequestQueue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView mTextView = (TextView) findViewById(R.id.text);

        RequestQueue mRequestQueue;

        // Instantiate the cache 1、初始化Cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        //2、建立网络连接
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        //3、构建初始化RequestQueue
        mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        //4、启动RequestQueue
        mRequestQueue.start();

        String url = "http://www.example.com";

        // Formulate the request and handle the response.
        //5、初始化并构建Request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        mTextView.setText("Response is: " + response.substring(0, 500));
                        Log.d("hlwang","CreateRequestQueue onResponse response is:"+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Log.d("hlwang","CreateRequestQueue onErrorResponse error is:"+error);
                    }
                });

        // Add the request to the RequestQueue.
        //6、将Request加入队列
        mRequestQueue.add(stringRequest);
    }
}
