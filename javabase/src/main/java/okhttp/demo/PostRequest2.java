package okhttp.demo;

import com.squareup.moshi.Json;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static okhttp.official.guide.guide.PostExample.JSON;

/**
 * Created by ifei on 2017/9/14.
 */

public class PostRequest2 {
    public static final MediaType JSONTYPE
            = MediaType.parse("application/json; charset=utf-8");
    public static void main(String args[]){
        OkHttpClient client = new OkHttpClient();

        String URL = "https://api.github.com/repos/square/okhttp/contributors";

        String contentJson = "这是一笔新的提交";

        //1、单独Body
        RequestBody body = RequestBody.create(JSONTYPE,contentJson);

        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        //2、BodyMap
        RequestBody bodyMap = new FormBody.Builder()
                .add("username","root")
                .add("pwd","admin")
                .build();
        Request requestWithBodyMap = new Request.Builder()
                .url(URL)
                .post(bodyMap)
                .build();

        try {

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String jsonSync = response.body().string();
                    System.out.println("response json:"+jsonSync);
                }
            });

            client.newCall(requestWithBodyMap).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String jsonSync = response.body().string();
                    System.out.println("response json:"+jsonSync);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
