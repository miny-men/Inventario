/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.delegate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import modelo.dto.Usuario;
import modelo.facade.UsuarioFacade;

/**
 *
 * @author beto
 */
public class DemoPFDelegate {
    private Connection cnn;
    private UsuarioFacade usuarioFacade;
    
    public DemoPFDelegate(){
        String user = "root";
        String pwd = "root";
        String url = "jdbc:mysql://localhost:3306/mydb";
        String driver = "com.mysql.jdbc.Driver";
        try{
            Class.forName(driver);
            cnn = DriverManager.getConnection(url, user, pwd);
            System.out.println("CONEXION EXITOSA!!!");
        }catch(Exception e){
            e.printStackTrace();
        }
        usuarioFacade = 
                new UsuarioFacade(cnn);
    }
    //BIEN
    public void crearUsuario(Usuario dto) throws SQLException{
        usuarioFacade.crear(dto);
    }
    public List listarUsuario()throws SQLException{
        return usuarioFacade.listar();
    }
    //BIEN
    public Usuario leerUsuario(Usuario dto) throws SQLException{
        return usuarioFacade.leer(dto);
    }
    
    public Usuario leerUsuarioPorNombreClave(Usuario dto) throws SQLException{
        //System.out.println("LUPC DemoDelegate");
        return usuarioFacade.leerNombreClave(dto);
    }
    //BIEN
    public void actualizarUsuario(Usuario dto) throws SQLException{
        usuarioFacade.actualizar(dto);
    }
    //BIEN
    public void eliminarUsuario(Usuario dto) throws SQLException{
        usuarioFacade.eliminar(dto);
    }
    
    
//    public static void main(String[] args) {
//        Usuario u = new Usuario();
//        u.setIdUsuario(4);
//        u.setNombreUsuario("rodrigo");
//        u.setClaveUsuario("compu");
//        DemoPFDelegate pf = new DemoPFDelegate();
//        try {
//            pf.actualizarUsuario(u);
//            pf.listarUsuario();
//            //System.out.println(pf.leerUsuarioPorNombreClave(u));            
//            //pf.crearUsuario(u);
//            //pf.eliminarUsuario(u);
//            //System.out.println(pf.listarUsuario());
//        } catch (SQLException ex) {
//            Logger.getLogger(DemoPFDelegate.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
