package com.programacionavanzada.sumativa02;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final class RutIngresado {
        private final int numero;
        private final char dv;

        private RutIngresado(int numero, char dv) {
            this.numero = numero;
            this.dv = dv;
        }
    }

    private final Biblioteca biblioteca;
    private final RegistroUsuarios registroUsuarios;
    private final RegistroPrestamos registroPrestamos;
    private final Scanner scanner;

    public Menu() {
        this(new Biblioteca(), new RegistroUsuarios(), new RegistroPrestamos(), new Scanner(System.in));
    }

    public Menu(Biblioteca biblioteca, RegistroUsuarios registroUsuarios, RegistroPrestamos registroPrestamos) {
        this(biblioteca, registroUsuarios, registroPrestamos, new Scanner(System.in));
    }

    public Menu(Biblioteca biblioteca, RegistroUsuarios registroUsuarios, RegistroPrestamos registroPrestamos, Scanner scanner) {
        this.biblioteca = biblioteca;
        this.registroUsuarios = registroUsuarios;
        this.registroPrestamos = registroPrestamos;
        this.scanner = scanner;
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion("Seleccione una opcion", 0, 6);
            switch (opcion) {
                case 1 -> menuLibros();
                case 2 -> menuUsuarios();
                case 3 -> menuPrestamos();
                case 4 -> listarLibros();
                case 5 -> listarUsuarios();
                case 6 -> menuConsultas();
                case 0 -> {
                    System.out.println("Saliendo del sistema...");
                    salir = true;
                }
                default -> System.out.println("Opcion no valida.");
            }
        }
    }

    private void mostrarMenuPrincipal() {
        System.out.println();
        System.out.println("==================================");
        System.out.println("      SISTEMA DE BIBLIOTECA");
        System.out.println("==================================");
        System.out.println("1. Gestion de libros");
        System.out.println("2. Gestion de usuarios");
        System.out.println("3. Prestamos y devoluciones");
        System.out.println("4. Listar libros");
        System.out.println("5. Listar usuarios");
        System.out.println("6. Consultas");
        System.out.println("0. Salir");
    }

    private void menuLibros() {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("----- GESTION DE LIBROS -----");
            System.out.println("1. Agregar libro");
            System.out.println("2. Buscar libro por ISBN");
            System.out.println("3. Listar libros");
            System.out.println("4. Eliminar libro");
            System.out.println("0. Volver");

            int opcion = leerOpcion("Seleccione una opcion", 0, 4);
            switch (opcion) {
                case 1 -> agregarLibro();
                case 2 -> buscarLibro();
                case 3 -> listarLibros();
                case 4 -> eliminarLibro();
                case 0 -> volver = true;
                default -> System.out.println("Opcion no valida.");
            }
        }
    }

    private void menuUsuarios() {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("----- GESTION DE USUARIOS -----");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Registrar docente");
            System.out.println("3. Buscar usuario por RUT");
            System.out.println("4. Listar usuarios");
            System.out.println("5. Eliminar usuario");
            System.out.println("0. Volver");

            int opcion = leerOpcion("Seleccione una opcion", 0, 5);
            switch (opcion) {
                case 1 -> registrarEstudiante();
                case 2 -> registrarDocente();
                case 3 -> buscarUsuario();
                case 4 -> listarUsuarios();
                case 5 -> eliminarUsuario();
                case 0 -> volver = true;
                default -> System.out.println("Opcion no valida.");
            }
        }
    }

    private void menuPrestamos() {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("----- PRESTAMOS Y DEVOLUCIONES -----");
            System.out.println("1. Registrar prestamo");
            System.out.println("2. Registrar devolucion");
            System.out.println("3. Buscar prestamo activo por usuario");
            System.out.println("4. Listar historial de prestamos");
            System.out.println("0. Volver");

            int opcion = leerOpcion("Seleccione una opcion", 0, 4);
            switch (opcion) {
                case 1 -> registrarPrestamo();
                case 2 -> registrarDevolucion();
                case 3 -> buscarPrestamoActivoPorUsuario();
                case 4 -> listarPrestamos();
                case 0 -> volver = true;
                default -> System.out.println("Opcion no valida.");
            }
        }
    }

    private void menuConsultas() {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("----- CONSULTAS Y LISTADOS -----");
            System.out.println("1. Consultar stock de libro");
            System.out.println("2. Consultar usuario");
            System.out.println("3. Consultar prestamo activo por ISBN");
            System.out.println("4. Ver resumen general");
            System.out.println("0. Volver");

            int opcion = leerOpcion("Seleccione una opcion", 0, 4);
            switch (opcion) {
                case 1 -> consultarStockLibro();
                case 2 -> buscarUsuario();
                case 3 -> buscarPrestamoActivoPorIsbn();
                case 4 -> mostrarResumen();
                case 0 -> volver = true;
                default -> System.out.println("Opcion no valida.");
            }
        }
    }

    private void agregarLibro() {
        System.out.println();
        System.out.println("Registro de libro");

        while (true) {
            try {
                String isbn = leerTexto("ISBN");
                String titulo = leerTexto("Titulo");
                String autor = leerTexto("Autor");
                int stockBiblioteca = leerEnteroPositivo("Stock total en biblioteca");
                int stockDisponible = leerEnteroEnRango("Stock disponible", 1, stockBiblioteca);
                String urlImagen = leerTextoOpcional("URL de imagen");

                Libro libro = new Libro(isbn, titulo, autor, stockBiblioteca, stockDisponible, urlImagen);
                boolean agregado = biblioteca.agregarLibro(libro);
                if (agregado) {
                    System.out.println("Libro registrado correctamente.");
                } else {
                    System.out.println("No fue posible registrar el libro.");
                }
                return;
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
                if (!confirmar("Desea intentar nuevamente")) {
                    return;
                }
            }
        }
    }

    private void buscarLibro() {
        String isbn = leerTexto("Ingrese ISBN a buscar");
        Libro libro = biblioteca.buscarLibro(isbn);
        if (libro == null) {
            System.out.println("No existe un libro con ese ISBN.");
            return;
        }
        mostrarLibro(libro);
    }

    private void listarLibros() {
        List<Libro> libros = biblioteca.listarLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }

        System.out.println();
        System.out.println("Listado de libros");
        for (Libro libro : libros) {
            mostrarLibro(libro);
        }
    }

    private void eliminarLibro() {
        String isbn = leerTexto("Ingrese ISBN del libro a eliminar");
        boolean eliminado = biblioteca.eliminarLibro(isbn);
        if (eliminado) {
            System.out.println("Libro eliminado correctamente.");
        } else {
            System.out.println("No fue posible eliminar el libro.");
        }
    }

    private void registrarEstudiante() {
        System.out.println();
        System.out.println("Registro de estudiante");

        while (true) {
            try {
                String carrera = leerTexto("Carrera");
                String nombre = leerTexto("Nombre");
                String apellido = leerTexto("Apellido");
                RutIngresado rut = leerRutCompletoConRetorno("RUT completo");
                if (rut == null) {
                    return;
                }
                String genero = leerGenero();

                Usuario estudiante = new Estudiante(carrera, nombre, apellido, rut.numero, rut.dv, genero);
                boolean agregado = registroUsuarios.agregar(estudiante);
                if (agregado) {
                    System.out.println("Estudiante registrado correctamente.");
                } else {
                    System.out.println("No fue posible registrar el estudiante.");
                }
                return;
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
                if (!confirmar("Desea intentar nuevamente")) {
                    return;
                }
            }
        }
    }

    private void registrarDocente() {
        System.out.println();
        System.out.println("Registro de docente");

        while (true) {
            try {
                String profesion = leerTexto("Profesion");
                String grado = leerTexto("Grado academico (Magister o Doctor)");
                String nombre = leerTexto("Nombre");
                String apellido = leerTexto("Apellido");
                RutIngresado rut = leerRutCompletoConRetorno("RUT completo");
                if (rut == null) {
                    return;
                }
                String genero = leerGenero();

                Usuario docente = new Docente(profesion, grado, nombre, apellido, rut.numero, rut.dv, genero);
                boolean agregado = registroUsuarios.agregar(docente);
                if (agregado) {
                    System.out.println("Docente registrado correctamente.");
                } else {
                    System.out.println("No fue posible registrar el docente.");
                }
                return;
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
                if (!confirmar("Desea intentar nuevamente")) {
                    return;
                }
            }
        }
    }

    private void buscarUsuario() {
        Integer rut = leerRutNumeroConRetorno("Ingrese RUT a buscar");
        if (rut == null) {
            return;
        }
        Usuario usuario = registroUsuarios.buscar(rut);
        if (usuario == null) {
            System.out.println("No existe un usuario con ese RUT.");
            return;
        }
        mostrarUsuario(usuario);
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = registroUsuarios.listar();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println();
        System.out.println("Listado de usuarios");
        for (Usuario usuario : usuarios) {
            mostrarUsuario(usuario);
        }
    }

    private void eliminarUsuario() {
        Integer rut = leerRutNumeroConRetorno("Ingrese RUT del usuario a eliminar");
        if (rut == null) {
            return;
        }
        boolean eliminado = registroUsuarios.eliminar(rut);
        if (eliminado) {
            System.out.println("Usuario eliminado correctamente.");
        } else {
            System.out.println("No fue posible eliminar el usuario.");
        }
    }

    private void registrarPrestamo() {
        System.out.println();
        System.out.println("Registro de prestamo");

        while (true) {
            try {
                String isbn = leerTexto("ISBN del libro");
                Integer rut = leerRutNumeroConRetorno("RUT del usuario");
                if (rut == null) {
                    return;
                }
                int diasPrestamo = leerEnteroPositivo("Cantidad de dias de prestamo");

                Prestamo prestamo = registroPrestamos.registrarPrestamo(isbn, rut, diasPrestamo, biblioteca, registroUsuarios);
                System.out.println("Prestamo registrado correctamente.");
                mostrarPrestamo(prestamo);
                return;
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
                if (!confirmar("Desea intentar nuevamente")) {
                    return;
                }
            }
        }
    }

    private void registrarDevolucion() {
        System.out.println();
        System.out.println("Registro de devolucion");

        while (true) {
            try {
                Integer rut = leerRutNumeroConRetorno("RUT del usuario");
                if (rut == null) {
                    return;
                }
                String isbn = leerTexto("ISBN del libro");
                LocalDate fechaDevolucion = leerFecha("Fecha de devolucion real (YYYY-MM-DD o vacio para hoy)");

                int multa = registroPrestamos.registrarDevolucion(rut, isbn, fechaDevolucion, biblioteca, registroUsuarios);
                System.out.println("Devolucion registrada correctamente.");
                if (multa > 0) {
                    System.out.println("Multa generada: $" + multa);
                } else {
                    System.out.println("No existe multa por atraso.");
                }
                return;
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
                if (!confirmar("Desea intentar nuevamente")) {
                    return;
                }
            }
        }
    }

    private void buscarPrestamoActivoPorUsuario() {
        Integer rut = leerRutNumeroConRetorno("Ingrese RUT del usuario");
        if (rut == null) {
            return;
        }
        Prestamo prestamo = registroPrestamos.buscarPrestamoActivoPorUsuario(rut);
        if (prestamo == null) {
            System.out.println("El usuario no tiene prestamos activos.");
            return;
        }
        mostrarPrestamo(prestamo);
    }

    private void buscarPrestamoActivoPorIsbn() {
        String isbn = leerTexto("Ingrese ISBN del libro");
        Prestamo prestamo = registroPrestamos.buscarPrestamo(isbn);
        if (prestamo == null) {
            System.out.println("No existe un prestamo activo para ese ISBN.");
            return;
        }
        mostrarPrestamo(prestamo);
    }

    private void listarPrestamos() {
        List<Prestamo> prestamos = registroPrestamos.listarPrestamos();
        if (prestamos.isEmpty()) {
            System.out.println("No hay prestamos registrados.");
            return;
        }

        System.out.println();
        System.out.println("Historial de prestamos");
        for (Prestamo prestamo : prestamos) {
            mostrarPrestamo(prestamo);
        }
    }

    private void consultarStockLibro() {
        String isbn = leerTexto("Ingrese ISBN del libro");
        Libro libro = biblioteca.buscarLibro(isbn);
        if (libro == null) {
            System.out.println("No existe un libro con ese ISBN.");
            return;
        }
        System.out.println("Titulo: " + libro.getTitulo());
        System.out.println("Stock total: " + libro.getStockBiblioteca());
        System.out.println("Stock disponible: " + libro.getStockDisponible());
    }

    private void mostrarResumen() {
        System.out.println();
        System.out.println("Resumen general");
        System.out.println("Libros registrados: " + biblioteca.listarLibros().size());
        System.out.println("Usuarios registrados: " + registroUsuarios.listar().size());
        System.out.println("Prestamos registrados: " + registroPrestamos.listarPrestamos().size());
    }

    private void mostrarLibro(Libro libro) {
        System.out.println("----------------------------------");
        System.out.println("ISBN: " + libro.getIsbn());
        System.out.println("Titulo: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor());
        System.out.println("Stock total: " + libro.getStockBiblioteca());
        System.out.println("Stock disponible: " + libro.getStockDisponible());
        System.out.println("URL imagen: " + libro.getUrlImagen());
    }

    private void mostrarUsuario(Usuario usuario) {
        System.out.println("----------------------------------");
        System.out.println("Tipo: " + usuario.getClass().getSimpleName());
        System.out.println("Nombre: " + usuario.getNombre() + " " + usuario.getApellido());
        System.out.println("RUT: " + usuario.getNumeroRut() + "-" + usuario.getDv());
        System.out.println("Genero: " + usuario.getGenero());
        System.out.println("Prestamo activo: " + usuario.getPrestamo());
        System.out.println("Maximo dias prestamo: " + usuario.getMaxDiasPrestamo());

        if (usuario instanceof Estudiante estudiante) {
            System.out.println("Carrera: " + estudiante.getCarrera());
        }
        if (usuario instanceof Docente docente) {
            System.out.println("Profesion: " + docente.getProfesion());
            System.out.println("Grado: " + docente.getGrado());
        }
    }

    private void mostrarPrestamo(Prestamo prestamo) {
        System.out.println("----------------------------------");
        System.out.println("Libro: " + prestamo.getLibro().getTitulo());
        System.out.println("ISBN: " + prestamo.getLibro().getIsbn());
        System.out.println("Usuario: " + prestamo.getUsuario().getNombre() + " " + prestamo.getUsuario().getApellido());
        System.out.println("RUT: " + prestamo.getUsuario().getNumeroRut() + "-" + prestamo.getUsuario().getDv());
        System.out.println("Fecha prestamo: " + prestamo.getFechaPrestamo());
        System.out.println("Fecha devolucion comprometida: " + prestamo.getFechaDevolucion());
        System.out.println("Fecha devolucion real: " + prestamo.getFechaDevolucionReal());
        System.out.println("Dias prestamo: " + prestamo.getDiasPrestamo());
        System.out.println("Activo: " + (prestamo.isActivo() ? "Si" : "No"));
        System.out.println("Multa: $" + prestamo.getMulta());
    }

    private int leerOpcion(String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje + ": ");
            String entrada = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor < minimo || valor > maximo) {
                    System.out.println("Debe ingresar un numero entre " + minimo + " y " + maximo + ".");
                    continue;
                }
                return valor;
            } catch (NumberFormatException ex) {
                System.out.println("Debe ingresar un numero valido.");
            }
        }
    }

    private int leerEnteroPositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje + ": ");
            String entrada = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor <= 0) {
                    System.out.println("Debe ingresar un numero mayor a cero.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException ex) {
                System.out.println("Debe ingresar un numero entero valido.");
            }
        }
    }

    private int leerEnteroEnRango(String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje + ": ");
            String entrada = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor < minimo || valor > maximo) {
                    System.out.println("Debe ingresar un valor entre " + minimo + " y " + maximo + ".");
                    continue;
                }
                return valor;
            } catch (NumberFormatException ex) {
                System.out.println("Debe ingresar un numero entero valido.");
            }
        }
    }

    private Integer leerRutNumeroConRetorno(String mensaje) {
        RutIngresado rut = leerRutCompletoConRetorno(mensaje);
        return rut == null ? null : rut.numero;
    }

    private RutIngresado leerRutCompletoConRetorno(String mensaje) {
        System.out.print(mensaje + ": ");
        String entrada = scanner.nextLine().trim();
        String rutSaneado = entrada.replace(".", "").replace("-", "").toUpperCase();

        if (rutSaneado.length() < 2) {
            System.out.println("RUT no es valido. Volviendo al menu anterior.");
            return null;
        }

        String numeroTexto = rutSaneado.substring(0, rutSaneado.length() - 1);
        char dv = rutSaneado.charAt(rutSaneado.length() - 1);

        if (!numeroTexto.matches("\\d+") || (!Character.isDigit(dv) && dv != 'K')) {
            System.out.println("RUT no es valido. Volviendo al menu anterior.");
            return null;
        }

        try {
            int rut = Integer.parseInt(numeroTexto);
            if (rut < 1000000 || rut > 99999999) {
                System.out.println("RUT no es valido. Volviendo al menu anterior.");
                return null;
            }
            return new RutIngresado(rut, dv);
        } catch (NumberFormatException ex) {
            System.out.println("RUT no es valido. Volviendo al menu anterior.");
            return null;
        }
    }

    private String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje + ": ");
            String texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("El valor no puede quedar vacio.");
                continue;
            }
            return texto;
        }
    }

    private String leerTextoOpcional(String mensaje) {
        System.out.print(mensaje + " (opcional): ");
        return scanner.nextLine().trim();
    }

    private String leerGenero() {
        while (true) {
            System.out.print("Genero (F/M): ");
            String genero = scanner.nextLine().trim().toUpperCase();
            if (genero.equals("F") || genero.equals("M")) {
                return genero;
            }
            System.out.println("Debe ingresar F o M.");
        }
    }

    private LocalDate leerFecha(String mensaje) {
        while (true) {
            System.out.print(mensaje + ": ");
            String texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                return LocalDate.now();
            }
            try {
                return LocalDate.parse(texto);
            } catch (DateTimeParseException ex) {
                System.out.println("La fecha debe tener formato YYYY-MM-DD.");
            }
        }
    }

    private boolean confirmar(String mensaje) {
        while (true) {
            System.out.print(mensaje + " (S/N): ");
            String respuesta = scanner.nextLine().trim().toUpperCase();
            if (respuesta.equals("S")) {
                return true;
            }
            if (respuesta.equals("N")) {
                return false;
            }
            System.out.println("Debe responder S o N.");
        }
    }
}
