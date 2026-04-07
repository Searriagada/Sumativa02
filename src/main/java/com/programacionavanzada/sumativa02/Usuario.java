
package com.programacionavanzada.sumativa02;

public class Usuario {
    protected String nombreCompleto; 
    protected String numeroRut; ; 
    protected String genero; 
    protected boolean prestamo; 

    public Usuario() {
    }

    public Usuario(String nombreCompleto, String numeroRut, String genero, boolean prestamo) {
        this.nombreCompleto = nombreCompleto;
        this.numeroRut = numeroRut;
        this.genero = genero;
        this.prestamo = prestamo;
    }

    // GETTER

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getNumeroRut() {
        return numeroRut;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isPrestamo() {
        return prestamo;
    }
    
    // SETTER

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setNumeroRut(String numeroRut) {
        this.numeroRut = numeroRut;
    }

    public void setGenero(String genero) {
        if(genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F")) {
            this.genero = genero;
        }
        else {
            System.out.println("Error: Debe ingresar F para femenino y M para masculino. ");
        }
        
    }

    public void setPrestamo(boolean prestamo) {
        this.prestamo = prestamo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombreCompleto=" + nombreCompleto + ", numeroRut=" + numeroRut + ", genero=" + genero + ", prestamo=" + prestamo + '}';
    }
    
    
}
