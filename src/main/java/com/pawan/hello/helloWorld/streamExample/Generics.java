package com.pawan.hello.helloWorld.streamExample;

public class Generics <T>{
    T value;

    public Generics (T value){
        this.value = value;
    }

    public String toString (){
        return "{ value : "+value+" }";
    }

    public static void main(String[] args) {
        Generics <String> StringTypeObject = new Generics<>("Test");
        System.out.println(StringTypeObject.toString());

        Generics <Integer> IntegerTypeObject = new Generics<>(1234);
        System.out.println(IntegerTypeObject.toString());

        Generics <Double> DoubleTypeObject = new Generics<>(23.45);
        System.out.println(DoubleTypeObject.toString());

    }
}
