/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

import java.util.Scanner;

/**
 *
 * @author vale
 */
public class ConversionNumero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese un texto para convertir a número entero: ");
        String entrada = scanner.nextLine();

        try {
            int numero = Integer.parseInt(entrada);
            System.out.println("Conversión exitosa. El número es: " + numero);
        } catch (NumberFormatException e) {
            System.out.println("Error: El texto ingresado '" + entrada + "' no tiene un formato numérico válido.");
        } finally {
            scanner.close();
        }
    }
}
