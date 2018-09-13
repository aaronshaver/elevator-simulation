package main.java;

import java.util.ArrayList;
import java.util.List;

class Building {
    private List<Person> persons;
    private List<Elevator> elevators = new ArrayList<>();

    int getPopulationCount() {
        return persons.size();
    }

    void populateBuilding(List<Person> personsIn) {
        persons = personsIn;
    }

    int getTotalFloors() {
        return 9;
    }

    void constructElevators(int elevatorsCount) {
        for (int i = 0; i < elevatorsCount; i++) {
            elevators.add(new Elevator());
        }
    }

    List<Elevator> getElevators() {
        return elevators;
    }
}
