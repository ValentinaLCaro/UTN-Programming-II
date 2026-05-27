/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_04;

/**
 *
 * @author vale
 */
public class Animal {
    private String nombre;
    
    // constructor
    
    public Animal(String nombre) {
        this.nombre = nombre;
    }
    
    // metodos

    public String getNombre() {
        return nombre;
    }
    
    public String hacerSonido(){
        String sonido = "sonido irreconocible";
        return sonido;
    }
    
    public String describirAnimal(){
        String descripcion = "animal irreconocible";
        return descripcion;
    }
}
