package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final int elevatorsCount = 1; // values above 1 are not well supported
        final int startingPopulation = 9; // >9 works, but display of group of people >9 currently is cut off
        boolean spinnerState = true;

        Building building = new Building();
        int totalFloors = building.getTotalFloors();
        building.populateBuilding(startingPopulation, building);
        building.constructElevators(elevatorsCount);

        while (getGlobalPopulation(building) > 0) {
            spinnerState = writeScreen(spinnerState, building, totalFloors);
            movePersons(building);

        }

        writeScreen(spinnerState, building, totalFloors); // final write to show empty building
    }

    private static boolean writeScreen(boolean spinnerState, Building building, int totalFloors) throws InterruptedException {
        Display.clearScreen();
        List<String> strings = new ArrayList<>();
        strings.add(Display.getWallOutput(totalFloors));
        strings.add(Display.getElevatorsOutput(totalFloors, building.getElevators()));
        strings.add(Display.getWallOutput(totalFloors));
        strings.add(Display.getWaitingAreasOutput(totalFloors, building.getPersons()));
        Display.printVertically(totalFloors, strings);
        spinnerState = Display.setAndPrintStatusSpinner(spinnerState);
        return spinnerState;
    }

    private static int getGlobalPopulation(Building building) {
        int elevatorsPopulation = 0;
        for (Elevator elevator : building.getElevators()) {
            elevatorsPopulation += elevator.getPassengersCount();
        }
        return building.getPopulationCount() + elevatorsPopulation;
    }

    private static void movePersons(Building building) {

        // move persons on current floor into elevator
        boolean peopleBoarded = false;
        List<Person> persons = new ArrayList<>(building.getPersons());
        for (Person person : persons) {
            if (person.waiting && !person.isInElevator) {
                for (Elevator elevator : building.getElevators()) {
                    if (elevator.getCurrentFloor() == person.getCurrentFloor()) {
                        peopleBoarded = true;
                        person.waiting = false;
                        person.isInElevator = true;
                        building.removePerson(person);
                        elevator.addPerson(person);
                    }
                }
            }
        }

        if (peopleBoarded) {
            return; // prevents simultaneous elevator movement and boarding -- don't want to chop heads off ;-)
        }

        // move elevators with passengers in them
        List<Elevator> elevators = new ArrayList<>(building.getElevators());
        for (Elevator elevator : elevators) {
            int currentFloor = elevator.getCurrentFloor();
            if (elevator.getPassengersCount() > 0) {
                List<Person> persons2 = new ArrayList<>(elevator.getPassengers());
                for (Person person : persons2) {
                    if (person.getDesiredFloors().size() > 0) {
                        int desiredFloor = person.getNextDesiredFloor();
                        if (currentFloor == desiredFloor) {
                            // let passenger off
                            elevator.removePerson(person);
                            building.addPerson(person);
                            person.waiting = true;
                            person.isInElevator = false;
                            person.removeMostRecentDesiredFloor();
                            if (person.getDesiredFloors().size() == 0) {
                                person.waiting = false;
                            }
                            person.setCurrentFloor(currentFloor);
                        } else if (currentFloor > desiredFloor) {
                            // move down
                            elevator.setCurrentFloor(currentFloor - 1);
                        } else {
                            // move up
                            elevator.setCurrentFloor(currentFloor + 1);
                        }
                    }
                }
            } else {
                // no passengers on elevators, so search for waiting passengers in building
                List<Person> persons2 = building.getPersons();
                List<Person> waitingPersons = persons2.stream()
                        .filter(x -> x.waiting)
                        .filter(x -> !x.isInElevator)
                        .collect(Collectors.toList());

                if (waitingPersons.size() > 0) {
                    List<Elevator> elevators2 = new ArrayList<>(building.getElevators());
                    for (Elevator elevator2 : elevators2) {
                        int currentFloor2 = elevator2.getCurrentFloor();
                        List<Person> persons3 = new ArrayList<>(waitingPersons);
                        for (Person person : persons3) {
                            int personFloor = person.getCurrentFloor();
                            if (currentFloor2 > personFloor) {
                                // move down
                                elevator2.setCurrentFloor(currentFloor - 1);
                            } else {
                                // move up
                                elevator2.setCurrentFloor(currentFloor + 1);
                            }
                        }
                    }
                }
            }
        }

        // empty the building of people who don't need to travel anymore
        List<Person> personsWithNoMoreDesiredFloors = new ArrayList<>();
        for (Person person : building.getPersons()) {
            if (person.getDesiredFloors().size() == 0 && !person.isInElevator) {
                personsWithNoMoreDesiredFloors.add(person);
            }
        }

        for (Person person : personsWithNoMoreDesiredFloors) {
            building.removePerson(person);
        }
    }
}
