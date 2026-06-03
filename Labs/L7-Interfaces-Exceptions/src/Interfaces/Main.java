/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

/**
 *
 * @author vale
 */
public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Valentina Caro", "valentina@email.com");

        Producto p1 = new Producto("Lenovo LOQ Laptop", 1200000.00);
        Producto p2 = new Producto("Mouse Gamer Logitech", 45000.00);
        Producto p3 = new Producto("Teclado Mecánico", 85000.00);

        Pedido pedido = new Pedido(cliente);

        pedido.agregarProducto(p1);
        pedido.agregarProducto(p2);
        pedido.agregarProducto(p3);

        System.out.println("REPORTE DE ENTRADA - SISTEMA E-COMMERCE");
        System.out.println("Total inicial del pedido: $" + pedido.calcularTotal());
        System.out.println("==================================================");

        pedido.cambiarEstado("Procesando Envío");
        System.out.println("==================================================");

        Pago tarjeta = new TarjetaDeCredito("4517-4829-1039-5821", "Valentina L. Caro");
        pedido.pagarPedido(tarjeta);
        System.out.println("==================================================");

        Pago paypal = new Paypal("valentina.caro@email.com");
        pedido.pagarPedido(paypal);
        System.out.println("==================================================");
    }
}
