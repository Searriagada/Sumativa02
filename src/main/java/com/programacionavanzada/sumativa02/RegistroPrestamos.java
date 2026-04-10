package com.programacionavanzada.sumativa02;

import java.util.ArrayList;

/**
 *
 * @author 
 */
public class RegistroPrestamos {
    
    private ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
    
    public void agregarPrestamo(Prestamo prestamo){
        listaPrestamos.add(prestamo);
    }
    
    public Prestamo buscarPrestamo(String isbn){
        for(Prestamo i : listaPrestamos){
            if(i.getUsuario().getPrestamo().equals(isbn)){
                return i;
            }
        }
        return null;
    }
    
}
