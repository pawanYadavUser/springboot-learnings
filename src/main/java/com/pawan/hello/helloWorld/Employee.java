package com.pawan.hello.helloWorld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

import static org.junit.Assert.assertThat;

public class Employee {

        int id;
        String name;
        double salary;

        public double incrementSalary(int percent){
            return salary + (salary * percent/100);
        }

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public static void main(String[] args) {

        Employee[] arrayOfEmps = {
                new Employee(1, "Jeff Bezos", 100000.0),
                new Employee(2, "Bill Gates", 200000.0),
                new Employee(3, "Mark Zuckerberg", 300000.0)
        };
//        We can also obtain a stream from an existing list:

        List<Employee> empList = Arrays.asList(arrayOfEmps);
        empList.stream().forEach(System.out::println);

//        And we can create a stream from individual objects using Stream.of():

        Stream.of(arrayOfEmps[0], arrayOfEmps[1], arrayOfEmps[2]).forEach(System.out::println);

//        Or simply using Stream.builder():

        Stream.Builder<Employee> empStreamBuilder = Stream.builder();

        empStreamBuilder.accept(arrayOfEmps[0]);
        empStreamBuilder.accept(arrayOfEmps[1]);
        empStreamBuilder.accept(arrayOfEmps[2]);

        Stream<Employee> empStream = empStreamBuilder.build();
        empStream.forEach(System.out::println);

//        Java Stream Operations
//        forEach
//        forEach() is the simplest and most common operation; it loops over the stream elements, calling the supplied function on each element.
//        forEach() is a terminal operation, which means that, after the operation is performed, the stream pipeline is considered consumed, and can no longer be used.

//        map
//        map() produces a new stream after applying a function to each element of the original stream. The new stream could be of a different type.
        Integer [ ] idList = {1,2,4,5};
        List <Employee> employees = Arrays.stream(idList).
                map(element -> new Employee(element, "employee"+element.toString(), element*10000.0 )).
                toList();

        employees.stream().forEach(System.out::println);

//        Here, we obtain an Integer stream of employee IDs from an array.
//        Each Integer is passed to the function —which returns the corresponding Employee object. This effectively forms an Employee stream.

//        collect ()
//        We saw how collect() works in the previous example; it’s one of the common ways to get stuff out of the stream once we are done with all the processing:
//        collect() performs mutable fold operations (repackaging elements to some data structures and applying some additional logic, concatenating them, etc.) on data elements held in the Stream instance.

//        filter()
//        produces a new stream that contains elements of the original stream that pass a given test (specified by a predicate).

        List<Employee> empWithSalaryGT20000 = employees.stream().filter(emp -> emp.salary > 20000).collect(Collectors.toList());
        empWithSalaryGT20000.stream().map(emp->emp.salary).forEach(System.out::println);

        //findFirst
        //findFirst() returns an Optional for the first entry in the stream. The Optional can, of course, be empty
        Employee employee = employees.stream()
                .filter(e -> e.salary> 20000)
                .findFirst()
                .orElse(null);
        System.out.println(employee.toString());
//        Here, the first employee with a salary greater than 20000 is returned. If no such employee exists, then null is returned.

//        toArray
//We saw how we used collect() to get data out of the stream. If we need to get an array out of the stream, we can simply use toArray():
        Employee [] newArray = employees.stream().toArray(Employee[]::new);
        System.out.println(Arrays.toString(newArray));
//        The syntax Employee[]::new creates an empty array of Employee—which is then filled with elements from the stream.

//        flatmap
//        A stream can hold complex data structures like Stream<List<String>>. In cases like this, flatMap() helps us to flatten the data structure to simplify further operations
        List<List<String>> namesNested = Arrays.asList(
                Arrays.asList("Jeff", "Bezos"),
                Arrays.asList("Bill", "Gates"),
                Arrays.asList("Mark", "Zuckerberg"));

        List<String> namesFlatStream = namesNested.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        namesFlatStream.stream().forEach(System.out::println);
//        Notice how we were able to convert the Stream<List<String>> to a simpler Stream<String>—using the flatMap() API.

//  peek
//We saw forEach() earlier in this section, which is a terminal operation.
// However, sometimes we need to perform multiple operations on each element of the stream before any terminal operation is applied.
//
//peek() can be useful in situations like this. Simply put,
// it performs the specified operation on each element of the stream and returns a new stream that can be used further. peek() is an intermediate operation:
        Employee[] arrayOfEmpsForPeek = {
                new Employee(1, "Jeff Bezos", 100000.0),
                new Employee(2, "Bill Gates", 200000.0),
                new Employee(3, "Mark Zuckerberg", 300000.0)
        };
        List<Employee> empListForPeek = Arrays.asList(arrayOfEmps);

        List<Employee> peekResult =  empList.stream()
                .peek(e->e.incrementSalary(10))
                .peek(System.out::println)
                .collect(Collectors.toList());

        peekResult.stream().forEach(System.out::println);

//        Flat Mapping Operations: MapToInt, MapToLong, and MapToDouble
//These methods transform a stream’s elements into an IntStream, LongStream, or DoubleStream respectively,
// which are specialized streams for handling primitive data types efficiently.
// By using these methods, you can avoid the overhead associated with boxing and unboxing objects.

        List<String> numbersAsString = Arrays.asList("10000", "20000");
        IntStream intStream = numbersAsString.stream()
                .mapToInt(Integer::parseInt);

        intStream.forEach(System.out::println);

        List<String> longNumbersAsString = Arrays.asList("10000000000", "20000000000");
        LongStream longStream = longNumbersAsString.stream()
                .mapToLong(Long::parseLong);
        longStream.forEach(System.out::println);

        List<String> doubleNumbersAsString = Arrays.asList("1.5", "2.5", "3.5");
        DoubleStream doubleStream = doubleNumbersAsString.stream()
                .mapToDouble(Double::parseDouble);
        doubleStream.forEach(System.out::println);

//        Flat Mapping Operations: flatMapToInt, flatMapToLong, and flatMapToDouble
//These operations are used when each element of a stream should be mapped to a stream of primitive values (IntStream, LongStream, or DoubleStream).
// They flatten the resulting streams into a single stream.

//        flatMapToInt
//        Maps each element to an IntStream and flattens the result.
        Stream<String> strings = Stream.of("1,2,3", "4,5");
        IntStream intFlatStream = strings.flatMapToInt(s -> Arrays.stream(s.split(","))
                .mapToInt(Integer::parseInt));
        intFlatStream.forEach(System.out::println);

//        flatMapToLong
//        Similar to flatMapToInt, flatMapToLong produces a LongStream
        Stream<String> complexLonStrings = Stream.of("10000000000,20000000000", "30000000000");
        LongStream longFlatStream = complexLonStrings.flatMapToLong(s -> Arrays.stream(s.split(","))
                .mapToLong(Long::parseLong));
        longFlatStream.forEach(System.out::println);

//        flatMapToDouble
//Maps each element to a DoubleStream and flattens the result.
        Stream<String> complexDoubleStrings = Stream.of("1.1,2.2", "3.3,4.4");
        DoubleStream doubleFlatStream = complexDoubleStrings.flatMapToDouble(s -> Arrays.stream(s.split(","))
                .mapToDouble(Double::parseDouble));
        doubleFlatStream.forEach(System.out::println);

//        mapMulti
//        Introduced in Java 9, the mapMulti methods provide a powerful way to perform multi-level mappings,
//        allowing you to handle more complex transformations that yield multiple results from a single input element.
        Stream.of(1, 2, 3).<String>mapMulti((number, consumer) -> {
            consumer.accept(number + "a");
            consumer.accept(number + "b");
        }).forEach(System.out::println);

//        mapMultiToInt
        Stream.of("1,2", "3,4").mapMultiToInt((s, consumer) -> {
            Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).forEach(consumer);
        }).forEach(System.out::println);

//        mapMultiToLong
        Stream.of("10000000000,20000000000").mapMultiToLong((s, consumer) -> {
            Arrays.stream(s.split(",")).mapToLong(Long::parseLong).forEach(consumer);
        }).forEach(System.out::println);

//        mapMultiToDouble
        Stream.of("1.1,2.2").mapMultiToDouble((s, consumer) -> {
            Arrays.stream(s.split(",")).mapToDouble(Double::parseDouble).forEach(consumer);
        }).forEach(System.out::println);

        //Stream Creation!
        //Streams can be created from different element sources
        //e.g. collections or arrays with the help of stream() and of() methods
        String [] arr = {"a","b","c"};
        //from array
        Stream <String> stream = Arrays.stream(arr);
        stream.forEach(System.out::println);
        //stream example
        Stream <String> streamFromStringSequence = Stream.of("a","b","c");
        streamFromStringSequence.forEach(System.out::println);

        List<String> list = new ArrayList<>();
        list.add("Pawan");
        list.add("Aman");
        list.add("Jay");
        list.add("Rahul");
        Stream<String> streamFromList = list.stream();
        streamFromList.forEach(System.out::println);
        //Multi-threading With Streams


        //Stream Operations
        // System.out.println(stream.distinct().count());

        //Stream Iterating
        for(String string : list){
            if(string.contains("a")){
                System.out.println(string);
            }
        }

        System.out.println("Checking if the list contains any string with char a");
        System.out.println(list.stream().anyMatch(element -> element.contains("a")));

        // boolean anyMatch(Predicate<? super T> predicate) Returns whether any elements of this stream match the provided predicate.

        //void forEach(Consumer<? super T> action)  Performs an action for each element of this stream.

        // The filter() method allows us to pick a stream of elements that satisfy a predicate.

        System.out.println("Printing string having character a in it");
        list.stream().filter(element-> element.contains("a")).forEach(System.out::println);

        //mapping
        //To convert elements of a Stream by applying a special function to them and to collect these new elements into a Stream
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);

        integers.stream().map(element -> element*2).forEach(System.out::println);

        //flat map
        List<List<Integer>> numbers = Arrays.asList(
                Arrays.asList(1,2,3,4),
                Arrays.asList(5,6),
                Arrays.asList(7,8,9,10)
        );

        List<Integer> flatNumberList = numbers.stream().flatMap(element -> element.stream()).toList();
        flatNumberList.stream().forEach(System.out::print);


    }

}