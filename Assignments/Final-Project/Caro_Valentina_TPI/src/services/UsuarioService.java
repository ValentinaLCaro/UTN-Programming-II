/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Usuario;
import exception.EmailDuplicadoException;
import exception.EntidadNoEncontradaException;
import java.util.ArrayList;

/**
 *
 * @author vale
 */
public class UsuarioService {
    private final ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public void crearUsuario(Usuario nuevoUsuario) {
        for (Usuario u : listaUsuarios) {
            if (!u.isEliminado() && u.getMail().equalsIgnoreCase(nuevoUsuario.getMail())) {
                throw new EmailDuplicadoException(); 
            }
        }
        listaUsuarios.add(nuevoUsuario);
    }

    public ArrayList<Usuario> obtenerUsuariosActivos() {
        ArrayList<Usuario> activos = new ArrayList<>();
        for (Usuario u : listaUsuarios) {
            if (!u.isEliminado()) {
                activos.add(u);
            }
        }
        return activos;
    }

    public Usuario buscarPorId(Long id) {
        for (Usuario u : listaUsuarios) {
            if (u.getId().equals(id) && !u.isEliminado()) {
                return u;
            }
        }
        throw new EntidadNoEncontradaException("Error: El usuario con ID " + id + " no existe o fue dado de baja.");
    }

    public void editarUsuario(Long id, String nuevoNombre, String nuevoApellido, String nuevoMail, String nuevoCelular) {
        Usuario u = buscarPorId(id); 

        // si cambia el mail, validar que el nuevo no este duplicado
        if (!u.getMail().equalsIgnoreCase(nuevoMail)) {
            for (Usuario otro : listaUsuarios) {
                if (!otro.isEliminado() && otro.getMail().equalsIgnoreCase(nuevoMail)) {
                    throw new EmailDuplicadoException();
                }
            }
        }

        u.setNombre(nuevoNombre);
        u.setApellido(nuevoApellido);
        u.setMail(nuevoMail);
        u.setCelular(nuevoCelular);
    }

    public void eliminarUsuario(Long id) {
        Usuario u = buscarPorId(id);
        u.setEliminado(true); 
    }
}
