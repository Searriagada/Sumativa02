package com.programacionavanzada.sumativa02;
import java.util.ArrayList;

public class Biblioteca {
    
    private ArrayList<Libro> listaLibros;

    public Biblioteca() {
        this.listaLibros = new ArrayList<>();
    }
    
    /**
     * método para agregar libros, si el isbn ya existe no se agrega a lista
     * @param libro  que será agregado
     * @return  true si fue agregado exitosamente, false si el isbn ya existe
     */
    public boolean agregarLibro(Libro libro){
        for(Libro i : listaLibros){
            if(i.getIsbn().equals(libro.getIsbn())){
                System.out.println("El isbn " + i.getIsbn() + " ya existe");
                return false;
            }
        }
        listaLibros.add(libro);
        return true;
    }
    /**
     * Método para buscar libro por su isbn
     * @param isbn
     * @return objeto libro
     */
    
    public Libro buscarLibro(String isbn){
        for(Libro i : listaLibros){
            if(isbn.equals(i.getIsbn())){
                return i;
            }
        }
        return null;
    } 
    
        public void mostrar (){
        for (Libro i : listaLibros) {
            System.out.println(i);
        }
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

    @Override
    public String toString() {
        return "Biblioteca{" + "listaLibros=" + listaLibros + '}';
    }
    
    
}


