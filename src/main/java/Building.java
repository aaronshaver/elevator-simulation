package main.java;

import java.util.ArrayList;
import java.util.List;

class Building {
    private List<Person> persons = new ArrayList<>();
    private List<Elevator> elevators = new ArrayList<>();

    int getPopulationCount() {
        return persons.size();
    }

    void populateBuilding(int startingPopulation, Building building) {
        for (int i = 0; i < startingPopulation; i++) {
            persons.add(new Person(building));
        }
    }

    int getTotalFloors() {
        return 12;
    }

    void constructElevators(int elevatorsCount) {
        for (int i = 0; i < elevatorsCount; i++) {
            elevators.add(new Elevator());
        }
    }

    List<Elevator> getElevators() {
        return elevators;
    }

    List<Person> getPersons() {
        return persons;
    }

    void removePerson(Person person) {
        persons.remove(person);
    }

    void addPerson(Person person) {
        persons.add(person);
    }
}
