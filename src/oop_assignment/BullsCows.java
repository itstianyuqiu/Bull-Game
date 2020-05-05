package oop_assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BullsCows{
    public static Scanner scanner = new Scanner (System.in);

    public static int guessTimes = 7;

    PlayerGuess playerGuess = new PlayerGuess();

    AIGuess aiGuess = new AIGuess();

    public void start(){
        String output = "Bulls & Cows game result.\n";
        playerGuess.init();
        aiGuess.init();

        output += "Your code: " + aiGuess.codeToString() + "\n";
        output += "Computer's code: " + playerGuess.codeToString() + "\n";

        boolean win = false;

        for(int i = 0 ; i < guessTimes ; i++){
            output += "---" + "\n";
            output += "Turn: " + (i + 1) + "\n";
            System.out.println("---");

            playerGuess.makeGuess();

            System.out.println(playerGuess.displayGuess());

            output += playerGuess.toFileGuess() + "\n";

            if (playerGuess.isWin()){
                win = true;
                System.out.println(playerGuess.myName + "win! :)");
                output += playerGuess.myName + "win! :)" + "\n";
                break;
            }

            System.out.println();
            aiGuess.makeGuess();
            System.out.println(aiGuess.displayGuess());
            output += aiGuess.toFileGuess() + "\n";
            if(aiGuess.isWin()){
                win = true;
                System.out.println(aiGuess.myName + "win! :)");
                output += aiGuess.myName + "win :)" + "\n";
                break;
            }
        }

        if (!win){
            System.out.println("Draw game.");
        }

        System.out.println("Do you want to save? (Y/N): ");
        String line = scanner.nextLine();
        if (line.equalsIgnoreCase("Y")){
            System.out.println("Enter filename: ");
            String filename = scanner.nextLine();
            try{
                FileWriter fileWriter = new FileWriter(new File(filename));
                fileWriter.write(output);
                fileWriter.close();
            }catch (IOException e){
                e.getMessage();
            }
        }
    }

    public static void main(String[] args) {

        new BullsCows().start();
    }
}
