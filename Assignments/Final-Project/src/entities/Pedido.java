/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.time.LocalDate;
import enums.Estado;
import enums.FormaPago;
import java.time.LocalDateTime;
import java.util.ArrayList;
import interfaces.Calculable;
import utilities.Validador;

/**
 *
 * @author vale
 */
public class Pedido extends Base implements Calculable{
    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private ArrayList<DetallePedido> detalles;
    private Usuario usuario;

    public Pedido(LocalDate fecha, Estado estado, FormaPago formaPago, Usuario usuario, boolean eliminado, LocalDateTime createdAt) {
        super(eliminado, createdAt);
        setFecha(fecha);
        setEstado(estado);
        this.total = 0.0; 
        setFormaPago(formaPago);
        this.detalles = new ArrayList<>(); 
        setUsuario(usuario);
    }

    public LocalDate getFecha() {return fecha; } 
    public Estado getEstado() { return estado; } 
    public double getTotal() { return total; } 
    public FormaPago getFormaDePago() { return formaPago; } 
    public ArrayList<DetallePedido> getDetalles() { return detalles; }
    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { 
        Validador.requerirObjeto(usuario, "usuario");
        this.usuario = usuario; 
    }
    public void setFecha(LocalDate fecha) { 
        Validador.requerirObjeto(fecha, "fecha del pedido");
        this.fecha = fecha; 
    } 
    public void setEstado(Estado estado) { 
        Validador.requerirObjeto(estado, "estado");
        this.estado = estado; 
    } 
    public void setTotal(Double total) { 
        Validador.requerirNoNegativo(total, "total");
        this.total = total; 
    } 
    public void setFormaPago(FormaPago formaPago) {
        Validador.requerirObjeto(formaPago, "forma de pago");
        this.formaPago = formaPago; 
    } 
    public void setDetalles(ArrayList<DetallePedido> detalles) { this.detalles = detalles; }
    
    @Override
    public void calcularTotal() {
        this.total = 0.0; // reiniciar para no duplicar sumas anteriores
        for (DetallePedido detalle : detalles) {
            this.total += detalle.getSubtotal(); 
        }
    }
    
    public void addDetallePedido(int cantidad, Producto p) {
        DetallePedido nuevoDetalle = new DetallePedido(cantidad, p, false, LocalDateTime.now()); 
        this.detalles.add(nuevoDetalle);
        calcularTotal(); 
    }
    
    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto().getId().equals(producto.getId())){
                return detalle;
            }
        }
        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido aEliminar = findDetallePedidoByProducto(producto);
        if (aEliminar != null) {
            this.detalles.remove(aEliminar);
            calcularTotal(); //actualiza el total después de borrar
        }
    }
    
    @Override
    public String toString() {
        return String.format(
            "Pedido [ID: %d | Fecha: %s | Estado: %s | Forma de Pago: %s | Total: $%.2f | Cant. Items: %d]",
            this.getId(),
            this.fecha,
            this.estado,
            this.formaPago,
            this.total,
            this.detalles.size()
        );
    }
}