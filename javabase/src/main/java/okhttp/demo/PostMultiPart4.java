package okhttp.demo;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ifei on 2017/9/14.
 *
 * http://blog.csdn.net/u012124438/article/details/54236967
 */

public class PostMultiPart4 {

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    public static void main(String args[]){
        OkHttpClient client = new OkHttpClient();

        String URL = "https://api.github.com/markdown/raw";

        //1、postFile

        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MEDIA_TYPE_MARKDOWN)
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"title\""),
                        RequestBody.create(null, "Square Logo")
                ).build();

        final Request requestMultiPart = new Request.Builder()
                .url(URL)
                .post(multipartBody)
                .build();

        client.newCall(requestMultiPart).enqueue(new Callback() {
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
