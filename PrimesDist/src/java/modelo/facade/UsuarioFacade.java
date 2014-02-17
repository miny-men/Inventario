/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.facade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import modelo.dao.UsuarioDAO;
import modelo.dto.Usuario;

/**
 *
 * @author beto
 */
public class UsuarioFacade {
    private Connection cnn;
    private UsuarioDAO dao;
    
    public UsuarioFacade(Connection cnn){
        this.cnn = cnn;
        dao = new UsuarioDAO();
    }
    
    public void crear(Usuario dto) throws SQLException{
        dao.create(dto, cnn);
    }
    public List listar()throws SQLException{
        return dao.loadAll(cnn);
    }
    public Usuario leer(Usuario dto) throws SQLException{
        return dao.load(dto, cnn);
    }
    public Usuario leerNombreClave(Usuario dto) throws SQLException{
        //System.out.println("LNC FACADE");
        return dao.loadNC(dto, cnn);
    }
    
    public void actualizar(Usuario dto) throws SQLException{
        dao.update(dto, cnn);
    }
    public void eliminar(Usuario dto) throws SQLException{
        dao.delete(dto, cnn);
    }
    
}
