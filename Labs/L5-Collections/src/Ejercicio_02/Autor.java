/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_02;

/**
 *
 * @author vale
 */
public class Autor {
    private static final String ERROR = "Invalido"; // constante con mensaje para validaciones
    private static int contadorId = 0; // contador de id para asignarlos automaticamente
    
    private String id;
    private String nombre;
    private String nacionalidad;
    
    // constructores
    public Autor(String nombre, String nacionalidad) {
        contadorId++; // el numero aumenta para cada autor
        this.id = "AUT-" + contadorId;
        setNombre(nombre);
        setNacionalidad(nacionalidad);
    }
    
    // getters y setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getNacionalidad() { return nacionalidad; }
    
    public void setNombre(String nombre) {
        if (validarString(nombre)) this.nombre = nombre;}
    public void setNacionalidad(String nacionalidad) {
        if (validarString(nacionalidad)) this.nacionalidad = nacionalidad;}
        
    // metodos
    
        // metodos para no repetir validaciones
    private boolean validarString(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
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
        return "Autor:" +
               "\nID: " + id + 
               "\nNombre: " + nombre + 
               "\nNacionalidad: " + nacionalidad;
    }
}
