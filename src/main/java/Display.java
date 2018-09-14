package main.java;

import java.util.List;

class Display {

    private boolean spinner = true;

    static void clearScreen() throws InterruptedException {
        Thread.sleep(1900); // pause to enable user to see the screen content for long enough

        final int maxClearedLines = 40;
        for (int i = 0; i < maxClearedLines; i++) {
            System.out.println();
        }

        Thread.sleep(20); // pause to prevent screen flashing/tearing
    }

    static String getWallOutput(int totalFloors) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < totalFloors; i++) {
            builder.append("#");
        }

        return builder.toString();
    }

    static String getWaitingAreasOutput(int totalFloors, List<Person> persons) {
        StringBuilder builder = new StringBuilder();

        for (int floor = 0; floor < totalFloors; floor++) {
            String floorContent = ".";
            int peopleWaiting = 0;
            for (Person person : persons) {
                if (person.waiting && person.getCurrentFloor() == floor) {
                    peopleWaiting++;
                }
            }
            if (peopleWaiting > 0) {
                floorContent = Integer.toString(peopleWaiting);
            }
            builder.append(floorContent);
        }

        return builder.toString();
    }

    static void printVertically(int totalFloors, List<String> strings) {
        for (int i = totalFloors - 1; i >= 0; i--) {
            StringBuilder builder = new StringBuilder();
            for (String string : strings) {
                builder.append(string.charAt(i));
            }
            System.out.println(builder);
        }
    }

    static String getElevatorsOutput(int totalFloors, List<Elevator> elevators) {
        StringBuilder elevatorColumn = new StringBuilder();
        for (int i = totalFloors - 1; i >= 0; i--) {
            elevatorColumn.append(".");
        }
        for (int floor = 0; floor < totalFloors; floor++) {
            for (Elevator elevator : elevators) {
                if (elevator.getCurrentFloor() == floor) {

                    String elevatorDisplay;
                    int passengersCount = elevator.getPassengersCount();
                    if (passengersCount == 0) {
                        elevatorDisplay = "]";
                    } else {
                        elevatorDisplay = Integer.toString(passengersCount);
                    }

                    StringBuilder newElevatorColumn = new StringBuilder(elevatorColumn.toString());
                    newElevatorColumn.setCharAt(floor, elevatorDisplay.charAt(0));
                    elevatorColumn = new StringBuilder(newElevatorColumn.toString());
                }
            }
        }

        return elevatorColumn.toString();
    }

    static boolean setAndPrintStatusSpinner(boolean spinnerState) {
        if (spinnerState) {
            System.out.println("/");
            spinnerState = false;
        } else {
            System.out.println("\\");
            spinnerState = true;
        }

        return spinnerState;
    }
}
