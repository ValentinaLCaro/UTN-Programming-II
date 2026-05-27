/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_03;

/**
 *
 * @author vale
 */
public class Main {
    public static void main(String[] args) {
        Universidad miFacultad = new Universidad("UTN - Programación a Distancia");

        // =========================================================================
        // TAREA 1 y 2: Crear al menos 3 profesores y 5 cursos, y agregarlos.
        // =========================================================================
        System.out.println("\n--- TAREAS 1 Y 2: Creando Profesores y Cursos ---");
        Profesor profe1 = new Profesor("Ada Wong", "Lógica y Algoritmos");
        Profesor profe2 = new Profesor("Ryan Gosling", "Programación Orientada a Objetos");
        Profesor profe3 = new Profesor("Alex Turner", "Matemáticas y Estructuras");

        Curso curso1 = new Curso("Programación I");
        Curso curso2 = new Curso("Programación II");
        Curso curso3 = new Curso("Base de Datos");
        Curso curso4 = new Curso("Probabilidad y Estadística");
        Curso curso5 = new Curso("Inglés Técnico");

        miFacultad.agregarProfesor(profe1);
        miFacultad.agregarProfesor(profe2);
        miFacultad.agregarProfesor(profe3);

        miFacultad.agregarCurso(curso1);
        miFacultad.agregarCurso(curso2);
        miFacultad.agregarCurso(curso3);
        miFacultad.agregarCurso(curso4);
        miFacultad.agregarCurso(curso5);
        System.out.println("=========================================");

        // asignar profesores a cursos
        miFacultad.asignarProfesorACurso("CUR-1", "PROF-2");
        miFacultad.asignarProfesorACurso("CUR-2", "PROF-2");
        miFacultad.asignarProfesorACurso("CUR-4", "PROF-3");
        miFacultad.asignarProfesorACurso("CUR-3", "PROF-1");
        miFacultad.asignarProfesorACurso("CUR-5", "PROF-1");

        // listar cursos con su profesor y profesores con sus cursos
        miFacultad.listarProfesores();
        miFacultad.listarCursos();
        System.out.println("=========================================");

        // cambiar el profesor de un curso y verificar sincronización
        miFacultad.asignarProfesorACurso("CUR-3", "PROF-3");
        
        profe1.listarCursos(); 
        profe3.listarCursos(); 
        System.out.println("=========================================");

        // remover un curso y confirmar que ya no aparece en el profesor
        miFacultad.eliminarCurso("CUR-5");
        profe1.listarCursos();
        System.out.println("=========================================");

        // remover un profesor y dejar profesor = null
        miFacultad.eliminarProfesor("PROF-2");
        
        Curso verificacion = miFacultad.buscarCursoPorCodigo("CUR-2");
        if (verificacion != null) verificacion.mostrarInfo();
        System.out.println("=========================================");

        // mostrar un reporte: cantidad de cursos por profesor
        miFacultad.mostrarReporteCursosPorProfesor();
        System.out.println("=========================================");
    }
}
