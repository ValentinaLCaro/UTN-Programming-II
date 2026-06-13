/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author vale
 */
public class StockInvalidoException extends NegocioException{
    
    public StockInvalidoException(){
        super("Error: El Stock del producto es invalido.");
    }
    
    public StockInvalidoException(String mensaje){
        super(mensaje);
    }
}
