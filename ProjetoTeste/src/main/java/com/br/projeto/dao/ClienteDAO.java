package com.br.projeto.dao;

import com.br.projeto.conexao.Condados;
import java.sql.SQLException;
import java.util.List;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import com.br.projeto.model.Cliente;

 
@ManagedBean(name="ClienteMB")
public class ClienteDAO {
     
     private Cliente cliente = new Cliente();
     
     public String cadastraCliente() throws SQLException {
          
                Condados con = new Condados();         
                
                
                if (con.insertCliente(cliente)) {
                     FacesContext.getCurrentInstance().addMessage(
                               null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cadastrado!", "Cliente cadastrado com sucesso!"));
                     
                } else {
                     FacesContext.getCurrentInstance().addMessage(
                               null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"CPF já cadastrado", "Erro no cadastrar de Cliente!"));
 
                }
         

                con.closeConnection();
                
                
                
          return "";
     }
     
     public String alteraCliente() throws SQLException {
         
         Condados con = new Condados();         
         
         
         if (con.updateCliente(cliente)) {
              FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso!", "Cliente alterado com sucesso!"));
         } else {
              FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro!", "Erro ao tentar alterar Cliente!"));

         }
         con.closeConnection();
         
         
         
   return "";
}
     
     
     public String deletaCliente(Cliente cliente) throws SQLException {
         
         Condados con = new Condados();         
         
         
         if (con.deleteCliente(cliente)) {
              FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Deletado!", "Cliente deletado com sucesso!"));
         } else {
              FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro!", "Erro ao deletar o Cliente!"));

         }
         
         
         con.closeConnection();
         
         
         
   return "";
}
     
     
     public List<Cliente> getclientes() throws SQLException {
 
          Condados con = new Condados();
          List<Cliente> listaClientes = con.listClientes();
     
          return listaClientes;
     }
     
     public Cliente getCliente() {
          return cliente;
     }
     
     public void setCliente(Cliente cliente) {
          this.cliente = cliente;
     }
     
     

    
     

     
     
     
}
