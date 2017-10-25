import java.sql.*;
public class ConexaoDao {
    //responsavel pela abertura do BD
    public static Connection connSistema;
    
    
    public static void abreConexao()throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");//acessando biblioteca do mysql
            connSistema=DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda","root","Reloaded11%");//acessando base de dados
            System.out.println("conexão estabelecida");
        } catch (Exception erro) {
            String mensagem = "Falha na conexão. Verifique as Strings da abertura do BD\nERRO ORIGINAL:";
            throw  new Exception(mensagem + erro.getMessage());
        }
    }
    public static void fechaConexao()throws Exception{
        try {
            connSistema.close();
            System.out.println("Conexão encerrada");
        }catch (Exception erro) {
            throw new Exception("Não a conexão de Banco de Dados ativa\nERRO ORIGINAL"+erro.getMessage());
        }
    }
}
