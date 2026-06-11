/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author vale
 */
public class EmailDuplicadoException extends NegocioException{
    
    public EmailDuplicadoException(){
        super("Error: El correo electronico fue registrado por otro usuario.");
    }
    
    public EmailDuplicadoException(String email){
        super("Error: El correo electronico: " + email + " ya fue registrado por otro usuario.");
    }
    
}
