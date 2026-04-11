
package com.programacionavanzada.sumativa02;

public class Sumativa02 {

    public static void main(String[] args) {
        
       Biblioteca registroLibro = new Biblioteca();
       RegistroUsuarios usuarios = new RegistroUsuarios();
       RegistroPrestamos prestamo1 = new RegistroPrestamos();
       
        
      /* Estudiante estudiante1 = new Estudiante("Ingeniria Civil Informática", "Albert", "Einstein", 16875655, '0', "M");
       Docente docente1 = new Docente ("Ingenieria Civil Industrial", "magister", "Julio", "Cortazar", 27699943, '5', "m");
       usuarios.agregar(docente1);
       usuarios.agregar(estudiante1);
        System.out.println("--------------------------");
       usuarios.mostrar(); 
        System.out.println("------------------------");
        usuarios.eliminar(16875650); 
        System.out.println("------------------------");
        usuarios.mostrar();
        System.out.println("-----------------------------");
        Docente docente2 = new Docente ("Ingenieria Civil Industrial", "magister", "Pepe", "Cortazar", 8507450, '4', "m");
        usuarios.agregar(docente2);
        System.out.println("-----------------");
        */
      
        Libro libro1 = new Libro("abc123", "Juego de tronos", "George Martin", 1, 1, "http://sample.png");
        registroLibro.agregarLibro(libro1);
        
        Libro libro2 = new Libro("abc123", "Choque de Reyes", "George Martin", 1, 1, "http://sample.png");
        registroLibro.agregarLibro(libro2);
        
        Libro libro3 = new Libro("abc125", "Tormenta de Espadas", "George Martin", 1, 1, "http://sample.png");
        registroLibro.agregarLibro(libro3);
        
        Libro libro4 = new Libro("abc126", "Festin de cuervos", "George Martin", 1, 1, "http://sample.png");
        registroLibro.agregarLibro(libro4);

        registroLibro.mostrar(); 
        
        System.out.println("----------------------------");
        registroLibro.eliminarLibro("abc123");
        registroLibro.mostrar();
        
        
        
      

        
        
        
       
        
    }
}
