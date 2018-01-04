import java.sql.*;
import java.util.*;

public class ClientesDAO {
    
    public static ResultSet rsClientes;//obj. p/ armazenar o resultado de uma consulta no bd
    public static Statement stClientes; // permite a execução de comando sql no bd
    
    public static Clientes consultaCliente(String tmpBusca, int tmpTipo)throws Exception{
        //consulta apenas 1 cliente
        try{
            ConexaoDAO.abreConexao();//abrindo conexao
            stClientes = ConexaoDAO.comSistema.createStatement();
            String sqlBusca = "";
            if(tmpTipo==1){
                sqlBusca = "Select * from customers where CustomerID = '" + tmpBusca + "'";
            }/*else if(tmpTipo==2){
                sqlBusca = "Select * from customers where CompanyName = '%" + tmpBusca + "%'";
            }//fechando else if*/
            
            rsClientes = stClientes.executeQuery(sqlBusca);
            
            if(rsClientes.next()){//se houver resultados
               Clientes tmpCliente = new Clientes();
               
               tmpCliente.setCodigo(rsClientes.getString("CustomerID"));
               tmpCliente.setNomeEmpresa(rsClientes.getString("CompanyName"));
               tmpCliente.setNomeRepresentante(rsClientes.getString("ContactName"));
               tmpCliente.setCargo(rsClientes.getString("ContactTitle"));
               tmpCliente.setEndereco(rsClientes.getString("Address"));
               tmpCliente.setCidade(rsClientes.getString("City"));
               tmpCliente.setPais(rsClientes.getString("Country"));
               tmpCliente.setCep(rsClientes.getString("PostalCode"));
               tmpCliente.setEstado(rsClientes.getString("Region"));
               tmpCliente.setTelefone(rsClientes.getString("Phone"));
               tmpCliente.setEmail(rsClientes.getString("Fax"));
               tmpCliente.setStatus(rsClientes.getInt("Status"));
               return tmpCliente;
            }else{//se nao houver resultados
                return null;
            }//fechamento if
        }catch(Exception erro){
            String msg = "Falha na consulta. "
                    + "Verifique a sintaxe, campos e tabelas da instrução SELECT.\n "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        //return null;
    }//fechando consultar clientes
    
    public static void cadastraCliente(Clientes tmpCliente)throws Exception{
        try{
            ConexaoDAO.abreConexao();
            stClientes = ConexaoDAO.comSistema.createStatement();
            String sqlCadastra = "Insert into Customers(";
            sqlCadastra+="CustomerID,CompanyName,ContactName,";
            sqlCadastra+="ContactTitle,Address,City,";
            sqlCadastra+="Region,PostalCode,Country,";
            sqlCadastra+="Phone,Fax,status)values(";
            sqlCadastra+="'"+tmpCliente.getCodigo()+"',";
            sqlCadastra+="'"+tmpCliente.getNomeEmpresa()+"',";
            sqlCadastra+="'"+tmpCliente.getNomeRepresentante()+"',";
            sqlCadastra+="'"+tmpCliente.getCargo()+"',";
            sqlCadastra+="'"+tmpCliente.getEndereco()+"',";
            sqlCadastra+="'"+tmpCliente.getCidade()+"',";
            sqlCadastra+="'"+tmpCliente.getEstado()+"',";
            sqlCadastra+="'"+tmpCliente.getCep()+"',";
            sqlCadastra+="'"+tmpCliente.getPais()+"',";
            sqlCadastra+="'"+tmpCliente.getTelefone()+"',";
            sqlCadastra+="'"+tmpCliente.getEmail()+"',1)";//o 1 se refere ao status
            stClientes.executeUpdate(sqlCadastra);//serve para executar instruções no sql que nao tem retorno
            ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            String msg = "Falha no cadastro. "
                    + "Verifique a sintaxe, campos e tabelas da instrução INSERT.\n "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        
    }//fechando void cadastraCliente
    
    public static void alteraCliente(String tmpCodigo, Clientes tmpCliente)throws Exception{
        try{
            
        }catch(Exception erro){
            String msg = "Falha na alteração. "
                    + "Verifique a sintaxe, campos e tabelas da instrução UPDATE.\n "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        
    }//fechando void alteraCliente
    
    public static void excluiCliente(String tmpCodigo)throws Exception{
        try{
          
        }catch(Exception erro){
            String msg = "Falha na exclusão. "
                    + "Verifique a sintaxe, campos e tabelas da instrução DELETE.\n "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        
    }//fechando excluiCliente
    
    public static List<Clientes> listaClientes()throws Exception{
     //é necessario fazer um metodo que retorne um vetor com os clientes, para ter a lista
     //diferente de quando retorna somente 1   
        try{
            List<Clientes>vetorClientes = new ArrayList<>();
            ConexaoDAO.abreConexao();
            stClientes = ConexaoDAO.comSistema.createStatement();
            String sqlClientes = "Select * from Customers";
            rsClientes = stClientes.executeQuery(sqlClientes);//joga o dado na variavel
            while(rsClientes.next()){
                Clientes clienteAtual = new Clientes();
                clienteAtual.setCodigo(rsClientes.getString("CustomerID"));
                clienteAtual.setNomeEmpresa(rsClientes.getString("CompanyName"));
                clienteAtual.setCidade(rsClientes.getString("City"));
                clienteAtual.setEmail(rsClientes.getString("Fax"));
                vetorClientes.add(clienteAtual);//add objeto cliente no vetor.                
            }//fechamento while
            ConexaoDAO.fechaConexao();//fechando conexao
            return vetorClientes;
        }catch(Exception erro){
            String msg = "Falha na consulta. "
                    + "Verifique a sintaxe, campos e tabelas da instrução SELECT.\n "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        
    }//fechando listaClientes
    
    public static void alteraStatus(String tmpCodigo, int tmpStatus)throws Exception{
        try{
           ConexaoDAO.abreConexao();
           
           int novoStatus;
           
           if(tmpStatus == 0){
               novoStatus=1;
           }else{
               novoStatus=0;
           }
           
           String sqlStatus = "Update customers set status=" + novoStatus + " where customerId like '" + tmpCodigo + "'";
           stClientes = ConexaoDAO.comSistema.createStatement();
           stClientes.executeUpdate(sqlStatus);
                
           
           ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            String msg = "Falha na alteração de Status. "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        
        
    }//fechamento void alteraStatus
    
    public static List<Clientes> consultaNome(String tmpNome)throws Exception{
        List<Clientes> Lista = new ArrayList<>();
                
        try{
            //Elaboração da busca
            ConexaoDAO.abreConexao();
            String sqlBusca = "Select * from customers where CompanyName like '%" + tmpNome + "%'";
            stClientes = ConexaoDAO.comSistema.createStatement();
            //String sqlClientes = "Select * from Customers";
            rsClientes = stClientes.executeQuery(sqlBusca);//joga o dado na variavel
            while(rsClientes.next()){
                Clientes clienteAtual = new Clientes(); //instancia da classe clientes
                clienteAtual.setCodigo(rsClientes.getString("CustomerID"));
                clienteAtual.setNomeEmpresa(rsClientes.getString("CompanyName"));
                clienteAtual.setCidade(rsClientes.getString("City"));
                clienteAtual.setEmail(rsClientes.getString("Fax"));
                Lista.add(clienteAtual);//add objeto cliente no vetor.                
            }//fechamento while       
            ConexaoDAO.fechaConexao();  
            return Lista;
        }catch(Exception erro){
            String msg = "Falha na Consulta. "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        //return Lista;
        
        
    }//fechando consultaNome
    
    
}//fechamento ClientesDAO
