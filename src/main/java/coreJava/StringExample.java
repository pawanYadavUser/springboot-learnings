package coreJava;

public class StringExample {
    static StringBuilder s4 = new StringBuilder();
    public static void main(String[] args) throws InterruptedException {
        String s1 = "Java";
        s1.concat(" String");
        System.out.println(s1);
        s1 = s1.concat("String");
        System.out.println("s1 : "+s1);

        StringBuilder s2 = new StringBuilder("Java");
        s2.append("String");
        System.out.println("s2 : "+s2);


        StringBuffer s3 = new StringBuffer("Java");
        s3.append("String");
        System.out.println("s3 : "+s3);

        //Checking the thread safety for the StringBuilder
        Thread t1 = new Thread(()->{
            for(int i=0;i<1000;i++){
                s4.append('B');
            }
        });

        Thread t2 = new Thread(()->{
            for(int i=0;i<1000;i++){
                s4.append('B');
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final length after running threads : "+s4.length());

    }
}
