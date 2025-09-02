package com.pawan.hello.helloWorld;

import java.util.*;

public class ObjectClassExample extends Object {

//    const int x = 10; , not allowed in java

    final int x = 10; //to create a constant in java.

    private List synchedList;

    public ObjectClassExample() {
        // create a new synchronized list to be used
        synchedList = Collections.synchronizedList(new LinkedList());
    }

    // method used to remove an element from the list
    public String removeElement() throws InterruptedException {
        synchronized (synchedList) {

            // while the list is empty, wait
            while (synchedList.isEmpty()) {
                System.out.println("List is empty...");
                synchedList.wait();
                System.out.println("Waiting...");
            }
            String element = (String) synchedList.remove(0);

            return element;
        }
    }

    // method to add an element in the list
    public void addElement(String element) {
        System.out.println("Opening...");
        synchronized (synchedList) {

            // add an element and notify all that an element exists
            synchedList.add(element);
            System.out.println("New Element:'" + element + "'");

            synchedList.notify();
            System.out.println("notify called!");
        }
        System.out.println("Closing...");
    }


    public static void main(String[] args) {
        GregorianCalendar cal = new GregorianCalendar();
        GregorianCalendar y = (GregorianCalendar) cal.clone();

        System.out.println(cal.getTime().toString());
        System.out.println(y.getTime().toString());
        System.out.println(cal.getClass());

        Integer i = Integer.valueOf(5);
        //no clone method, as It doesnt extends cloneable interface
        System.out.println(i);
        System.out.println(i.getClass());
        System.out.println(i.hashCode());

        ArrayList<String> al = new ArrayList<>();


        al.add("First element");

        List<String> alClone = (List) al.clone();

        System.out.println(al);
        System.out.println(alClone);
        System.out.println(al.getClass());
        System.out.println(al.hashCode());

        HashMap<String, String> map = new HashMap<>();
        map.put("t","TutorialsPoint");
        map.put("j","Javatpoint");

        HashMap<String, String> mapCLone = (HashMap) map.clone();

        System.out.println(map);
        System.out.println(mapCLone);
        System.out.println(map.toString());

        final ObjectClassExample demo = new ObjectClassExample();

        Runnable runA = new Runnable() {

            public void run() {
                try {
                    String item = demo.removeElement();
                    System.out.println("" + item);
                } catch (InterruptedException ix) {
                    System.out.println("Interrupted Exception!");
                } catch (Exception x) {
                    System.out.println("Exception thrown.");
                }
            }
        };

        Runnable runB = new Runnable() {

            // run adds an element in the list and starts the loop
            public void run() {
                demo.addElement("Hello!");
            }
        };

        try {
            Thread threadA1 = new Thread(runA, "A");
            threadA1.start();

            Thread.sleep(500);

            Thread threadA2 = new Thread(runA, "B");
            threadA2.start();

            Thread.sleep(500);

            Thread threadB = new Thread(runB, "C");
            threadB.start();

            Thread.sleep(1000);

            threadA1.interrupt();
            threadA2.interrupt();
        } catch (InterruptedException x) {
            System.out.println("error : "+x.getMessage());
        }
    }
}
