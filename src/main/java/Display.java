package main.java;

import java.util.List;

class Display {

    static void ClearScreen() throws InterruptedException {
        Thread.sleep(190); // pause to enable user to see the screen content for long enough

        final int maxClearedLines = 40;
        for (int i = 0; i < maxClearedLines; i++) {
            System.out.println();
        }

        Thread.sleep(20); // pause to prevent screen flashing/tearing
    }

    static String GetWallOutput(int totalFloors) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < totalFloors; i++) {
            builder.append("|");
        }

        return builder.toString();
    }

    static String GetWaitingAreasOutput(int totalFloors, List<Person> persons) {
        StringBuilder builder = new StringBuilder();

        for (int floor = 0; floor < totalFloors; floor++) {
            String floorContent = "_";
            int peopleWaiting = 0;
            for (Person person : persons) {
                if (person.waiting && person.GetCurrentFloor() == floor) {
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

    static void PrintVertically(int totalFloors, List<String> strings) {
        for (int i = totalFloors - 1; i >= 0; i--) {
            StringBuilder builder = new StringBuilder();
            for (String string : strings) {
                builder.append(string.charAt(i));
            }
            System.out.println(builder);
        }
    }
}
