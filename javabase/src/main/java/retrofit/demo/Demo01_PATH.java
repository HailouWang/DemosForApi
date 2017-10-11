package retrofit.demo;

import java.io.IOException;

import retrofit.official.SimpleService;

/**
 * Created by ifei on 2017/9/15.
 */

public class Demo01_PATH {

    public static void main(String args[]){
        try {
            SimpleService.main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
