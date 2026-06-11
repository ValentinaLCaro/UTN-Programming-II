/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author vale
 */
public class NegocioException extends RuntimeException{
    
    public NegocioException (String mensaje){
        super(mensaje);
    }
}
