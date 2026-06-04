/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caro_valentina_parcial2;

import entities.*;
import enums.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author vale
 */
public class Main {
    public static void main(String[] args) {
        
        // INSTANCIAR 3 CATEGORIAS
        Categoria cat1 = new Categoria("Comida Rápida", "Hamburguesas y papas", null, null, false, LocalDateTime.now());
        Categoria cat2 = new Categoria("Bebidas", "Gaseosas y aguas", null, null, false, LocalDateTime.now());
        Categoria cat3 = new Categoria("Postres", "Helados y tortas", null, null, false, LocalDateTime.now());

        // INSTANCIAR 6 PRODUCTOS 
        Producto p1 = new Producto("Hamburguesa Doble con Queso", 4500.0, "Doble carne con cheddar", 15, "hamburguesa.png", cat1, null, false, LocalDateTime.now());
        Producto p2 = new Producto("Papas Fritas Grandes", 2000.0, "Papas crujientes", 30, "papas.png", cat1, null, false, LocalDateTime.now());
        cat1.agregarProducto(p1);
        cat1.agregarProducto(p2);

        Producto p3 = new Producto("Coca Cola 500ml", 1200.0, "Bebida original", 50, "coca.png", cat2, null, false, LocalDateTime.now());
        Producto p4 = new Producto("Agua Mineral 500ml", 1000.0, "Agua sin gas", 0, "agua.png", cat2, null, false, LocalDateTime.now()); 
        cat2.agregarProducto(p3);
        cat2.agregarProducto(p4);

        Producto p5 = new Producto("Cucurucho doble", 2500.0, "Crema americana con chocolate", 10, "helado.png", cat3, null, false, LocalDateTime.now());
        Producto p6 = new Producto("Porción de Lemon Pie", 2800.0, "Masa quebrada, limón y merengue", 8, "lemon.png", cat3, null, false, LocalDateTime.now());
        cat3.agregarProducto(p5);
        cat3.agregarProducto(p6);

        // INSTANCIAR 2 USUARIOS
        Usuario userAdmin = new Usuario("Agustin", "Caro", "agustin@mail.com", "3461112233", "admin123", Rol.ADMIN, new ArrayList<>(), null, false, LocalDateTime.now());
        Usuario userNormal = new Usuario("Valentina", "Farris", "vale@mail.com", "45889851", "vale2026", Rol.USUARIO, new ArrayList<>(), null, false, LocalDateTime.now());

        // INSTANCIAR 4 PEDIDOS 
        Pedido ped1 = new Pedido(LocalDate.now(), Estado.CONFIRMADO, FormaPago.EFECTIVO, userAdmin, null, false, LocalDateTime.now());
        Pedido ped2 = new Pedido(LocalDate.now(), Estado.PENDIENTE, FormaPago.TRANSFERENCIA, userAdmin, null, false, LocalDateTime.now());
        userAdmin.agregarPedido(ped1);
        userAdmin.agregarPedido(ped2);

        Pedido ped3 = new Pedido(LocalDate.now(), Estado.TERMINADO, FormaPago.TARJETA, userNormal, null, false, LocalDateTime.now());
        Pedido ped4 = new Pedido(LocalDate.now(), Estado.CANCELADO, FormaPago.EFECTIVO, userNormal, null, false, LocalDateTime.now());
        userNormal.agregarPedido(ped3);
        userNormal.agregarPedido(ped4);

        // CARGAR 12 DETALLES
        ped1.addDetallePedido(2, p1);
        ped1.addDetallePedido(1, p3); 
        ped1.addDetallePedido(1, p5); 

        ped2.addDetallePedido(1, p2); 
        ped2.addDetallePedido(2, p3); 
        ped2.addDetallePedido(1, p6); 

        ped3.addDetallePedido(3, p1); 
        ped3.addDetallePedido(3, p2); 
        ped3.addDetallePedido(4, p3); 

        ped4.addDetallePedido(1, p1); 
        ped4.addDetallePedido(1, p4); 
        ped4.addDetallePedido(2, p6); 

        // guarda los usuarios en una lista para iterar de forma automatica
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(userAdmin);
        listaUsuarios.add(userNormal);

        for (Usuario u : listaUsuarios) {
            System.out.println("=========================================================================");
            System.out.printf("USUARIO: [%s %s] | Mail: [%s] | Rol: [%s]%n", u.getNombre(), u.getApellido(), u.getMail(), u.getRol());
            System.out.println("=========================================================================");
            
            Double totalAcumuladoUsuario = 0.0;
            
            for (Pedido p : u.getPedidos()) {
                System.out.printf("> Pedido #[%d] | Fecha: [%s] | Estado: [%s] | FormaPago: [%s]%n", 
                        p.getId(), p.getFecha(), p.getEstado(), p.getFormaDePago());
                System.out.println("-------------------------------------------------------------------------");
                
                for (DetallePedido d : p.getDetalles()) {
                    System.out.printf("  - DetallePedido #[%d]: [%s] x [%d] => Subtotal: $[%.2f]%n", 
                            d.getId(), d.getProducto().getNombre(), d.getCantidad(), d.getSubtotal());
                }
                
                System.out.printf("  TOTAL DEL PEDIDO: $[%.2f]%n", p.getTotal());
                System.out.println("-------------------------------------------------------------------------");
                
                totalAcumuladoUsuario += p.getTotal();
            }
            
            System.out.printf("  TOTAL ACUMULADO del usuario: $[%.2f]%n", totalAcumuladoUsuario);
            System.out.println("=========================================================================");
        }
    }
}
