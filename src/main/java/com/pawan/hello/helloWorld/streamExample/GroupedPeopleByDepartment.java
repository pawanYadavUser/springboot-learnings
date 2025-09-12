package com.pawan.hello.helloWorld.streamExample;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee{
    String name;
    String department;
    Employee(String name, String department){
        this.name = name;
        this.department = department;
    }

    public String getDepartment(){return department;}

    public String getName(){ return name;}
}

public class GroupedPeopleByDepartment {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice","HR"),
                new Employee("Bob","IT"),
                new Employee("Charlie","IT"),
                new Employee("Diana","Finance")
        );

        // "HR"->["Alice"]
        // "IT"->[Bob, Charlie
        // ]
        // "Finance"->[Diana]

        Map<String, List<Employee>> groupedByDepart = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println(groupedByDepart);

        groupedByDepart.forEach((depart,employeeList)->{
            System.out.println(depart+"->"+employeeList.stream().map(Employee::getName).toList());
        });
    }
}
