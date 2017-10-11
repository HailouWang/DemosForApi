package okhttp.official.simple_client;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpContributors {
  private static final String ENDPOINT = "https://api.github.com/repos/square/okhttp/contributors";
  private static final Moshi MOSHI = new Moshi.Builder().build();
  private static final JsonAdapter<List<Contributor>> CONTRIBUTORS_JSON_ADAPTER = MOSHI.adapter(
      Types.newParameterizedType(List.class, Contributor.class));

  static class Contributor {
    String login;
    int contributions;
  }

  public static void main(String... args) throws Exception {
    OkHttpClient client = new OkHttpClient();

    // Create request for remote resource.
    //1、初始化Request
    Request request = new Request.Builder()
        .url(ENDPOINT)
        .build();

    // Execute the request and retrieve the response.
    // 2、执行Request，接收Response
    try (Response response = client.newCall(request).execute()) {
      // Deserialize HTTP response to concrete type.
      //3、反序列化Http Respoonse
      ResponseBody body = response.body();

      //---------------------------
      System.out.println("---------------------------");
      System.out.println(OkHttpContributors.class.getName()+",main body:"+body.source());
      System.out.println("---------------------------");
      List<Contributor> contributors = CONTRIBUTORS_JSON_ADAPTER.fromJson(body.source());
      //---------------------------

      // Sort list by the most contributions.
      // 4、排序
      Collections.sort(contributors, new Comparator<Contributor>() {
        @Override public int compare(Contributor c1, Contributor c2) {
          return c2.contributions - c1.contributions;
        }
      });

      // Output list of contributors.
      // 5、输出
      for (Contributor contributor : contributors) {
        System.out.println(contributor.login + ": " + contributor.contributions);
      }
    }
  }

  private OkHttpContributors() {
    // No instances.
  }
}
