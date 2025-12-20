package com.pawan.hello.helloWorld.streamExample;

class UserDetails {
    private String name;
    private int age;

    public UserDetails(String name, int age){
        this.name= name;
        this.age = age;
    }

    public static UserDetails of(String name , int age){
        return new UserDetails(name, age);
    }

    public String toString(){
        return  "{ name : "+name+ " , age : "+age+" }";
    }
}
public class StaticfactoryMethodExample {
    public static void main(String[] args) {
        UserDetails user1 = new UserDetails("user1", 23);
        System.out.println("normal object creation: "+ user1.toString());

        UserDetails userDetails2 = UserDetails.of("user2",34);
        System.out.println("Object creation via static factory method : "+userDetails2);

    }

}
