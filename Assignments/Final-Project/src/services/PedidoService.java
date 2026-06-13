/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.DetallePedido;
import entities.Pedido;
import exception.EntidadNoEncontradaException;
import exception.PedidoSinDetallesException;
import exception.StockInvalidoException;
import exception.UsuarioEliminadoException;
import java.util.ArrayList;
import utilities.Validador;
/**
 *
 * @author vale
 */
public class PedidoService {
    private final ArrayList<Pedido> listaPedidos = new ArrayList<>();

    public void registrarPedido(Pedido nuevoPedido) {
        if (nuevoPedido.getUsuario() == null || nuevoPedido.getUsuario().isEliminado()) {
            throw new UsuarioEliminadoException();
        }

        if (nuevoPedido.getDetalles() == null || nuevoPedido.getDetalles().isEmpty()) {
            throw new PedidoSinDetallesException();
        }

        // validar disponibilidad de stock 
        for (DetallePedido detalle : nuevoPedido.getDetalles()) {
            int stockDisponible = detalle.getProducto().getStock();
            if (detalle.getCantidad() > stockDisponible) {
                // si un solo producto no tiene stock suficiente, cancelamos todo lanzando el error
                throw new StockInvalidoException("Error: Stock insuficiente para '" 
                        + detalle.getProducto().getNombre() + "'. Solicitado: " 
                        + detalle.getCantidad() + " | Disponible: " + stockDisponible);
            }
        }

        for (DetallePedido detalle : nuevoPedido.getDetalles()) {
            int stockActual = detalle.getProducto().getStock();
            detalle.getProducto().setStock(stockActual - detalle.getCantidad());
        }

        nuevoPedido.calcularTotal();

        listaPedidos.add(nuevoPedido);
    }

    public ArrayList<Pedido> obtenerPedidosActivos() {
        ArrayList<Pedido> activos = new ArrayList<>();
            for (Pedido p : listaPedidos) {
                if (!p.isEliminado()) {
                    activos.add(p);
                }
            }
        return activos;
    }

    public Pedido buscarPorId(Long id) {
        return Validador.buscarPorId(listaPedidos, id, "pedido");
    }

    public void actualizarEstadoYPago(Long id, enums.Estado nuevoEstado, enums.FormaPago nuevaFormaPago) {
        Pedido p = buscarPorId(id);
        p.setEstado(nuevoEstado);
        p.setFormaPago(nuevaFormaPago);
    }

    public void eliminarPedido(Long id) {
        Pedido p = buscarPorId(id);
        p.setEliminado(true);
        
        for (DetallePedido detalle : p.getDetalles()) {
            detalle.setEliminado(true);
        }
    }
}
