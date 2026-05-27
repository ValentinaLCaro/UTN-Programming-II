/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_03;

/**
 *
 * @author vale
 */
import java.util.ArrayList;

public class Profesor {
    private static final String ERROR = "Invalido"; 
    private static int contadorId = 0; 
    
    private String id;
    private String nombre;
    private String especialidad;
    private ArrayList<Curso> cursos;
    
    // constructor
    public Profesor(String nombre, String especialidad) {
        contadorId++; 
        this.id = "PROF-" + contadorId;
        setNombre(nombre);
        setEspecialidad(especialidad);
        this.cursos = new ArrayList<>();
    }
    
    // getters y setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }
    public ArrayList<Curso> getCursos() { return cursos; }
    
    public void setNombre(String nombre) {
        if (validarString(nombre)) this.nombre = nombre;
    }
    public void setEspecialidad(String especialidad) {
        if (validarString(especialidad)) this.especialidad = especialidad;
    }
        
    // metodos
    
    private boolean validarString(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            System.out.println(ERROR);
            return false;
        }
        return true;
    }
    
    public void agregarCurso(Curso c) {
        if (c != null && !this.cursos.contains(c)) {
            this.cursos.add(c);
            c.setProfesor(this); // 
        }
    }
    
    public void eliminarCurso(Curso c) {
        if (c != null && this.cursos.contains(c)) {
            this.cursos.remove(c);
            if (c.getProfesor() == this) {
                c.setProfesor(null); 
            }
        }
    }
    
    public void listarCursos() {
        if (this.cursos.isEmpty()) {
            System.out.println("El profesor " + this.nombre + " no dicta ningún curso.");
        } else {
            System.out.println("Cursos dictados por " + this.nombre + ":");
            for (Curso c : this.cursos) {
                System.out.println("- " + c.getCodigo() + ": " + c.getNombre());
            }
        }
    }

    public void mostrarInfo(){
        System.out.println(this.toString());
    }
    
    @Override
    public String toString() {
        return "Profesor:" +
               "\nID: " + id + 
               "\nNombre: " + nombre + 
               "\nEspecialidad: " + especialidad +
               "\nCantidad de cursos asignados: " + cursos.size();
    }
}
