package okhttp.demo;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static okhttp.demo.PostRequest2.JSONTYPE;

/**
 * Created by ifei on 2017/9/14.
 */

public class PostFile3 {

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    public static void main(String args[]){
        OkHttpClient client = new OkHttpClient();

        String URL = "https://api.github.com/markdown/raw";

        File file = new File("markdown");

        //1、postFile

        RequestBody requestBodyPostFile = RequestBody.create(MEDIA_TYPE_MARKDOWN,file);

        final Request requestPostFile = new Request.Builder()
                .url(URL)
                .post(requestBodyPostFile)
                .build();

        //2、postString

        String postString = "" +
                "## 一、注解类型 ##\n" +
                "\n" +
                "### 1、网络方法 ###\n" +
                "\n" +
                "**1、**网络请求方法：\n" +
                "方法：@GET @POST @PUT @DELETE @PATH @HEAD @OPTIONS @HTTP";

        RequestBody requestBodyPostString = RequestBody.create(MEDIA_TYPE_MARKDOWN,postString);

        final Request requestPostString = new Request.Builder()
                .url(URL)
                .post(requestBodyPostString)
                .build();


        client.newCall(requestPostFile).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("reponse json:"+response.body().string());

                System.out.println("Server: " + response.header("Server"));
                System.out.println("Date: " + response.header("Date"));
                System.out.println("Vary: " + response.headers("Vary"));

                System.out.println("Heads: " + response.headers());
            }
        });

        client.newCall(requestPostString).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("reponse json:"+response.body().string());

                System.out.println("Server: " + response.header("Server"));
                System.out.println("Date: " + response.header("Date"));
                System.out.println("Vary: " + response.headers("Vary"));

                System.out.println("Heads: " + response.headers());
            }
        });
    }
}
