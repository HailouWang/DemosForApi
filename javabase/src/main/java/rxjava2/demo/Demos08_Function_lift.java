package rxjava2.demo;

import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.o;

/**
 * Created by ifei on 2017/9/17.
 */

public class Demos08_Function_lift {

    public static void main(String args[]){
        System.out.println("1、=========================>");
        Observable.fromArray(companys)
                .lift(new ObservableOperator<User[], Company>() {
                    @Override
                    public Observer<? super Company> apply(@NonNull final Observer<? super User[]> observer) throws Exception {
                        return new Observer<Company>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull Company company) {
                                //转换代码
                                observer.onNext(company.usersArray);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        };
                    }
                })
                .subscribe(new Consumer<User[]>() {
                    @Override
                    public void accept(User[] users) throws Exception {
                        for(User user:users){
                            System.out.println("--->"+user);
                        }
                    }
        });

        System.out.println("2、双重lift变换=========================>");
        Observable.fromArray(companys)
                .lift(new ObservableOperator<User[], Company>() {
                    @Override
                    public Observer<? super Company> apply(@NonNull final Observer<? super User[]> observer) throws Exception {
                        return new Observer<Company>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull Company company) {
                                //转换代码
                                observer.onNext(company.usersArray);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        };
                    }
                })
                .lift(new ObservableOperator<User, User[]>() {
                    @Override
                    public Observer<? super User[]> apply(@NonNull final Observer<? super User> observer) throws Exception {
                        return new Observer<User[]>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull User[] users) {
                                //转换代码
                                for(User user:users){
                                    observer.onNext(user);
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        };
                    }
                })
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        System.out.println("------>"+user.name);
                    }
                });
    }

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

}
