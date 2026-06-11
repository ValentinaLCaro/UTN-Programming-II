/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author vale
 */
public class EntidadNoEncontradaException extends NegocioException{
    
    public EntidadNoEncontradaException(){
        super("Error: No se ha encontrado la entidad ingresada."); // mensaje generico de error
    }
    
    public EntidadNoEncontradaException (String mensaje){
        super(mensaje); // otra opcion de constructor para personalizar el mensaje
    }
}

