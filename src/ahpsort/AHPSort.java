/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ahpsort;

import java.util.Scanner;

/**
 *
 * @author andrea
 */
public class AHPSort {
    
    static String currentDataFile = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opt = 0;
        
        do {
            System.out.println("---AHP SORT---");
            opt = showMainMenu();
            
            switch (opt){
                case 1:
                    //TODO
                    System.out.println("Lo haría, pero aún no me han implementado.\n");
                    break;
                case 2:
                    //TODO
                    System.out.println("Lo haría, pero aún no me han implementado.\n");
                    break;
                case 3:
                    if (currentDataFile == null){
                        opt = -1;
                        break;
                    }
                    
                    //TODO 
                    System.out.println("Lo haría, pero aún no me han implementado.\n");
                
            }
            
        } while (opt != 0);
    }
    
    /**
     * It shows the main menu and gets the user's input.
     * 
     * It also checks if the input is valid. If it isn't, the user is asked again.
     * @return opcion option chosen by the user
     */
    private static int showMainMenu(){
        int option = 0;
        do{
            System.out.println("Elije una de las siguientes opciones:");
            System.out.println("1. Resolver ejercicio de clase (no necesita cargar fichero)");
            System.out.print("2. Cargar fichero de datos");

            if(currentDataFile != null) System.out.println(" (Archivo actual: " + currentDataFile);
            else System.out.println(" (No hay un archivo cargado)");

            if (currentDataFile != null) System.out.println("3. Resolver usando datos cargados.");
            
            System.out.println("0. Salir.");
            System.out.println();
            Scanner keyboard = new Scanner(System.in);
            System.out.print("¿Cual es tu selección? ");

            try {
                option = Integer.parseInt(keyboard.next());
            } catch(NumberFormatException e) {
                System.out.println("La opción escogida no es un número.");
                option = -1;
            }
            
        }while(option < 0 || option > 3);
        
        return option;
    }
    
}
