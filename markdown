## 一、注解类型 ##

### 1、网络方法 ###

**1、**网络请求方法：
方法：@GET @POST @PUT @DELETE @PATH @HEAD @OPTIONS @HTTP

|方法|值|
|----|-----|
|@GET |接口方式GET|
|@POST |接口方式POST|
|@PUT |接口方式PUT|
|@DELETE |接口方式DELETE|
|@PATH |接口方式PATH|
|@HEAD |接口方式HEAD|
|@OPTIONS |接口方式OPTIONS|
|@HTTP|以上方式，可以通过HTTP来设定|

**2、**：注解接口中网络请求方法：

```
public interface xxxx{
    @METHOD(/*baseUrl*/"path")
    Call<T> xxxxx();
}

public interface xxxx{
    @HTTP(method="GET",path="api/v1/{id}",hasBody = false)
    Call<T> xxxx(@Path("id") int id);
}
```
T:泛型类型
METHOD:方法名称。

### 2、标记 ###
** 2.1、** 标记内容：
标记：@FormUrlEncoded @Multipart @Streaming

|标记|备注|
|----|----|
|@FormUrlEncoded |表示请求体为Form表单|
|@Multipart |支持文件上传的Form表单|
|@Streaming |返回的数据为 “流” 的形式（适用于返回数据较大，默认返回数据保存/读取来自内存）|

** 2.2、**标记使用方式：

```
public interface xxxx{

//定义

    @POST("/api/v1")
    @FormUrlEncoded
    Call<T> xxxxxxx(@Field("username") String name,@Field("password" String pwd);

    @POST("/api/v1")
    @Multipart
    Call<T> xxxxxxx(@Part("username") RequestBody name,@Part("password" MultipartBody.Part pwd);


//使用

Call<ResponseBody> call = xx.xxxxxx("root","123456");


RequestBody name = RequestBody.create(textType,"");
MultipartBody pwd = MultipartBody.Part.createFormData("","",xx);

Call<ResponseBody> call = xx.xxxxxx(name,pwd);

}
```

备注：
表单形式：Content-Type:application/x-www-form-urlencoded。
@Field("username"),将参数name作为username的值。

### 3、网络请求参数 ###

**3.1、网络请求参数：**

|网络请求参数|备注|
|------------|----|
|@Headers|添加请求头|
|@Header|添加不固定请求头|
|@Body|请求内容Body|
|@Field|向POST表单传入键值|
|@FieldMap|向POST表单传入键值|
|@Part|用于表单字段，用于文件上传|
|@PartMap|用于表单字段，用于文件上传|
|@Query|用于表单字段，同@Field，区别在于URL上。|
|@QueryMap|用于表单字段，同@Field，区别在于URL上。|
|@Path|url缺省值|
|@URL|url设定|

** 3.2、 网络参数的使用：**

3.2.1、Header
```
public interface xxxx{
    // @Header用于不固定请求头，@Headers用于固定请求头。
    // @Header作用于方法参数，  @Headers作用于方法。


    @GET("api/v1")
    Call<T> xxxxxx(@Header("Authorization") String auth);

    @GET("api/v1")
    @Headers("Authorization:authorization")
    Call<T> xxxxxx();
}
```

3.2.2、Body

```
public interface xxxx{
    FormBody.Builder builder = new FormBody.Builder();
    builder.add("KEY","VALUE");
}
```

3.2.3、Field

与@FormUrlEncoded配合使用

```
public interface xxxx{
//声明

    @POST("api/v1/form")
    @FormUrlEncoded
    Call<T> xxxxxx(@Field("username") String name);

    @POST("api/v1/form")
    @FormUrlEncoded
    Call<T> xxxxxx(@FieldMap Map<String,Object> map);

//使用
    Call<T> xxx = xxx.xxxxxxx("admin");

    Map<String,Object> map = new HashMap<>();
    map.put("name","admin");
    map.put("pwd","123456");
    Call<T> xxx = xxx.xxxxxxx(map);
}
```

3.2.4、Part

与@FormUrlEncoded配合使用

```
public interface xxxx{
//定义

    @POST("/api/v1")
    @FormUrlEncoded
    Call<T> xxxxxxx(@Field("username") String name,@Field("password" String pwd);

    @POST("/api/v1")
    @Multipart
    Call<T> xxxxxxx(@Part("username") RequestBody name,@Part("password" MultipartBody.Part pwd);


//使用

    Call<ResponseBody> call = xx.xxxxxx("root","123456");

    RequestBody name = RequestBody.create(textType,"");
    MultipartBody pwd =             MultipartBody.Part.createFormData("","",xx);

    Call<ResponseBody> call = xx.xxxxxx(name,pwd);
}
```

3.2.5、Query

例如：url = http://www.xxxx.com/?username=root，其中，Query = username。

```
public interface xxxx{
//声明

    @POST("api/v1/getPwdFromName")
    Call<T> xxxxxx(@Query("username") String name);
}
```

3.2.6、Path

```
public interface xxxx{
//声明

    @GET("api/v1/{user}/repos")
    Call<T> xxxxxx(@Query("username") String name);
}
```

3.2.7、URL

```
public interface xxxx{
//声明

    @GET
    Call<T> xxxxxx(@Url String url,@Query("username") String name);
}
```

## 二、数据解析器（Converter） ##

解析器包括：Gson、Jackson、Simple XML、Protobuf、Moshi、Wire、Scalars。

依赖参考：https://github.com/square/retrofit/tree/master/retrofit-converters

## 三、网络请求适配器（CallAdapter） ##

适配器包括：guava、Java8、rxjava

依赖参考：https://github.com/square/retrofit/tree/master/retrofit-adapters