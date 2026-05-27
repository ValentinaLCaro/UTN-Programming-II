/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_03;

/**
 *
 * @author vale
 */
public class EmpleadoPlanta extends Empleado{
    private final double IMPUESTO = 0.15;
    private double sueldoBaseNeto;
    
    // constructor

    public EmpleadoPlanta(double sueldoBaseNeto, String nombre) {
        super(nombre);
        this.sueldoBaseNeto = sueldoBaseNeto;
    }
    
    // metodos

    @Override
    public double calcularSueldo() {
        double sueldoFinal = sueldoBaseNeto - (IMPUESTO * sueldoBaseNeto);
        return sueldoFinal;
    }
    
}
