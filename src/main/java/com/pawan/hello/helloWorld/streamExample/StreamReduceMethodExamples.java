package com.pawan.hello.helloWorld.streamExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Review {

    private int points;
    private String review;

    public Review(int points, String review){
        this.points = points;
        this.review = review;
    }

    public int getPoints(){
        return points;
    }

    public String getReview(){
        return review;
    }

    // constructor, getters and setters
}

class Rating {

    double points;
    List<Review> reviews = new ArrayList<>();

    public Rating(double points, List<Review> reviews){
        this.points = points;
        this.reviews = reviews;
    }

    public double getPoints(){
        return this.points;
    }

    public void addRating(Review review){
        reviews.add(review);
        computeRating();
    }

    public double computeRating(){
        double totalPoints = reviews.stream().map(Review::getPoints).reduce(0,Integer::sum);
        this.points = totalPoints/ reviews.size();
        return this.points;
    }

    public static Rating average(Rating r1, Rating r2){
        Rating combined = new Rating(0,null);
        combined.reviews = new ArrayList<>(r1.reviews);
        combined.reviews.addAll(r2.reviews);
        combined.computeRating();
        return combined;
    }
}

class User{
    private String name;
    private int age;
    private Rating rating;

    public User (String name, int age, Rating rating){
        this.name = name;
        this.age = age;
        this.rating = rating;
    }

    public int getAge(){
        return age;
    }

    public String getName(){
        return name;
    }

    public Rating getRating(){
        return rating;
    }
}

public class StreamReduceMethodExamples {

    private static int divide(int value, int factor) {
        int result = 0;
        try {
            result = value / factor;
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception: Division by Zero");
        }
        return result;
    }


    public static void main(String[] args) {
        /*
            Before we look deeper into using the Stream.reduce() operation, let’s break down the operation’s participant elements into separate blocks.
            That way, we’ll understand more easily the role that each one plays.
            Identity – an element that is the initial value of the reduction operation and the default resu
            lt if the stream is empty
            Accumulator – a function that takes two parameters: a partial result of the reduction operation and the next element of the stream
            Combiner – a function used to combine the partial result of the reduction operation when the reduction is parallelized or
            when there’s a mismatch between the types of the accumulator arguments and the types of the accumulator implementation
        */
        List<String> letters = Arrays.asList("a","b","c","d");
        System.out.println("result 1 via accumulator expression : "+letters.stream().reduce("",(partialResult, element)-> partialResult+element));
        System.out.println("result 2 via method reference : "+letters.stream().reduce("",String::concat));

        //Joining the uppercase elements of the letters
        System.out.println("Uppercase version : "+letters.stream().reduce("",(partialResult, element)-> partialResult.toUpperCase()+element.toUpperCase()));

        //we can use reduce() in a parallelized stream


        /*
        When a stream executes in parallel, the Java runtime splits the stream into multiple substreams.
        In such cases, we need to use a function to combine the results of the substreams into a single one.
        This is the role of the combiner — in the above snippet, it’s the Integer::sum method reference.
        */
        List <Integer> ages = Arrays.asList(25,30,45,50,65);
        System.out.println("Parallelized stream result : "+ ages.stream().reduce(0, (a,b)->a+b));
        System.out.println("Parallelized stream result via method reference : "+ ages.parallelStream().reduce(0, Integer::sum, Integer::sum));

        //reduce method with class objects
        List<User> users = Arrays.asList(new User("user1",34, new Rating(2, null)), new User("user2", 45, new Rating(2, null)));
        int sumOfAges = users.stream().reduce(0, (partialResultAgeSum, user)-> partialResultAgeSum+user.getAge(), Integer::sum );
        System.out.println("Reduce example result with combiner : "+sumOfAges);

        //In this case, we have a stream of User objects, and the types of the accumulator arguments are Integer and User.
        // However, the accumulator implementation is a sum of Integers, so the compiler just can’t infer the type of the user parameter
        //We can fix this issue by using a combiner

        //When we use parallelized streams, we should make sure that reduce() or any other aggregate operations executed on the streams are:
        //
        //associative: the result is not affected by the order of the operands
        //non-interfering: the operation doesn’t affect the data source
        //stateless and deterministic: the operation doesn’t have state and produces the same output for a given input
        //We should fulfill all these conditions to prevent unpredictable results.
        //
        //As expected, operations performed on parallelized streams, including reduce(), are executed in parallel,
        // hence taking advantage of multi-core hardware architectures.


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int divider = 0;
        System.out.println(numbers.stream().reduce(0, (a, b) -> divide(a, divider) + divide(a,divider)));

        //add the rating for user1
        User john = new User("John", 30, new Rating(0, new ArrayList<>()));
        john.getRating().addRating(new Review(5,"Positive"));
        john.getRating().addRating(new Review(3, "normal"));

        //add the rating for user2
        User julie = new User("Julie", 30, new Rating(0, new ArrayList<>()));
        julie.getRating().addRating(new Review(4,"average"));
        julie.getRating().addRating(new Review(2, "normal"));
        julie.getRating().addRating(new Review(4, "below average"));

        List<User> usersForReduce = Arrays.asList(john, julie);

        Rating averageRating = usersForReduce.stream().reduce(new Rating(0,new ArrayList<>()), (resultRating, user) -> Rating.average(resultRating, user.getRating()), Rating::average);

        System.out.println("Average rating : "+averageRating.getPoints()+ " for users "+ usersForReduce.stream().reduce("", (partialUserList, user)-> user.getName()+" , "+partialUserList, String::concat));

    }
}
