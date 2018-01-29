package com.br.projeto.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.br.projeto.model.Cliente;
 
public class Condados {
	  Connection con = null;
	   
      public Condados() throws SQLException {
  
           try {
                 Class.forName("com.mysql.jdbc.Driver");
                 System.out.println("Instalou driver");
           } catch (ClassNotFoundException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
                 
           }
  
           String url = "jdbc:mysql://localhost:3306/cliente";
           String user = "root";
           String password = "1234";
           con = DriverManager.getConnection(url, user, password);
      }
  
      public void closeConnection() throws SQLException {
  
           con.close();
      }
  
      // cadastral no banco um cliente passado como parametro
      public boolean insertCliente(Cliente cliente) {
  
           Statement st = null;
           ResultSet rs = null;
  
           try {
                 st = con.createStatement();
  
                 PreparedStatement preparedStatement = con
                           .prepareStatement("insert into cliente( nome, cpf, endereco, email, data_cadastro) values(?,?,?,?,?)");
                 
                 preparedStatement.setString(1, cliente.getNome());
                 preparedStatement.setString(2, cliente.getCpf());
                 preparedStatement.setString(3, cliente.getEndereco());
                 preparedStatement.setString(4, cliente.getEmail());
                 preparedStatement.setDate(5, new java.sql.Date(new Date().getTime()));
  
                 preparedStatement.execute();
                 return true;
           } catch (SQLException ex) {
                 Logger lgr = Logger.getLogger(Condados.class.getName());
                 lgr.log(Level.SEVERE, ex.getMessage(), ex);
                 return false;
  
           }
           
      }
      
      //lista todos os clientes cadastrados no banco de dados
      public List<Cliente> listClientes() {
  
           ArrayList<Cliente> lista = new ArrayList<Cliente>();
  
           Statement st = null;
           ResultSet rs = null;
  
           try {
                 st = con.createStatement();
                 String sql = "select * from cliente ";
                rs = st.executeQuery(sql);
  
                 while (rs.next()) {
  
                      Cliente cliente = new Cliente();
                      cliente.setId(rs.getInt(1));
                      cliente.setNome(rs.getString(2));
                      cliente.setCpf(rs.getString(3));
                      cliente.setEndereco(rs.getString(4));
                      cliente.setEmail(rs.getString(5));
                      cliente.setDataCadastro(rs.getDate(6));
                      lista.add(cliente);
                 }
  
           } catch (SQLException ex) {
                 Logger lgr = Logger.getLogger(Condados.class.getName());
                 lgr.log(Level.SEVERE, ex.getMessage(), ex);
  
           } finally {
                 try {
                      if (rs != null) {
                           rs.close();
                      }
                      if (st != null) {
                           st.close();
                      }
                      if (con != null) {
                           con.close();
                      }
  
                 } catch (SQLException ex) {
                      Logger lgr = Logger.getLogger(Condados.class.getName());
                      lgr.log(Level.WARNING, ex.getMessage(), ex);
                 }
           }
           return lista;
      }
      
      public boolean deleteCliente(Cliente cliente) {
    	  
          Statement st = null;
          ResultSet rs = null;
 
          try {
                st = con.createStatement();
 
                PreparedStatement preparedStatement = con
                          .prepareStatement("DELETE FROM cliente WHERE ID = ?");
                
                preparedStatement.setInt(1, cliente.getId());
                
                preparedStatement.executeUpdate();
                return true;
          } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Condados.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
 
          }
         
     } 

 public boolean updateCliente(Cliente cliente) {
    	  
	
         Statement st = null;
         ResultSet rs = null;

         try {
               st = con.createStatement();

               PreparedStatement preparedStatement = con
                         .prepareStatement("update cliente set nome = ?, cpf = ?, endereco = ?, email = ? where id = ?");
               
               preparedStatement.setString(1, cliente.getNome());
               preparedStatement.setString(2, cliente.getCpf());
               preparedStatement.setString(3, cliente.getEndereco());
               preparedStatement.setString(4, cliente.getEmail());
               preparedStatement.setInt(5, cliente.getId());

               preparedStatement.execute();
               return true;
               
               
         } catch (SQLException ex) {
               Logger lgr = Logger.getLogger(Condados.class.getName());
               lgr.log(Level.SEVERE, ex.getMessage(), ex);
               return false;

         }
         
    }

}