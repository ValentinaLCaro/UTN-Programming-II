/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_02;

/**
 *
 * @author vale
 */
public class Rectangulo extends Figura{
    private double base;
    private double altura;
    
    // constructor

    public Rectangulo(double base, double altura, String nombre) {
        super(nombre);
        this.base = base;
        this.altura = altura;
    }
    
    // metodo

    @Override
    public void calcularArea() {
            double area = base * altura;
            System.out.println("El area del rectangulo es: " + area);
    }
    
}
