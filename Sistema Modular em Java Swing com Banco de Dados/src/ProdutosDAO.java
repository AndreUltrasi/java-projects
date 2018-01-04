import java.sql.*;
import java.util.*;

public class ProdutosDAO {
    
    public static ResultSet rsProdutos;//obj. p/ armazenar o resultado de uma consulta no bd
    public static Statement stProdutos; // permite a execução de comando sql no bd
    
    public static Produtos consultaProduto(String tmpBusca, int tmpTipo)throws Exception{
        //consulta apenas 1 cliente
        try{
            ConexaoDAO.abreConexao();//abrindo conexao
            stProdutos = ConexaoDAO.comSistema.createStatement();
            String sqlBusca = "";
            if(tmpTipo==1){
                sqlBusca = "Select * from products where ProductID = '" + tmpBusca + "'";
            }
            
            rsProdutos = stProdutos.executeQuery(sqlBusca);
            
            if(rsProdutos.next()){//se houver resultados
               Produtos tmpProduto = new Produtos();
               
               tmpProduto.setProductID(rsProdutos.getString("ProductID"));
               tmpProduto.setProductName(rsProdutos.getString("ProductName"));
               tmpProduto.setSupplierID(rsProdutos.getString("SupplierID"));
               tmpProduto.setCategoryID(rsProdutos.getString("CategoryID"));
               tmpProduto.setQuantityPerUnit(rsProdutos.getString("QuantityPerUnit"));
               tmpProduto.setUnitPrice(rsProdutos.getString("UnitPrice"));
               tmpProduto.setUnitsInStock(rsProdutos.getString("UnitsInStock"));
               tmpProduto.setUnitsOnOrder(rsProdutos.getString("UnitsOnOrder"));
               tmpProduto.setReorderLevel(rsProdutos.getString("ReorderLevel"));
               tmpProduto.setDiscontinued(rsProdutos.getInt("Discontinued"));
               
               return tmpProduto;
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
    
    public static void cadastraProduto(Produtos tmpProduto)throws Exception{
        try{
            ConexaoDAO.abreConexao();
            stProdutos = ConexaoDAO.comSistema.createStatement();
            
            String sqlCadastra = "Insert into Products(";
            sqlCadastra+="ProductID, ProductName, SupplierID,";
            sqlCadastra+="CategoryID, QuantityPerUnit, UnitPrice,";
            sqlCadastra+="UnitsInStock, UnitsOnOrder, ReorderLevel,";
            sqlCadastra+="Discontinued)values(";
            sqlCadastra+="'"+tmpProduto.getProductID()+"',";
            sqlCadastra+="'"+tmpProduto.getProductName()+"',";
            sqlCadastra+="'"+tmpProduto.getSupplierID()+"',";
            sqlCadastra+="'"+tmpProduto.getCategoryID()+"',";
            sqlCadastra+="'"+tmpProduto.getQuantityPerUnit()+"',";
            sqlCadastra+="'"+tmpProduto.getUnitPrice()+"',";
            sqlCadastra+="'"+tmpProduto.getUnitsInStock()+"',";
            sqlCadastra+="'"+tmpProduto.getUnitsOnOrder()+"',";
            sqlCadastra+="'"+tmpProduto.getReorderLevel()+"',";
            sqlCadastra+="'"+tmpProduto.getDiscontinued()+"')";
            
            stProdutos.executeUpdate(sqlCadastra);//serve para executar instruções no sql que nao tem retorno
            ConexaoDAO.fechaConexao();
            
        }catch(Exception erro){
            String msg = "Falha no cadastro. "
                    + "Verifique a sintaxe, campos e tabelas da instrução INSERT.\n "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        
    }//fechando void cadastraCliente
    
    public static void alteraProduto(String tmpCodigo, Produtos tmpProduto)throws Exception{
        try{
            
        }catch(Exception erro){
            String msg = "Falha na alteração. "
                    + "Verifique a sintaxe, campos e tabelas da instrução UPDATE.\n "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        
    }//fechando void alteraCliente
    
    public static void excluiProduto(String tmpCodigo)throws Exception{
        try{
          
        }catch(Exception erro){
            String msg = "Falha na exclusão. "
                    + "Verifique a sintaxe, campos e tabelas da instrução DELETE.\n "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        
    }//fechando excluiCliente
    
    public static List<Produtos> listaProdutos()throws Exception{
     //é necessario fazer um metodo que retorne um vetor com os clientes, para ter a lista
     //diferente de quando retorna somente 1   
        try{
            List<Produtos>vetorProdutos = new ArrayList<>();
            ConexaoDAO.abreConexao();
            stProdutos = ConexaoDAO.comSistema.createStatement();
            String sqlProdutos = "Select * from Products";
            rsProdutos = stProdutos.executeQuery(sqlProdutos);//joga o dado na variavel
            while(rsProdutos.next()){
                Produtos produtoAtual = new Produtos();
                produtoAtual.setProductID(rsProdutos.getString("ProductID"));
                produtoAtual.setProductName(rsProdutos.getString("ProductName"));
                produtoAtual.setUnitPrice(rsProdutos.getString("UnitPrice"));
                produtoAtual.setUnitsInStock(rsProdutos.getString("UnitsInStock"));
                produtoAtual.setDiscontinued(rsProdutos.getInt("Discontinued"));
                vetorProdutos.add(produtoAtual);//add objeto cliente no vetor.                
            }//fechamento while
            ConexaoDAO.fechaConexao();//fechando conexao
            return vetorProdutos;
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
           
           String sqlStatus = "Update products set discontinued=" + novoStatus 
                   + " where ProductID like '" + tmpCodigo + "'";
           stProdutos = ConexaoDAO.comSistema.createStatement();
           stProdutos.executeUpdate(sqlStatus);
           ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            String msg = "Falha na alteração de Status. "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
        }//fechando try
        
        
    }//fechamento void alteraStatus
    
    public static List<Produtos> consultaNome(String tmpNome)throws Exception{
        List<Produtos> Lista = new ArrayList<>();
                
        try{
            //Elaboração da busca
            ConexaoDAO.abreConexao();
            String sqlBusca = "Select * from products where ProductName like "
                    + "'%" + tmpNome + "%'";
            
            stProdutos = ConexaoDAO.comSistema.createStatement();
            rsProdutos = stProdutos.executeQuery(sqlBusca);//joga o dado na variavel
            while(rsProdutos.next()){
                Produtos produtoAtual = new Produtos(); //instancia da classe clientes
                produtoAtual.setProductID(rsProdutos.getString("ProductID"));
                produtoAtual.setProductName(rsProdutos.getString("ProductName"));
                produtoAtual.setUnitPrice(rsProdutos.getString("UnitPrice"));
                produtoAtual.setUnitsInStock(rsProdutos.getString("UnitsInStock"));
                produtoAtual.setDiscontinued(rsProdutos.getInt("Discontinued"));
                Lista.add(produtoAtual);//add objeto cliente no vetor.                
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