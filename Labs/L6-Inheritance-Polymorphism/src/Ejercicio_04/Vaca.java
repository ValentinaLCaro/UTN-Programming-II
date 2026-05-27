/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_04;

/**
 *
 * @author vale
 */
public class Vaca extends Animal{
    private String manchas;
    
    // constructor

    public Vaca(String manchas, String nombre) {
        super(nombre);
        this.manchas = manchas;
    }
    
    // metodos

    public String getManchas() {
        return manchas;
    }

    @Override
    public String describirAnimal() {
        String descripcion = "Animal de granja | Manchas: " + getManchas();
        return descripcion;
    }

    @Override
    public String hacerSonido() {
        String sonido = "muuu";
        return sonido;
    }
    
}
