/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author vale
 */
public class UsuarioEliminadoException extends NegocioException{
    
    public UsuarioEliminadoException(){
        super("Error: No se puede asociar el pedido. El usuario seleccionado ya no existe.");
    }
    
    public UsuarioEliminadoException(String nombre){
        super("Error: No se puede asociar el pedido. El usuario: " + nombre + " ya no existe.");
    }
}
