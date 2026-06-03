/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author vale
 */
public class TryWithResources {
    public static void main(String[] args) {
        String rutaArchivo = "ejemplo.txt";

        // El recurso se declara dentro de los paréntesis del try para asegurar su cierre automático
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            System.out.println("--- Contenido del archivo ---");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error de E/S al intentar procesar el archivo: " + e.getMessage());
        }
    }
}
