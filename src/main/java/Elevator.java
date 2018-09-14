package main.java;

import java.util.ArrayList;
import java.util.List;

class Elevator {
    private int currentFloor = 0; // start the day on the ground floor
    private List<Person> passengers = new ArrayList<>();

    int getCurrentFloor() {
        return currentFloor;
    }

    int getPassengersCount() {
        if (passengers != null && passengers.size() > 0) {
            return passengers.size();
        }
        return 0;
    }

    void addPerson(Person person) {
        passengers.add(person);
    }
}
