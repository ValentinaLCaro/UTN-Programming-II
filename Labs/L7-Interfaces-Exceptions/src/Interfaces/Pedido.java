/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.util.ArrayList;

/**
 *
 * @author vale
 */
public class Pedido implements Pagable{
    private ArrayList<Producto> lista;
    private Cliente cliente;
    private String estado;

    public Pedido(Cliente cliente) {
        this.lista = new ArrayList<>(); 
        this.cliente = cliente;
        this.estado = "Pendiente";
    }

    public void agregarProducto(Producto producto) {
        this.lista.add(producto);
    }
    
    public void pagarPedido(Pago medioPago){
        double totalAPagar = this.calcularTotal(); 
        
        medioPago.procesarPago(totalAPagar);
    }
    
    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado; 
        
        System.out.println("LOG: El estado del pedido cambió a [" + this.estado + "].");
        
        this.cliente.notificar(); 
    }
    
    public String getEstado() {
        return this.estado;
    }

    @Override
    public double calcularTotal() {
        double total = 0.00;
        for (Producto producto : lista) {
            total += producto.getPrecio();
        }
        return total;
    }
    
}
