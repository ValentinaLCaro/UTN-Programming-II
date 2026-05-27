/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_02;

/**
 *
 * @author vale
 */
public class Libro {
    private static final String ERROR = "Invalido"; // constante con mensaje para validaciones
    
    private String isbn;
    private String titulo;
    private int añoPublicacion;
    private Autor autor;
    
    // constructores
    public Libro(String isbn, String titulo, int añoPublicacion, Autor autor) {
        setIsbn(isbn);
        setTitulo(titulo);
        setAnioPublicacion(añoPublicacion);
        setAutor(autor);
    }

    // getters y setters
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public int getAnioPublicacion() { return añoPublicacion; }
    public Autor getAutor() { return autor; }
    
    public void setIsbn(String isbn) {
        if (validarString(isbn)) this.isbn = isbn;}
    public void setTitulo(String titulo) {
        if (validarString(titulo)) this.titulo = titulo;}
    public void setAnioPublicacion(int anioPublicacion) {
        if (validarCondicion(anioPublicacion > 0)) this.añoPublicacion = anioPublicacion;}
    public void setAutor(Autor autor) {
        if (validarCondicion(autor != null)) this.autor = autor;}
        
    // metodos
    
        // metodos para no repetir validaciones
    private boolean validarString(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            System.out.println(ERROR);
            return false;
        }
         return true;
    }
    private boolean validarCondicion(boolean condicionValida) {
        if (!condicionValida) {
            System.out.println(ERROR);
            return false;
        }
        return true;
    }

        // mostrar informacion
    public void mostrarInfo(){
        System.out.println(this.toString());
        System.out.println("Autor: " + autor.getNombre() + " (" + autor.getNacionalidad() + ")");
    }
    
    @Override
    public String toString() {
        return "Libro:" +
               "\nISBN: " + isbn + 
               "\nTítulo: " + titulo + 
               "\nAño: " + añoPublicacion;
    }
}
