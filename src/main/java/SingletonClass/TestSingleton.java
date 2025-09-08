package SingletonClass;

//class Singleton {
//    // create instance at class loading time
//    private static final Singleton instance = new Singleton();
//    // private constructor prevents instantiation
//    private Singleton() {}
//
//    public static Singleton getInstance() {
//        return instance;
//    }
//}

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Singleton2 {
    private static Singleton2 instance;

//✅ Instance created only when needed
//❌ Multiple threads may create multiple objects → not safe

    private Singleton2() {}

    public static Singleton2 getInstance() {
        if (instance == null) {
            try {
                // artificial delay to simulate slow creation
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton2(); // not thread-safe
        }
        return instance;
    }
}

class Singleton3 {
    private static Singleton3 instance;
//✅ Thread-safe
//❌ synchronized slows performance (locking every call)
    private Singleton3(){

    }

    public static synchronized Singleton3 getInstance(){
        if(instance == null){
            instance = new Singleton3();
        }
        return instance;
    }
}

class Singleton4 {
    private static volatile Singleton4 instance;

    private Singleton4() {}

//✅ Thread-safe + efficient
//✅ Most commonly used in interviews

    public static Singleton4 getInstance() {
        if (instance == null) {                // 1st check
            synchronized (Singleton4.class) {
                if (instance == null) {        // 2nd check
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}

// Enum Singleton (Simplest & Best)
enum Singleton5 {
    //✅ Thread-safe
//✅ Handles serialization automatically
//✅ Easiest way to implement singleton in Java

    INSTANCE;

    public void showMessage() {
        System.out.println("Hello from Singleton Enum!");
    }
}

public class TestSingleton {
    public static void main(String[] args) {
//        Singleton obj4 = new Singleton(); will give compile-time error : constructor has private access mode activated!
//        Singleton obj1 = Singleton.getInstance();
//        Singleton obj2 = Singleton.getInstance();
//        Singleton obj3 = Singleton.getInstance();
//
//        System.out.println(obj1);  // prints something like Singleton@5a07e868

//        System.out.println(obj2);  // same reference Singleton@5a07e868
//        System.out.println(obj3);  // same reference Singleton@5a07e868
//
//        System.out.println(obj1 == obj2); // true
//        System.out.println(obj2 == obj3); // true

        //2nd way of creating singleton class
        Singleton2 obj4 = Singleton2.getInstance();
        Singleton2 obj5 = Singleton2.getInstance();
        Singleton2 obj6 = Singleton2.getInstance();

        System.out.println(obj4);  // prints something like Singleton@5a07e868
        System.out.println(obj5);  // same reference Singleton@5a07e868
        System.out.println(obj6);  // same reference Singleton@5a07e868

        System.out.println(obj4 == obj5); // true
        System.out.println(obj5 == obj6); // true

        //3rd way of creating singleton class
        Runnable task = ()->{
          Singleton3 obj7 = Singleton3.getInstance();
            System.out.println(Thread.currentThread().getName() + " got " +obj7);
        };
        for(int i=0;i<5;i++){
            new Thread(task).start();
        }

        //4th way of creating singleton class
        Runnable task4 = ()-> {
            Singleton4 obj8 = Singleton4.getInstance();
            System.out.println(Thread.currentThread().getName() + " got " +obj8 + "from singleton4 way");
        };
        for(int i=0;i<5;i++){
            new Thread(task4).start();
        }

        Singleton5 obj9 = Singleton5.INSTANCE;
        obj9.showMessage();

        // Get singleton instances
        Singleton5 obj10 = Singleton5.INSTANCE;

        // Call method
        obj10.showMessage();

        // Check if both references are the same
        System.out.println("singleton5 "+(obj9 == obj10)); // true
        System.out.println("singleton5 "+obj9.hashCode());
        System.out.println("singleton5 "+obj10.hashCode());

        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
            out.writeObject(obj10);
            out.close();

            // Deserialize from file
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("singleton.ser"));
            Singleton5 obj11 = (Singleton5) in.readObject();
            in.close();

            // Compare both instances
            System.out.println("instance1 hashCode: " + obj10.hashCode());
            System.out.println("instance2 hashCode: " + obj11.hashCode());
            System.out.println("Are both same? " + (obj10 == obj11));
//            Both hash codes match → same object.
//
//(instance1 == instance2) is true → JVM guarantees only one enum constant exists.

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
