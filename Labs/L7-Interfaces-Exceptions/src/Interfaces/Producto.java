/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

/**
 *
 * @author vale
 */
public class Producto implements Pagable{
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        setNombre(nombre);
        setPrecio(precio);
    }
    
    public Producto (String nombre){
        this(nombre, 0.00);
    }

    public void setNombre(String nombre) {
        if(nombre == null || nombre.isEmpty()){
            System.out.println("Inválido.");
        }else{
            this.nombre = nombre;
        }
    }

    public void setPrecio(double precio) {
        if(precio < 0){
            System.out.println("Inválido");
        }else{
            this.precio = precio;
        }
    }

    public double getPrecio() { return precio; }
    
    @Override
    public double calcularTotal() {
        return this.precio;
    }
    
}
