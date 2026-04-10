package com.programacionavanzada.sumativa02;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 *
 * @author 
 */
public class Devolucion {
    
    private Libro libro;
    private Usuario usuario;
    private RegistroPrestamos registroPrestamos;
    private LocalDate fechaEntrega;

    public Devolucion() {
    }
    
    public Devolucion(String isbn, int numeroRut, Biblioteca biblioteca, RegistroUsuarios listaUsuarios, RegistroPrestamos registroPrestamos){
      
        Libro libro = biblioteca.buscarLibro(isbn);
        if(libro == null){
            throw new IllegalArgumentException("El libro no existe");
        }
        Usuario usuario = listaUsuarios.buscar(numeroRut);
        if(usuario == null){
            throw new IllegalArgumentException("el usuario no existe");
        }
        
        this.libro = libro;
        this.usuario = usuario;
        this.fechaEntrega = LocalDate.now();
        this.registroPrestamos = registroPrestamos;
    }

    //GETTER
    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }
    
    //SETTER

    public void setFechaEntrega(LocalDate fechaDevolucion) {
        this.fechaEntrega = fechaDevolucion;
    }
    
    public void generarDevolucion(){
        Prestamo prestamo = registroPrestamos.buscarPrestamo(libro.getIsbn());
        if(prestamo == null){
            throw new IllegalArgumentException("El libro no corresponde al usuario");
        }
        usuario.setPrestamo("0");
        libro.setStockBiblioteca(libro.getStockBiblioteca()+1);
        int multa = calcularMulta(prestamo);
        
        if(multa > 0){
            System.out.println("Retraso con multa por $"+multa);
        }else{
            System.out.println("Devolución dentro del plazo.");
        }
    }
    
    public int calcularMulta(Prestamo prestamo){
        LocalDate fechaDevolucion = prestamo.getFechaDevolucion();
        
        long diasDiferencia = ChronoUnit.DAYS.between(fechaDevolucion, fechaEntrega);

        if(fechaEntrega.isAfter(fechaDevolucion)){
            return (int)diasDiferencia * 1000;
        }
        return 0;
    }
}


