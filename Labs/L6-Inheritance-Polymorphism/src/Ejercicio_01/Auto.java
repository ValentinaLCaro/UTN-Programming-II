/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_01;

/**
 *
 * @author vale
 */
public class Auto extends Vehiculo{
    private int cantidadPuertas;
    
    // constructor

    public Auto(int cantidadPuertas, String modelo, String marca) {
        super(modelo, marca);
        this.cantidadPuertas = cantidadPuertas;
    }
    
    // metodos

    @Override
    public void mostrarInfo() {
        super.mostrarInfo(); // info del vehiculo
        System.out.println("Detalles del auto: " + toString()); // info del auto
    }
    
    @Override
    public String toString() {
        return "Auto{" + "cantidadPuertas=" + cantidadPuertas + '}';
    }
}
