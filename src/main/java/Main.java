package main.java;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String characters = "->";

        for (int i = 0; i < 50; i++) {
            Display.ClearScreen();
            String output = new String(new char[i]).replace("\0", " ");
            output += characters;
            System.out.println(output);
            Thread.sleep(190);
        }
    }
}
