package com.pawan.hello.helloWorld.java17;

public class RecordExample {
    public record Employee(int id, String name, String address, int age) {
        public boolean isAdult(int age){
            return age>=18;
        }
        public Employee {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Username cannot be empty");
            }
            if (address.contains("@")) {
                throw new IllegalArgumentException("Invalid address");
            }
        }
    };

    public static void main(String[] args) {
        Employee emp1 = new Employee(1,"Pawan","New Delhi", 45);
        System.out.println(emp1.toString());
        System.out.println(emp1.name);
        System.out.println("Is adult : "+emp1.isAdult(emp1.age()));

//        Employee emp2 = new Employee(1,null,"New Delhi", 45);
//        System.out.println("employee2 details : "+emp2.toString());

        Employee emp3 = new Employee(1,"aman","New@Delhi", 45);
        System.out.println("employee3 details : "+emp3.toString());

    }
}
