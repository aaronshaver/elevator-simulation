package main.java;

class Display {

    static void ClearScreen() throws InterruptedException {
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
        Thread.sleep(20); // pause to prevent screen flashing/tearing
    }
}
