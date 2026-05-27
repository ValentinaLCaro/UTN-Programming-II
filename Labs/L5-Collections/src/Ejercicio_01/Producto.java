/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_01;

/**
 *
 * @author vale
 */
public class Producto {
    private static final String ERROR = "Invalido"; // constante con mensaje para validaciones
    private static int contadorId = 0; // contador de id para asignarlos automaticamente
    
    private String id;
    private String nombre;
    private double precio;
    private int cantidad;
    private CategoriaProducto categoria;
    
    // constructores

    public Producto(String nombre, double precio, int cantidad, CategoriaProducto categoria) {
        contadorId++; // el numero aumenta para cada producto, asi nunca es el mismo
        this.id = "PROD-" + contadorId;
        setNombre(nombre);
        setPrecio(precio);
        setCantidad(cantidad);
        setCategoria(categoria);
    }

    public Producto(String nombre, double precio, int cantidad) {
        this(nombre, precio, cantidad, CategoriaProducto.GENERAL);
    }

    public Producto(String nombre, CategoriaProducto categoria) {
        this(nombre, 0.00, 1, categoria);
    }

    // getters y setters
    
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public CategoriaProducto getCategoria() { return categoria; }
    
    public void setNombre(String nombre) {
        if (validarString(nombre)) this.nombre = nombre;}
    public void setPrecio(double precio) {
        if (validarCondicion(precio >= 0.0)) this.precio = precio;}
    public void setCantidad(int cantidad) {
        if (validarCondicion(cantidad > 0)) this.cantidad = cantidad;}
    public void setCategoria(CategoriaProducto categoria) {
        if (validarCondicion(categoria != null)) this.categoria = categoria;}
    
    // metodos
    
        // metodos para no repetir validaciones
    private boolean validarString(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            System.out.println(ERROR);
            return false;
        }
         return true;
    }
    private boolean validarCondicion(boolean condicionValida) {
        if (!condicionValida) {
            System.out.println(ERROR);
            return false;
        }
        return true;
    }
    
        // mostrar informacion
    public void mostrarInfo(){
        System.out.println(this.toString());
    }
    
    @Override
    public String toString() {
        return "Producto= "+
               "\nID: " + id + 
               "\nNombre: " + nombre + 
               "\nPrecio: $" + precio + 
               "\nCantidad: " + cantidad +
               "\nCategoría: " + categoria;
    }
}
