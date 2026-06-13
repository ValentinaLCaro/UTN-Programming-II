/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import utilities.Validador;

/**
 *
 * @author vale
 */
public class Categoria extends Base {
    private String nombre;
    private String descripcion;
    private ArrayList<Producto> productos;

    public Categoria(String nombre, String descripcion, ArrayList<Producto> productos, boolean eliminado, LocalDateTime createdAt) {
        super(eliminado, createdAt);
        setNombre(nombre);
        setDescripcion(descripcion);
        this.productos = new ArrayList<Producto>();
    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public ArrayList<Producto> getProductos() { return this.productos; }

    public void setNombre(String nombre) { 
        Validador.requerirTexto(nombre, "nombre");
        this.nombre = nombre; 
    }
    public void setDescripcion(String descripcion) {
        Validador.requerirTexto(descripcion, "descripcion");
        this.descripcion = descripcion; 
    }

    public void agregarProducto(Producto p){
        if (p != null) {
            productos.add(p);
            if (p.getCategoria() != this) {
                p.setCategoria(this);
            }
        }
    }
    
   @Override
    public String toString() {
        return String.format(
            "Categoria [nombre: %s | descripcion: %s | productos: %s]", 
            nombre, 
            descripcion, 
            productos
        );
    }
}
