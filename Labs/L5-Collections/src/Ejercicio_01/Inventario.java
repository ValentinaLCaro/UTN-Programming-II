/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_01;

import java.util.ArrayList;

/**
 *
 * @author vale
 */
public class Inventario {
    private ArrayList<Producto> productos;
    
    // constructor
    public Inventario() {
    this.productos = new ArrayList<>(); // la lista nace vacía 
    }
    
    // metodos
    public void agregarProducto(Producto p){
        if (p != null){
            this.productos.add(p);
        }else{
            System.out.println("No se puede agregar un producto nulo.");
        }
    }
    
    public void listarProductos(){
        if (this.productos.isEmpty()){
            System.out.println("El inventario esta vacio.");
        }else{
            for (Producto p : this.productos) {
            p.mostrarInfo();
            }
        }
    }
    
    public Producto buscarProductoPorId(String id){
        if (id == null){
            System.out.println("Invalido: el id no puede ser nulo.");
            return null;
        }
        for (Producto p : this.productos) {
            if (p.getId().equalsIgnoreCase(id)) {
              return p; 
            }
        }
        System.out.println("No se encontro el producto.");
        return null;
    }
    
    public void eliminarProducto(String id){
        Producto encontrado = buscarProductoPorId(id);
        
        if (encontrado == null){
            return;
        }
        productos.remove(encontrado);
    }
    
    public void actualizarStock(String id, int nuevaCantidad){
        Producto encontrado = buscarProductoPorId(id);
        
        if (encontrado == null){
            return;
        }
        encontrado.setCantidad(nuevaCantidad);
    }
    
    public void filtrarPorCategoria(CategoriaProducto categoria){
        if (categoria == null) {
        System.out.println("Invalido: La categoría no puede ser nula.");
        return;
        }

        boolean encontroAlMenosUno = false;

        for (Producto p : this.productos) {
            if (p.getCategoria() == categoria) {
                p.mostrarInfo();
                encontroAlMenosUno = true;
            }
        }

        if (!encontroAlMenosUno) {
            System.out.println("No hay productos registrados en la categoría: " + categoria.getDescripcion());
        }
    }
    
    public int obtenerTotalStock(){
        int totalStock = 0;
    
        for (Producto p : this.productos) {
            totalStock += p.getCantidad(); 
        }
        return totalStock;
    }
    
    public Producto obtenerProductoConMayorStock() {
        if (this.productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return null;
        }

        Producto mayorStock = this.productos.get(0);

        for (Producto p : this.productos) {
            if (p.getCantidad() > mayorStock.getCantidad()) {
                mayorStock = p;
            }
        }
        return mayorStock;
    }
    
    public void filtrarProductosPorPrecio(double min, double max) {
        if (min < 0 || max < 0 || min > max) {
            System.out.println("Invalido: Rango de precios incoherente.");
            return;
        }

        boolean encontroAlMenosUno = false;

        for (Producto p : this.productos) {
            if (p.getPrecio() >= min && p.getPrecio() <= max) {
                p.mostrarInfo();
                encontroAlMenosUno = true;
            }
        }

        if (!encontroAlMenosUno) {
            System.out.println("No se encontraron productos entre $" + min + " y $" + max);
        }
    }

    public void mostrarCategoriasDisponibles() {
        System.out.println("=== CATEGORÍAS DISPONIBLES EN EL SISTEMA ===");

        for (CategoriaProducto cat : CategoriaProducto.values()) {
            System.out.println("- " + cat.name() + ": " + cat.getDescripcion());
        }
        System.out.println("============================================");
    }
}
