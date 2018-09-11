package main.java;

import java.util.List;

class Building {
    private List<Person> persons;

    int GetPopulationCount() {
        return persons.size();
    }

    void PopulateBuilding(List<Person> personsIn) {
        persons = personsIn;
    }

    int GetTotalFloors() {
        return 2;
    }
}
