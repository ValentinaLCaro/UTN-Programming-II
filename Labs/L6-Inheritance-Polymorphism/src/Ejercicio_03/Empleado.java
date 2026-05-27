/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_03;

/**
 *
 * @author vale
 */
public abstract class Empleado {
    private String nombre;
    
    // constructor 

    public Empleado(String nombre) {
        this.nombre = nombre;
    }
    
    // metodos
    
    public String getNombre() { 
        return nombre; 
    }
    
    public abstract double calcularSueldo();}
