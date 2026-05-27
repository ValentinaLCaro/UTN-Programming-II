/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_02;

/**
 *
 * @author vale
 */
public class Circulo extends Figura{
    private double radio;
    
    // cosntructor

    public Circulo(double radio, String nombre) {
        super(nombre);
        this.radio = radio;
    }
    
    // metodos
    @Override
    public void calcularArea() {
            double area = Math.PI * Math.pow(radio, 2);
            System.out.println("El area del circulo es: " + area);
    }
}
