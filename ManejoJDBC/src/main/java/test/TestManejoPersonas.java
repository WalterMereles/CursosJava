package test;

import com.sun.source.tree.Tree;
import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Natsu
 */
public class TestManejoPersonas {
    public static void main(String[] args) {
        Connection conexion= null;
        try {
            //PRUEVA DE MANEJO DE TRANSACCIONES
            conexion = Conexion.getConnection();
            
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            PersonaDAO personaDao= new PersonaDAO(conexion);
            
            Persona cambioPersona= new Persona();
            cambioPersona.setIdPersona(2);
            cambioPersona.setNombre("Jose");
            cambioPersona.setApellido("Jara");
            cambioPersona.setEmail("jjara@gmail.com");
            cambioPersona.setTelefono("320325113");
            personaDao.actualizar(cambioPersona);
            
            /*Persona nuevaPersona= new Persona();
            //nuevaPersona.setNombre("Juannaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            nuevaPersona.setNombre("Juana");
            nuevaPersona.setApellido("Garcia");
            personaDao.insertar(nuevaPersona);*/
            conexion.commit();
            System.out.println("SE A HECHO COMMIT CORRECTAMENTE");
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }   
    
            
            
            
    //=========================================================================================================//
            /* PRUEVAS DE LAS CLASES PERSONA Y USUARIO
            //INSERTANDO UN NUEVO OBJETO DE TIPO PERSONA
            //        Persona personaNueva=new Persona("Fernanda", "Barboza", "fbarboza@gmail.com", "35132213");
            //        int registros = personaDao.insertar(personaNueva);
            
            //Modificar un objeto de persona existente
            //        Persona personaModificar= new Persona(4, "Juan", "Gomez", "jgomez@gmail.com", "535131213121");
            //        personaDao.actualizar(personaModificar);
            
            //ELIMINAR un objeto de persona existente
            Persona personaEliminar= new Persona(5);
            personaDao.eliminar(personaEliminar);
            
            List<Persona> personas= personaDao.seleccionar();
            for (Persona persona : personas) {
            
            System.out.println("persona= " + persona);
            
            }
            //System.out.println("registros: " + registrosa);
            */
       
    }
}
