package rxjava2.demo;

import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.o;

/**
 * Created by ifei on 2017/9/17.
 */

public class Demos09_Function_compose {

    public static void main(String args[]){
        Observable observer饮料 = Observable.fromArray(company饮料);
        Observable observer包子 = Observable.fromArray(company包子);

        ObservableFromCompanyToUser transformer = new ObservableFromCompanyToUser();

        observer饮料.compose(transformer).subscribe(new Consumer<User>() {
            @Override
            public void accept(User user) throws Exception {
                System.out.println("copany饮料:------------>"+user.name);
            }
        });

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        observer饮料.compose(transformer).subscribe(new Consumer<User>() {
            @Override
            public void accept(User user) throws Exception {
                System.out.println("copany饮料:------------>"+user.name);
            }
        });

    }

    static class ObservableFromCompanyToUser implements ObservableTransformer<Company,User>{

        @Override
        public ObservableSource<User> apply(@NonNull final Observable<Company> upstream) {
            return upstream
                    .lift(new ObservableOperator<User[], Company>() {
                        @Override
                        public Observer<? super Company> apply(@NonNull final Observer<? super User[]> observer) throws Exception {
                            return new Observer<Company>() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {

                                }

                                @Override
                                public void onNext(@NonNull Company company) {
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
                    });
        }
    }

    static Company[] company饮料;
    static Company[] company包子;

    static {
        company饮料 = new Company[]{
                new Company("可口可乐",new User[]{
                        new User("可乐工01"),
                        new User("可乐工02"),
                        new User("可乐工03"),
                        new User("可乐工04"),
                }),
                new Company("芬达",new User[]{
                        new User("芬达工01"),
                })
        };

        company包子 = new Company[]{
                new Company("薛扶集包子",new User[]{
                        new User("薛扶集包子工01"),
                        new User("薛扶集包子工02"),
                }),
                new Company("九里沟包子",new User[]{
                        new User("九里沟包子工01"),
                })
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
