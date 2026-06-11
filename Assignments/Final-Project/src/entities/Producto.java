/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.time.LocalDateTime;
import utilities.Validador;

/**
 *
 * @author vale
 */
public class Producto extends Base{
    private String nombre;
    private Double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    private Categoria categoria;

    public Producto(String nombre, double precio, String descripcion, int stock, String imagen, Categoria categoria, boolean eliminado, LocalDateTime createdAt) {
        super(eliminado, createdAt);
        setNombre(nombre);
        setPrecio(precio);
        setDescripcion(descripcion);
        setStock(stock);
        setImagen(imagen);
        setCategoria(categoria);
        setDisponible(disponible);
    }
    
    public Producto(String nombre, double precio, int stock, Categoria categoria, boolean eliminado, LocalDateTime createdAt){
        this(nombre, precio, "Sin descripción", stock, "default.png", categoria, eliminado, createdAt );
    }

    public boolean isDisponible() { // define automaticamente si el producto esta disponible segun el stock
        this.disponible = (this.stock > 0); 
        return this.disponible; 
    }
    public String getNombre() { return nombre; } 
    public double getPrecio() { return precio; } 
    public String getDescripcion() { return descripcion; } 
    public int getStock() { return stock; } 
    public String getImagen() { return imagen; } 
    public Categoria getCategoria() { return categoria; } 

    public void setNombre(String nombre) {
        Validador.requerirTexto(nombre, "nombre del producto"); 
        this.nombre = nombre;
    }
    public void setPrecio(Double precio) { 
        Validador.requerirNoNegativo(precio, "precio");
        this.precio = precio; 
    } 
    public void setDescripcion(String descripcion) { 
        Validador.requerirTexto(descripcion, "descripcion");
        this.descripcion = descripcion; 
    }
    public void setStock(int stock) {
        Validador.requerirNoNegativo(stock, "sstock");
        this.stock = stock; 
        this.disponible = (stock > 0); // actualiza el estado para mantener la coherencia
    }
    public void setImagen(String imagen) {
        Validador.requerirTexto(imagen, "imagen");
        this.imagen = imagen; 
    } 
    public void setDisponible(boolean disponible) {
        this.disponible = disponible; 
    }
    public void setCategoria(Categoria categoria){
        Validador.requerirObjeto(categoria, "categoria");
        this.categoria = categoria;
    }
        
    @Override
    public String toString() {
        return String.format(
            "Producto [ID: %d | Nombre: %s | Precio: $%.2f | Stock: %d | Disponible: %b | Categoría: %s]",
            this.getId(), 
            this.nombre,
            this.precio,
            this.stock,
            this.isDisponible(),
            (this.categoria != null) ? this.categoria.getNombre() : "Sin Categoría"
        );
    }
}
