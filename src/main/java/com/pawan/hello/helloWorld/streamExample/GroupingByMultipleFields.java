package com.pawan.hello.helloWorld.streamExample;

import org.apache.tomcat.util.json.JSONParser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Worker {
    String name;
    String department;
    String role;
    int salary;

    Worker(String name, String department, String role, int salary) {
        this.name = name;
        this.department = department;
        this.role = role;
        this.salary = salary;
    }

    String getDepartment() { return department; }
    String getRole() { return role; }
    int getSalary() { return salary; }
    String getName() { return name; }
}

//4️⃣ Group by Multiple Fields using Composite Key
record DeptRole(String department, String role) {}



public class GroupingByMultipleFields {

    public static void main(String[] args) {

        List<Worker> workers = List.of(
                new Worker("Amit",  "IT", "DEV",   70000),
                new Worker("Ravi",  "IT", "DEV",   75000),
                new Worker("Neha",  "IT", "QA",    60000),
                new Worker("Pooja", "HR", "ADMIN", 50000),
                new Worker("Karan", "HR", "ADMIN", 52000),
                new Worker("Maya",  "HR", "HRBP",  65000)
        );

//    1️⃣ Group by Department → Role
        Map<String, Map<String, List<String>>> groupedByDepartmentAndRole = workers.stream()
                .collect(Collectors.groupingBy(Worker::getDepartment,
                        Collectors.groupingBy(Worker::getRole,
                                Collectors.mapping(Worker::getName, Collectors.toList()))));
        System.out.println("Grouped by department and role : "+ groupedByDepartmentAndRole);

//    2️⃣ Group by Department → Role → COUNT
        Map<String, Map<String, Long>> groupedByDepartAndRoleAndGetCount = workers.stream()
                .collect(Collectors.groupingBy(Worker::getDepartment,
                        Collectors.groupingBy(Worker::getRole, Collectors.counting())
                )
        );
        System.out.println("Grpued by depart, role and count : "+groupedByDepartAndRoleAndGetCount);

        //    3️⃣ Group by Department → Role → Salary SUM
        Map<String, Map<String, Integer>>  groupedByDepartAndRoleAndSummingSalary = workers.stream().collect(Collectors.groupingBy(
                Worker::getDepartment,
                Collectors.groupingBy(
                        Worker::getRole,
                        Collectors.summingInt(n->n.getSalary()))
                )
        );

        System.out.println("Grouped by depart, role and count : "+groupedByDepartAndRoleAndSummingSalary);

//        //4️⃣ Group by Multiple Fields using Composite Key
////        record DeptRole(String department, String role) {}
//        Map<DeptRole, List<Employee>> flatGroup =
//                workers.stream()
//                        .collect(Collectors.groupingBy(
//                                e -> new DeptRole(e.getDepartment(), e.getRole())
//                        ));



//        5️⃣ Group by multiple fields → store only names
        Map<String, Map<String, List<String>>> namesGrouped =
                workers.stream()
                        .collect(Collectors.groupingBy(
                                Worker::getDepartment,
                                Collectors.groupingBy(
                                        Worker::getRole,
                                        Collectors.mapping(
                                                Worker::getName,
                                                Collectors.toList()
                                        )
                                )
                        ));
        System.out.println("named group : "+namesGrouped);


//        6️⃣ Same logic using 3 fields (Department → Role → Salary Range)
//        Map<String, Map<String, Map<String, List<Long> tripleGroup =
//                workers.stream()
//                        .collect(Collectors.groupingBy(
//                                Worker::getDepartment,
//                                Collectors.groupingBy(
//                                        Worker::getRole,
//                                        Collectors.mapping(
//                                                e -> e.getSalary() > 65000 ? "HIGH" : "LOW",
//                                                Collectors.toList()
//                                        )
//                                )
//                        ));

    }

}
