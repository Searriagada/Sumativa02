package com.programacionavanzada.sumativa02;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    
    private ArrayList<Libro> listaLibros = new ArrayList<>();
    
    /**
     * método para agregar libros, si el isbn ya existe no se agrega a lista
     * @param libro  que será agregado
     * @return  true si fue agregado exitosamente, false si el isbn ya existe
     */
    public boolean agregarLibro(Libro libro){
        for(Libro i : listaLibros){
            if(i.getIsbn().equals(libro.getIsbn())){
                System.out.println("El isbn ya existe");
                return false;
            }
        }
        listaLibros.add(libro);
        return true;
    }
    
    public Libro buscarLibro(String isbn){
        for(Libro i : listaLibros){
            if(isbn.equals(i.getIsbn())){
                return i;
            }
        }
        return null;
    } 

    /**
     * método para eliminar un libro, valida que el isbn exista antes de eliminar el objeto
     * @param isbn del libro a eliminar
     * @return true si el libro existe y fue removido, false si el isbn no existe
     */
    public boolean eliminarLibro(String isbn){
        for(Libro i : listaLibros){
            if(i.getIsbn().equals(isbn)){
                listaLibros.remove(i);
                return true;
            }
        }
        System.out.println("El isbn no existe");
        return false;
    }

    public List<Libro> listarLibros() {
        return new ArrayList<>(listaLibros);
    }
}
