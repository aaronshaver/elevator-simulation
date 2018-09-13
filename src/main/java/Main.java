package main.java;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final int elevatorsCount = 1;
        final int startingPopulation = 1;

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < startingPopulation; i++) {
            persons.add(new Person());
        }

        Building building = new Building();
        int totalFloors = building.getTotalFloors();
        building.populateBuilding(persons);
        building.constructElevators(elevatorsCount);

        while (building.getPopulationCount() > 0) {
            Display.clearScreen();
            List<String> strings = new ArrayList<>();
            strings.add(Display.getWallOutput(totalFloors));
            strings.add(Display.getElevatorsOutput(totalFloors, building.getElevators()));
            strings.add(Display.getWallOutput(totalFloors));
            strings.add(Display.getWaitingAreasOutput(totalFloors, persons));
            Display.printVertically(totalFloors, strings);
        }
    }
}
