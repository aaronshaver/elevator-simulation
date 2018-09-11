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
            Display.PrintWall(totalFloors);
//            Display.
            Display.PrintWall(totalFloors);
            Display.PrintWaitingAreas(totalFloors, persons);
            System.out.println();
        }
    }
}
