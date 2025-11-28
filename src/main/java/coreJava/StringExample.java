package coreJava;

public class StringExample {
    public static void main(String[] args) {
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

    }
}
