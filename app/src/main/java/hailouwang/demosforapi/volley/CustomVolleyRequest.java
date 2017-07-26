package hailouwang.demosforapi.volley;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import hailouwang.demosforapi.R;

public class CustomVolleyRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView mTextView = (TextView) findViewById(R.id.text);

        //1、获得RequestQueue
        RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        //2、初始化GSON Request
        String url = "http://www.sojson.com/api/beian/sojson.com";/*http://www.google.com*/
        GsonRequest<ComplanyWebSiteInfo> gsonRequest = new GsonRequest<>(url,ComplanyWebSiteInfo.class,
            null,new Response.Listener<ComplanyWebSiteInfo>(){
                @Override
                public void onResponse(ComplanyWebSiteInfo response) {
                    Log.d("hlwang","CustomVolleyRequest onResponse ComplanyWebSiteInfo is:"+response);
                    mTextView.setText(response.toString());
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("hlwang","CustomVolleyRequest onErrorResponse error:"+error.getMessage());
                    mTextView.setText("That didn't work!");
                }
            });
        //3、加入RequestQueue
        queue.add(gsonRequest);
    }

    class ComplanyWebSiteInfo{
        private String indexUrl;
        private String icp;

        public String getIndexUrl() {
            return indexUrl;
        }

        public void setIndexUrl(String indexUrl) {
            this.indexUrl = indexUrl;
        }

        @Override
        public String toString() {
            return "ComplanyWebSiteInfo{" +
                    "indexUrl='" + indexUrl + '\'' +
                    ", icp='" + icp + '\'' +
                    '}';
        }
    }

    class GsonRequest<T> extends Request<T> {
        private final Gson gson = new Gson();
        private final Class<T> clazz;
        private final Map<String, String> headers;
        private final Response.Listener<T> listener;

        /**
         * Make a GET request and return a parsed object from JSON.
         *
         * @param url URL of the request to make
         * @param clazz Relevant class object, for Gson's reflection
         * @param headers Map of request headers
         */
        public GsonRequest(String url, Class<T> clazz, Map<String, String> headers,
                           Response.Listener<T> listener, Response.ErrorListener errorListener) {
            super(Method.GET, url, errorListener);
            this.clazz = clazz;
            this.headers = headers;
            this.listener = listener;
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return headers != null ? headers : super.getHeaders();
        }

        @Override
        protected void deliverResponse(T response) {
            listener.onResponse(response);
        }

        @Override
        protected Response<T> parseNetworkResponse(NetworkResponse response) {
            try {
                String json = new String(
                        response.data,
                        HttpHeaderParser.parseCharset(response.headers));
                return Response.success(
                        gson.fromJson(json, clazz),
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JsonSyntaxException e) {
                return Response.error(new ParseError(e));
            }
        }
    }
}
