/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.delegate.DemoPFDelegate;
import modelo.dto.Usuario;

/**
 *
 * @author beto
 */
@ManagedBean
@RequestScoped
public class UsuarioMB{
    private static final String LOGIN_NAME = "usuario";
    private String nombreUsuario;
    private String claveUsuario;
    private List<Usuario> lista = 
            new ArrayList();
    public UsuarioMB() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getClaveUsuario() {
        return claveUsuario;
    }
    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }
    public List<Usuario> getLista() throws Exception {
        lista = lista();
        return lista;
    }
    public void setLista(List<Usuario> lista) throws Exception {
        this.lista = lista;
    }
    
    public String login(){        
        String paginaMostrar = "";
        DemoPFDelegate del = new DemoPFDelegate();
        Usuario u = new Usuario();
        u.setNombreUsuario(nombreUsuario);
        u.setClaveUsuario(claveUsuario);
        try{            
            u = del.leerUsuarioPorNombreClave(u);            
            if( u != null){
              // paginaMostrar = "welcomePrimefaces.xhtml";
                paginaMostrar = "admon.xhtml";
                System.out.println("Si paso");
            }else{               
                paginaMostrar = "index.xhtml";
            }
        }catch(Exception e){
        }
        return paginaMostrar;
    }
    
    private List<Usuario>lista()throws Exception{
        DemoPFDelegate del = new DemoPFDelegate();
        return (List) del.listarUsuario();
    }        
}
