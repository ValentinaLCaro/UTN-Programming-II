/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_02;

/**
 *
 * @author vale
 */
public abstract class Figura {
    private String nombre;
    
    // Constructor
    public Figura(String nombre) {
        this.nombre = nombre;
    }
    
    // Getter para poder usar el nombre 
    public String getNombre() {
        return nombre;
    }
    
    // Método abstracto
    public abstract void calcularArea();
}