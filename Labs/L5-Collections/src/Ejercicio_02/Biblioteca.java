/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_02;

/**
 *
 * @author vale
 */
import java.util.ArrayList;

public class Biblioteca {
    private String nombre;
    private ArrayList<Libro> libros;
    
    // constructor
    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>(); // la lista nace vacía 
    }
    
    // getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    // metodos
    public void agregarLibro(String isbn, String titulo, int anioPublicacion, Autor autor) {
        if (autor != null) {
            Libro nuevoLibro = new Libro(isbn, titulo, anioPublicacion, autor);
            this.libros.add(nuevoLibro);
        } else {
            System.out.println("No se puede agregar un libro con un autor nulo.");
        }
    }
    
    public void listarLibros(){
        if (this.libros.isEmpty()){
            System.out.println("La biblioteca esta vacia.");
        } else {
            for (Libro l : this.libros) {
                l.mostrarInfo();
            }
        }
    }
    
    public Libro buscarLibroPorIsbn(String isbn){
        if (isbn == null){
            System.out.println("Invalido: el ISBN no puede ser nulo.");
            return null;
        }
        for (Libro l : this.libros) {
            if (l.getIsbn().equalsIgnoreCase(isbn)) {
              return l; 
            }
        }
        System.out.println("No se encontro el libro.");
        return null;
    }
    
    public void eliminarLibro(String isbn){
        Libro encontrado = buscarLibroPorIsbn(isbn);
        
        if (encontrado == null){
            return;
        }
        libros.remove(encontrado);
        System.out.println("El libro fue eliminado correctamente.");
    }
    
    public int obtenerCantidadLibros() {
        return this.libros.size();
    }
    
    public void filtrarLibrosPorAnio(int anio){
        if (anio <= 0) {
            System.out.println("Invalido: El año debe ser mayor a 0.");
            return;
        }

        boolean encontroAlMenosUno = false;

        for (Libro l : this.libros) {
            if (l.getAnioPublicacion() == anio) {
                l.mostrarInfo();
                encontroAlMenosUno = true;
            }
        }

        if (!encontroAlMenosUno) {
            System.out.println("No hay libros publicados en el año: " + anio);
        }
    }
    
    public void mostrarAutoresDisponibles() {
        if (this.libros.isEmpty()) {
            System.out.println("La biblioteca está vacía. No hay autores.");
            return;
        }

        System.out.println("=== AUTORES DISPONIBLES EN LA BIBLIOTECA ===");
        // lista temporal para no repetir autores si tienen varios libros
        ArrayList<String> idsAutoresVistos = new ArrayList<>();

        for (Libro l : this.libros) {
            Autor autor = l.getAutor();
            if (!idsAutoresVistos.contains(autor.getId())) {
                System.out.println("- " + autor.getNombre() + " (" + autor.getNacionalidad() + ")");
                idsAutoresVistos.add(autor.getId());
            }
        }
    }
}
