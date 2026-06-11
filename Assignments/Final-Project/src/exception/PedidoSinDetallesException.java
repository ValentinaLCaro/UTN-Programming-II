/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author vale
 */
public class PedidoSinDetallesException extends NegocioException{
    
    public PedidoSinDetallesException(){
        super("Error: No se puede registrar un pedido sin al menos un producto en el detalle.");
    }
    
    public PedidoSinDetallesException(String mensaje){
        super(mensaje);
    }
}
