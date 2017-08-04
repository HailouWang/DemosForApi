package hailouwang.demosforapi.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import hailouwang.demosforapi.R;

public class SimpleNetworkRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_network_request);

        findViewById(R.id.btn_network_submit).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try{
                            String params = "content=test";
                            byte[] entity = params.getBytes();
                            String path = "https://hailouwang.github.io/RequestForms.html";
                            HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
                            conn.setConnectTimeout(5000);
                            conn.setRequestMethod("GET");//post
                            conn.setDoOutput(true);
                            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
                            conn.getOutputStream().write(entity);
                            int responseCode = conn.getResponseCode();
                            Log.d("DemosAndApi","SimpleNetworkRequest responseCode :"+responseCode);
                        }catch (Exception e) {
                            e.printStackTrace();
                            Log.d("DemosAndApi","SimpleNetworkRequest exception :"+e.getMessage());
                        }
                    }
                }.start();
            }
        });
    }
}
