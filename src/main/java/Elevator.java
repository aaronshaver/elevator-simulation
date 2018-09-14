package main.java;

import java.util.ArrayList;
import java.util.List;

class Elevator {
    private int currentFloor = 0; // start the day on the ground floor
    private List<Person> passengers = new ArrayList<>();

    int getCurrentFloor() {
        return currentFloor;
    }

    void setCurrentFloor(int newFloor) {
        currentFloor = newFloor;
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

    void removePerson(Person person) {
        passengers.remove(person);
    }

    List<Person> getPassengers() {
        return passengers;
    }
}
