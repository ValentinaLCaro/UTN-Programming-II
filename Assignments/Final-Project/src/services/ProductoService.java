/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Producto;
import exception.CategoriaEliminadaException;
import exception.EntidadNoEncontradaException;
import java.util.ArrayList;
import utilities.Validador;

/**
 *
 * @author vale
 */
public class ProductoService {
    private final ArrayList<Producto> listaProductos = new ArrayList<>();

    public void crearProducto(Producto nuevoProducto) {
        if (nuevoProducto.getCategoria() != null && nuevoProducto.getCategoria().isEliminado()) {
            throw new CategoriaEliminadaException();
        }
        listaProductos.add(nuevoProducto);
    }

    public ArrayList<Producto> obtenerProductosActivos() {
        ArrayList<Producto> activos = new ArrayList<>();
        for (Producto p : listaProductos) {
            if (!p.isEliminado()) {
                activos.add(p);
            }
        }
        return activos;
    }

    public Producto buscarPorId(Long id) {
        return Validador.buscarPorId(listaProductos, id, "producto");
    }

    public void editarProducto(Long id, String nuevoNombre, Double nuevoPrecio, int nuevoStock, String nuevaImagen) {
        Producto p = buscarPorId(id);
        
        p.setNombre(nuevoNombre);
        p.setPrecio(nuevoPrecio);
        p.setStock(nuevoStock); // el setter actualiza la disponibilidad automaticamente
        p.setImagen(nuevaImagen);
    }

    public void eliminarProducto(Long id) {
        Producto p = buscarPorId(id);
        p.setEliminado(true); // soft delete
    }
}