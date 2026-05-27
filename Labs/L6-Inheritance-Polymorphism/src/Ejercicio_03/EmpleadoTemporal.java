/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_03;

/**
 *
 * @author vale
 */
public class EmpleadoTemporal extends Empleado{
    private final double PAGO_POR_HORA = 20.0;
    private int horasTrabajadas;
    
    // constructor

    public EmpleadoTemporal(int horasTrabajadas, String nombre) {
        super(nombre);
        this.horasTrabajadas = horasTrabajadas;
    }
    
    // metodo

    @Override
    public double calcularSueldo() {
       double sueldo = horasTrabajadas * PAGO_POR_HORA;
       return sueldo;
    }
    
}
