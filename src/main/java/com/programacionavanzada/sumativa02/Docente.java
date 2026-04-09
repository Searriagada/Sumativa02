
package com.programacionavanzada.sumativa02;

public class Docente extends Usuario {
    private String profesion; 
    private String grado; 

    public Docente() {
    }

    public Docente(String profesion, String grado) {
        this.setProfesion(profesion);
        this.setGrado(grado);
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
        if (profesion == null || profesion.trim().isEmpty()){
            System.out.println("Error, el campo profesión no puede quedar vacío");
        }
        else{
            if(profesion.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")){
                System.out.println("Error, solo puede utilizar letras y espacios");
            }
            else{
                this.profesion = profesion.toUpperCase();
            }
        }
    }

    public void setGrado(String grado) {
        if(grado.equalsIgnoreCase("Magister") || grado.equalsIgnoreCase("Doctor")) {
            this.grado = grado;
        }
        else {
            System.out.println("Error, debe ingresar un grado válido. Magíster o Doctor");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Docente{" + "profesion=" + profesion + ", grado=" + grado + '}';
    }
    
    
    
}
