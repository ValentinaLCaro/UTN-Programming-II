/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_04;

/**
 *
 * @author vale
 */
public class Gato extends Animal{
    private String color;
    private String edad;
    
    // constructor

    public Gato(String color, String nombre, String edad) {
        super(nombre);
        this.color = color;
        this.edad = edad;
    }
    
    // metodos

    public String getColor() {
        return color;
    }

    public String getEdad() {
        return edad;
    }

    @Override
    public String describirAnimal() {
        String descripcion = "Animal doméstico | Color: " + getColor()+ " | Edad: " + getEdad();
        return descripcion;
    }

    @Override
    public String hacerSonido() {
        String sonido= "miau";
        return sonido;
    }
    
}
