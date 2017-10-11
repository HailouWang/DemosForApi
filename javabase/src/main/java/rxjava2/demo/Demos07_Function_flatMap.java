package rxjava2.demo;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.o;

/**
 * Created by ifei on 2017/9/16.
 */

public class Demos07_Function_flatMap {

    static Company[] companys;

    static {
        companys = new Company[]{
                new Company("可口可乐",new User[]{
                        new User("可乐工01"),
                        new User("可乐工02"),
                        new User("可乐工03"),
                        new User("可乐工04"),
                }),
                new Company("芬达",new User[]{
                        new User("芬达工01"),
                }),
                new Company("雪碧",new User[]{
                        new User("雪碧工01"),
                        new User("雪碧工02"),
                        new User("雪碧工03"),
                }),
                new Company("冰红茶",new User[]{
                        new User("冰红茶工01"),
                }),
                new Company("康师傅",new User[]{
                        new User("康师傅工01"),
                }),
        };
    }

    static class Company{
        public User[] usersArray = new User[0];
        public String companeName;

        public Company(String name){
            companeName = name;
        }

        public Company(String name,User... users){
            usersArray = users;
            companeName = name;
        }
    }

    static class User{
        String name;

        public User(String n){
            name = n;
        }
    }

    public static void main(String args[]){
        System.out.println("1、==========================================>");
        //1、打印公司名称
        Observable.fromArray(companys)
                .map(new Function<Company, String>() {

                    @Override
                    public String apply(@NonNull Company company) throws Exception {
                        return company.companeName;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("----->"+s);
                    }
                });

        System.out.println("2、==========================================>");

        //2、利用循环打印数组
        Observable.fromArray(companys)
                .subscribe(new Consumer<Company>() {
                    @Override
                    public void accept(Company company) throws Exception {
                        System.out.println("---------company name:"+company.companeName);
                        for(User user:company.usersArray){
                            System.out.println("---------company's user name:"+user.name);
                        }
                   }
                });

        System.out.println("3、==========================================>");

        //3、利用flatMap，直接使用Company中的User[]数据
        Observable.fromArray(companys)
                .flatMap(new Function<Company, ObservableSource<User[]>>() {
                    @Override
                    public ObservableSource<User[]> apply(@NonNull final Company company) throws Exception {
                        return new ObservableSource<User[]>(){

                            @Override
                            public void subscribe(@NonNull Observer<? super User[]> observer) {
                                System.out.println("------>Company' Name:"+company.companeName);
                                observer.onNext(company.usersArray);
                                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                            }
                        };
                    }
                }).subscribe(new Consumer<User[]>() {

                    @Override
                    public void accept(User[] users) throws Exception {
                        for(User user:users){
                            System.out.println("------>"+user.name);
                        }
                    }
        });

        System.out.println("4、==========================================>");

        //4、利用flatMap，直接使用Company中的User数据,传递User数据
        Observable.fromArray(companys)
                .flatMap(new Function<Company, ObservableSource<User>>() {
                    @Override
                    public ObservableSource<User> apply(@NonNull final Company company) throws Exception {
                        System.out.println("------>Company' Name:"+company.companeName);
                        return Observable.fromArray(company.usersArray);
                    }
                }).subscribe(new Consumer<User>() {

            @Override
            public void accept(User user) throws Exception {
                System.out.println("------>"+user.name);
            }
        });

    }

}
