/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_01;

/**
 *
 * @author vale
 */
public class Vehiculo {
    private String modelo;
    private String marca;
    
    // constructor

    public Vehiculo(String modelo, String marca) {
        this.modelo = modelo;
        this.marca = marca;
    }
    
    // metodos
    public void mostrarInfo(){
        System.out.println("Vehiculo{" + "modelo=" + modelo + ", marca=" + marca + '}');
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "modelo=" + modelo + ", marca=" + marca + '}';
    }
}
