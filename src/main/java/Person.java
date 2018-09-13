package main.java;

import java.util.ArrayList;
import java.util.List;

class Person {
    private int currentFloor = 0; // start the day on the ground floor
    boolean waiting = true;

    private List<Integer> desiredFloors = new ArrayList<>();

    Person() {
        desiredFloors.add(1);
        desiredFloors.add(0); // go home at the end of the day
    }

    int getCurrentFloor() {
        return currentFloor;
    }
}
