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
    private int diasPrestamo;

    public Prestamo() {
    }

    public Prestamo(String isbn, int numeroRut, int diasPrestamo, Biblioteca biblioteca, RegistroUsuarios listaUsuarios) {
        
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
        setDiasPrestamo(diasPrestamo);
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = fechaPrestamo.plusDays(diasPrestamo);
    }
    
    // GETTER

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

    public int getDiasPrestamo() {
        return diasPrestamo;
    }
    
    // SETTER

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

    public void setDiasPrestamo(int diasPrestamo) {
        if(usuario.getMaxDiasPrestamo()<diasPrestamo){
            throw new IllegalArgumentException("EL usuario supera el límite de días permitido");
        }
        this.diasPrestamo = diasPrestamo;
    }
    
    public void generarPrestamo(){
        libro.setStockBiblioteca(libro.getStockBiblioteca()-1);
        usuario.setPrestamo(libro.getIsbn());
        imprimirTicket();
    }
    
    public void imprimirTicket(){
        System.out.println("-_-_-_-_-_-_-_-_-La Biblioteca-_-_-_-_-_-_-_-_");
        System.out.println("* Cliente: "+usuario.getNombre()+" "+usuario.getApellido()+" Rut: "+usuario.getNumeroRut()+"-"+usuario.getDv());
        System.out.println("* Se ha prestado el libro: "+libro.getTitulo()+" del autor "+libro.getAutor()+" ISBN: "+libro.getIsbn()+"");
        System.out.println("* Por un total de "+this.getDiasPrestamo()+" días, desde el "+this.getFechaPrestamo()+" hasta el día "+this.getFechaDevolucion());
        System.out.println("* De no entregar en el plazo convenido se expone a una multa de $1.000 por día de retraso");
        System.out.println("* Por favor evite multa y devuelva el libro en el plazo convenido y el mundo será un lugar mejor.");
        System.out.println("");
        System.out.println("Cordialmente, La Biblioteca");
    }    
}
