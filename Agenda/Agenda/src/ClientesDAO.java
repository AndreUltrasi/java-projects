import java.sql.*;
import java.util.*;

public class ClientesDAO {
    // utilizado para armazenar resultado de uma consulta do banco de dados
    public static ResultSet rsClientes;
    // responsavel pela execução de comandos SQL no banco de dados
    public static Statement stClientes;
    
    // retorna apenas um Cliente
    public static Clientes consultaCliente(String tmpBusca,int tmpTipo)throws Exception{
        try {
            ConexaoDao.abreConexao();
            stClientes = ConexaoDao.connSistema.createStatement();
            String sqlBusca = null;
            if (tmpTipo == 1){
                sqlBusca = "select * from agenda where cod = '" 
                           + tmpBusca + "'";
            }else if(tmpTipo == 2){
                sqlBusca = "select * from agenda where nome = '%"
                           + tmpBusca + "%'";
            }
            // guarda o resultado de stClientes da variavel sqlBusca na variavel
            rsClientes = stClientes.executeQuery(sqlBusca);
            if (rsClientes.next()){//se houver registros
                Clientes tmpCliente = new Clientes();
                // definir códigos nos respectivos campos
                tmpCliente.setCodigo(rsClientes.getString("cod"));
                tmpCliente.setNomeRepresentante(rsClientes.getString("nome"));
                tmpCliente.setTelefone(rsClientes.getString("telefone"));
                return tmpCliente;
                
            }else{//se não houver resultados
                return null;
            }
        } catch (Exception erro) {
            String msg = "Falha na consulta.Verifique a sintaxe, campos e tabelas da instrução select.\nERRO ORIGINAL"+erro.getMessage();
            throw new Exception(msg);
        }
    }//fechando consultar cliente
    public static void cadastraCliente(Clientes tmpClientes)throws Exception{
        try {
            ConexaoDao.abreConexao();
            stClientes = ConexaoDao.connSistema.createStatement();
            String sqlCadastra = "insert into agenda(";
            sqlCadastra += "cod, nome, telefone)values(";
            sqlCadastra += "'"+ tmpClientes.getCodigo()+ "',";
            sqlCadastra += "'"+ tmpClientes.getNomeRepresentante()+ "',";
            sqlCadastra += "'"+ tmpClientes.getTelefone()+ "')";
            
            
            stClientes.executeUpdate(sqlCadastra);
            ConexaoDao.fechaConexao();
            
        } catch (Exception erro) {
            String msg = "Falha no cadastro. Verifique a sintaxe, campos e tabelas da instrução insert.\nERRO ORIGINAL"+erro.getMessage();
            throw new Exception(msg);
        }
        
    }
    public static void AlteraCliente(String tmpCodigo, Clientes tmpCliente)throws Exception{
        try {
            
        } catch (Exception erro) {
            String msg="Falha ao alterar registro do cliente. Verifique a sintaxe\nERRO ORIGINAL"+erro.getMessage();
            throw new Exception(msg);
        }
        
    }
    public static void excluiCliente(Clientes tmpClientes)throws Exception{
        try {
            ConexaoDao.abreConexao();
            stClientes = ConexaoDao.connSistema.createStatement();
            String sqlCadastra = "delete from agenda where cod=";
            sqlCadastra += "'"+ tmpClientes.getCodigo()+ "'";
            
            stClientes.executeUpdate(sqlCadastra);
            ConexaoDao.fechaConexao();
        } catch (Exception erro) {
            String msg="Erro ao excluir cliente\nERRO ORIGINAL" + erro.getMessage();
            throw new Exception(msg);
        }
        
    }
    //List<> pois retorna o vetor cliente
    public static List<Clientes> listaClientes()throws Exception{
        try {
            List<Clientes> vetorClientes = new ArrayList<Clientes>();
            ConexaoDao.abreConexao();
            stClientes = ConexaoDao.connSistema.createStatement();
            String sqlClientes = "select * from agenda";
            rsClientes = stClientes.executeQuery(sqlClientes);
            while(rsClientes.next()){
                Clientes clienteAtual = new Clientes();
                // preenchendo objeto Clientes com os dados do banco
                clienteAtual.setCodigo(rsClientes.getString("cod"));
                clienteAtual.setNomeRepresentante(rsClientes.getString("nome"));
                clienteAtual.setTelefone(rsClientes.getString("telefone"));
                // adicionando objeto Clientes no vetor
                vetorClientes.add(clienteAtual); 
            }
            ConexaoDao.fechaConexao();
            return vetorClientes;
        } catch (Exception erro) {
            String msg="Erro ao exibir lista de clientes\nERRO ORIGINAL" + erro.getMessage();
            throw new Exception(msg);
        }       
    }
    
}
