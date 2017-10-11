package retrofit.demo;

import java.io.IOException;
import java.lang.reflect.Method;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Query;

/**
 * Created by ifei on 2017/9/14.
 *
 * 金山词霸翻译：
 *
 * // URL模板
 * http://fy.iciba.com/ajax.php
 *
 * // URL实例
 * http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=helloworld
 *
 * // 参数说明：
 * // a：固定值 fy
 * // f：原文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
 * // t：译文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
 * // w：查询内容
 */

public class Demo02_PATH {

    private static final String BASE_URL = "http://fy.iciba.com";

    /**
     * 步骤一：创建 接收服务器返回数据 的类
     {
     "status": 1, 请求成功 1
     "content": {
        "from": "en-EU", 初始语言
        "to": "zh-CN", 目标语言
        "out": "helloworld", 译文结果
        "vendor": "ciba", 平台
        "err_no": 0  请求成功为0
     }
     }

     http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=helloworld
     */
    class Transalation{
        int status;
        Content content;

        class Content{
            String from;
            String to;
            String out;
            String vendor;
            int err_no;

            @Override
            public String toString() {
                return "Content{" +
                        "from='" + from + '\'' +
                        ", to='" + to + '\'' +
                        ", out='" + out + '\'' +
                        ", vendor='" + vendor + '\'' +
                        ", err_no=" + err_no +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Transalation{" +
                    "status=" + status +
                    ", content=" + content +
                    '}';
        }
    }

    /**
     * 步骤二：创建 用于描述网络请求 的接口
     */
    interface GetGsonRequest{
        @GET("ajax.php?a=fy&f=en&t=zh&w=hello world")
        Call<Transalation> getHelloWorldResult();

        @GET("ajax.php?a=fy&f=en&t=zh")
        Call<Transalation> getTransalationResult(@Query("w") String orignal);

        @HTTP(method = "GET",path="ajax.php?a=fy&f=en&t=zh",hasBody = false)
        Call<Transalation> getTransalation(@Query("w") String orignal);
    }

    public static void main(String args[]){
        //Retrofit将 Http请求 抽象成 Java接口：采用 注解 描述网络请求参数 和配置网络请求参数

        //步骤三：创建 Retrofit 实例
        Retrofit retrofitGet = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        try {
            //Demo1
            //步骤四： 创建 网络请求接口实例 并 配置网络请求参数
            GetGsonRequest getGsonRequest = retrofitGet.create(GetGsonRequest.class);
            Call<Transalation> transalationGet = getGsonRequest.getHelloWorldResult();

            //步骤五：发送网络请求（异步 / 同步）
            Response<Transalation> transalationResponse = transalationGet.execute();
            System.out.println("FirstText_1 transalationResponse:"+transalationResponse);

            //步骤六：处理服务器返回的数据
            Transalation transalation = transalationResponse.body();
            System.out.println("FirstText_1 transalationResponse transalation:"+transalation);

            System.out.println("-------------------");

            //Demo2 带参数
            Call<Transalation> transalationGetWithParams = getGsonRequest.getTransalationResult("Good Farmer!");
            Response<Transalation> transalationResponseWithParams = transalationGetWithParams.execute();

            System.out.println("FirstText_1 transalationResponse:"+transalationResponseWithParams);

            Transalation transalationWithParams = transalationResponseWithParams.body();
            System.out.println("FirstText_1 transalationResponse transalation:"+transalationWithParams);

            //Demo3 带参数
            Call<Transalation> transalationGetHtml = getGsonRequest.getTransalation("I'm fine,thanks you!");
            Response<Transalation> transalationResponseHtml = transalationGetHtml.execute();

            System.out.println("FirstText_1 transalationGetHtml:"+transalationResponseHtml);

            Transalation transalationHtml = transalationResponseHtml.body();
            System.out.println("FirstText_1 transalationGetHtml transalation:"+transalationHtml);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
