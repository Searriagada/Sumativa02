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
    /**
     * Método para generar devolución:
     * Valida que el usuario tenga asignado el libro a devolver o arroja una excepcion
     * EL usuario queda habilitado para solicitar nuevos libros
     * El stock del libro disponible aumenta en 1
     * Se calcula la multa si procede
     *  
     */
    public void generarDevolucion(){
        Prestamo prestamo = registroPrestamos.buscarPrestamoActivoPorUsuario(usuario.getNumeroRut());
        if(prestamo == null){
            throw new IllegalArgumentException("El usuario no tiene préstamo activo");
        }
        if(!prestamo.getLibro().getIsbn().equals(libro.getIsbn())){
            throw new IllegalArgumentException("El ISBN no corresponde al libro prestado");
        }
        int multa = prestamo.registrarDevolucion(fechaEntrega);
        
        if(multa > 0){
            System.out.println("Retraso con multa por $"+multa);
        }else{
            System.out.println("Devolución dentro del plazo.");
        }
    }
    /**
     * Método para calcular la multa, si la fecha del registro supera a la fecha de devolución se aplica una multa de $1.000 por día
     * @param prestamo objeto prestamo
     * @return valor total de la multa
     */
    public int calcularMulta(Prestamo prestamo){
        LocalDate fechaDevolucion = prestamo.getFechaDevolucion();
        
        long diasDiferencia = ChronoUnit.DAYS.between(fechaDevolucion, fechaEntrega);

        if(fechaEntrega.isAfter(fechaDevolucion)){
            return (int)diasDiferencia * 1000;
        }
        return 0;
    }
}


