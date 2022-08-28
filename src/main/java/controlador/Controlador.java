package controlador;

import conexion.BaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controlador {
    
    BaseDeDatos bd = new BaseDeDatos();
    Connection conexion = bd.estableceConexion();
    
    
    public boolean existePais(int codigoPais){
        ResultSet rs = null;
        boolean existe = false;
        try {
            Statement s = conexion.createStatement();
            rs = s.executeQuery("Select * from tp2laboratorio4.pais WHERE codigoPais=" + codigoPais + ";");
            if(rs.next()) existe=true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return existe;
    }
    
    
    public void insertarPais(int codigoPais, String nombrePais, String capitalPais, String regionPais, long poblacion, double latitud, double longitud) throws SQLException{
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement("insert into tp2laboratorio4.pais(codigoPais, nombrePais, capitalPais, region, poblacion, latitud, longitud) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, codigoPais);
            ps.setString(2, nombrePais);
            ps.setString(3, capitalPais);
            ps.setString(4, regionPais);
            ps.setLong(5, poblacion);
            ps.setDouble(6, latitud);
            ps.setDouble(7, longitud);
            
            ps.executeUpdate();
            conexion.commit();
            
            //return true;
        } catch (SQLException e) {
            conexion.rollback();
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, e);
            //return false;
            System.out.println("Codigo Pais: " + codigoPais);
        } catch (Exception e){
            conexion.rollback();
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, e);
            //return false;
            System.out.println("Codigo Pais: " + codigoPais);
        }  
        finally {
            if(ps!=null) ps.close();
        }
    }
    
    public void actualizarPais(int codigoPais, String nombrePais, String capitalPais, String regionPais, long poblacion, double latitud, double longitud) throws SQLException{
        PreparedStatement ps = null;
        try {
            
            ps = conexion.prepareStatement("UPDATE tp2laboratorio4.pais SET nombrePais = ?, capitalPais = ?," +
                    " region = ?, poblacion = ?, latitud = ?, longitud = ? " +
                    "WHERE codigoPais = ?;");
            
            
            ps.setString(1, nombrePais);
            ps.setString(2, capitalPais);
            ps.setString(3, regionPais);
            ps.setLong(4, poblacion);
            ps.setDouble(5, latitud);
            ps.setDouble(6, longitud);
            ps.setInt(7, codigoPais);
            
            ps.executeUpdate();
            conexion.commit();
            
            
        } catch (SQLException e) {
            conexion.rollback();
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Codigo Pais: " + codigoPais);
            //return false;
        } catch (Exception e){
            conexion.rollback();
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Codigo Pais: " + codigoPais);
            //return false;
        }  
        finally {
            if(ps!=null) ps.close();
        }
    }
    

     
    
}
