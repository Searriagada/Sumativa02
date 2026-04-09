
package com.programacionavanzada.sumativa02;

import java.util.ArrayList;

public class RegistroUsuarios {
    private ArrayList<Usuario> listaUsuarios; 

    public RegistroUsuarios() {
        this.listaUsuarios = new ArrayList<>(); 
    }
    

    /**
    * método buscar usuario. 
    * si encuentra al usuario en la lista retorna true.
    * si no encuentra al usuario retorna false.
    */
    public boolean buscar (int numeroRut) {
        for (Usuario i : listaUsuarios) {
            if(numeroRut == i.getNumeroRut()) {
                return true; 
            }
        }
        return false; 
    }
    

    /** 
     * Metodo agregar usuario a la lista
     * la busqueda se realiza mediante el rut del usuario
     * primero busca que el usuario no exista, si el usuario existe, no lo agrega a la lista
     * si el usuario no existe lo agrega a la lista
     * @param usuario que será agregado a la lista
     */
    public void agregar (Usuario usuario) {
            if (buscar(usuario.getNumeroRut())) {
                System.out.println("Usuario ya registrado");
            }
            else{
                listaUsuarios.add(usuario); 
                System.out.println("Usuario ingresado correctamente");
            }
        }

    // Método mostrar
    public void mostrar (){
        for (Usuario i : listaUsuarios) {
            System.out.println(i);
        }
    }
    

    /**
     * método para eliminar un usuario, valida que el numeroRut exista antes de eliminar el objeto
     * @param numeroRut que será eliminado
     * @return true si el usuario existe y fue removido, false si el usuario no existe
     */
    public boolean eliminar (int numeroRut) {
        for (Usuario i : listaUsuarios) {
            if (numeroRut == i.getNumeroRut()){
                listaUsuarios.remove(i);
                return true;
            }
        }
        return false; 
    }

    
    
    
}
