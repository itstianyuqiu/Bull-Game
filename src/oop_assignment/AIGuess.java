package oop_assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AIGuess extends Guess {
    public int aimode = 0;

    public ArrayList<String> possible = new ArrayList<String>();

    public AIGuess(){
        this.myName = "Computer";
        generatePossible();
    }

    public void generatePossible(){
        for (int i = 123; i <9999 ; i++) {
            String str=String.valueOf(i);
            str=str.length()==4?str:"0"+str;
            boolean b=true;
            char[] charstrs=str.toCharArray();
            for (int j = 0; j <str.length() ; j++) {
                for (int k = 0; k < j; k++) {
                    if(charstrs[k]==charstrs[j]){
                        b=false;
                    }

                }

            }
            if(b) possible.add(str);

        }
    }

    public void removepossible(String code, int bulls, int cows){
        int index = 0;
        while(true){
            if (index >= possible.size()){
                break;
            }

            String str = possible.get(index);

            int x[] = new int[4];

            for (int i = 0; i<x.length; i++){
                x[i] =secretCode[i];
            }

            for (int i = 0; i<secretCode.length; i++){
                secretCode[i] = str.charAt(i) - '0';
            }

            if(bullMatch(code) == bulls && cowMatch(code) == cows){
                index++;

            }else{
                possible.remove(index);
            }
            for (int i = 0; i<secretCode.length; i++){
                secretCode[i] = x[i];
            }
        }
    }

    public String randomString(){
        Random random = new Random();
        int a[] = new int [4];

        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();

        for(int i=0;i<a.length;i++) {
            while(true) {
                int r = random.nextInt(9) + 1;
                if(map.containsKey(r)) {
                    ;
                } else{
                    map.put(r, 1);
                    a[i] = r;
                    break;
                }
            }
        }
        String str = "";
        for (int i = 0; i<a.length; i++){
            str += a[i] + "";
        }
        return str;
    }

    private String easyAI(){

        return randomString();
    }

    private String medAI(){
        while (true){
            String code = randomString();
            if (guessHistory.contains(code)){
                continue;
            }else {
                return code;
            }
        }
    }

    private String hardAI(){
        Random random = new Random();
        String code = possible.get(random.nextInt(possible.size()));
        removepossible(code , bullMatch(code) , cowMatch(code));
        return code;
    }

    @Override
    public String generateSecretCode() {
        System.out.println("Please enter your secret code: ");
        String code = BullsCows.scanner.nextLine();
        return code;
    }

    @Override
    public String guess() {
        if (aimode == 0){
            return easyAI();
        }else if (aimode == 1){
            return medAI();
        }else if (aimode == 2){
            return hardAI();
        }
        return null;
    }

    @Override
    public void chooseMode() {
        System.out.println("select Computer's Mode: ");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.println("Enter your choice: ");
        String str = BullsCows.scanner.nextLine();
        this.aimode = str.charAt(0) - '0' -1;
    }
}