
package com.programacionavanzada.sumativa02;

public class Usuario {
    protected String nombre;
    protected String apellido;
    protected int numeroRut;
    protected char dv;
    protected String genero; 
    protected String prestamo; 

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, int numeroRut, char dv, String genero, String prestamo) {
        this.setNombre(nombre);
        this.setApellido(apellido); 
        this.setNumeroRut(numeroRut);
        this.setDv(dv);
        this.setGenero(genero);
        this.setPrestamo(prestamo);
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
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
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
        this.dv = dv;
    }

    // Validación de género
    public void setGenero(String genero) {
        if(genero.equalsIgnoreCase("F") || genero.equalsIgnoreCase("M")) {
            this.genero = genero;
        }
        else {
            System.out.println("Error: Debe declarar el genero como F para femenino y M para masculino");
        }
    }

    // FALTA VALIDAR EL PRESTAMO. 
    public void setPrestamo(String prestamo) {
        if(prestamo == null || prestamo.trim().isEmpty()){
            System.out.println("Error, la condición de prestamo no puede estar vacía");
        }
        else{
            if(prestamo.equals("0") || prestamo.matches("[0-9\\-]+")){
                this.prestamo = prestamo;
            }
            else{
                System.out.println("Debe ingresar 0 o un ISBN válido");
            }
        }
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", apellido=" + apellido + ", numeroRut=" + numeroRut + ", dv=" + dv + ", genero=" + genero + ", prestamo=" + prestamo + '}';
    }
    


}