/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.Usuario;

/**
 *
 * @author beto
 */
public class UsuarioDAO {
    private static final String SQL_INSERT = 
            "INSERT INTO usuario ("
            + "nombreusuario,claveusuario) "
            + "VALUES(?,?)";
    private static final String SQL_SELECT = 
            "SELECT idusuario,nombreusuario,claveusuario "
            + "FROM usuario WHERE idusuario= ?";
    private static final String SQL_SELECT_USER_PASS = 
            "SELECT idusuario,nombreusuario,claveusuario "
            + "FROM usuario WHERE nombreusuario=? and claveusuario=?";
    private static final String SQL_SELECT_ALL = 
            "SELECT idusuario,nombreusuario,claveusuario "
            + "FROM usuario";
    private static final String SQL_UPDATE = 
            "UPDATE usuario SET  "
            + "nombreusuario=?,claveusuario=? "
            + "WHERE idusuario =?";
    private static final String SQL_DELETE = 
            "DELETE FROM usuario WHERE "
            + "idusuario=?";
    
    public void create(Usuario dto,Connection conn) throws SQLException{
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getNombreUsuario());
            ps.setString(2, dto.getClaveUsuario());
            ps.executeUpdate();
        }finally{
            cerrar(ps);
            cerrar(conn);
        }
    }
    
    public Usuario loadNC (Usuario dto,Connection conn) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.prepareStatement(SQL_SELECT_USER_PASS);
            ps.setString(1,dto.getNombreUsuario());
            ps.setString(2,dto.getClaveUsuario());
            rs = ps.executeQuery();
            List results = getResults(rs);
            if ( results.size() > 0 ){
                return (Usuario) results.get(0);
            }else{
                return null;
            }
        }finally{
            cerrar(rs);
            cerrar(ps);
            cerrar(conn);
        }
    }
    
    public Usuario load (Usuario dto,Connection conn) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setInt(1,dto.getIdUsuario());
            rs = ps.executeQuery();
            List results = getResults(rs);
            if ( results.size() > 0 ){
                return (Usuario) results.get(0);
            }else{
                return null;
            }
        }finally{
            cerrar(rs);
            cerrar(ps);
            cerrar(conn);
        }
    }
    
    public List loadAll(Connection conn)throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            List results = getResults(rs);
            if ( results.size() > 0 ){
                return results;
            }else{
                return null;
            }
        }finally{
            cerrar(rs);
            cerrar(ps);
            cerrar(conn);
        }        
    }
    
    public void update (Usuario dto,Connection conn) throws SQLException{
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getNombreUsuario());
            ps.setString(2, dto.getClaveUsuario());
            ps.setInt(3,dto.getIdUsuario());
            ps.executeUpdate();
        }finally{
            cerrar(ps);
            cerrar(conn);
        }
    }
    
    public void delete (Usuario dto,Connection conn) throws SQLException{
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1,dto.getIdUsuario());     
            ps.executeUpdate();
        }finally{
            cerrar(ps);
            cerrar(conn);
        }        
    }
    
    private List getResults(ResultSet rs) throws SQLException{
        List results = new ArrayList();
        while( rs.next() ){
            Usuario dto = new Usuario();
            dto.setIdUsuario(rs.getInt("idusuario"));
            dto.setNombreUsuario(rs.getString("nombreusuario"));
            dto.setClaveUsuario(rs.getString("claveusuario"));
            results.add(dto);
        }
        return results;
    }
    
    private void cerrar(PreparedStatement ps)throws SQLException{
        if(ps != null){
            try{
                ps.close();
            }catch(SQLException e){}
        }
    }
    
    private void cerrar(ResultSet rs){
        if( rs != null){
            try{
                rs.close();
            }catch(SQLException e){}
        }
    }
    
    private void cerrar(Connection conn){
        if( conn != null){
            try{
                conn.close();
            }catch(SQLException e){}
        }
    }
}
