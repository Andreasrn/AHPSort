/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ahpsort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
                    showLoadingFileMenu();
                    break;
                case 3: //Solve using custom file's data
                    if (currentDataFile == null){
                        opt = -1;
                        break;
                    }
                    
                    ArrayList<ArrayList<ArrayList<Double>>> data = fileToMatrix();
                    if (data != null) printData(data);
                    
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
    
    /**
     * It returns an array of matrixes derived from currentDataFile attribute.
     * 
     * The result is an static array of ArrayList<ArrayList<Double>>, which
     * are matrixes. The first one is the one about criteria, and the other ones
     * correspond to each criterion
     * 
     * @return array Array containing matrixes
     */
    private static ArrayList<ArrayList<ArrayList<Double>>> fileToMatrix(){
        if (!currentDataFile.exists()){
            System.out.println("El archivo cargado ya no existe. Por favor, cárgalo de nuevo. \n");
            return null;
        }
        
        ArrayList<ArrayList<ArrayList<Double>>> output = new ArrayList<>();
        
        try{
            
            FileReader fr = new FileReader(currentDataFile);
            BufferedReader br = new BufferedReader(fr);
            
            int numCriteria = Integer.parseInt(br.readLine());
            int numAlternatives = Integer.parseInt(br.readLine());
            
            br.readLine(); //Skip empty line
            
            String m;
            for (int i = 0; i < numCriteria+1; i++){
                output.add(stringToMatrix(br.readLine()));
                
                if (i == numCriteria && output.size() != i+1){ //Check if file was wrongly written
                    throw new Exception();
                }
            }

        } catch (Exception e){
            System.out.println("Ha habido un problema con el archivo.");
            currentDataFile = null;
            return null;
        } 

        return output;
    }  
    
    /**
     * It turns an string into a matrix following certain rules.
     * 
     * A given string will contain values separated by ','. Each row will
     * be divided by a '-'
     * @param s string to turn into a matrix
     * @return Matrix
     */
    public static ArrayList<ArrayList<Double>> stringToMatrix(String s){
        String[] rows = s.split("-");
        ArrayList<ArrayList<Double>> m = new ArrayList<>();

        for (int j = 0; j < rows.length; j++){
            m.add(new ArrayList<>());

            String[] values  = rows[j].split(",");

            for (int k = 0; k< values.length; k++){
                m.get(j).add(Double.parseDouble(values[k]));
            }

        }
        
        return m;
    }
    
    /**
     * It shows on the screen the matrix given
     * @param m Matrix to be shown
     */
    public static void printMatrix(ArrayList<ArrayList<Double>> m){
        for (int i = 0;i < m.size(); i++){
            for (int j = 0; j < m.get(i).size(); j++){
                System.out.print(m.get(i).get(j)+"  ");
            }
            
            System.out.println();
        }
        
        System.out.println();
    }
    
    /**
     * It shows on the screen the loaded file's data
     * @param data 
     */
    public static void printData(ArrayList<ArrayList<ArrayList<Double>>> data){
        System.out.println("Estos son los datos del archivo "+currentDataFile.getName());
        System.out.println("Matriz de comparación entre criterios:");
        printMatrix(data.get(0));
        System.out.println("Matrices de comparación de alternativas según cada criterio: ");
        
        for (int i = 1; i < data.size(); i++){
            System.out.println("Criterio "+i+": ");
            printMatrix(data.get(i));
        }
    }
}
