/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import org.apache.jasper.tagplugins.jstl.core.Catch;
import java.sql.SQLException;
public class Conexion {
    
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static final String HOST = "localhost";
    public static final String PORT = "3306";
    public static final String DATABASE = "agencia_de_viajes";
    public static final String CLASSNAME = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE;
    
    public java.sql.Connection con;
    public Conexion(){
        try {
            Class.forName(CLASSNAME);
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException e){
            System.out.println("Clase No Encontrada");
        } catch (SQLException e){
            System.out.println("Erro SQL");
        }
    }
  
}
