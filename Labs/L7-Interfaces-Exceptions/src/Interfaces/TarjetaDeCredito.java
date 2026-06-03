/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

/**
 *
 * @author vale
 */
public class TarjetaDeCredito implements Pago{
    private String numeroTarjeta;
    private String titular;

    public TarjetaDeCredito(String numeroTarjeta, String titular) {
        setNumeroTarjeta(numeroTarjeta);
        setTitular(titular);
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        if (numeroTarjeta == null){
            System.out.println("Invalido");
        }else{
            this.numeroTarjeta = numeroTarjeta;
        }
    }

    public void setTitular(String titular) {
        if(titular == null || titular.isEmpty()){
            System.out.println("Invalido");
        }else{
            this.titular = titular;
        }
    }

    @Override
    public void procesarPago(double monto) {
        System.out.println("Procesando pago de $" + monto + " con Tarjeta de Crédito de " + this.titular);
        System.out.println("Pago aprobado exitosamente.");}
    
}
