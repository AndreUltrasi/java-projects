import java.sql.*;//sempre fazer esse import, permite acesso/manipulação de dados

public class ConexaoDAO {
    
  public static Connection comSistema;//classe responsável pela abertura a conexao ao bd
  public static String mensagem = "Falha na Conexão. Verifique as Strings de abertura do BD.\n ERRO ORIGINAL:";
  
  public static void abreConexao()throws Exception{
      try{
          Class.forName("com.mysql.jdbc.Driver");
          comSistema = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind","root","");//colar parametros: caminho / usuario / senha
          System.out.println("Conexão Estabelecida");
      }catch(Exception erro){
          throw new Exception(mensagem + erro.getMessage());
          
      }//fechando try
      
  }//fechando abreconexao
    
  public static void fechaConexao()throws Exception{
      try{
          comSistema.close();
          System.out.println("Conexão Encerrada");
      }catch(Exception erro){
          throw new Exception("Não existem conexões de BD ativas para este programa.");
      }//fechando try
      
  }//fechando fechaconexao
  
}//fechando classe
