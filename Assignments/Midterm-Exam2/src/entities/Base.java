/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.time.LocalDateTime;

/**
 *
 * @author vale
 */
public abstract class Base {
    private static Long contadorId = 0L; // contador de id para asignarlos automaticamente
    
    private Long id;
    private boolean eliminado;
    private LocalDateTime createdAt;

    public Base(Long id, boolean eliminado, LocalDateTime createdAt) {
        contadorId ++;
        this.id = contadorId;
        this.eliminado = eliminado;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public boolean isEliminado() { return eliminado; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public abstract String toString();
}
