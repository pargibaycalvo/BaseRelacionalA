/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author oracle
 */
public class BaserelacionalA {

    /**
     * @param args the command line arguments
     */
    
    static Connection conn;
    static ResultSet result;
    
    public static void main(String[] args) {
        //TODO code application logic here
        conexion();
        insireProduto(new metodos("p1","parafusos",3));
        insireProduto(new metodos("p2","cravos",4));
        insireProduto(new metodos("p3","tachas",6));
        listaProducto();
        actualizarPre();
        borrarfila();
        amosarfila();
    }
    
    static void conexion(){
        try {
            String driver = "jdbc:oracle:thin:";
            String host = "localhost.localdomain";
            String porto = "1521";
            String sid = "orcl";
            String usuario = "hr";
            String password = "hr";
            String url = driver+usuario
                    +"/"+password+"@"+host
                    +":"+porto+":"+sid;
            conn = DriverManager.getConnection(url);
            System.out.println("Base de datos operativa. Conectado");
        } catch (SQLException ex) {
            Logger.getLogger(BaserelacionalA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
    static void insireProduto(metodos pro){
       
        try {
            PreparedStatement ps = conn.prepareStatement("Insert into productos(CODIGO, DESCRICION, PREZO) values(?,?,?)");
            ps.setString(1,pro.getCODIGO());
            ps.setString(2,pro.getDESCRICION());
            ps.setInt(3,pro.getPREZO());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Error al escribir nuevos datos en la tabla los datos son existentes"+ex.getMessage());
        }
        
    }
    
    static void listaProducto(){
        try {
            PreparedStatement ver = conn.prepareStatement("Select * from productos");
            result=ver.executeQuery();
            while(result.next()){
                System.out.println("CODIGO  "+":"+ result.getString("CODIGO"));
                System.out.println("DESCRICION  "+":"+ result.getString("DESCRICION"));
                System.out.println("PREZO  "+":"+ result.getString("PREZO"));
            }
        } catch (SQLException ex) {
            System.out.println("Error, verificar que la base este conectada "+ex.getMessage());
        }
    }
    
    static void actualizarPre(){
        String reg=JOptionPane.showInputDialog("Inserte el código del producto para actualizar la columna PREZO:");
        Integer precio=Integer.parseInt(JOptionPane.showInputDialog("Inserte el precio a actualizar:"));
        try {   
            PreparedStatement actualiza = conn.prepareStatement("update productos set PREZO='"+precio+"'where CODIGO='"+reg+"'");
            actualiza.execute();
            System.out.println("Prezo actualizado");
        } catch (SQLException ex) {
            System.out.println("Error, introduzca el CODIGO correcto "+ex.getMessage());
        }
    }
    
    static void borrarfila(){
        String reg=JOptionPane.showInputDialog("Inserte el número de CODIGO para borrar la fila correspondiente:"); 
        try{ 
            Statement st = conn.createStatement(); 
            st.execute("delete from productos where CODIGO='"+reg+"'"); 
            System.out.println("Fila borrada con éxito"); 
        }catch(SQLException ex){ 
            System.out.println("Error, introduzca el CODIGO correcto "+ex.getMessage()); 
        }
    }
    
    static void amosarfila(){
        String reg=JOptionPane.showInputDialog("Inserte el CODIGO del producto para mostrar la fila correspondiente:");
        try {
            PreparedStatement ver = conn.prepareStatement("Select * from productos where CODIGO='"+reg+"'");
            result=ver.executeQuery();
            while(result.next()){
                System.out.println("CODIGO  "+":"+ result.getString("CODIGO"));
                System.out.println("DESCRICION  "+":"+ result.getString("DESCRICION"));
                System.out.println("PREZO  "+":"+ result.getString("PREZO"));
            }
        } catch (SQLException ex) {
            System.out.println("Error, introduzca el CODIGO correcto "+ex.getMessage());
        }
    }
    
}
