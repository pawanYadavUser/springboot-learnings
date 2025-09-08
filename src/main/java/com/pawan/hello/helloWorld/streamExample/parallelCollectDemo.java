package com.pawan.hello.helloWorld.streamExample;

import java.util.Arrays;

public class parallelCollectDemo {

    public static void main(String[] args) {
        String input = "This is Parallel stream example";

        String result = Arrays.stream(input.split(" "))
                .parallel() //run in multiple threads
                .collect(
                        StringBuilder::new,
                        (sb, ch) -> {
                            System.out.println(Thread.currentThread().getName()+" adding "+ch+" to "+sb+ch);
                            sb.insert(0,ch);

                        },
                        (sb1, sb2)->{
                            System.out.println(Thread.currentThread().getName()+" merging "+sb2+" and "+sb2);
                            sb1.insert(0,sb2);
                        }
                ).toString();
        System.out.println("parallel thread result : "+result);
    }
}
