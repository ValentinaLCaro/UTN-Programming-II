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

public class Universidad {
    private String nombre;
    private ArrayList<Profesor> profesores;
    private ArrayList<Curso> cursos;
    
    // constructor
    public Universidad(String nombre) {
        this.nombre = nombre;
        this.profesores = new ArrayList<>();
        this.cursos = new ArrayList<>();
    }
    
    // metodos requeridos
    public void agregarProfesor(Profesor p) {
        if (p != null && !profesores.contains(p)) {
            profesores.add(p);
        }
    }
    
    public void agregarCurso(Curso c) {
        if (c != null && !cursos.contains(c)) {
            cursos.add(c);
        }
    }
    
    public void asignarProfesorACurso(String codigoCurso, String idProfesor) {
        Curso cursoEncontrado = buscarCursoPorCodigo(codigoCurso);
        Profesor profeEncontrado = buscarProfesorPorId(idProfesor);
        
        if (cursoEncontrado != null && profeEncontrado != null) {
            cursoEncontrado.setProfesor(profeEncontrado);
            System.out.println("Profesor asignado correctamente.");
        } else {
            System.out.println("No se pudo asignar. Verifique ID y Código.");
        }
    }
    
    public void listarProfesores() {
        System.out.println("=== LISTADO DE PROFESORES ===");
        for (Profesor p : profesores) {
            p.mostrarInfo();
        }
    }
    
    public void listarCursos() {
        System.out.println("=== LISTADO DE CURSOS ===");
        for (Curso c : cursos) {
            c.mostrarInfo();
        }
    }
    
    public Profesor buscarProfesorPorId(String id) {
        for (Profesor p : profesores) {
            if (p.getId().equalsIgnoreCase(id)) return p;
        }
        return null;
    }
    
    public Curso buscarCursoPorCodigo(String codigo) {
        for (Curso c : cursos) {
            if (c.getCodigo().equalsIgnoreCase(codigo)) return c;
        }
        return null;
    }
    
    public void eliminarCurso(String codigo) {
        Curso encontrado = buscarCursoPorCodigo(codigo);
        if (encontrado != null) {
            encontrado.setProfesor(null); 
            cursos.remove(encontrado);
            System.out.println("Curso eliminado exitosamente.");
        }
    }
    
    public void eliminarProfesor(String id) {
        Profesor encontrado = buscarProfesorPorId(id);
        if (encontrado != null) {
            ArrayList<Curso> cursosACargo = new ArrayList<>(encontrado.getCursos());
            
            for (Curso c : cursosACargo) {
                c.setProfesor(null);
            }
            profesores.remove(encontrado);
            System.out.println("Profesor eliminado exitosamente.");
        }
    }
    
    public void mostrarReporteCursosPorProfesor() {
        System.out.println("\n=== REPORTE: CANTIDAD DE CURSOS POR PROFESOR ===");
        for (Profesor p : profesores) {
            System.out.println(p.getNombre() + " (" + p.getEspecialidad() + ") -> Dicta " + p.getCursos().size() + " cursos.");
        }
    }
}
