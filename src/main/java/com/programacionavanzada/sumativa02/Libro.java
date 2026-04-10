package com.programacionavanzada.sumativa02;

/**
 *
 * @author 
 */
public class Libro {
    
    private String isbn;
    private String titulo;
    private String autor;
    private int stockBiblioteca;
    private int stockDisponible;
    private String urlImagen; 
    
    public Libro(){}

    public Libro(String isbn, String titulo, String autor, int stockBiblioteca, int stockDisponible, String urlImagen) {
        setIsbn(isbn);
        setTitulo(titulo);
        setAutor(autor);
        setStockBiblioteca(stockBiblioteca);
        setStockDisponible(stockDisponible);
        setUrlImagen(urlImagen);
    }

    //GETTER
    
    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getStockBiblioteca() {
        return stockBiblioteca;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public String getUrlImagen() {
        return urlImagen;
    }
    
    //SETTER
    
    public void setIsbn(String isbn) {
        if(isbn == null || isbn.isBlank()){
            throw new IllegalArgumentException("El isbn no puede ser nulo y debe contener texto");
        }
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        if(titulo == null || titulo.isBlank()){
            throw new IllegalArgumentException("El título no puede ser nulo y debe contener texto");
        }
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        if(autor == null || autor.isBlank()){
            throw new IllegalArgumentException("El autor no puede ser nulo y debe contener texto");
        }
        this.autor = autor;
    }

    public void setStockBiblioteca(int stockBiblioteca) {
        if(stockBiblioteca <= 0){
            throw new IllegalArgumentException("El stock debe ser mayor o igual a 1");
        }
        this.stockBiblioteca = stockBiblioteca;
    }

    public void setStockDisponible(int stockDisponible) {
        if(stockDisponible <= 0 || stockDisponible > stockBiblioteca){
            throw new IllegalArgumentException("El stock debe ser mayor o igual a 1 y no debe exceder la cantidad en biblioteca");
        }
        this.stockDisponible = stockDisponible;
    }

    public void setUrlImagen(String urlImagen) {
        if(urlImagen == null || urlImagen.isBlank()){
            this.urlImagen = "http://bibliotecaunab.cl/sample.png";
        }else{
            this.urlImagen = urlImagen;
        }
    }
    
    public void actualizarStockDisponible(){
        this.stockDisponible --;
    }
    
    public void devolverStockDisponible(){
        this.stockDisponible ++;
    }
}
