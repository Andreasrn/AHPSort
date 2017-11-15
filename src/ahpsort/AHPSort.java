/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ahpsort;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author andrea
 */
public class AHPSort {
    
    static File currentDataFile = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int opt = 0;
        
        do {
            System.out.println("---AHP SORT---");
            opt = showMainMenu();
            
            switch (opt){
                case 1: //Solve class problems
                    //TODO
                    System.out.println("Lo haría, pero aún no me han implementado.\n");
                    break;
                case 2: //Load custom file
                    //TODO
                    showLoadingFileMenu();
                    break;
                case 3: //Solve using custom file's data
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

            if(currentDataFile != null) System.out.println(" (Archivo actual: " + currentDataFile.getName() + ")");
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
    
    /**
     * It shows the menu for loading a custom file. 
     * 
     * It handles the possible errors which can appear. If a file is stored,
     * it must exist.
     */
    private static void showLoadingFileMenu() throws IOException{
        System.out.println("---Cargar fichero---");
        System.out.println("Instrucciones para crear un archivo de valores.");
        System.out.println("-Primera línea: Número de criterios.");
        System.out.println("-Segunda línea: Número de alternativas.");
        System.out.println("-Tercera línea: Línea en blanco");
        System.out.println("-Cuarta línea: Matriz de criterios, separando los valores con comas e indicando el fin de una línea con un guión. \nEjemplo de matriz identidad 2x2: 1,0-0,1");
        System.out.println("-Sucesivas líneas(sin líneas en blanco): Matrices de comparaciones según criterio.");
        System.out.println("-Para ver un ejemplo, consulta el archivo sample_input.txt");
        
        Scanner keyboard = new Scanner(System.in);
        File file = null;
        boolean exit = true;
        do {
            System.out.print("Nombre del archivo (escribe 0 para volver atrás): ");
            file = new File(keyboard.next());
            
            if (!file.getName().equals("0")){
                if (!file.exists()){
                    System.out.println("El archivo no existe.");
                    exit = false;
                } else {
                    currentDataFile = file;
                    exit = true;
                }
            } else {
                exit = true;
            }
            
        } while(!exit);

    }
    
}
