
package com.programacionavanzada.sumativa02;

import java.util.ArrayList;

public class RegistroUsuarios {
    private ArrayList<Usuario> listaUsuarios; 

    public RegistroUsuarios() {
        this.listaUsuarios = new ArrayList<>(); 
    }
    
    // CUSTOMERS
    // Metodo buscar
    public boolean buscar (String numeroRut) {
        for (Usuario i : listaUsuarios) {
            if(numeroRut.equalsIgnoreCase(i.getNumeroRut())) {
                return true; 
            }
        }
        return false; 
    }
    
    // Método agregar 
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
    
    // Método eliminar 
    public boolean eliminar (String numeroRut) {
        for (Usuario i : listaUsuarios) {
            if (numeroRut.equalsIgnoreCase(i.getNumeroRut())){
                listaUsuarios.remove(i);
                return true;
            }
        }
        return false; 
    }

    
    
    
}
