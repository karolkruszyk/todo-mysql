package pl.karolkruszyk;

import java.util.Scanner;

public class Menu {
    public static void showMenu (Tasks tasks){
        Scanner keyboard = new Scanner(System.in);
        int userChoice;
        while (true){
            System.out.println();
            System.out.println("<1> Nowe zadanie");
            System.out.println("<2> Wyświetl zadania");
            System.out.println("<3> Usuń zadanie");
            System.out.println("<6> Wyjście");
            System.out.print("Wybierz: ");
            userChoice = keyboard.nextInt();
            System.out.println();


            switch(userChoice){
                case 1 -> tasks.addTask();
                case 2 -> tasks.displayTaskList();
                case 3 -> tasks.deleteTask();
                case 6 -> System.exit(0);
                default -> System.out.println("Nie ma takiej funkcji!");
            }
        }
    }
}
