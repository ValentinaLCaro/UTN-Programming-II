/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author vale
 */
public class Validador {
    
        // valida que un objeto cualquiera no sea nulo
    public static void requerirObjeto(Object objeto, String nombreCampo) {
        if (objeto == null) {
            throw new IllegalArgumentException("Error de formato: El campo '" + nombreCampo + "' es obligatorio y no puede ser nulo.");
        }
    }

        // valida que un texto no sea nulo ni este vacio
    public static void requerirTexto(String texto, String nombreCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: El campo '" + nombreCampo + "' no puede estar vacio.");
        }
    }

        // valida que un numero no sea negativo (>= 0)
    public static void requerirNoNegativo(double valor, String nombreCampo) {
        if (valor < 0) {
            throw new IllegalArgumentException("Error: El campo '" + nombreCampo + "' no puede ser negativo.");
        }
    }

        // valida que un numero entero sea mayor a cero (> 0)
    public static void requerirMayorACero(int valor, String nombreCampo) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Error: El campo '" + nombreCampo + "' debe ser mayor a cero.");
        }
    }

        // valida input de numeros enteros
    public static int leerEntero(Scanner scanner, String mensajeLectura) {
        while (true) {
            System.out.print(mensajeLectura);
            try {
                int numero = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un numero entero valido.");
                scanner.nextLine(); // limpiar el error del buffer
            }
        }
    }

        // valida input de numeros decimaels
    public static double leerDouble(Scanner scanner, String mensajeLectura) {
        while (true) {
            System.out.print(mensajeLectura);
            try {
                double numero = scanner.nextDouble();
                scanner.nextLine(); // limpiar buffer
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un numero decimal valido.");
                scanner.nextLine(); // limpiar el error del buffer
            }
        }
    }

        // valida que la opcion elegida este en el menu
    public static void validarOpcionMenu(int opcion, int min, int max) {
        if (opcion < min || opcion > max) {
            throw new IllegalArgumentException("Opcion invalida. Debe seleccionar un numero entre " + min + " y " + max + ".");
        }
    }

    public static void requerirMayorCero(int cantidad, String cantidad0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
