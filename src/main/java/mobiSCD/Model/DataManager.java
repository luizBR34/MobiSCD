/*
    Document   : DataManager
    Created on : 07/10/2016, 17:12:14
    Author     : Luiz Fernando
    Descrição  : Classe usada para a utilização do driver JDBC para conexão com a base MYSQL.
 */
package mobiSCD.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.FacesEvent;


public class DataManager {
    
    

    public FacesMessage msg;

    public Statement statement;

    
    
    public DataManager(FacesMessage out) {

        this.msg = out;

        carregaDriver("com.mysql.jdbc.Driver");
        Connection c = conecta("jdbc:mysql://localhost/SCDataBase", "root", "hadouken83");
        statement = criaStatement(c);

    }
    
    
    
    
    public DataManager() {

        carregaDriver("com.mysql.jdbc.Driver");
        Connection c = conecta("jdbc:mysql://localhost/SCDataBase", "root", "hadouken83");
        statement = criaStatement(c);

    }
    
    
    
    
    
    
    public void carregaDriver(String endereco) {

        try {

            Class.forName(endereco);

        } catch (ClassNotFoundException e) {

            msg.setSummary("A classe não pode ser localizada!");
        }
    }




    public Connection conecta(String host, String user, String password) {

        Connection c;

        try {

            c = DriverManager.getConnection(host, user, password);
            return c;

        } catch (SQLException e) {

            msg.setSummary("Ocorreu um erro de acesso ao banco!");

            return null;
        }
    }
    
    
    
    

    public Statement criaStatement(Connection c) {

        Statement st;

        try {

            st = c.createStatement();
            return st;

        } catch (SQLException e) {

            msg.setSummary("Ocorreu um erro de acesso ao banco ou a conexao esta fechada!");

            return null;
        }
    }
    
    
    
    
    
    
    public ResultSet executaQuery(Statement st, String query) {

    ResultSet rs;
        

        try {
            
            rs = st.executeQuery(query);
            return rs;

        } catch (SQLTimeoutException e) {
            
            msg.setSummary("Timeout excedido na comunicação com o banco!");
            return null;


        } catch (SQLException e2) { 
        
            msg.setSummary("Ocorreu um erro de acesso ao banco ou a conexao do Statment esta fechada, ou ha um erro de sql!");
            return null;
            
        } catch (Exception e3) { 
        
            return null;
        }

    }
    
    
    
    
    
    
    

    public int executaUpdate(Statement st, String query) {

        int resultado;

        try {
            
            resultado = st.executeUpdate(query);
            return resultado;

        } catch (SQLTimeoutException e) {
            
            msg.setSummary("Timeout excedido na comunicação com o banco!");
            return 0;


        } catch (SQLException e2) { 
        
            msg.setSummary("Ocorreu um erro de acesso ao banco ou a conexao do Statment esta fechada, ou ha um erro de sql!");
            return 0;
            
        } catch (Exception e3) { 
        
            return 0;
        }
    }
    
    
    
    
    
    public String getStringColumn(ResultSet rs, String nomeColuna) {
        
        String valor = "";
        
        try {
            
            if (rs.next()) {
 
                valor = rs.getString(nomeColuna);        
                return valor;  

            } else {
                
                msg.setSummary("Usuário não cadastrado.");
                return "";
            }
            
            
        } catch (SQLException e) { 
        
            msg.setSummary("Nome da coluna invalido ou ocorreu um erro de acesso ao banco!");
            return "";
            
        } catch (Exception e2) { 
        
            msg.setSummary(e2.getMessage());
            return "";
        }
    }
    
    
    
    
    
    
    public String[] getStringColumns(ResultSet rs) {
        
        String[] colunas = new String[5];
        
        try {
            
            while (rs.next()) {

                colunas[0] = rs.getString("CLIENTE_ID");
                colunas[1] = rs.getString("clt_nome");
                colunas[2] = rs.getString("clt_endereco");
                colunas[3] = rs.getString("clt_senha");
                colunas[4] = rs.getString("clt_ITR_ID");
            } 
            
        } catch (SQLException e) { 
        
            msg.setSummary("Nome da coluna invalido (TAB Cliente) ou ocorreu um erro de acesso ao banco!");
            
        } catch (Exception e2) { }
        
        return colunas;
    }
    
    
    
    
    
    public String[][] getStringColumnsAparelho(ResultSet rs, int quantCanais) {
        
        String[][] colunas = new String[quantCanais][5];
        int i = 0;
        
        try {
            
            while (rs.next()) {
                
                colunas[i][0] = rs.getString("CANAL_ID");
                colunas[i][1] = rs.getString("apr_nome");
                colunas[i][2] = rs.getString("apr_consumo");
                colunas[i][3] = rs.getString("apr_data");
                colunas[i][4] = rs.getString("apr_estado");
                
                    if (i < (quantCanais-1)) {
                        i++;
                    }
            } 
            
        } catch (SQLException e) { 
        
            msg.setSummary("Nome da coluna invalido (TAB Aparelho) ou ocorreu um erro de acesso ao banco!");
            
        } catch (Exception e2) { }
        
        
        return colunas;
    }
    
    
    
    

    public int getIntColumn(ResultSet rs, String nomeColuna) {
        
        int valor = 0;
        
        try {
            
            if (rs.next()) {
                
                valor = rs.getInt(nomeColuna);
                return valor;
                
            } else {
                
                msg.setSummary("Usuário não cadastrado.");
                return 0;
            }
            
            
        } catch (SQLException e) { 
        
            msg.setSummary("Nome da coluna invalido ou ocorreu um erro de acesso ao banco!");
            return 0;
            
        } catch (Exception e2) {
            
            return 0;
        }
    }
    
    
    

    
    
    public void fecharConexao(Statement st) {
        
        try {
        
        st.close();
        st.getConnection().close();
        
        } catch (SQLException e) { }

    }
    
    
    
    
    
    public void mostrarMSG(FacesContext contexto, FacesEvent evento, FacesMessage msg) {

        msg.setSummary(msg.getSummary());
        contexto.addMessage(evento.getComponent().getClientId(), msg);
    }
    

}
