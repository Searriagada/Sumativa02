package com.programacionavanzada.sumativa02;

import java.util.Date;

/**
 *
 * @author
 */
public class Prestamo {
    
    private String isbn;
    private int numeroRut;
    private Biblioteca biblioteca;
    private RegistroUsuarios listaUsuarios;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public Prestamo() {
    }

    public Prestamo(String isbn, int numeroRut, Date fechaPrestamo, Date fechaDevolucion, Biblioteca biblioteca, RegistroUsuarios listaUsuarios) {
        setIsbn(isbn);
        this.numeroRut = numeroRut;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.biblioteca = biblioteca;
        this.listaUsuarios = listaUsuarios;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getNumeroRut() {
        return numeroRut;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setIsbn(String isbn) {
        if(isbn == null || isbn.isBlank()){
            throw new IllegalArgumentException("El isbn es requerido");
        }
        if(!biblioteca.buscarLibro(isbn)){
            throw new IllegalArgumentException("El isbn consultado no existe");
        }
        if(biblioteca.consultarStock(isbn) <= 0){
            throw new IllegalArgumentException("Stock insuficiente");
        }
        this.isbn = isbn;
    }

    public void setNumeroRut(int numeroRut) {
        if(!listaUsuarios.buscar(numeroRut)){
            throw new IllegalArgumentException("El rut consultado no existe");
        }
        this.numeroRut = numeroRut;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    
}
