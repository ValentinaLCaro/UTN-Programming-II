/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_02;

/**
 *
 * @author vale
 */
public class Main {
    public static void main(String[] args) {
        // crear una biblioteca.
        Biblioteca miBiblioteca = new Biblioteca("Biblioteca Nacional UTN");
        System.out.println("Bienvenido a la " + miBiblioteca.getNombre());
        System.out.println("==========================");

        // crear al menos tres autores
        Autor autor1 = new Autor("Stephen King", "Estadounidense");
        Autor autor2 = new Autor("Bram Stoker", "Irlandés");
        Autor autor3 = new Autor("George Orwell", "Británico");
        
        autor1.mostrarInfo(); 
        autor2.mostrarInfo(); 
        autor3.mostrarInfo(); 
        System.out.println("==========================");

        // agregar 5 libros asociados a alguno de los Autores a la biblioteca
        miBiblioteca.agregarLibro("978-0307743657", "El Resplandor", 1977, autor1);
        miBiblioteca.agregarLibro("978-1501143106", "Misery", 1987, autor1);
        miBiblioteca.agregarLibro("978-0141439846", "Drácula", 1897, autor2);
        miBiblioteca.agregarLibro("978-0451524935", "1984", 1949, autor3);
        miBiblioteca.agregarLibro("978-0451526342", "Rebelión en la granja", 1945, autor3);
        System.out.println("5 libros agregados exitosamente.");
        System.out.println("==========================");

        // listar todos los libros con su información y la del autor
        miBiblioteca.listarLibros();
        System.out.println("==========================");

        // buscar un libro por su ISBN y mostrar su información
        Libro buscado = miBiblioteca.buscarLibroPorIsbn("978-0141439846");
        if (buscado != null) {
            buscado.mostrarInfo();
        }
        System.out.println("==========================");

        // filtrar y mostrar los libros publicados en un año específico
        miBiblioteca.filtrarLibrosPorAnio(1977);
        System.out.println("==========================");

        // eliminar un libro por su ISBN y listar los libros restantes
        miBiblioteca.eliminarLibro("978-1501143106");
        System.out.println("\nActualización del inventario:");
        miBiblioteca.listarLibros();
        System.out.println("==========================");

        // mostrar la cantidad total de libros en la biblioteca
        System.out.println("Total de ejemplares actualmente: " + miBiblioteca.obtenerCantidadLibros());
        System.out.println("==========================");

        // listar todos los autores de los libros disponibles
        miBiblioteca.mostrarAutoresDisponibles();
        System.out.println("==========================");
    }
}
