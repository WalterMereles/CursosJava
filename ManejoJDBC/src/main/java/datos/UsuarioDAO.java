
package datos;

import domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Natsu
 */
public class UsuarioDAO {
    
    private static final String SQL_SELECT= "SELECT id_usuario, username, password FROM usuario";
    private static final String SQL_INSERT= "INSERT INTO usuario(username, password) VALUES(?, ?)";
    private static final String SQL_UPDATE= "UPDATE usuario SET username= ?, password= ? WHERE id_usuario= ?";
    private static final String SQL_DELETE= "DELETE FROM usuario WHERE id_usuario= ?";
    
    public List<Usuario> seleccionar(){
        Connection conn= null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        Usuario usuario;
        List<Usuario> usuarios= new ArrayList<>();
        try {
            conn= Conexion.getConnection();
            stmt= conn.prepareStatement(SQL_SELECT);
            rs= stmt.executeQuery();
            
            while (rs.next()) {                
                int idUsuario= rs.getInt("id_usuario");
                String username= rs.getString("username");
                String password= rs.getString("password");
                usuario= new Usuario(idUsuario, username, password);
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
                
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return usuarios;
    }
    
    public int insertar(Usuario usuario){
        Connection conn= null;
        PreparedStatement stmt=null;
        int registros=0;
        try {
            conn= Conexion.getConnection();
            stmt= conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            registros= stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }  
        }
        
        return registros;
    }
    
      public int actualizar(Usuario usuario){
        Connection conn= null;
        PreparedStatement stmt=null;
        int registros=0;
        try {
            conn= Conexion.getConnection();
            stmt= conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdUsuario());
            registros= stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }  
        }
        
        return registros;
    }
      
    public int eliminar(Usuario usuario){
        Connection conn= null;
        PreparedStatement stmt=null;
        int registros=0;
        try {
            conn= Conexion.getConnection();
            stmt= conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getIdUsuario());
            registros= stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }  
        }
        
        return registros;
    }
}
