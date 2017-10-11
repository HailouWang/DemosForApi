package okhttp.demo;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.o;

/**
 * Created by ifei on 2017/9/14.
 */

public class SimpleRequest1 {

    public static void main(String args[]){
        OkHttpClient client = new OkHttpClient();

        String URL = "https://api.github.com/repos/square/okhttp/contributors";

        Request request = new Request.Builder().url(URL).build();

        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            System.out.println("SimpleRequest1 json:"+json);


            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String jsonSync = response.body().string();
                    System.out.println("SimpleRequest2 json:"+jsonSync);
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
