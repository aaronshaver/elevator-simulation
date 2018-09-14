package main.java;

import java.util.ArrayList;
import java.util.List;

class Person {
    private int currentFloor = 0; // start the day on the ground floor
    boolean waiting = true;
    boolean isInElevator = false;

    private List<Integer> desiredFloors = new ArrayList<>();

    Person() {
        desiredFloors.add(1);
        desiredFloors.add(0); // go home at the end of the day
    }

    int getCurrentFloor() {
        return currentFloor;
    }

    int getNextDesiredFloor() {
        return desiredFloors.get(0);
    }

    int getNumberOfDesiredFloors() {
        return desiredFloors.size();
    }

    void removeMostRecentDesiredFloor() {
        desiredFloors.remove(0);
    }

    void setCurrentFloor(int newFloor) {
        currentFloor = newFloor;
    }
}
