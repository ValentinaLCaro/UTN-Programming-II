/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_04;

/**
 *
 * @author vale
 */
public class Perro extends Animal{
    private String raza;
    private String tamaño;
    
    // constructor 

    public Perro(String raza, String nombre, String tamaño) {
        super(nombre);
        this.raza = raza;
        this.tamaño = tamaño;
    }
    
    // metodos

    public String getRaza() {
        return raza;
    }

    public String getTamaño() {
        return tamaño;
    }

    @Override
    public String describirAnimal() {
        String descripcion = "Animal doméstico | Raza: " + getRaza() + " | Tamaño: " + getTamaño();
        return descripcion;
    }

    @Override
    public String hacerSonido() {
        String sonido = "GUAU GUAU";
        return sonido;
     }
    
}
