package com.programacionavanzada.sumativa02;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecaAppTest {

    @Test
    void crearDocenteValido() {
        Docente docente = crearDocente(11111111);

        assertEquals("ANA", docente.getNombre().toUpperCase());
        assertEquals(20, docente.getMaxDiasPrestamo());
    }

    @Test
    void crearEstudianteValido() {
        Estudiante estudiante = crearEstudiante(12222222);

        assertEquals("JUAN", estudiante.getNombre().toUpperCase());
        assertEquals(10, estudiante.getMaxDiasPrestamo());
    }

    @Test
    void intentarCrearUsuarioConRunRepetido() {
        RegistroUsuarios registro = new RegistroUsuarios();

        assertTrue(registro.agregar(crearEstudiante(13333333)));
        assertFalse(registro.agregar(crearDocente(13333333)));
    }

    @Test
    void intentarCrearUsuarioConGeneroInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                new Estudiante("Ingenieria", "Juan", "Perez", 14444444, '5', "X", "0"));
    }

    @Test
    void intentarCrearUsuarioConRunInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                new Docente("Profesor", "Doctor", "Ana", "Lopez", 12345, 'K', "F", "0"));
    }

    @Test
    void eliminarUsuarioExistente() {
        RegistroUsuarios registro = new RegistroUsuarios();
        registro.agregar(crearEstudiante(15555555));

        assertTrue(registro.eliminar(15555555));
        assertNull(registro.buscar(15555555));
    }

    @Test
    void intentarEliminarUsuarioInexistente() {
        RegistroUsuarios registro = new RegistroUsuarios();

        assertFalse(registro.eliminar(16666666));
    }

    @Test
    void crearLibroValido() {
        Libro libro = crearLibro("ISBN-001", 3);

        assertEquals("ISBN-001", libro.getIsbn());
        assertEquals(3, libro.getStockDisponible());
    }

    @Test
    void intentarCrearLibroConIsbnRepetido() {
        Biblioteca biblioteca = new Biblioteca();

        assertTrue(biblioteca.agregarLibro(crearLibro("ISBN-002", 2)));
        assertFalse(biblioteca.agregarLibro(crearLibro("ISBN-002", 4)));
    }

    @Test
    void intentarCrearLibroConStockInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                new Libro("ISBN-003", "Libro Malo", "Autor", 0, 0, ""));
    }

    @Test
    void eliminarLibroExistente() {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.agregarLibro(crearLibro("ISBN-004", 2));

        assertTrue(biblioteca.eliminarLibro("ISBN-004"));
        assertNull(biblioteca.buscarLibro("ISBN-004"));
    }

    @Test
    void intentarEliminarLibroInexistente() {
        Biblioteca biblioteca = new Biblioteca();

        assertFalse(biblioteca.eliminarLibro("ISBN-404"));
    }

    @Test
    void registrarPrestamoValidoParaEstudiante() {
        Biblioteca biblioteca = new Biblioteca();
        RegistroUsuarios usuarios = new RegistroUsuarios();
        RegistroPrestamos prestamos = new RegistroPrestamos();
        Libro libro = crearLibro("ISBN-005", 2);
        Estudiante estudiante = crearEstudiante(17777777);
        biblioteca.agregarLibro(libro);
        usuarios.agregar(estudiante);

        Prestamo prestamo = prestamos.registrarPrestamo("ISBN-005", 17777777, 7, biblioteca, usuarios);

        assertNotNull(prestamo);
        assertEquals("ISBN-005", estudiante.Prestamo());
        assertEquals(1, libro.getStockDisponible());
    }

    @Test
    void registrarPrestamoValidoParaDocente() {
        Biblioteca biblioteca = new Biblioteca();
        RegistroUsuarios usuarios = new RegistroUsuarios();
        RegistroPrestamos prestamos = new RegistroPrestamos();
        Libro libro = crearLibro("ISBN-006", 2);
        Docente docente = crearDocente(18888888);
        biblioteca.agregarLibro(libro);
        usuarios.agregar(docente);

        Prestamo prestamo = prestamos.registrarPrestamo("ISBN-006", 18888888, 15, biblioteca, usuarios);

        assertNotNull(prestamo);
        assertEquals("ISBN-006", docente.Prestamo());
        assertEquals(1, libro.getStockDisponible());
    }

    @Test
    void intentarPrestamoConUsuarioInexistente() {
        Biblioteca biblioteca = new Biblioteca();
        RegistroUsuarios usuarios = new RegistroUsuarios();
        RegistroPrestamos prestamos = new RegistroPrestamos();
        biblioteca.agregarLibro(crearLibro("ISBN-007", 1));

        assertThrows(IllegalArgumentException.class, () ->
                prestamos.registrarPrestamo("ISBN-007", 19999999, 5, biblioteca, usuarios));
    }

    @Test
    void intentarPrestamoConLibroInexistente() {
        RegistroUsuarios usuarios = new RegistroUsuarios();
        RegistroPrestamos prestamos = new RegistroPrestamos();
        usuarios.agregar(crearEstudiante(21111111));

        assertThrows(IllegalArgumentException.class, () ->
                prestamos.registrarPrestamo("ISBN-008", 21111111, 5, new Biblioteca(), usuarios));
    }

    @Test
    void intentarPrestamoSinStockDisponible() {
        Biblioteca biblioteca = new Biblioteca();
        RegistroUsuarios usuarios = new RegistroUsuarios();
        RegistroPrestamos prestamos = new RegistroPrestamos();
        Libro libro = crearLibro("ISBN-009", 1);
        biblioteca.agregarLibro(libro);
        usuarios.agregar(crearEstudiante(22222222));
        usuarios.agregar(crearDocente(23333333));
        prestamos.registrarPrestamo("ISBN-009", 22222222, 5, biblioteca, usuarios);

        assertThrows(IllegalArgumentException.class, () ->
                prestamos.registrarPrestamo("ISBN-009", 23333333, 5, biblioteca, usuarios));
    }

    @Test
    void intentarPrestamoConUsuarioQueYaTienePrestamoActivo() {
        Biblioteca biblioteca = new Biblioteca();
        RegistroUsuarios usuarios = new RegistroUsuarios();
        RegistroPrestamos prestamos = new RegistroPrestamos();
        usuarios.agregar(crearEstudiante(24444444));
        biblioteca.agregarLibro(crearLibro("ISBN-010", 2));
        biblioteca.agregarLibro(crearLibro("ISBN-011", 2));
        prestamos.registrarPrestamo("ISBN-010", 24444444, 5, biblioteca, usuarios);

        assertThrows(IllegalArgumentException.class, () ->
                prestamos.registrarPrestamo("ISBN-011", 24444444, 5, biblioteca, usuarios));
    }

    @Test
    void intentarPrestamoConDiasFueraDeRangoPermitido() {
        Biblioteca biblioteca = new Biblioteca();
        RegistroUsuarios usuarios = new RegistroUsuarios();
        RegistroPrestamos prestamos = new RegistroPrestamos();
        usuarios.agregar(crearEstudiante(25555555));
        biblioteca.agregarLibro(crearLibro("ISBN-012", 2));

        assertThrows(IllegalArgumentException.class, () ->
                prestamos.registrarPrestamo("ISBN-012", 25555555, 11, biblioteca, usuarios));
    }

    @Test
    void registrarDevolucionValidaSinMulta() {
        Biblioteca biblioteca = new Biblioteca();
        RegistroUsuarios usuarios = new RegistroUsuarios();
        RegistroPrestamos prestamos = new RegistroPrestamos();
        Libro libro = crearLibro("ISBN-013", 2);
        Estudiante estudiante = crearEstudiante(26666666);
        biblioteca.agregarLibro(libro);
        usuarios.agregar(estudiante);
        Prestamo prestamo = prestamos.registrarPrestamo("ISBN-013", 26666666, 5, biblioteca, usuarios);

        int multa = prestamos.registrarDevolucion(26666666, "ISBN-013", prestamo.getFechaDevolucion(), biblioteca, usuarios);

        assertEquals(0, multa);
        assertEquals("0", estudiante.Prestamo());
        assertEquals(2, libro.getStockDisponible());
    }

    @Test
    void registrarDevolucionValidaConMulta() {
        Biblioteca biblioteca = new Biblioteca();
        RegistroUsuarios usuarios = new RegistroUsuarios();
        RegistroPrestamos prestamos = new RegistroPrestamos();
        biblioteca.agregarLibro(crearLibro("ISBN-014", 1));
        usuarios.agregar(crearDocente(27777777));
        Prestamo prestamo = prestamos.registrarPrestamo("ISBN-014", 27777777, 5, biblioteca, usuarios);
        prestamo.setFechaDevolucion(LocalDate.now().minusDays(2));

        int multa = prestamos.registrarDevolucion(27777777, "ISBN-014", LocalDate.now(), biblioteca, usuarios);

        assertEquals(2000, multa);
    }

    @Test
    void intentarDevolucionConRunInexistente() {
        Biblioteca biblioteca = new Biblioteca();
        RegistroUsuarios usuarios = new RegistroUsuarios();
        RegistroPrestamos prestamos = new RegistroPrestamos();
        biblioteca.agregarLibro(crearLibro("ISBN-015", 1));

        assertThrows(IllegalArgumentException.class, () ->
                prestamos.registrarDevolucion(28888888, "ISBN-015", LocalDate.now(), biblioteca, usuarios));
    }

    @Test
    void intentarDevolucionConIsbnInexistente() {
        Biblioteca biblioteca = new Biblioteca();
        RegistroUsuarios usuarios = new RegistroUsuarios();
        RegistroPrestamos prestamos = new RegistroPrestamos();
        biblioteca.agregarLibro(crearLibro("ISBN-016", 1));
        usuarios.agregar(crearEstudiante(29999999));
        prestamos.registrarPrestamo("ISBN-016", 29999999, 5, biblioteca, usuarios);

        assertThrows(IllegalArgumentException.class, () ->
                prestamos.registrarDevolucion(29999999, "ISBN-NO-EXISTE", LocalDate.now(), biblioteca, usuarios));
    }

    @Test
    void intentarDevolucionConIsbnDistintoAlPrestado() {
        Biblioteca biblioteca = new Biblioteca();
        RegistroUsuarios usuarios = new RegistroUsuarios();
        RegistroPrestamos prestamos = new RegistroPrestamos();
        biblioteca.agregarLibro(crearLibro("ISBN-017", 1));
        biblioteca.agregarLibro(crearLibro("ISBN-018", 1));
        usuarios.agregar(crearDocente(31111111));
        prestamos.registrarPrestamo("ISBN-017", 31111111, 5, biblioteca, usuarios);

        assertThrows(IllegalArgumentException.class, () ->
                prestamos.registrarDevolucion(31111111, "ISBN-018", LocalDate.now(), biblioteca, usuarios));
    }

    @Test
    void intentarDevolucionSinPrestamoActivo() {
        Biblioteca biblioteca = new Biblioteca();
        RegistroUsuarios usuarios = new RegistroUsuarios();
        RegistroPrestamos prestamos = new RegistroPrestamos();
        biblioteca.agregarLibro(crearLibro("ISBN-019", 1));
        usuarios.agregar(crearEstudiante(32222222));

        assertThrows(IllegalArgumentException.class, () ->
                prestamos.registrarDevolucion(32222222, "ISBN-019", LocalDate.now(), biblioteca, usuarios));
    }

    @Test
    void listarUsuariosConRegistrosExistentes() {
        RegistroUsuarios registro = new RegistroUsuarios();
        registro.agregar(crearEstudiante(33333333));
        registro.agregar(crearDocente(34444444));

        assertEquals(2, registro.listar().size());
    }

    @Test
    void listarUsuariosSinRegistros() {
        RegistroUsuarios registro = new RegistroUsuarios();

        assertTrue(registro.listar().isEmpty());
    }

    @Test
    void listarLibrosConRegistrosExistentes() {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.agregarLibro(crearLibro("ISBN-020", 1));
        biblioteca.agregarLibro(crearLibro("ISBN-021", 2));

        assertEquals(2, biblioteca.listarLibros().size());
    }

    @Test
    void listarLibrosSinRegistros() {
        Biblioteca biblioteca = new Biblioteca();

        assertTrue(biblioteca.listarLibros().isEmpty());
    }

    @Test
    void listarPrestamosConRegistrosExistentes() {
        Biblioteca biblioteca = new Biblioteca();
        RegistroUsuarios usuarios = new RegistroUsuarios();
        RegistroPrestamos prestamos = new RegistroPrestamos();
        biblioteca.agregarLibro(crearLibro("ISBN-022", 1));
        usuarios.agregar(crearEstudiante(35555555));
        prestamos.registrarPrestamo("ISBN-022", 35555555, 5, biblioteca, usuarios);

        assertEquals(1, prestamos.listarPrestamos().size());
    }

    @Test
    void listarPrestamosSinRegistros() {
        RegistroPrestamos prestamos = new RegistroPrestamos();
        assertTrue(prestamos.listarPrestamos().isEmpty());
    }

    private Estudiante crearEstudiante(int rut) {
        return new Estudiante("Ingenieria", "Juan", "Perez", rut, '5', "M", "0");
    }

    private Docente crearDocente(int rut) {
        return new Docente("Profesor", "Doctor", "Ana", "Lopez", rut, 'K', "F", "0");
    }

    private Libro crearLibro(String isbn, int stock) {
        return new Libro(isbn, "Libro " + isbn, "Autor " + isbn, stock, stock, "");
    }
}
