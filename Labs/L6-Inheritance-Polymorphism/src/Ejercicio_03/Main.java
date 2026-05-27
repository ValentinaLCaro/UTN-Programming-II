/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_03;

import java.util.ArrayList;

/**
 *
 * @author vale
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        
        listaEmpleados.add(new EmpleadoPlanta(450000.00, "Mariano"));
        listaEmpleados.add(new EmpleadoTemporal(80, "Mateo"));
    
        int contadorPlanta = 0;
        int contadorTemporal = 0;

        for (Empleado emp : listaEmpleados) {
            System.out.println("Empleado: " + emp.getNombre() + " | Sueldo Total: $" + emp.calcularSueldo());

            if (emp instanceof EmpleadoPlanta) {
                contadorPlanta++;
            } else if (emp instanceof EmpleadoTemporal) {
                contadorTemporal++;
            }
        }
        
        System.out.println("Cantidad de Empleados de Planta: " + contadorPlanta);
        System.out.println("Cantidad de Empleados Temporales: " + contadorTemporal);
    }
}
