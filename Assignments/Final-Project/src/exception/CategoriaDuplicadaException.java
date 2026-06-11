/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author vale
 */
public class CategoriaDuplicadaException extends NegocioException{
    
    public CategoriaDuplicadaException(){
        super("Error: Ya existe una categoria registrada con ese nombre.");
    }
    
    public CategoriaDuplicadaException(String mensaje){
        super(mensaje);
    }
}
