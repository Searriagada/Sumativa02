package com.programacionavanzada.sumativa02;

import java.time.LocalDate;

/**
 *
 * @author
 */
public class Prestamo {
    
    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo() {
    }

    public Prestamo(String isbn, int numeroRut, LocalDate fechaPrestamo, LocalDate fechaDevolucion, Biblioteca biblioteca, RegistroUsuarios listaUsuarios) {
        
        Libro libro = biblioteca.buscarLibro(isbn);
        if(libro == null){
            throw new IllegalArgumentException("El libro no existe");
        }
        Usuario usuario = listaUsuarios.buscar(numeroRut);
        if(usuario == null){
            throw new IllegalArgumentException("el usuario no existe");
        }

        setLibro(libro);
        setUsuario(usuario);
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setLibro(Libro libro) {
        if(libro.getStockDisponible() <=0){
            throw new IllegalArgumentException("Sin stock disponible");
        }
        this.libro = libro;
    }

    public void setUsuario(Usuario usuario) {
        if(usuario.isPrestamo().equals("0")){
            this.usuario = usuario;
        }else{
           throw new IllegalArgumentException("El usuario cuenta con el siguiente prestamo activo: "+usuario.isPrestamo());
        }
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

  
    
}
