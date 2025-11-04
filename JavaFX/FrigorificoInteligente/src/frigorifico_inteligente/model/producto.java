/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frigorifico_inteligente.model;

import java.io.Serializable;

/**
 *
 * @author adrian
 */
public class producto implements Serializable {
    
    private static final long serializarVersion = 1L;
    
    private String nombre;
    private String cantidad;
    private String unidad;
    private String tipo;

    public producto() {
    }

    public producto(String nombre, String cantidad, String unidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.tipo = "";
    }

    public producto(String nombre, String cantidad, String unidad, String tipo) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.tipo = tipo;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad + " " + unidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
