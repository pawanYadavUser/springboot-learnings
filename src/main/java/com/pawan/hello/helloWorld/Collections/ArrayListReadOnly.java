package com.pawan.hello.helloWorld.Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListReadOnly {
    public static void main(String[] args) {
        try{
            List<String> sampleList  = new ArrayList<>();
            sampleList.add("practice");
            sampleList.add("solve");
            sampleList.add("Interview");

            System.out.println("Initial list is : "+sampleList);

            List<String> sampleList_readonly = Collections.unmodifiableList(sampleList);
            System.out.println("Read only list : "+ sampleList_readonly);

            System.out.println("Trying adding the element in read-only list");
            sampleList_readonly.add("job");

        } catch(UnsupportedOperationException exception){

            System.out.println(exception);
        }
    }
}
