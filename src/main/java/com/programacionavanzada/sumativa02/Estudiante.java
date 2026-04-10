
package com.programacionavanzada.sumativa02;

public class Estudiante extends Usuario {
    
    private String carrera; 

    public Estudiante() {
    }

    public Estudiante(String carrera) {
        this.setCarrera(carrera);
    }

    public Estudiante(String carrera, String nombre, String apellido, int numeroRut, char dv, String genero, String prestamo) {
        super(nombre, apellido, numeroRut, dv, genero, prestamo);
        setCarrera(carrera);
    }
    
    
    
    // GETTER

    public String getCarrera() {
        return carrera;
    }
    
    public int getMaxDiasPrestamo(){
        return 10;
    }
    
    // SETTER

    public void setCarrera(String carrera) {
        if(carrera == null || carrera.trim().isEmpty()){
            throw new IllegalArgumentException("Error, debe ingresar una carrera válida");
        }
        if(!carrera.trim().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")){
                throw new IllegalArgumentException("Error, el nombre de la carrera debe contener letras y espacios");
        }
        this.carrera = carrera.toUpperCase();
    }

    @Override
    public String toString() {
        return super.toString() + "Estudiante{" + "carrera=" + carrera + '}';
    }
    
    
}
