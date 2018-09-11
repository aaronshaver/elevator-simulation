package main.java;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final int startingPopulation = 7;
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < startingPopulation; i++) {
            Person person = new Person();
            persons.add(person);
        }

        Building building = new Building();
        int totalFloors = building.GetTotalFloors();
        building.PopulateBuilding(persons);

        while (building.GetPopulationCount() > 0) {
            Display.ClearScreen();
            List<String> strings = new ArrayList<>();
            strings.add(Display.GetWallOutput(totalFloors));
            strings.add(Display.GetWallOutput(totalFloors));
            strings.add(Display.GetWaitingAreasOutput(totalFloors, persons));
            Display.PrintVertically(totalFloors, strings);
        }
    }
}
