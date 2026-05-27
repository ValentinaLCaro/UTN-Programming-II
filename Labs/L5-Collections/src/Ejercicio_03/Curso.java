/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_03;

/**
 *
 * @author vale
 */
public class Curso {
    private static final String ERROR = "Invalido"; 
    private static int contadorCodigo = 0; 
    
    private String codigo;
    private String nombre;
    private Profesor profesor;
    
    // constructor
    public Curso(String nombre) {
        contadorCodigo++; 
        this.codigo = "CUR-" + contadorCodigo;
        setNombre(nombre);
        this.profesor = null; 
    }

    // getters y setters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public Profesor getProfesor() { return profesor; }
    
    public void setNombre(String nombre) {
        if (validarString(nombre)) this.nombre = nombre;
    }
    
    public void setProfesor(Profesor nuevoProfesor) {
        if (this.profesor == nuevoProfesor) {
            return;
        }

        Profesor profesorAnterior = this.profesor;
        this.profesor = nuevoProfesor; 

        if (profesorAnterior != null) {
            profesorAnterior.eliminarCurso(this);
        }

        if (nuevoProfesor != null) {
            nuevoProfesor.agregarCurso(this);
        }
    }
        
    // metodos
    private boolean validarString(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            System.out.println(ERROR);
            return false;
        }
        return true;
    }

    public void mostrarInfo(){
        System.out.println("====== DATOS DEL CURSO ======");
        System.out.println(this.toString());
        if (this.profesor != null) {
            System.out.println("Profesor a cargo: " + this.profesor.getNombre());
        } else {
            System.out.println("Profesor a cargo: [SIN ASIGNAR]");
        }
    }
    
    @Override
    public String toString() {
        return "Curso:" +
               "\nCódigo: " + codigo + 
               "\nNombre: " + nombre;
    }
}
