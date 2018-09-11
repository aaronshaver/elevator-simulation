package main.java;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            Display.ClearScreen();
            System.out.println(i);
            Thread.sleep(200);
        }
    }
}
