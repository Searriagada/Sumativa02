
package com.programacionavanzada.sumativa02;

import java.util.ArrayList;

public class RegistroUsuarios {
    private ArrayList<Usuario> listaUsuarios; 

    public RegistroUsuarios() {
        this.listaUsuarios = new ArrayList<>(); 
    }
    

    /**
    * método buscar usuario. 
    * si encuentra al usuario en la lista retorna un objeto usuario.
    * si no encuentra al usuario retorna null.
    */
    public Usuario buscar (int numeroRut) {
        for (Usuario i : listaUsuarios) {
            if(numeroRut == i.getNumeroRut()) {
                return i; 
            }
        }
        return null; 
    }
    

    /** 
     * Metodo agregar usuario a la lista
     * la busqueda se realiza mediante el rut del objeto usuario
     * primero busca que el usuario exista, si el usuario existe, no lo agrega a la lista
     * si el usuario no existe lo agrega a la lista
     * @param usuario que será agregado a la lista
     */
    public void agregar (Usuario usuario) {
            if (buscar(usuario.getNumeroRut()) != null) {
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
