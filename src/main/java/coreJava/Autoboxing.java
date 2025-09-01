package coreJava;

public class Autoboxing {
    public static void main(String[] args) {
        int primitiveInt = 23;
        Integer wrapperInt = primitiveInt;
        int primitiveFromWrapper = wrapperInt;
        //autoboxing
        System.out.println("Primitive int : "+primitiveInt);
        System.out.println("Wrapper int : "+wrapperInt);
        //unboxing
        System.out.println("back to primitive int : "+primitiveFromWrapper);

    }
}
