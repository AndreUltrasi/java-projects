import java.sql.*;
public class UsuariosDAO {
    
    public static Statement stUsuarios;
    public static ResultSet rsUsuarios;
    
    public UsuariosDAO(){
        
    }
    
    public static Usuarios validaUsuario(String tmpNome, String tmpSenha) throws Exception{
       try{
           ConexaoDAO.abreConexao();
           Usuarios tmpUsuarios  = new Usuarios();
           stUsuarios = ConexaoDAO.comSistema.createStatement();
           String sqlLogin = "select * from usuarios where email like '" + tmpNome + 
                   "' and senha like '" + tmpSenha + "'";
           
           rsUsuarios = stUsuarios.executeQuery(sqlLogin);
            //executeQuery nunca retorn resultados nulos
           
            //se rsUsuarios estiver nulo retorna um valor nulo pois o rsUsuarios
            //nunca pode retornar nula
            if(!rsUsuarios.next()){
                ConexaoDAO.fechaConexao();
                return null;
            }else{
                //preenchendo tmpUsuario
                tmpUsuarios.setEmail(rsUsuarios.getString("email"));
                tmpUsuarios.setSenha(rsUsuarios.getString("senha"));
                tmpUsuarios.setPermissao(rsUsuarios.getInt("permissao"));
                ConexaoDAO.fechaConexao();
                return tmpUsuarios;
            }
       }catch(Exception erro){
           String msg = "Falha no processo de login. "
                    + "ERRO ORIGINAL: " + erro.getMessage();
            throw new Exception(msg);
       }       
       
    }
}
