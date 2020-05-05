package oop_assignment;

import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class PlayerGuess extends Guess {

    public int guessmode = 0;

    public BufferedReader input = null;

    public PlayerGuess() {

        this.myName = "You";
    }

    @Override
    public String guess() {
        String code="";
        if (guessmode == 0) {
            System.out.println("Enter your guess: ");
             code = BullsCows.scanner.nextLine();
        } else {
            try {
                if ((code = input.readLine()) != null) {
                } else {
                    System.out.println("Enter your guess: ");
                    code = BullsCows.scanner.nextLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  code;
    }

    @Override
    public String generateSecretCode() {
        Random random = new Random();
        int a[] = new int[4];
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            while (true) {
                int ran = random.nextInt(9) + 1;
                if (map.containsKey(ran)) {

                } else {
                    map.put(ran, 1);
                    a[i] = ran;
                    break;
                }
            }
        }
        String str = " ";
        for (int i = 0; i < a.length; i++) {
            str += a[i] + " ";
        }
        return str;
    }

    @Override
    public void chooseMode() throws FileNotFoundException {
        System.out.println("Guess mode: ");
        System.out.println("1. Manual guess");
        System.out.println("2. Guess from a file");
        System.out.println("Enter your choice: ");

        String str = BullsCows.scanner.nextLine();
        this.guessmode = str.charAt(0) - '0' - 1;

        if (guessmode == 1) {
            System.out.println("Enter the filename: ");
            String filename = BullsCows.scanner.nextLine();
            input = new BufferedReader(new FileReader(new File(filename)));
        }

    }

}