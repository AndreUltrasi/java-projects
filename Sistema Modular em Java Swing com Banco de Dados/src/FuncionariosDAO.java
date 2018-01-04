import java.sql.*;
import java.util.*;

public class FuncionariosDAO {
    
    public static ResultSet rsFuncionarios;//obj. p/ armazenar o resultado de uma consulta no bd
    public static Statement stFuncionarios; // permite a execução de comando sql no bd
    
    public static Funcionarios consultaFuncionario(String tmpBusca, int tmpTipo)throws Exception{
        //consulta apenas 1 cliente
        try{
            ConexaoDAO.abreConexao();//abrindo conexao
            stFuncionarios = ConexaoDAO.comSistema.createStatement();
            String sqlBusca = "";
            if(tmpTipo==1){
                sqlBusca = "Select * from employees where EmployeeID = '" + tmpBusca + "'";
            }/*else if(tmpTipo==2){
                sqlBusca = "Select * from funcionarios where CompanyName = '%" + tmpBusca + "%'";
            }//fechando else if*/
            
            rsFuncionarios = stFuncionarios.executeQuery(sqlBusca);
            
            if(rsFuncionarios.next()){//se houver resultados
               Funcionarios tmpFuncionario = new Funcionarios();
               
               tmpFuncionario.setEmployeeID(rsFuncionarios.getString("EmployeeID"));
               tmpFuncionario.setFirstName(rsFuncionarios.getString("FirstName"));
               tmpFuncionario.setLastName(rsFuncionarios.getString("LastName"));
               tmpFuncionario.setTitle(rsFuncionarios.getString("Title"));
               tmpFuncionario.setHireDate(rsFuncionarios.getString("HireDate"));
               tmpFuncionario.setAddress(rsFuncionarios.getString("Address"));
               tmpFuncionario.setCity(rsFuncionarios.getString("City"));
               tmpFuncionario.setRegion(rsFuncionarios.getString("Region"));
               tmpFuncionario.setPostalCode(rsFuncionarios.getString("PostalCode"));
               tmpFuncionario.setHomePhone(rsFuncionarios.getString("HomePhone"));
               tmpFuncionario.setCountry(rsFuncionarios.getString("Country"));
               tmpFuncionario.setStatus(rsFuncionarios.getInt("Status"));
               return tmpFuncionario;
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
    
    public static void cadastraFuncionario(Funcionarios tmpFuncionario)throws Exception{
        try{
            ConexaoDAO.abreConexao();
            stFuncionarios = ConexaoDAO.comSistema.createStatement();
            String sqlCadastra = "Insert into employees(";
            sqlCadastra+="EmployeeID, FirstName, LastName,";
            sqlCadastra+="Title, HireDate, Address,";
            sqlCadastra+="City, Region, PostalCode,";
            sqlCadastra+="HomePhone, Country, status)values(";
            sqlCadastra+="'"+tmpFuncionario.getEmployeeID()+"',";
            sqlCadastra+="'"+tmpFuncionario.getFirstName()+"',";
            sqlCadastra+="'"+tmpFuncionario.getLastName()+"',";
            sqlCadastra+="'"+tmpFuncionario.getTitle()+"',";
            sqlCadastra+="'"+tmpFuncionario.getHireDate()+"',";
            sqlCadastra+="'"+tmpFuncionario.getAddress()+"',";
            sqlCadastra+="'"+tmpFuncionario.getCity()+"',";
            sqlCadastra+="'"+tmpFuncionario.getRegion()+"',";
            sqlCadastra+="'"+tmpFuncionario.getPostalCode()+"',";
            sqlCadastra+="'"+tmpFuncionario.getHomePhone()+"',";
            sqlCadastra+="'"+tmpFuncionario.getCountry()+"',1)";//o 1 se refere ao status
            stFuncionarios.executeUpdate(sqlCadastra);//serve para executar instruções no sql que nao tem retorno
            ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            String msg = "Falha no cadastro. "
                    + "Verifique a sintaxe, campos e tabelas da instrução INSERT.\n "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        
    }//fechando void cadastraCliente
    
    public static void alteraFuncionario(String tmpCodigo, Funcionarios tmpFuncionario)throws Exception{
        try{
            
        }catch(Exception erro){
            String msg = "Falha na alteração. "
                    + "Verifique a sintaxe, campos e tabelas da instrução UPDATE.\n "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        
    }//fechando void alteraCliente
    
    public static void excluiFuncionario(String tmpCodigo)throws Exception{
        try{
          
        }catch(Exception erro){
            String msg = "Falha na exclusão. "
                    + "Verifique a sintaxe, campos e tabelas da instrução DELETE.\n "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        
    }//fechando excluiCliente
    
    public static List<Funcionarios> listaFuncionarios()throws Exception{
     //é necessario fazer um metodo que retorne um vetor com os clientes, para ter a lista
     //diferente de quando retorna somente 1   
        try{
            List<Funcionarios>vetorFuncionarios = new ArrayList<>();
            ConexaoDAO.abreConexao();
            stFuncionarios = ConexaoDAO.comSistema.createStatement();
            String sqlFuncionarios = "Select * from Employees";
            rsFuncionarios = stFuncionarios.executeQuery(sqlFuncionarios);//joga o dado na variavel
            while(rsFuncionarios.next()){
                Funcionarios funcionarioAtual = new Funcionarios();
                funcionarioAtual.setEmployeeID(rsFuncionarios.getString("EmployeeID"));
                funcionarioAtual.setFirstName(rsFuncionarios.getString("FirstName"));
                funcionarioAtual.setLastName(rsFuncionarios.getString("LastName"));
                funcionarioAtual.setTitle(rsFuncionarios.getString("Title"));
                funcionarioAtual.setCountry(rsFuncionarios.getString("Country"));
                funcionarioAtual.setStatus(rsFuncionarios.getInt("Status"));
                vetorFuncionarios.add(funcionarioAtual);//add objeto cliente no vetor.                
            }//fechamento while
            ConexaoDAO.fechaConexao();//fechando conexao
            return vetorFuncionarios;
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
           
           String sqlStatus = "Update employees set status=" + novoStatus + " where EmployeeID like '" + tmpCodigo + "'";
           stFuncionarios = ConexaoDAO.comSistema.createStatement();
           stFuncionarios.executeUpdate(sqlStatus);
                
           
           ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            String msg = "Falha na alteração de Status. "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        
        
    }//fechamento void alteraStatus
    
    public static List<Funcionarios> consultaNome(String tmpNome)throws Exception{
        List<Funcionarios> Lista = new ArrayList<>();
                
        try{
            //Elaboração da busca
            ConexaoDAO.abreConexao();
            String sqlBusca = "Select * from employees where (FirstName like '%"
            + tmpNome + "%' or LastName like '%" + tmpNome + "%')";
            
            stFuncionarios = ConexaoDAO.comSistema.createStatement();
            //String sqlClientes = "Select * from funcionarios";
            rsFuncionarios = stFuncionarios.executeQuery(sqlBusca);//joga o dado na variavel
            while(rsFuncionarios.next()){
                Funcionarios funcionarioAtual = new Funcionarios(); //instancia da classe clientes
                funcionarioAtual.setEmployeeID(rsFuncionarios.getString("EmployeeID"));
                funcionarioAtual.setFirstName(rsFuncionarios.getString("FirstName"));
                funcionarioAtual.setLastName(rsFuncionarios.getString("LastName"));
                funcionarioAtual.setTitle(rsFuncionarios.getString("Title"));
                funcionarioAtual.setCountry(rsFuncionarios.getString("Country"));
                funcionarioAtual.setStatus(rsFuncionarios.getInt("Status"));
                Lista.add(funcionarioAtual);//add objeto cliente no vetor.                
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
    
}