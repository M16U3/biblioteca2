/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mig.beans;

/**
 *
 * @author miguel
 */
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import myconector.Dao;
import com.mig.exception.BibliotecaException;

        
         

public class Libro implements Serializable {
    
    private String librIsbn;    
    private String librEdicion;
    private String librTitulo;
    private Long librEditorial;
    private Long librNumPag;
    private Long librExistencia;
        
    private List<Libro> libros = new ArrayList();
    private Libro libro;
        
    private String error;
    
    public Libro() {        
    }

    public String getLibrEdicion() {
        return librEdicion;
    }

    public Long getLibrEditorial() {
        return librEditorial;
    }

    public Long getLibrExistencia() {
        return librExistencia;
    }

    public String getLibrIsbn() {
        return librIsbn;
    }

    public String getLibrTitulo() {
        return librTitulo;
    }

    public Long getLibrNumPag() {
        return librNumPag;
    }

    public void setLibrEdicion(String librEdicion) {
        this.librEdicion = librEdicion;
    }

    public void setLibrEditorial(Long librEditorial) {
        this.librEditorial = librEditorial;
    }

    public void setLibrExistencia(Long librExistencia) {
        this.librExistencia = librExistencia;
    }

    public void setLibrIsbn(String librIsbn) {
        this.librIsbn = librIsbn;
    }

    public void setLibrTitulo(String librTitulo) {
        this.librTitulo = librTitulo;
    }

    public void setLibrNumPag(Long librNumPag) {
        this.librNumPag = librNumPag;
    }
    
    public void setId(String id) {
     
        try {
            
            Dao dao = new Dao();
            
            ResultSet rst = dao.consultar("SELECT libr_isbn, " +
                                    "	libr_titulo, " +
                                    "	libr_edicion, " +
                                    "	libr_editorial, " +
                                    "	libr_numpag, " +
                                    "	LIBR_EXISTENCIA " +
                                    "FROM Libro Where libr_isbn = '"+id+"'"
                    + "");
            
            while (rst != null && rst.next()) {
                libro = new Libro();
                libro.setLibrIsbn(rst.getString("LIBR_ISBN"));
                libro.setLibrEdicion(rst.getString("LIBR_EDICION"));      
                libro.setLibrEditorial(rst.getLong("LIBR_EDITORIAL"));
                libro.setLibrNumPag(rst.getLong("LIBR_NUMPAG"));
                libro.setLibrExistencia(rst.getLong("LIBR_EXISTENCIA"));
                libro.setLibrTitulo(rst.getString("LIBR_TITULO"));
            }
            
            dao.salir();
            
        } catch (SQLException e) {
            e.printStackTrace();
            error = e.getMessage();
        }
        
    }
    
    public void setCondicion(String condicion) {
        try {
            
            Dao dao = new Dao();
            
            ResultSet rst = dao.consultar("SELECT "
                                  + "LIBR_ISBN, " +
                                    "	LIBR_TITULO, " +
                                    "	LIBR_EDICION, " +
                                    "	LIBR_EDITORIAL, " +
                                    "	LIBR_NUMPAG, " +
                                    "	LIBR_EXISTENCIA " +
                                    "FROM Libro "+condicion
                    + "");
            
            while (rst != null && rst.next()) {
                libro = new Libro();
                libro.setLibrIsbn(rst.getString("LIBR_ISBN"));
                libro.setLibrEdicion(rst.getString("LIBR_EDICION"));      
                libro.setLibrEditorial(rst.getLong("LIBR_EDITORIAL"));
                libro.setLibrNumPag(rst.getLong("LIBR_NUMPAG"));
                libro.setLibrExistencia(rst.getLong("LIBR_EXISTENCIA"));
                libro.setLibrTitulo(rst.getString("LIBR_TITULO"));
               libros.add(libro);
            }
            
            dao.salir();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public List<Libro> buscarLibros() {
        
        String condicion = " Order by 1 ";
        setCondicion(condicion);
        
     return getLibros();
    }
    
    public List<Libro> buscarTitulo(String cadena) {
        
        String condicion = " Where libr_titulo like '%"+cadena+"%' ";
        setCondicion(condicion);
        
     return getLibros();
    }
    
    public Libro getLibro() {
        return libro;
    }
    
    public List<Libro> getLibros() {
        return libros;
    }
    
    public Libro agrLibro(Libro libr) throws BibliotecaException {
        
        try {
                        
            Dao dao = new Dao();
            
           
            dao.modificar("Insert into Libro "
                    + " (libr_isbn,libr_titulo,libr_edicion,libr_numpag,libr_editorial,libr_existencia) "
                    + " Values ('"+libr.getLibrIsbn()+"','"+libr.getLibrTitulo()+"','"+libr.getLibrEdicion()+"','"+libr.getLibrNumPag()+"','"+libr.getLibrEditorial()+"','"+libr.getLibrExistencia()+"')");
            
            dao.salir();
            
        } catch (SQLException e) {
            throw new BibliotecaException(e);
        }
        
        
      return libr;  
    }
    
    public void actLibro(Libro libr) throws BibliotecaException {
        
        try {
            
            Dao dao = new Dao();
            
            dao.modificar("Update Libro "
                    + " Set libr_titulo = '"+libr.getLibrTitulo()+"',"
                    + "libr_edicion = '"+libr.getLibrEdicion()+"',libr_numpag = '"+libr.getLibrNumPag()+"', "
                    + "libr_editorial = '"+libr.getLibrEditorial()+"',"
                    + "libr_existencia = '"+libr.getLibrExistencia()+"' Where libr_isbn = '"+libr.getLibrIsbn()+"' ");
            
            dao.salir();
            
        } catch (SQLException e) {
            throw new BibliotecaException(e);
        }        
    }
    
    public String getError(){
        return error;
    } 
    
    
    public void asignarAutor(String isbn, Long autor) throws BibliotecaException {
        
        try {
          
            Dao dao = new Dao();
            dao.modificar("Insert into AutorLibro (alib_autor,alib_isbn) values ('"+autor+"','"+isbn+"') ");
            dao.salir();
            
        } catch (SQLException e) {
            throw new BibliotecaException(e);
        }         
    }
    
    
    public void asignarCategoria(String isbn, Long categoria) throws BibliotecaException {
        try {
          
            Dao dao = new Dao();
            dao.modificar("Insert into LibroCategoria (lica_categoria,lica_isbn) values ('"+categoria+"','"+isbn+"') ");
            dao.salir();
            
        } catch (SQLException e) {
            throw new BibliotecaException(e);
        } 
    }
    
    public void desasinarAutor(String isbn, Long autor) throws BibliotecaException {
        try {
          
            Dao dao = new Dao();
            dao.modificar("Delete From AutorLibro Where alib_autor = '"+autor+"' And alib_isbn = '"+isbn+"' ");
            dao.salir();
            
        } catch (SQLException e) {
            throw new BibliotecaException(e);
        } 
    }
    
    public void desasinarCategoria(String isbn, Long categoria) throws BibliotecaException {
        try {
          
            Dao dao = new Dao();
            dao.modificar("Delete From LibroCategoria Where lica_categoria = '"+categoria+"' And lica_isbn = '"+isbn+"' ");
            dao.salir();
            
        } catch (SQLException e) {
            throw new BibliotecaException(e);
        } 
    }
}
