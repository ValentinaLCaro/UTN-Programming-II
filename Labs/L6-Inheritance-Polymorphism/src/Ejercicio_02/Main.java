/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_02;
import java.util.ArrayList;

/**
 *
 * @author vale
 */

public class Main {
    public static void main(String[] args) {
        // crear la lista que va a contener hijos de la clase Figura
        ArrayList<Figura> listaFiguras = new ArrayList<>();
        
        listaFiguras.add(new Circulo(5.0, "Círculo"));
        listaFiguras.add(new Rectangulo(4.0, 6.0, "Rectángulo"));
        
        // recorrer la lista y ejecutar el método polimórfico
        for (Figura f : listaFiguras) {
            System.out.print("Nombre de la figura: " + f.getNombre() + " -> ");
            f.calcularArea(); 
        }
    }
}
