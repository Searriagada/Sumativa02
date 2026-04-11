package com.programacionavanzada.sumativa02;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class RegistroPrestamos {

    private ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
    /**
     * Lista con prestamos realizados.
     * @param prestamo
     */
    public void agregarPrestamo(Prestamo prestamo){
        listaPrestamos.add(prestamo);
    }

    public Prestamo registrarPrestamo(String isbn, int numeroRut, int diasPrestamo, Biblioteca biblioteca, RegistroUsuarios registroUsuarios) {
        Prestamo prestamo = new Prestamo(isbn, numeroRut, diasPrestamo, biblioteca, registroUsuarios);
        prestamo.generarPrestamo(this);
        return prestamo;
    }
    /**
     * Método para buscar un prestamo si libro devuelto corresponda al cliente ingresado
     * @param isbn del libro a devolver
     * @return objeto prestamo
     */
    public Prestamo buscarPrestamo(String isbn){
        for(Prestamo i : listaPrestamos){
            if(i.getLibro().getIsbn().equals(isbn) && i.isActivo()){
                return i;
            }
        }
        return null;
    }

    public Prestamo buscarPrestamoActivoPorUsuario(int numeroRut) {
        for (Prestamo prestamo : listaPrestamos) {
            if (prestamo.isActivo() && prestamo.getUsuario().getNumeroRut() == numeroRut) {
                return prestamo;
            }
        }
        return null;
    }

    public int registrarDevolucion(int numeroRut, String isbn, LocalDate fechaReal, Biblioteca biblioteca, RegistroUsuarios registroUsuarios) {
        Usuario usuario = registroUsuarios.buscar(numeroRut);
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no existe");
        }

        Libro libro = biblioteca.buscarLibro(isbn);
        if (libro == null) {
            throw new IllegalArgumentException("El libro no existe");
        }

        Prestamo prestamo = buscarPrestamoActivoPorUsuario(numeroRut);
        if (prestamo == null) {
            throw new IllegalArgumentException("El usuario no tiene préstamo activo");
        }

        if (!prestamo.getLibro().getIsbn().equals(isbn)) {
            throw new IllegalArgumentException("El ISBN no corresponde al libro prestado");
        }

        return prestamo.registrarDevolucion(fechaReal);
    }

    public List<Prestamo> listarPrestamos() {
        return new ArrayList<>(listaPrestamos);
    }

}
