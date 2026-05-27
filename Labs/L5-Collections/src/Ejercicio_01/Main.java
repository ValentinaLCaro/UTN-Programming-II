/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_01;

/**
 *
 * @author vale
 */
public class Main {
    public static void main(String[] args) {
        Inventario miInventario = new Inventario();

        // crear al menos cinco productos con diferentes categorías
        Producto p1 = new Producto("Leche Entera 1L", 1200.00, 50, CategoriaProducto.ALIMENTOS);
        Producto p2 = new Producto("Auriculares Bluetooth", 45000.00, 15);
        Producto p3 = new Producto("Sartén Antiadherente", CategoriaProducto.HOGAR);
        Producto p4 = new Producto("Sartén Antiadherente", 28000.00, 8, CategoriaProducto.HOGAR);
        Producto p5 = new Producto("Yerba Mate 1Kg", 3500.00, 120, CategoriaProducto.ALIMENTOS);

        miInventario.agregarProducto(p1);
        miInventario.agregarProducto(p2);
        miInventario.agregarProducto(p3);
        miInventario.agregarProducto(p4);
        miInventario.agregarProducto(p5);

        // listar todos los productos mostrando su información
        miInventario.listarProductos();
        System.out.println("================================");

        // buscar un producto por ID y mostrar su información 
        Producto buscado = miInventario.buscarProductoPorId("PROD-2");
        if (buscado != null) {
            buscado.mostrarInfo();
        }
        System.out.println("================================");

        // filtrar y mostrar productos de una categoría específica
        miInventario.filtrarPorCategoria(CategoriaProducto.ALIMENTOS);
        System.out.println("================================");

        // eliminar un producto por su ID y listar los restantes
        miInventario.eliminarProducto("PROD-3");
        miInventario.listarProductos();
        System.out.println("================================");

        // actualizar el stock de un producto existente
        miInventario.actualizarStock("PROD-1", 65);
        
        Producto p1Actualizado = miInventario.buscarProductoPorId("PROD-1");
        if (p1Actualizado != null) {
            p1Actualizado.mostrarInfo();
        }
        System.out.println("================================");

        // mostrar el total de stock disponible
        int stockTotal = miInventario.obtenerTotalStock();
        System.out.println("Cantidad total de unidades en stock: " + stockTotal);
        System.out.println("================================");

        // obtener y mostrar el producto con mayor stock
        Producto masStock = miInventario.obtenerProductoConMayorStock();
        if (masStock != null) {
            System.out.println("El producto con más unidades es: " + masStock.getNombre() + " (" + masStock.getCantidad() + " unidades)");
        }
        System.out.println("================================");

        // filtrar productos con precios entre $1000 y $3000
        miInventario.filtrarProductosPorPrecio(1000.00, 3000.00);
        System.out.println("================================");

        // mostrar las categorías disponibles con sus descripciones
        miInventario.mostrarCategoriasDisponibles();
        System.out.println("================================");
    }
}
