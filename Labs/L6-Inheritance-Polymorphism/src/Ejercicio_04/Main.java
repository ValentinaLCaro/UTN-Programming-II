/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_04;

import java.util.ArrayList;

/**
 *
 * @author vale
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Animal> listaAnimales = new ArrayList<>();
        
        listaAnimales.add(new Perro("Labrador", "Lupita", "Mediano"));
        listaAnimales.add(new Gato("Blanco", "Azrael", "Adulto"));
        listaAnimales.add(new Vaca("Marrones", "Lola"));
        
        for (Animal an : listaAnimales) {
            System.out.println(an.getNombre());
            System.out.println("Descripcion: " + an.describirAnimal()+ "\nSonido: " + an.hacerSonido());
        }
    }
}
