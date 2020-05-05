package oop_assignment;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class Guess {
    int secretCode[] = null;

    ArrayList<String> guessHistory = new ArrayList<String>();

    public String myName = "";


    public abstract String generateSecretCode();

    public abstract void chooseMode() throws FileNotFoundException;

    public void init() {
        String code = generateSecretCode();
        setSecretCode(code);
        try {
            chooseMode();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setSecretCode(String code) {

        if(code.toCharArray().length!=4){
            String str="";
            for (int i = 0; i <4 ; i++) {
                str+=code.toCharArray()[(i*2)+1];
            }
            code=str;
        }
        secretCode = new int [4];
        for (int i = 0 ; i < secretCode.length; i++){
            secretCode[i] = code.charAt(i)- '0';
        }

    }
    public String codeToString() {
        String str = "";
        for (int i = 0; i < secretCode.length; i++){
            str += secretCode[i] + "";
        }
        return str;
    }



    public int bullMatch(String code){
        int sum =  0;

        for (int i = 0; i < secretCode.length; i++){
            if (secretCode[i] == code.charAt(i) - '0'){
                sum ++;
            }
        }
        return sum;
    }

    public int cowMatch(String code){
        int match[] = new int[15];
        for (int i = 0; i<secretCode.length; i++){
            match[secretCode[i]]++;
        }
        for (int i = 0; i<secretCode.length; i++){
            match[code.charAt(i) - '0'] ++;
        }
        int sum = 0;
        for (int i = 0; i<match.length; i++){
            if(match[i] == 2){
                sum++;
            }
        }
        return sum - bullMatch(code);
    }

    public abstract String guess();

    public boolean isWin() {
        String code = guessHistory.get(guessHistory.size() - 1);
        return bullMatch(code) == secretCode.length;
    }

    public void makeGuess() {
        String code = guess();
        guessHistory.add(code);
    }

    public String displayGuess() {
        String str = "";
        String code = guessHistory.get(guessHistory.size()-1);
        str += myName + " guess: " + code + "\n";
        int bulls = bullMatch(code);
        int cows = cowMatch(code);
        str += "Result: " + bulls + " ";

        if (bulls == 1 ){
            str += "bull";
        }else {
            str += "bulls";
        }
        str += " and " + cows + " ";

        if (cows == 1){
            str += "cow";
        }else {
            str += "cows";
        }
        return str;
    }


    public String toFileGuess() {
        String str = "";
        String code = guessHistory.get(guessHistory.size() - 1);
        str += myName + "guess: " + code + ", ";
        int bulls = bullMatch(code);
        int cows = cowMatch(code);
        str += "scoring " + bulls + " ";
        if (bulls == 1){
            str += "bull";
        }else{
            str += "bulls";
        }

        str += " and " + cows + " ";
        if (cows == 1){
            str += "cow";
        }else{
            str += "cows";
        }
        return str;
    }


}
