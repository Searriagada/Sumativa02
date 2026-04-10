
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
    
    public int getMaxDiasPrestamo(){
        return 20;
    }
    
    // SETTER

    /**
     * Validación de que profesión se escriba segun un formato y no quede vacío
     * @param profesion 
     */
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

    /**
     * Validación de grado para Docentes, en el cual solo puede como grados Magíster o Doctor
     * @param grado 
     */
    public void setGrado(String grado) {
        if (grado == null || grado.trim().isEmpty()){
            System.out.println("Error, el campo grado no puede estar vacío");
        }
        else {
            if(grado.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                System.out.println("Error, no debe usar caracteres especiales y usar espacios");
            }
            else{
                if(grado.equalsIgnoreCase("Magister") || grado.equalsIgnoreCase("Doctor")) {
                    this.grado = grado.toUpperCase();
            }
        }
      }
    }

    @Override
    public String toString() {
        return super.toString() + "Docente{" + "profesion=" + profesion + ", grado=" + grado + '}';
    }
    
    
    
}
