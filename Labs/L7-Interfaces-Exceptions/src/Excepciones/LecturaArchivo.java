/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author vale
 */
public class LecturaArchivo {
    public static void main(String[] args) {
        File archivo = new File("archivo.txt");

        try {
            Scanner lector = new Scanner(archivo);
            while (lector.hasNextLine()) {
                System.out.println(lector.nextLine());
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: El archivo de texto especificado no existe o no se pudo encontrar.");
        }
    }
}
