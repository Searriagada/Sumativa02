
package com.programacionavanzada.sumativa02;

public class Usuario {
    private String nombreCompleto; 
    private int numeroRut; 
    private char dv; 
    private String genero; 
    private boolean prestamo; 

    public Usuario() {
    }

    public Usuario(String nombreCompleto, int numeroRut, char dv, String genero, boolean prestamo) {
        this.nombreCompleto = nombreCompleto;
        this.numeroRut = numeroRut;
        this.dv = dv;
        this.genero = genero;
        this.prestamo = prestamo;
    }
    
    // GETTER

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public int getNumeroRut() {
        return numeroRut;
    }

    public char getDv() {
        return dv;
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

    public void setNumeroRut(int numeroRut) {
        this.numeroRut = numeroRut;
    }

    public void setDv(char dv) {
        this.dv = dv;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPrestamo(boolean prestamo) {
        this.prestamo = prestamo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombreCompleto=" + nombreCompleto + ", numeroRut=" + numeroRut + ", dv=" + dv + ", genero=" + genero + ", prestamo=" + prestamo + '}';
    }
    
    
}
