/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Categoria;
import entities.Producto;
import exception.CategoriaDuplicadaException;
import exception.EntidadNoEncontradaException;
import exception.NegocioException;
import java.util.ArrayList;
import utilities.Validador;

/**
 *
 * @author vale
 */
public class CategoriaService {
    private final ArrayList<Categoria> listaCategorias = new ArrayList<>(); // almacenamiento en memoria 

    public void crearCategoria(Categoria nuevaCategoria) {
        for (Categoria cat : listaCategorias) { // validar que no exista
            if (!cat.isEliminado() && cat.getNombre().equalsIgnoreCase(nuevaCategoria.getNombre())) {
                throw new CategoriaDuplicadaException(); // lanzar excepcion personalizada
            }
        }
        listaCategorias.add(nuevaCategoria);
    }

    public ArrayList<Categoria> obtenerCategoriasActivas() {
        ArrayList<Categoria> activas = new ArrayList<>();
        for (Categoria cat : listaCategorias) {
            if (!cat.isEliminado()) { 
                activas.add(cat);
            }
        }
        return activas;
    }

    public Categoria buscarPorId(Long id) {
        return Validador.buscarPorId(listaCategorias, id, "caetgoria");
    }

    public void eliminarCategoria(Long id) {
        Categoria cat = buscarPorId(id); 

        // validar si tiene productos activos 
        for (Producto p : cat.getProductos()) {
            if (!p.isEliminado()) {
                throw new NegocioException("Error: No se puede eliminar la categoría '" + cat.getNombre() + "' porque tiene productos activos asociados.");
            }
        }
        cat.setEliminado(true); // soft delete 
    }
}
