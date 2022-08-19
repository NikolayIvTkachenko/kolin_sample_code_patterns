package java_block.java_language;

import io.reactivex.Observable;

public class AppDemoRxJava001 {

    public static void main(String[] args) {

        Observable.range(1, 10)
                .subscribe(AppDemoRxJava001::someShowData);

    }


    private static void someShowData(int value){
        System.out.println(value);
    }
}
