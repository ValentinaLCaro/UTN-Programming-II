/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import enums.Rol;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author vale
 */
public class Usuario extends Base{
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contraseña;
    private Rol rol;
    private ArrayList<Pedido> pedidos;

    public Usuario(String nombre, String apellido, String mail, String celular, String contraseña, Rol rol, ArrayList<Pedido> pedidos, Long id, boolean eliminado, LocalDateTime createdAt) {
        super(id, eliminado, createdAt);
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contraseña = contraseña;
        this.rol = rol;
        this.pedidos = pedidos;
    }

    public String getNombre() { return nombre; } 
    public String getApellido() { return apellido; } 
    public String getMail() { return mail; } 
    public String getCelular() { return celular; }
    public Rol getRol() { return rol; } 
    public ArrayList<Pedido> getPedidos() { return pedidos; }

    public void setNombre(String nombre) { this.nombre = nombre; } 
    public void setApellido(String apellido) { this.apellido = apellido; } 
    public void setMail(String mail) { this.mail = mail; } 
    public void setCelular(String celular) { this.celular = celular; } 
    public void setContraseña(String contraseña) { this.contraseña = contraseña; } 
    public void setRol(Rol rol) { this.rol = rol; } 
    
    public void agregarPedido(Pedido p){
        if (p != null) {
            pedidos.add(p);
            if (p.getUsuario() != this) {
                p.setUsuario(this); 
            }
        }
    }
    
    @Override
    public String toString() {
        return String.format(
            "Usuario [ID: %d | Nombre completo: %s %s | Email: %s | Celular: %s | Rol: %s | Cantidad de Pedidos: %d]",
            this.getId(),
            this.nombre,
            this.apellido,
            this.mail,
            this.celular,
            this.rol,
            (this.pedidos != null) ? this.pedidos.size() : 0
        );
    }
}
