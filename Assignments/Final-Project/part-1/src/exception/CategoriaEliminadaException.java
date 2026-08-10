/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author vale
 */
public class CategoriaEliminadaException extends NegocioException{
    
    public CategoriaEliminadaException(){
        super("Error: La categoria seleccionada ya no puede recibir nuevos productos.");
    }
    
    public CategoriaEliminadaException(String mensaje){
        super(mensaje);
    }
}
