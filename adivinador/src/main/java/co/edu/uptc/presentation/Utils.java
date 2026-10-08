package co.edu.uptc.presentation;

import java.util.Scanner;

public class Utils {
    private Scanner scanner; 

    public Utils(){
        scanner = new Scanner(System.in); 
    }

    public String read(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public int readInt(String message) {
        System.out.print(message);
        String text = scanner.nextLine();
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    //FALTA VALIDAR QUE SOLO PUEDA SER SI O NO, ESTA TOMANDO CUALQUIER COSA QUE NO SEA SI, COMO NO 
    public boolean yesOrNot(String answer){
        boolean flag = false; 
        if (answer.equalsIgnoreCase("si")) {
            flag = true; 
        }
        return flag; 
    }

    public int formatterInt(String line){
        int number; 
        try {
            number = Integer.parseInt(line); 
        } catch (Exception e) {
            number = -1; 
        }
        return number; 
    }
}
