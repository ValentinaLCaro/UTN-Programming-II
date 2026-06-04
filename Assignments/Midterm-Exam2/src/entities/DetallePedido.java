/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.time.LocalDateTime;

/**
 *
 * @author vale
 */
public class DetallePedido extends Base {
    private int cantidad;
    private Double subtotal; 
    private Producto producto;

    public DetallePedido(int cantidad, Producto producto, Long id, boolean eliminado, LocalDateTime createdAt) {
        super(id, eliminado, createdAt);
        this.cantidad = cantidad;
        this.producto = producto;
        calcularSubtotal();
    }

    public int getCantidad() { return cantidad; } 
    public Double getSubtotal() { return subtotal; } 
    public Producto getProducto() { return producto; }

    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; } 
    public void setProducto(Producto producto) { this.producto = producto; }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal(); // se recalcula automáticamente al cambiar la cantidad
    }
    
    public Double calcularSubtotal(){
        if (producto != null) {
            subtotal = producto.getPrecio() * cantidad;
        } else {
            subtotal = 0.0;
        }
        return subtotal;
    }

    @Override
    public String toString() {
        return String.format(
            "Detalle del pedido [ID: %d | Producto: %s | Cantidad: %d | Subtotal: $%.2f]",
            this.getId(), 
            (this.producto != null) ? this.producto.getNombre() : "Sin Producto",
            this.cantidad,
            this.subtotal
        );
    }
}
