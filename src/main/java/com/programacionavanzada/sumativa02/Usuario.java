
package com.programacionavanzada.sumativa02;

import java.util.Objects;

public class Usuario {
    protected String nombre;
    protected String apellido;
    protected int numeroRut;
    protected char dv;
    protected String genero; 
    protected String prestamo; 

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, int numeroRut, char dv, String genero) {
        this.setNombre(nombre);
        this.setApellido(apellido); 
        this.setNumeroRut(numeroRut);
        this.setDv(dv);
        this.setGenero(genero);
        this.prestamo = "0";
    }

    // GETTER

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
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

    public String getPrestamo() {
        return prestamo;
    }
    
    public int getMaxDiasPrestamo(){
        return 10;
    }
    
    // SETTER

    //Validación de Nombre
    public void setNombre(String nombre) {
        if(nombre == null || nombre.trim().isEmpty()) {
             throw new IllegalArgumentException("Debe ingresar un nombre");
        }
        if (!nombre.trim().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
             throw new IllegalArgumentException("El nombre solo puede contener letras");
        }
        this.nombre = nombre;
    }
    
    // Validación Apellido
    public void setApellido(String apellido) {
        if(apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un apellido");
        }
        if (!apellido.trim().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
             throw new IllegalArgumentException("El apellido solo puede contener letras");
             
        }
        this.apellido = apellido;
    }

    // Validación rut
    public void setNumeroRut(int numeroRut) {
        if(numeroRut<1000000 || numeroRut>99999999){
            throw new IllegalArgumentException("El número de RUT debe tener entre 7 y 8 dígitos, y sin puntos ni guón.");
        }
        this.numeroRut = numeroRut;
    }

    // Validación digito verificador
    public void setDv(char dv) {
        dv = Character.toUpperCase(dv);
        if (!Character.isDigit(dv) && dv != 'K') {
            throw new IllegalArgumentException("El dígito verificador debe ser un número o letra K.");
        } 
        if(!calcularDV(numeroRut).equals(String.valueOf(dv))){
            throw new IllegalArgumentException("El DV ingresado no corresponde a Rut ");
        }
        this.dv = dv;
    }

    // Validación de género
    public void setGenero(String genero) {
        if(genero != null && (genero.equalsIgnoreCase("F") || genero.equalsIgnoreCase("M"))) {
            this.genero = genero;
        }
        else {
            throw new IllegalArgumentException("Debe declarar el genero como F para femenino y M para masculino");
        }
    }

    // FALTA VALIDAR EL PRESTAMO. 
    public void setPrestamo(String prestamo) {
        if(prestamo == null || prestamo.trim().isEmpty()){
            throw new IllegalArgumentException("La condición de prestamo no puede estar vacía");
        }
        else{
            if(prestamo.equals("0") || prestamo.matches("[A-Za-z0-9\\-]+")){
                this.prestamo = prestamo;
            }
            else{
                throw new IllegalArgumentException("Debe ingresar 0 o un ISBN válido");
            }
        }
    }


    /**
     *
     * @param rut
     * @return
     */
    private String calcularDV(int rut) {
        int suma = 0;
        int multiplicador = 2;

        while (rut > 0) {
            suma += (rut % 10) * multiplicador;
            rut /= 10;
            multiplicador = (multiplicador == 7) ? 2 : multiplicador + 1;
        }

        int resto = 11 - (suma % 11);

        if (resto == 11) return "0";
        if (resto == 10) return "K";
        return String.valueOf(resto);
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", apellido=" + apellido + ", numeroRut=" + numeroRut + ", dv=" + dv + ", genero=" + genero + ", prestamo=" + prestamo + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Usuario other)) {
            return false;
        }
        return numeroRut == other.numeroRut;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroRut);
    }

}
