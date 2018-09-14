package main.java;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final int elevatorsCount = 1;
        final int startingPopulation = 3;
        boolean spinnerState = true;

        Building building = new Building();
        int totalFloors = building.getTotalFloors();
        building.populateBuilding(startingPopulation);
        building.constructElevators(elevatorsCount);

        while (getGlobalPopulation(building) > 0) {
            Display.clearScreen();
            List<String> strings = new ArrayList<>();
            strings.add(Display.getWallOutput(totalFloors));
            strings.add(Display.getElevatorsOutput(totalFloors, building.getElevators()));
            strings.add(Display.getWallOutput(totalFloors));
            strings.add(Display.getWaitingAreasOutput(totalFloors, building.getPersons()));
            Display.printVertically(totalFloors, strings);
            spinnerState = Display.setAndPrintStatusSpinner(spinnerState);

            movePersons(building);
        }
    }

    private static int getGlobalPopulation(Building building) {
        int elevatorsPopulation = 0;
        for (Elevator elevator : building.getElevators()) {
            elevatorsPopulation += elevator.getPassengersCount();
        }
        return building.getPopulationCount() + elevatorsPopulation;
    }

    private static void movePersons(Building building) {

        // move persons into elevator
        List<Person> persons = new ArrayList<>(building.getPersons());
        for (Person person : persons) {
            if (person.waiting && !person.isInElevator) {
                for (Elevator elevator : building.getElevators()) {
                    if (elevator.getCurrentFloor() == person.getCurrentFloor()) {
                        person.waiting = false;
                        person.isInElevator = true;
                        building.removePerson(person);
                        elevator.addPerson(person);
                    }
                }
            }
        }

        // move elevators
        List<Elevator> elevators = new ArrayList<>(building.getElevators());
        for (Elevator elevator : elevators) {
            int currentFloor = elevator.getCurrentFloor();
            if (elevator.getPassengersCount() > 0) {
                List<Person> persons2 = new ArrayList<>(elevator.getPassengers());
                for (Person person : persons2) {
                    int desiredFloor = person.getNextDesiredFloor();
                    if (currentFloor < desiredFloor) {
                        // move up
                        elevator.setCurrentFloor(currentFloor + 1);
                        break;
                    } else if (currentFloor > desiredFloor) {
                        // move down
                        elevator.setCurrentFloor(currentFloor - 1);
                        break;
                    } else {
                        // let passenger off
                        elevator.removePerson(person);
                        building.addPerson(person);
                        person.waiting = true;
                        person.isInElevator = false;
                        person.removeMostRecentDesiredFloor();
                        person.setCurrentFloor(currentFloor);
                    }
                }
            }
        }

        for (Person person : building.getPersons()) {
            if (person.getNumberOfDesiredFloors() == 0) {
                building.removePerson(person);
            }
        }
    }
}
