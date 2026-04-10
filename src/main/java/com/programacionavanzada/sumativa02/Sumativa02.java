
package com.programacionavanzada.sumativa02;

public class Sumativa02 {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        RegistroUsuarios registroUsuarios = new RegistroUsuarios();

        Libro libro1 = new Libro(
                "978-956-123456-7",
                "Clean Code",
                "Robert C. Martin",
                5,
                5,
                "https://example.com/clean-code.jpg"
        );
        Libro libro2 = new Libro(
                "978-84-376-0494-7",
                "Don Quijote de la Mancha",
                "Miguel de Cervantes",
                3,
                3,
                ""
        );

        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);

        Usuario estudiante = new Estudiante(
                "Ingenieria en Informatica",
                "Francisco",
                "Perez",
                12345678,
                '5',
                "M",
                "0"
        );
        Usuario docente = new Docente(
                "Ingeniero Civil Informatico",
                "Magister",
                "Ana",
                "Gonzalez",
                23456789,
                'K',
                "F",
                "0"
        );

        registroUsuarios.agregar(estudiante);
        registroUsuarios.agregar(docente);

        System.out.println("=== LIBRO REGISTRADO ===");
        System.out.println("Titulo: " + libro1.getTitulo());
        System.out.println("ISBN: " + libro1.getIsbn());
        System.out.println("Stock disponible: " + libro1.getStockDisponible());

        System.out.println("\n=== USUARIOS REGISTRADOS ===");
        registroUsuarios.mostrar();

        System.out.println("\n=== PRESTAMO DE PRUEBA ===");
        Prestamo prestamo = new Prestamo(
                libro1.getIsbn(),
                estudiante.getNumeroRut(),
                7,
                biblioteca,
                registroUsuarios
        );
        prestamo.generarPrestamo();

        System.out.println("\nEstado del usuario despues del prestamo: " + estudiante.Prestamo());
        System.out.println("Stock en biblioteca despues del prestamo: " + libro1.getStockBiblioteca());
    }
}
