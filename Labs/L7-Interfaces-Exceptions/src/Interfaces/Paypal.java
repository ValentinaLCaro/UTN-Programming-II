/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

/**
 *
 * @author vale
 */
public class Paypal implements PagoConDescuento{
    private String emailUsuario;

    public Paypal(String emailUsuario) {
        setEmailUsuario(emailUsuario);
    }

    public void setEmailUsuario(String emailUsuario) {
        if(emailUsuario == null || emailUsuario.isEmpty()){
            System.out.println("Inválido");
        }else{
            this.emailUsuario = emailUsuario;
        }
    }

    @Override
    public double aplicarDescuento(double monto) {
        double montoConDescuento = monto * 0.90; 
        return montoConDescuento;
    }

    @Override
    public void procesarPago(double monto) {
        System.out.println("Cobrando con 10% de descuento. Total final: $" + aplicarDescuento(monto));
        System.out.println("Pago aprobado exitosamente.");
    }
    
}
