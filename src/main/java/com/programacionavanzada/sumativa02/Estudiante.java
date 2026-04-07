
package com.programacionavanzada.sumativa02;

public class Estudiante extends Usuario {
    
    private String carrera; 

    public Estudiante() {
    }

    public Estudiante(String carrera) {
        this.carrera = carrera;
    }

    public Estudiante(String carrera, String nombre, String apellido, int numeroRut, char dv, String genero, boolean prestamo) {
        super(nombre, apellido, numeroRut, dv, genero, prestamo);
        this.carrera = carrera;
    }
    
    
    
    // GETTER

    public String getCarrera() {
        return carrera;
    }
    
    // SETTER

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return super.toString() + "Estudiante{" + "carrera=" + carrera + '}';
    }
    
    
}
