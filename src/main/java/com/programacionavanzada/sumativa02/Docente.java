
package com.programacionavanzada.sumativa02;

public class Docente extends Usuario {
    private String profesion; 
    private String grado; 

    public Docente() {
    }

    public Docente(String profesion, String grado) {
        this.profesion = profesion;
        this.grado = grado;
    }

    public Docente(String profesion, String grado, String nombre, String apellido, int numeroRut, char dv, String genero, String prestamo) {
        super(nombre, apellido, numeroRut, dv, genero, prestamo);
        this.profesion = profesion;
        this.grado = grado;
    }
    
    
    // GETTER

    public String getProfesion() {
        return profesion;
    }

    public String getGrado() {
        return grado;
    }
    
    // SETTER

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    @Override
    public String toString() {
        return super.toString() + "Docente{" + "profesion=" + profesion + ", grado=" + grado + '}';
    }
    
    
    
}
