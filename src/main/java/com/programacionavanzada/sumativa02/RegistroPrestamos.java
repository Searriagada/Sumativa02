package com.programacionavanzada.sumativa02;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroPrestamos {

    private final ArrayList<Prestamo> listaPrestamos = new ArrayList<>();

    public Prestamo registrarPrestamo(String isbn, int numeroRut, int diasPrestamo, Biblioteca biblioteca, RegistroUsuarios registroUsuarios) {
        Prestamo prestamo = new Prestamo(isbn, numeroRut, diasPrestamo, biblioteca, registroUsuarios);
        prestamo.generarPrestamo();
        listaPrestamos.add(prestamo);
        return prestamo;
    }

    public int registrarDevolucion(int numeroRut, String isbn, LocalDate fechaReal, Biblioteca biblioteca, RegistroUsuarios registroUsuarios) {
        if (registroUsuarios.buscar(numeroRut) == null) {
            throw new IllegalArgumentException("El usuario no existe");
        }
        if (biblioteca.buscarLibro(isbn) == null) {
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

    public Prestamo buscarPrestamoActivoPorUsuario(int numeroRut) {
        for (Prestamo prestamo : listaPrestamos) {
            if (prestamo.isActivo() && prestamo.getUsuario().getNumeroRut() == numeroRut) {
                return prestamo;
            }
        }
        return null;
    }

    public List<Prestamo> listarPrestamos() {
        return new ArrayList<>(listaPrestamos);
    }
}
